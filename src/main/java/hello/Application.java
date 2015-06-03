package hello;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


@SpringBootApplication
public class Application implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	@Autowired
    JdbcTemplate jdbcTemplate;
	
	
	// jdbc app
    @Override
    public void run(String... strings) throws Exception {
        
        jdbcTemplate.execute("drop table greetings if exists");
        jdbcTemplate.execute("create table greetings(id serial, receiver varchar(255), message varchar(255), sender varchar(255))");

        
	}
	
	

}