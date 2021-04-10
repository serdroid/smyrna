package info.serdroid.smyrna.model;

import java.time.LocalDate;
import java.util.Objects;

public class JobItem {
	private String id;
	private String position;
	private LocalDate start;
	private LocalDate end;
	public JobItem() {}
	public JobItem(String id, String position, LocalDate start, LocalDate end) {
		super();
		this.id = id;
		this.position = position;
		this.start = start;
		this.end = end;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public LocalDate getStart() {
		return start;
	}
	public void setStart(LocalDate start) {
		this.start = start;
	}
	public LocalDate getEnd() {
		return end;
	}
	public void setEnd(LocalDate end) {
		this.end = end;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobItem other = (JobItem) obj;
		return
			Objects.equals(id, other.id) &&
			Objects.equals(position, other.position);
	}
	
}
