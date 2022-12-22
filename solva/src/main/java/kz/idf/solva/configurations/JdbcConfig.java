package kz.idf.solva.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import static kz.idf.solva.utility.SqlUtility.*;

@Configuration
public class JdbcConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(JDBCDriver);
        driverManagerDataSource.setUrl(MyURL);
        driverManagerDataSource.setUsername(USER_NAME);
        driverManagerDataSource.setPassword(PASSWORD);
        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
}
