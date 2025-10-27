package com.mauriciocogo.tcc_backend.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScrapingService {

    private static final String BASE_URL = "https://www.iffarroupilha.edu.br";
    private static final String CAMPUS_URL = BASE_URL + "/sao-vicente-do-sul";

    public List<Map<String, String>> getNews() throws IOException {
        List<Map<String, String>> news = new ArrayList<>();

        Document doc = Jsoup.connect(CAMPUS_URL).get();
        Elements items = doc.select("div.allmode-item");

        for (int i = 0; i < Math.min(3, items.size()); i++) {
            Element newz = items.get(i);
            Map<String, String> map = new HashMap<>();

            Element titleEl = newz.selectFirst("h4.allmode-title a");
            map.put("title", titleEl.text());

            map.put("link", BASE_URL + titleEl.attr("href"));

            Element imgEl = newz.selectFirst("div.allmode-img img");
            map.put("banner", imgEl != null ? BASE_URL + imgEl.attr("src") : "");

            Element descEl = newz.selectFirst("div.allmode-text");
            map.put("description", descEl != null ? descEl.text() : "");

            news.add(map);
        }

        return news;
    }

    public List<Map<String, String>> getAnnouncements() throws IOException {
        List<Map<String, String>> announcements = new ArrayList<>();

        Document doc = Jsoup.connect(CAMPUS_URL).get();
        Elements items = doc.select("div.tile-collection div.tileItem");

        for (Element item : items) {
            Element linkEl = item.selectFirst("h4 a");
            String title = linkEl.text();

            if (title.startsWith("Edital nº")) {
                Map<String, String> map = new HashMap<>();
                map.put("title", title);
                map.put("link", BASE_URL + linkEl.attr("href"));
                announcements.add(map);
            }
        }

        return announcements;
    }
}
