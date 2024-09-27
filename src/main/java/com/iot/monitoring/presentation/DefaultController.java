package com.iot.monitoring.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iot.monitoring.processing.TelemeticsEventsProcessingService;

@Controller
@RequestMapping("/")
public class DefaultController {

	private final TelemeticsEventsProcessingService telemeticsEventsProcessingService;

	public DefaultController(TelemeticsEventsProcessingService telemeticsEventsProcessingService) {
		this.telemeticsEventsProcessingService = telemeticsEventsProcessingService;
	}

	@GetMapping
	public ModelAndView defaultDashboard(ModelAndView mv) {
		long processedCount = telemeticsEventsProcessingService.getProcessedEventCount();
		mv.setViewName("dashboard");
		mv.addObject("message", "Processed " + processedCount + " Events");
		return mv;
	}
}
