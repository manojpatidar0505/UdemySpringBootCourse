package com.test.rest.webservice.controller.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	@GetMapping("filtering")
	public SomeBean retriveSomeBean() {
		return new SomeBean("value1", "value2", "value3");
	}

	@GetMapping("filtering-list")
	public List<SomeBean> retriveSomeBeanLit() {
		return Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value1", "value2", "value3"));

	}
}
