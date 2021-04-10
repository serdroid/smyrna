package info.serdroid.smyrna.data;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Arrays;

import info.serdroid.smyrna.model.JobHistory;
import info.serdroid.smyrna.model.JobItem;
import info.serdroid.smyrna.model.Person;

public class HardCodedPersonStore {
	public static final Person[] PEOPLE = {
			new Person("123", "ali", "at", "ali.at@example.com"),
			new Person("124", "veli", "tut", "veli.tut@example.com"),
			new Person("125", "oya", "kos", "oya.kos@example.com"),
			new Person("126", "ayla", "cos", "ayla.cos@example.com"),
	};

	public static final JobHistory[] ALL_HISTORIES = {
			new JobHistory(PEOPLE[0].getId(), Arrays.asList(
					new JobItem(generateRandomString(), "Software Engineer", LocalDate.of(2012, 3, 12), LocalDate.of(2014, 6, 21)), 
					new JobItem(generateRandomString(), "Software Engineer", LocalDate.of(2014, 7, 1), null)
					)),
			new JobHistory(PEOPLE[1].getId(), Arrays.asList(
					new JobItem(generateRandomString(), "Devops Engineer", LocalDate.of(2010, 2, 2), LocalDate.of(2016, 3, 1)), 
					new JobItem(generateRandomString(), "Senior Devops Engineer", LocalDate.of(2016, 3, 10), null)
					)),
	};
	
	public static String generateRandomString() {
	    SecureRandom secureRandom = new SecureRandom();
	    byte[] token = new byte[16];
	    secureRandom.nextBytes(token);
	    return new BigInteger(1, token).toString(16);
	}
}
