package vn.gteam;

import vn.gteam.app.properties.MyProperties;
import vn.gteam.app.startup.AppStartup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties(MyProperties.class)
public class MyApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MyApplication.class, args);
        context.getBean(AppStartup.class).init();
    }

}
