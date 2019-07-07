package in.anandm.msl.taskservice.service;

public class TaskSerchCriteria {

	private String email;
	private Boolean deleted;
	private Boolean completed;
	private String task;
	private int page;
	private int pageSize;

	public TaskSerchCriteria(String email) {
		super();
		this.email = email;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getEmail() {
		return email;
	}
}
