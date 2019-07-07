package in.anandm.msl.taskservice.api.v1;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

	
	public ResponseEntity<Map<String, Object>> getTaskList() {
		Map<String, Object> result = new HashMap<>();
		
		return result;
	}
}
