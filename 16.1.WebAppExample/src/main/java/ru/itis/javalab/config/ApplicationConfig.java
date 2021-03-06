package ru.itis.javalab.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.repositories.UsersRepositoryJdbcImpl;
import ru.itis.javalab.services.SignInService;
import ru.itis.javalab.services.SignInServiceImpl;
import ru.itis.javalab.services.UsersService;
import ru.itis.javalab.services.UsersServiceImpl;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "ru.itis.javalab")
@PropertySource(value = "classpath:application.properties")
public class ApplicationConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }


    @Bean
    public UsersRepository usersRepository() {
        return new UsersRepositoryJdbcImpl(dataSource());
    }

    @Bean
    public SignInService signInService() {
        return new SignInServiceImpl(usersRepository());
    }

    @Bean
    public UsersService usersService() {
        return new UsersServiceImpl(usersRepository());
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("db.url"));
        hikariConfig.setDriverClassName(environment.getProperty("db.driver.name"));
        hikariConfig.setUsername(environment.getProperty("db.user"));
        hikariConfig.setPassword(environment.getProperty("db.password"));
        return hikariConfig;
    }

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix(".ftlh");
        resolver.setContentType("text/html;charset=UTF-8");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/ftl/");
        return configurer;
    }
}
