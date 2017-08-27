package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginControler {
	@RequestMapping("/")
	public String welcome() {
		//model.put("title", TITLE);
		//model.put("message", MESSAGE);
		return "This is login page. Welcome here ";
	}

}
