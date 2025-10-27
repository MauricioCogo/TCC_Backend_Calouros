package com.mauriciocogo.tcc_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mauriciocogo.tcc_backend.service.ScrapingService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class ScrapingController {

    private final ScrapingService scrapingService;

    public ScrapingController(ScrapingService scrapingService) {
        this.scrapingService = scrapingService;
    }

    @GetMapping("/news")
    public List<Map<String, String>> news() throws IOException {
        return scrapingService.getNews();
    }

    @GetMapping("/announcements")
    public List<Map<String, String>> announcements() throws IOException {
        return scrapingService.getAnnouncements();
    }
}
