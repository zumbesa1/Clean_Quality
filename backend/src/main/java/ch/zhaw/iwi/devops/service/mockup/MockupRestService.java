package ch.zhaw.iwi.devops.service.mockup;

import static spark.Spark.get;
import static spark.Spark.put;

import ch.zhaw.iwi.devops.service.AbstractRestService;

public class MockupRestService extends AbstractRestService {
	
	@Override
	public void init() {

		get("services/singleform", (req, res) -> {
			return "[{ \"key\": { \"key\": 1, \"name\": \"formKey\" }}]";
		});
		
		get("services/dummyform/1", (req, res) -> {
			return "[{ \"key\": 1, \"name\": \"situationKey\" }]";
		});

		put("services/dummyform/1", (req, res) -> {
			return "[{ \"key\": 1, \"name\": \"situationKey\" }]";
		});		
	}

}