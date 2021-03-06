package ru.itis.javalab.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.config.AppConfiguration;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.repositories.UsersRepositoryJdbcImpl;
import ru.itis.javalab.services.UsersService;
import ru.itis.javalab.services.UsersServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;


@WebListener
public class AppConfigServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

//        ServletContext servletContext = servletContextEvent.getServletContext();
//
//        Properties properties = new Properties();
//        try {
//            properties.load(servletContext.getResourceAsStream("/WEB-INF/properties/db.properties"));
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl(properties.getProperty("db.url"));
//        hikariConfig.setDriverClassName(properties.getProperty("db.driver.classname"));
//        hikariConfig.setUsername(properties.getProperty("db.username"));
//        hikariConfig.setPassword(properties.getProperty("db.password"));
//
//        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.max-pool-size")));
//        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
////
//        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
//        UsersService usersService = new UsersServiceImpl(usersRepository);
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        servletContext.setAttribute("usersService", usersService);
//        servletContext.setAttribute("passwordEncoder", passwordEncoder);
        ServletContext servletContext = servletContextEvent.getServletContext();
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        servletContext.setAttribute("applicationContext", context);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

