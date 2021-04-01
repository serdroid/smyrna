package info.serdroid.smyrna.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import info.serdroid.smyrna.model.Person;

public class PersonServiceTest {
	@Test
	public void generateFromEncodedString() {
		String personId = "123";
		PersonService service = new PersonService();
		Person person = service.getPerson(personId);
		Person expected = new Person();
		expected.setId(personId);
		expected.setName("ali");
		expected.setSurname("al");
		expected.setEmail("ali.al@example.com");
		
		assertThat(person).isEqualTo(expected);
	}


}
