package br.edu.utfpr.pb.tcc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class ServerApplication {

	private static final Logger logger = LoggerFactory.getLogger(ServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		logger.info("\n\n" +
				"####################\n" +
				"#-PROJETO INICIADO-#\n" +
				"####################\n");
	}

}