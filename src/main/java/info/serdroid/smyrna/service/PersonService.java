package info.serdroid.smyrna.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.serdroid.smyrna.data.HardCodedPersonStore;
import info.serdroid.smyrna.model.JobHistory;
import info.serdroid.smyrna.model.JobItem;
import info.serdroid.smyrna.model.Person;

@Singleton
public class PersonService {
	private static final Logger logger = LoggerFactory.getLogger(PersonService.class);
	
	private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4); 

	public Person getPerson(String personId) {
		return HardCodedPersonStore.PEOPLE[0];
	}

	public JobHistory getJobHistory(String personId) {
		logger.debug("getting JobHistory for : {}", personId);
		return getJobHistoryAsync(personId);
	}
	
	private JobHistory getJobHistoryAsync(String personId) {
		Future<JobHistory> future = fixedThreadPool.submit(find(personId));
		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	Callable<JobHistory> find(String personId) {
		return () -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			JobHistory jobHistory = HardCodedPersonStore.ALL_HISTORIES[0];
			logger.debug("returning JobHistory : {}", jobHistory);
			return jobHistory;
		};
	}
	
	public long calculateTotalExperience(List<JobHistory> histories) {
		return calculateTotalExperience(histories,LocalDate.now());
	}
	
	long calculateTotalExperience(List<JobHistory> histories, LocalDate today) {
		return histories.stream().parallel().mapToLong(h -> calculateTotalMonths(h, today)).sum();
	}
	
	long calculateMonths(JobItem jobItem, LocalDate today) {
		LocalDate end = jobItem.getEnd();
		if ( end == null ) {
			end = today;
		}
		return ChronoUnit.MONTHS.between( jobItem.getStart(), end );
	}
	
	long calculateTotalMonths(JobHistory jobHistory, LocalDate today) {
		logger.debug("Calculating total months for {}", jobHistory.getPersonId());
		return jobHistory.getJobList().stream().mapToLong(i -> calculateMonths(i, today)).sum();
	}
}
