package fda;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.accumulo.core.cli.BatchWriterOpts;
import org.apache.accumulo.core.cli.ClientOpts;
import org.apache.accumulo.core.cli.ScannerOpts;
import org.apache.accumulo.core.client.AccumuloException;
import org.apache.accumulo.core.client.AccumuloSecurityException;
import org.apache.accumulo.core.client.BatchWriter;
import org.apache.accumulo.core.client.Connector;
import org.apache.accumulo.core.client.TableExistsException;
import org.apache.accumulo.core.client.TableNotFoundException;
import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class ThreadedIngesterService implements IngesterService {

	private final FDARecordProvider provider;
	private final String[] args;
	private final String targetTable;
	private static final Logger log = Logger.getLogger(ThreadedIngesterService.class);

	
	@Inject
	ThreadedIngesterService(final FDARecordProvider provider, 
			@Named("args") final String[] args, 
			@Named("Target Table") final String targetTable) {
		this.provider = provider;
		this.args = args;
		this.targetTable = targetTable;
	}
	
	@Override
	public boolean ingest() {
		ClientOpts opts = new ClientOpts();
		ScannerOpts scanOpts = new ScannerOpts();
		BatchWriterOpts bwOpts = new BatchWriterOpts();
		opts.parseArgs(RowOperations.class.getName(), args, scanOpts, bwOpts);

		try {
			Connector connector = opts.getConnector();
			
			try {
				connector.tableOperations().create(targetTable);
			} catch (TableExistsException tee) {
				connector.tableOperations().delete(targetTable);
				try {
					connector.tableOperations().create(targetTable);
				} catch (TableExistsException e) {
					log.error("Cannot re-create target table " + targetTable, e);
					return false;
				}
			}

			BatchWriter batchWriter = connector.createBatchWriter(targetTable, bwOpts.getBatchWriterConfig());
			
			ExecutorService executorService = Executors.newFixedThreadPool(4);

			while(provider.hasNext()) {
				FDARecord record = provider.getRecord();
				executorService.execute(new Ingester(batchWriter, record));
			}
			
			executorService.shutdown();
			while (!executorService.isTerminated()) { }
			batchWriter.flush();
			
		} catch (TableNotFoundException e) {
			log.error("Ingest table not created", e);
			return false;
		}
		catch (AccumuloException | AccumuloSecurityException e) {
			log.error("Cannot access Accumulo instance", e);
		}
		
		return true;
	}

}
