package fda;

public class FDARecord {

	private String applicant;
	private String statement_or_summary;
	private String expedited_review_flag;
	private String postal_code;
	private String country_code;
	private String decision_date;
	private String product_code;
	private String city;
	private OpenFDA openfda;
	private String date_received;
	private String state;
	private String address_1;
	private String advisory_committee_description;
	private String review_advisory_committee;
	private String third_party_flag;
	private String decision_description;
	private String decision_code;
	private String clearance_type;
	private String device_name;
	private String advisory_committee;
	private String contact;
	private String k_number;
	private String address_2;
	private String zip_code;

	@Override
	public String toString() {
		return "FDAResult [applicant=" + applicant + ", statement_or_summary=" + statement_or_summary
				+ ", expedited_review_flag=" + expedited_review_flag + ", postal_code=" + postal_code
				+ ", country_code=" + country_code + ", decision_date=" + decision_date + ", product_date="
				+ product_code + ", city=" + city + ", openfda=" + openfda + ", date_received=" + date_received
				+ ", state=" + state + ", address_1=" + address_1 + ", advisory_committee_description="
				+ advisory_committee_description + ", review_advisory_committee=" + review_advisory_committee
				+ ", third_party_flag=" + third_party_flag + ", decision_description=" + decision_description
				+ ", decision_code=" + decision_code + ", clearance_type=" + clearance_type + ", device_name="
				+ device_name + ", advisory_committee=" + advisory_committee + ", contact=" + contact + ", k_number="
				+ k_number + ", address_2=" + address_2 + ", zip_code=" + zip_code + "]";
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getStatement_or_summary() {
		return statement_or_summary;
	}

	public void setStatement_or_summary(String statement_or_summary) {
		this.statement_or_summary = statement_or_summary;
	}

	public String getExpedited_review_flag() {
		return expedited_review_flag;
	}

	public void setExpedited_review_flag(String expedited_review_flag) {
		this.expedited_review_flag = expedited_review_flag;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getDecision_date() {
		return decision_date;
	}

	public void setDecision_date(String decision_date) {
		this.decision_date = decision_date;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public OpenFDA getOpenfda() {
		return openfda;
	}

	public void setOpenfda(OpenFDA openfda) {
		this.openfda = openfda;
	}

	public String getDate_received() {
		return date_received;
	}

	public void setDate_received(String date_received) {
		this.date_received = date_received;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddress_1() {
		return address_1;
	}

	public void setAddress_1(String address_1) {
		this.address_1 = address_1;
	}

	public String getAdvisory_committee_description() {
		return advisory_committee_description;
	}

	public void setAdvisory_committee_description(String advisory_committee_description) {
		this.advisory_committee_description = advisory_committee_description;
	}

	public String getReview_advisory_committee() {
		return review_advisory_committee;
	}

	public void setReview_advisory_committee(String review_advisory_committee) {
		this.review_advisory_committee = review_advisory_committee;
	}

	public String getThird_party_flag() {
		return third_party_flag;
	}

	public void setThird_party_flag(String third_party_flag) {
		this.third_party_flag = third_party_flag;
	}

	public String getDecision_description() {
		return decision_description;
	}

	public void setDecision_description(String decision_description) {
		this.decision_description = decision_description;
	}

	public String getDecision_code() {
		return decision_code;
	}

	public void setDecision_code(String decision_code) {
		this.decision_code = decision_code;
	}

	public String getClearance_type() {
		return clearance_type;
	}

	public void setClearance_type(String clearance_type) {
		this.clearance_type = clearance_type;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getAdvisory_committee() {
		return advisory_committee;
	}

	public void setAdvisory_committee(String advisory_committee) {
		this.advisory_committee = advisory_committee;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getK_number() {
		return k_number;
	}

	public void setK_number(String k_number) {
		this.k_number = k_number;
	}

	public String getAddress_2() {
		return address_2;
	}

	public void setAddress_2(String address_2) {
		this.address_2 = address_2;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

}
