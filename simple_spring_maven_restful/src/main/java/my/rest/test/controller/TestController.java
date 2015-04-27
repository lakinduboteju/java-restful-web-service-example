package my.rest.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	@RequestMapping( value = "/greet", method = RequestMethod.GET )
	@ResponseBody
	public String hello()
	{
		return "Hello!";
	}
}
