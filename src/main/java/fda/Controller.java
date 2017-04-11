package fda;

import org.apache.accumulo.core.client.AccumuloException;
import org.apache.accumulo.core.client.AccumuloSecurityException;
import org.apache.accumulo.core.client.TableExistsException;
import org.apache.accumulo.core.client.TableNotFoundException;
import org.apache.log4j.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;


public class Controller {

	private static final Logger log = Logger.getLogger(Controller.class);

	public static void main(String[] args)
			throws AccumuloException, AccumuloSecurityException, TableNotFoundException, TableExistsException {
		long start = System.currentTimeMillis();
		
	    Injector injector = Guice.createInjector(new BootstrapModule(args));
	    IngesterService ingesterService = injector.getInstance(IngesterService.class);
	    ingesterService.ingest();

		long elapsed = System.currentTimeMillis() - start;
		log.info("Ingestion of 510K data complete.  Elased time = " + elapsed);

		
	}
}
