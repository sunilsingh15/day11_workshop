package sg.edu.nus.iss.day01workshop;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day01WorkshopApplication {

	private static final Logger logger = LoggerFactory.getLogger(Day01WorkshopApplication.class);

	// default port number
	private static final String DEFAULT_PORT = "3000";

	public static void main(String[] args) {
		logger.info("main method started...");	

		// initialize the Spring app
		SpringApplication app = new SpringApplication(Day01WorkshopApplication.class);
		
		// read args array and check for "port" parameter
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		List opsValues = appArgs.getOptionValues("port");

		String portNumber = null;

		// if port number is not in argument
		if (opsValues == null || opsValues.get(0) == null) {
			
			// read port number from env variables
			portNumber = System.getenv("PORT");

			if (portNumber == null) {
				portNumber = DEFAULT_PORT;
			}
		} else {
			// passing port number from CLI
			portNumber = opsValues.get(0).toString();
		}

		if (portNumber != null) {
			// setting port number in the spring-boot config
			app.setDefaultProperties(Collections.singletonMap("server.port", portNumber));
		}

		// run spring boot app
		app.run(args);
	}

}
