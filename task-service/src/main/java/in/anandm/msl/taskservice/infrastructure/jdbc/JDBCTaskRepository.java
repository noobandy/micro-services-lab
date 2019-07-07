package in.anandm.msl.taskservice.infrastructure.jdbc;

import java.util.List;

import in.anandm.msl.taskservice.model.SortOrder;
import in.anandm.msl.taskservice.model.Task;
import in.anandm.msl.taskservice.model.TaskRepository;

public class JDBCTaskRepository implements TaskRepository {

	@Override
	public List<Task> findAll(String email, String search, String sortField, SortOrder sortOrder, int page,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> findAllCompleted(String email, String search, String sortField, SortOrder sortOrder, int page,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> findAllDeleted(String email, String search, String sortField, SortOrder sortOrder, int page,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Task task) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Task task) {
		// TODO Auto-generated method stub

	}

}
