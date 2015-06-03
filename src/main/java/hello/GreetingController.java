package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class GreetingController {
	
	@Autowired
		JdbcTemplate jdbcTemplate;
	
	
	
	@RequestMapping(value = "/home")
	public String home() {
		
		return "home";
	}
	
	@RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
	
	@RequestMapping("/test")
	public String test(@RequestParam(value="name", required=false, defaultValue="World") String name, @RequestParam(value = "language", required=false) String language, @RequestParam(value = "time", required=false) String time, Model model) {
		
		
		String translation = "";
		
		switch(language) {
			case "Japanese":
				switch(time) {
					case "Morning":
						translation = "Ohayou Gozaimasu " + name;
						break;
						
					case "Afternoon":
						translation = "Konnichi wa " + name;
						break;
					
					case "Evening":
						translation = "Konban wa " + name;
						break;
					default:
						translation = "Sorry "+name+", I couldn't understand the time.";
					}
				break;
			case "English":
				switch(time) {
					case "Morning":
						translation = "Good morning " + name;
						break;
						
					case "Afternoon":
						translation = "Good afternoon " + name;
						break;
						
					case "Evening":
						translation = "Good evening " + name;
						break;
					default:
						translation = "Sorry "+name+", I couldn't understand the time.";
					}
				break;
			case "French":
				switch(time) {
					case "Morning":
						translation = "Bonjour " + name;
						break;
						
					case "Afternoon":
						translation = "Bon apres-midi " + name;
						break;
						
					case "Evening":
						translation = "Bonsoir " + name;
						break;
					default:
						translation = "Sorry "+name+", I couldn't understand the time.";
					}
				break;
			default:
				translation = "Sorry "+name+", I couldn't understand the language.";
				}
				
		/*
		if (time.equals("morning"))
			
		else if (time.equals("afternoon"))
			translation = "Konnichi wa";
		else if (time.equals("evening"))
			translation = "Konban wa";
		else
			translation = "unknown";
		*/
		model.addAttribute("translation", translation);
	
		return "test";
	}
	
	@RequestMapping(value="/greet_form", method=RequestMethod.GET)
    public String greetingForm(Model model) {
        model.addAttribute("greet_form", new Greeting());
        return "greet_form";
    }

    @RequestMapping(value="/greet_form", method=RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        //model.addAttribute("greet_form", greeting);
       
		jdbcTemplate.update("INSERT INTO greetings(receiver, message, sender) values(?,?,?)", greeting.getReceiver(), greeting.getMessage(), greeting.getSender());
		 
		return "result";
	}
	
	@RequestMapping(value="/show_greetings")
	public String showGreetings(Model model) {
		
		List<Greeting> greetings = jdbcTemplate.query(
                "select * from greetings",
                new RowMapper<Greeting>() {
                    @Override
                    public Greeting mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Greeting(rs.getString("receiver"), rs.getString("message"), rs.getString("sender"));
                    }
                });
		
		model.addAttribute("greetings", greetings);
		
		return "show_greetings";
		
	}
}

