package com.mauriciocogo.tcc_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/admin/home")
    public String dashboard() {
        return "home";
    }

    @GetMapping("/admin/responsibles")
    public String responsibles() {
        return "responsibles";
    }

    @GetMapping("/admin/sectors")
    public String sectors() {
        return "sectors";
    }

    @GetMapping("/admin/informations")
    public String informations() {
        return "informations";
    }
}
