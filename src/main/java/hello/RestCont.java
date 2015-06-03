package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
class RestCont {

	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

	
	@RequestMapping("/get_greeting")
    public SimpleGreeting get_greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new SimpleGreeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}