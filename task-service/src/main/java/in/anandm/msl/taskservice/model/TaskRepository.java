package in.anandm.msl.taskservice.model;

import java.util.List;

public interface TaskRepository {

	List<Task> findAll(String email, String search, String sortField, SortOrder sortOrder, int page, int pageSize);

	List<Task> findAllCompleted(String email, String search, String sortField, SortOrder sortOrder, int page,
			int pageSize);

	List<Task> findAllDeleted(String email, String search, String sortField, SortOrder sortOrder, int page,
			int pageSize);

	void save(Task task);

	void update(Task task);

}
