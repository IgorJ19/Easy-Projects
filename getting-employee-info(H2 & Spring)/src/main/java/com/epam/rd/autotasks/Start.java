package com.epam.rd.autotasks;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class Start implements RepositoryRestConfigurer{

    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

    @Bean
    Validator validator(){
        return new LocalValidatorFactoryBean();
    }

    @Override
    public void configureValidatingRepositoryEventListener(final ValidatingRepositoryEventListener validatingListener) {
        validatingListener.addValidator("beforeCreate", validator());
        validatingListener.addValidator("beforeSave", validator());

    }

    @Bean
    public CommandLineRunner initializeDatabase(JdbcTemplate jdbcTemplate) {
        return args -> {
            jdbcTemplate.execute("CREATE ALIAS IF NOT EXISTS COUNT_DEPARTMENTS FOR \"com.epam.rd.autotasks.DepartmentQuery.countDepartments\";");
            jdbcTemplate.execute("CREATE ALIAS IF NOT EXISTS COUNT_EMPLOYEE FOR \"com.epam.rd.autotasks.DepartmentQuery.countEmployees\";");
            jdbcTemplate.execute("CREATE ALIAS IF NOT EXISTS COUNT_EMPLOYEES_BY_DEPARTMENT_ID FOR \"com.epam.rd.autotasks.DepartmentQuery.countEmployees\";");
        };
    }

}
