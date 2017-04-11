package fda;

import org.apache.accumulo.core.client.BatchWriter;
import org.apache.accumulo.core.client.MutationsRejectedException;
import org.apache.accumulo.core.data.Mutation;
import org.apache.accumulo.core.data.Value;
import org.apache.hadoop.io.Text;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.mockito.Mockito.*;
import static org.mockito.Matchers.any;

import java.util.Arrays;

public class IngesterTest {

	@Mock BatchWriter batchWriter;
	@Mock FDARecord record;
	@Mock OpenFDA openFDA;
	@Mock Mutation row;
	Ingester fixture;
	final String applicant = "applicant";
	final String statement_or_summary = "statement_or_summary";
	final String regulation_number = "regulation_number";
	final String device_name = "device_name";
	final String device_class = "device_class";
	final String numbers[] = "123,456,789".split(",");	
	final String openfda = "openfda";
	
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		when(record.getApplicant()).thenReturn(applicant);
		when(record.getStatement_or_summary()).thenReturn(statement_or_summary);
		when(record.getOpenfda()).thenReturn(openFDA);
		when(openFDA.getRegulation_number()).thenReturn(regulation_number);
		when(openFDA.getDevice_name()).thenReturn(device_name);
		when(openFDA.getDevice_class()).thenReturn(device_class); 
		when(openFDA.getFei_number()).thenReturn(Arrays.asList(numbers));
	}
	
	@Test
	public void testInsert() throws MutationsRejectedException {		
		fixture = new Ingester(batchWriter, record) {
			protected Mutation createMutation(String id) {
				return row;
			}
		};
		fixture.run();
		verify(row, atLeast(8)).put(any(Text.class), any(Text.class), anyLong(), any(Value.class));
		verify(batchWriter).addMutation(row);
	}
	
	
}
