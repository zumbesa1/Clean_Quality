package ch.zhaw.iwi.devops;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;

import ch.zhaw.iwi.devops.server.CorsHeaders;
import ch.zhaw.iwi.devops.server.Database;
import ch.zhaw.iwi.devops.service.exception.ExceptionRestService;
import ch.zhaw.iwi.devops.service.file.FileRestService;
import ch.zhaw.iwi.devops.service.interactionelement.InteractionElementRestService;
import ch.zhaw.iwi.devops.service.interactionelement.button.BackButtonElementRestService;
import ch.zhaw.iwi.devops.service.interactionelement.button.ButtonElementRestService;
import ch.zhaw.iwi.devops.service.interactionelement.button.ButtonSelectionElementRestService;
import ch.zhaw.iwi.devops.service.interactionelement.button.ButtonSelectionElementValueRestService;
import ch.zhaw.iwi.devops.service.interactionelement.form.FormDateFieldElementRestService;
import ch.zhaw.iwi.devops.service.interactionelement.form.FormFileTypeRestService;
import ch.zhaw.iwi.devops.service.interactionelement.form.FormFileUploadElementRestService;
import ch.zhaw.iwi.devops.service.interactionelement.form.FormNumberFieldElementRestService;
import ch.zhaw.iwi.devops.service.interactionelement.form.FormRadioButtonListElementRestService;
import ch.zhaw.iwi.devops.service.interactionelement.form.FormRadioButtonValueRestService;
import ch.zhaw.iwi.devops.service.interactionelement.form.FormTextFieldElementRestService;
import ch.zhaw.iwi.devops.service.interactionelement.list.ButtonListElementRestService;
import ch.zhaw.iwi.devops.service.interactionelement.list.TileListElementRestService;
import ch.zhaw.iwi.devops.service.interactionelement.text.TextElementRestService;
import ch.zhaw.iwi.devops.service.interactionstep.InteractionStepRestService;
import ch.zhaw.iwi.devops.service.language.LanguageUtility;
import ch.zhaw.iwi.devops.service.mockup.MockupRestService;
import ch.zhaw.iwi.devops.service.patient.PatientRestService;
import ch.zhaw.iwi.devops.service.questionnaire.QuestionnaireRestService;
import ch.zhaw.iwi.devops.service.questionnaireresponse.QuestionnaireResponseRestService;
import ch.zhaw.iwi.devops.service.sport.SportRestService;
import ch.zhaw.iwi.devops.service.studyprogram.StudyProgramRestService;
import ch.zhaw.iwi.devops.service.user.UserRestService;
import ch.zhaw.iwi.devops.service.user.permission.PermissionFunctionRestService;
import ch.zhaw.iwi.devops.service.user.permission.PermissionRoleRestService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class Main {
	private static final String HEROKU_PORT = "PORT";
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	private static final JpaPersistModule persistModule = new JpaPersistModule("AssessmentTool");
	private static Injector injector;

	public static void main(String[] args) {
		initFrontend();
		initServer();
		initDatabase();

		// Cache
		before((request, response) -> {
			response.header("cache-control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
			response.header("pragma", "no-cache"); // HTTP 1.0
			response.header("expires", "0"); // HTTP 1.0 proxies
		});

		// Language
		before("services/*", (request, response) -> {
			String languageCode = request.queryParams("languageCode");
			if (languageCode != null) {
				LanguageUtility.setUserLanguage(languageCode);
			}
		});

		// Wake Up Ping (Heroku)
		get("services/ping", (req, res) -> {
			Jws<Claims> jwt = JwtUtility.getJsonWebToken(req);
			String userId = null;
			if (jwt != null) {
				userId = jwt.getBody().getSubject();
			}
			String defaultUser = "";
			if (req.url().contains("localhost")) {
				defaultUser = "admin";
				String jwtString = JwtUtility.createJsonWebToken(defaultUser, "de");
				res.header("Authorization", jwtString);
			}
			String languageCode = "de";
			return "{ \"status\": \"ok\", \"userId\": \"" + (userId == null ? defaultUser : userId) + "\", \"languageCode\": \"" + languageCode + "\",\"version\": \"" + new Version().getVersion() + "\"}";
		});
		
		// Model Services
		injector.getInstance(StudyProgramRestService.class).init();
		
		injector.getInstance(MockupRestService.class).init();
		injector.getInstance(FileRestService.class).init();
		injector.getInstance(UserRestService.class).init();
		injector.getInstance(PatientRestService.class).init();
		injector.getInstance(QuestionnaireRestService.class).init();
		injector.getInstance(QuestionnaireResponseRestService.class).init();
		injector.getInstance(InteractionStepRestService.class).init();
		injector.getInstance(InteractionElementRestService.class).init();
		injector.getInstance(BackButtonElementRestService.class).init();
		injector.getInstance(ButtonElementRestService.class).init();
		injector.getInstance(ButtonSelectionElementRestService.class).init();
		injector.getInstance(ButtonSelectionElementValueRestService.class).init();
		injector.getInstance(FormDateFieldElementRestService.class).init();
		injector.getInstance(FormFileUploadElementRestService.class).init();
		injector.getInstance(FormFileTypeRestService.class).init();
		injector.getInstance(FormNumberFieldElementRestService.class).init();
		injector.getInstance(FormRadioButtonValueRestService.class).init();
		injector.getInstance(FormRadioButtonListElementRestService.class).init();
		injector.getInstance(FormTextFieldElementRestService.class).init();
		injector.getInstance(ButtonListElementRestService.class).init();
		injector.getInstance(TileListElementRestService.class).init();
		injector.getInstance(TextElementRestService.class).init();
		injector.getInstance(SportRestService.class).init();
		injector.getInstance(PermissionRoleRestService.class).init();
		injector.getInstance(PermissionFunctionRestService.class).init();
		
		// Exception Handler
		injector.getInstance(ExceptionRestService.class).init();

		logger.info("devops prototype running on localhost:" + getHerokuAssignedPort());
	}

	private static void initFrontend() {
		try {
			File frontendDirectory = new File("../frontend/dist");
			if (!frontendDirectory.isDirectory()) {
				frontendDirectory = new File("frontend/dist");
			}
			staticFiles.externalLocation(frontendDirectory.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not resolve frontend files");
			System.exit(1);
		}
	}
	
	private static void initDatabase() {
		Properties properties = new Properties();
		ProcessBuilder processBuilder = new ProcessBuilder();
		/*if (processBuilder.environment().get(HEROKU_PORT) != null) {
			properties.put("javax.persistence.jdbc.driver", Driver.class.getName());
			properties.put("javax.persistence.jdbc.url", processBuilder.environment().get("JDBC_DATABASE_URL"));
			properties.put("javax.persistence.jdbc.user", "");
			properties.put("javax.persistence.jdbc.password", "");
			properties.put("hibernate.dialect", PostgreSQL95Dialect.class.getName());
		}*/
		persistModule.properties(properties);
		injector = Guice.createInjector(persistModule);
		
		injector.getInstance(Database.class).init("9998", "9999", true);
	}

	public static void initServer() {
		port(getHerokuAssignedPort());
		CorsHeaders.init();
	}

	static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get(HEROKU_PORT) != null) {
			return Integer.parseInt(processBuilder.environment().get(HEROKU_PORT));
		}
		return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
	}

	@SuppressWarnings("unused")
	private static class TranslatedMessage {
		private String messageKey;
		private List<String> parameters;
	}

}
