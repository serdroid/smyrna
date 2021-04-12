package info.serdroid.smyrna.rest;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.core.Response;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import info.serdroid.smyrna.data.HardCodedPersonStore;
import info.serdroid.smyrna.model.JobHistory;
import info.serdroid.smyrna.model.Person;
import info.serdroid.smyrna.service.PersonService;

public class PersonEndpointTest {
	@Mock
	PersonService personService;

	@InjectMocks
	PersonEndpoint personEndpoint;
	
	@Rule
    public MockitoRule initRule = MockitoJUnit.rule();
	
	
	@Test
	public void getPerson() {
		String personId = "123";
		Mockito.when(personService.getPerson(personId)).thenReturn(HardCodedPersonStore.PEOPLE[0]);
		Response response = personEndpoint.getPerson(personId);
		Person person = response.readEntity(Person.class);
		Person expected = HardCodedPersonStore.PEOPLE[0];
		assertThat(person).isEqualTo(expected);
	}

	@Test
	public void getJobHistory() {
		String personId = "123";
		Mockito.when(personService.getJobHistory(personId)).thenReturn(HardCodedPersonStore.ALL_HISTORIES[0]);
		Response response = personEndpoint.getJobHistory(personId);
		JobHistory jobHistory = response.readEntity(JobHistory.class);
		JobHistory expected = HardCodedPersonStore.ALL_HISTORIES[0];
		assertThat(jobHistory).isEqualTo(expected);
	}


}
