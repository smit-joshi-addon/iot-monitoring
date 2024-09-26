package com.iot.monitoring.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Example model attribute
        model.addAttribute("message", "Welcome to the IoT Device Health Dashboard!");
        return "dashboard"; // returns dashboard.html
    }
}
