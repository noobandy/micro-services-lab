package in.anandm.msl.taskservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import in.anandm.msl.taskservice.infrastructure.jdbc.QueryStore;

@Configuration
public class AppConfig {
	
	
	@Bean
	public QueryStore queryStore() {
		List<String> queryFiles = new ArrayList<>();
		queryFiles.add("queries.xml");
		
		return new QueryStore(queryFiles);
	}

}
