package org.gradle.controller;

import javax.validation.Valid;

import org.gradle.dto.PersonDto;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PersonController {
	
	@RequestMapping("/home.do")
	public String validationTest(@Valid PersonDto personDto, BindingResult result){
		if(result.hasErrors()){
			return "home";
		}
		return "home";
	}
}
