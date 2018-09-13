package tk.roydgar.wildfly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WildflyApplication {

	public static void main(String[] args) {
	    SpringApplication.run(WildflyApplication.class, args);
	}

}
