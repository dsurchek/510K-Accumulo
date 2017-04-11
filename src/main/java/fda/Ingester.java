package fda;

import org.apache.accumulo.core.client.BatchWriter;
import org.apache.accumulo.core.client.MutationsRejectedException;
import org.apache.accumulo.core.data.Mutation;
import org.apache.accumulo.core.data.Value;
import org.apache.hadoop.io.Text;
import org.apache.log4j.Logger;

public class Ingester implements Runnable {

	private final BatchWriter batchWriter;
	private final FDARecord record;
	private static final Logger log = Logger.getLogger(Ingester.class);

	long start;

	public Ingester(BatchWriter batchWriter, final FDARecord record) {
		this.batchWriter = batchWriter;
		this.record = record;
	}

	public void run() {
		start = System.currentTimeMillis();
		ingest();
	}

	private void ingest() {
		try {
			writeRecord();
		} catch (MutationsRejectedException e) {
			log.error("Cannot insert FDA 510k record with ID " + record.getK_number(), e);
		}
	}

	private void addMutation(Mutation row, String cf, String cq, String value) {
		if (value != null)
			row.put(new Text(cf), new Text(cq), start, new Value(value));
	}
	
	protected Mutation createMutation(String id) {
		return new Mutation(id);
	}

	private void writeRecord() throws MutationsRejectedException {
		String rowId = record.getK_number();
		Mutation row = createMutation(rowId);
		addMutation(row, "record", "applicant", record.getApplicant());
		addMutation(row, "record", "statement_or_summary", record.getStatement_or_summary());
		addMutation(row, "record", "expedited_review_flag", record.getExpedited_review_flag());
		addMutation(row, "record", "postal_code", record.getPostal_code());
		addMutation(row, "record", "country_code", record.getCountry_code());
		addMutation(row, "record", "decision_date", record.getDecision_date());
		addMutation(row, "record", "product_code", record.getProduct_code());
		addMutation(row, "record", "city", record.getCity());
		addMutation(row, "record", "date_received", record.getDate_received());
		addMutation(row, "record", "state", record.getState());
		addMutation(row, "record", "address_1", record.getAddress_1());
		addMutation(row, "record", "advisory_committee_description", record.getAdvisory_committee_description());
		addMutation(row, "record", "review_advisory_committee", record.getReview_advisory_committee());
		addMutation(row, "record", "third_party_flag", record.getThird_party_flag());
		addMutation(row, "record", "decision_description", record.getDecision_description());
		addMutation(row, "record", "decision_code", record.getDecision_code());
		addMutation(row, "record", "clearance_type", record.getClearance_type());
		addMutation(row, "record", "device_name", record.getDevice_name());
		addMutation(row, "record", "advisory_committee", record.getAdvisory_committee());
		addMutation(row, "record", "contact", record.getContact());
		addMutation(row, "record", "address_2", record.getAddress_2());
		addMutation(row, "record", "zip_code", record.getZip_code());

		OpenFDA openFDA = record.getOpenfda();
		if (openFDA != null) {
			addMutation(row, "record", "openfda/regulation_number", openFDA.getRegulation_number());
			addMutation(row, "record", "openfda/device_name", openFDA.getDevice_name());
			addMutation(row, "record", "openfda/device_class", openFDA.getDevice_class());
			addMutation(row, "record", "openfda/medical_specialty_description",
					openFDA.getMedical_specialty_description());
			for (int i = 0; openFDA.getFei_number() != null && i < openFDA.getFei_number().size(); i++) {
				addMutation(row, "record", "openfda/fei_number/" + i, record.getOpenfda().getFei_number().get(i));
			}
			for (int i = 0; openFDA.getRegistration_number() != null
					&& i < record.getOpenfda().getRegistration_number().size(); i++) {
				addMutation(row, "record", "openfda/registration_number/" + i,
						record.getOpenfda().getRegistration_number().get(i));
			}
		}

		batchWriter.addMutation(row);
		long elapsed = System.currentTimeMillis() - start;
		log.trace("inserted FDA 510K record with ID " + record.getK_number() + " in " + elapsed + " milliseconds");
	}
}