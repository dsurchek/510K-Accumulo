package fda;

import java.util.List;

public class OpenFDA {
	String regulation_number;
	List<String> fei_number;
	String device_name;
	String device_class;
	String medical_specialty_description;
	List<String> registration_number;

	@Override
	public String toString() {
		return "OpenFDA [regulation_number=" + regulation_number + ", fei_number=" + fei_number + ", device_name="
				+ device_name + ", device_class=" + device_class + ", medical_specialty_description="
				+ medical_specialty_description + ", registration_number=" + registration_number + "]";
	}

	public String getRegulation_number() {
		return regulation_number;
	}

	public void setRegulation_number(String regulation_number) {
		this.regulation_number = regulation_number;
	}

	public List<String> getFei_number() {
		return fei_number;
	}

	public void setFei_number(List<String> fei_number) {
		this.fei_number = fei_number;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getDevice_class() {
		return device_class;
	}

	public void setDevice_class(String device_class) {
		this.device_class = device_class;
	}

	public String getMedical_specialty_description() {
		return medical_specialty_description;
	}

	public void setMedical_specialty_description(String medical_specialty_description) {
		this.medical_specialty_description = medical_specialty_description;
	}

	public List<String> getRegistration_number() {
		return registration_number;
	}

	public void setRegistration_number(List<String> registration_number) {
		this.registration_number = registration_number;
	}

}
