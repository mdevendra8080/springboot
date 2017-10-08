package com.example;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginControler {
	@RequestMapping("/login")
	public String welcome(Map<String, Object> model	) {
		//model.put("title", TITLE);
		//model.put("message", MESSAGE);
		
		//model
	
		return "index";
	}

}
					