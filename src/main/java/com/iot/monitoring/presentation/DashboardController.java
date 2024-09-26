package com.iot.monitoring.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.iot.monitoring.ingestion.HealthCheckIngestionService;

@RestController
@RequestMapping("/api/v1")
public class DashboardController {

	@Autowired
	private HealthCheckIngestionService service;

	@GetMapping("/dashboard")
	public ModelAndView dashboard(ModelAndView mv) {
		// Example model attribute
		mv.setViewName("dashboard");
		mv.addObject("message", "Welcome to the IoT Device Health Dashboard!");
		return mv; // returns dashboard.html
	}

	@GetMapping("/health/{data}")
	public void send(@PathVariable String data) {
		service.sendHealthCheck(data);
	}
}
