package info.serdroid.smyrna.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import info.serdroid.smyrna.data.HardCodedPersonStore;
import info.serdroid.smyrna.model.JobHistory;
import info.serdroid.smyrna.model.JobItem;
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
		JobHistory jobHistory = service.getJobHistory(personId).orElse(new JobHistory(personId, Collections.emptyList()));
		JobHistory expected = HardCodedPersonStore.ALL_HISTORIES[0];
		assertThat(jobHistory).isEqualTo(expected);
	}

	@Test
	public void calculateMonths() {
		PersonService service = new PersonService();
		LocalDate today = LocalDate.of(2021, 4, 10);
		JobItem first = HardCodedPersonStore.ALL_HISTORIES[0].getJobList().get(0);
		long months = service.calculateMonths(first, today);
		assertThat(months).isEqualTo(27);
	}

	@Test
	public void calculateMonths_ToCurrentDate() {
		PersonService service = new PersonService();
		LocalDate today = LocalDate.of(2021, 4, 10);
		JobItem first = HardCodedPersonStore.ALL_HISTORIES[1].getJobList().get(1);
		long months = service.calculateMonths(first, today);
		assertThat(months).isEqualTo(61);
	}

	@Test
	public void calculateTotalExperience() {
		PersonService service = new PersonService();
		LocalDate today = LocalDate.of(2021, 4, 10);
		List<JobHistory> histories = Arrays.asList( HardCodedPersonStore.ALL_HISTORIES );
		long months = service.calculateTotalExperience(histories, today);
		assertThat(months).isEqualTo(339);
	}


}
