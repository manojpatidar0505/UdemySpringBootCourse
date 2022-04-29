package com.test.rest.webservice.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.test.rest.webservice.bean.HelloWorldBean;

@RestController
public class HelloController {
	@Autowired
	private MessageSource messageSource;

	@GetMapping("hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping("hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}

	@GetMapping("hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldBeanPathVariable(@PathVariable("name") String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}

	@GetMapping("hello-world-internationalized")
	public String helloWorldInternationalized(
	/* @RequestHeader(name = "Accept-Language", required = false) Locale locale */) {
		return messageSource.getMessage("good.morning.messsage", null, "Default Message",
				/* locale */ LocaleContextHolder.getLocale());
	}
}
