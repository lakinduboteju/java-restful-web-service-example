package my.rest.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource( { "classpath*:/app_config.xml" } )
@ComponentScan( basePackages = "my.rest.test" )
public class AppConfig {

}
