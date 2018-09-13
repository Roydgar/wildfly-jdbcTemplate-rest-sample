package tk.roydgar.wildfly.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import tk.roydgar.wildfly.WildflyApplication;

@Configuration
public class WildFlyConfig  extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WildflyApplication.class);
    }

}
