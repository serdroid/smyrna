package info.serdroid.smyrna.model;

import java.util.List;

public class JobHistory {
	private String personId;
	private List<JobItem> jobList;
	public JobHistory() {}
	
	public JobHistory(String personId, List<JobItem> jobList) {
		super();
		this.personId = personId;
		this.jobList = jobList;
	}
	
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public List<JobItem> getJobList() {
		return jobList;
	}
	public void setJobList(List<JobItem> jobList) {
		this.jobList = jobList;
	}
	
}
