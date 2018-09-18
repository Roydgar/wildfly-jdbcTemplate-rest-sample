package tk.roydgar.wildfly.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
public class AppConfig {


    @Bean
    public DataSource dataSource() throws NamingException {
        JndiTemplate jndiTemplate = new JndiTemplate();
        return (DataSource)jndiTemplate.lookup("java:/MySqlDS");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger("app");
    }

}
