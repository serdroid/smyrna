package info.serdroid.smyrna.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import info.serdroid.smyrna.data.HardCodedPersonStore;
import info.serdroid.smyrna.model.JobHistory;
import info.serdroid.smyrna.model.Person;

public class PersonServiceTest {
	
	@Test
	public void getPerson() {
		String personId = "123";
		PersonService service = new PersonService();
		Person person = service.getPerson(personId);
		Person expected = HardCodedPersonStore.PEOPLE[0];
		assertThat(person).isEqualTo(expected);
	}

	@Test
	public void getJobHistory() {
		String personId = "123";
		PersonService service = new PersonService();
		JobHistory jobHistory = service.getJobHistory(personId);
		JobHistory expected = HardCodedPersonStore.ALL_HISTORIES[0];
		assertThat(jobHistory).isEqualTo(expected);
	}


}
