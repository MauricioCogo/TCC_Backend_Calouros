package com.mauriciocogo.tcc_backend.controller;

import com.mauriciocogo.tcc_backend.service.ScrapingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@Tag(
    name = "Scraping",
    description = "Coleta de notícias e comunicados diretamente do portal do IFFarroupilha - Campus SVS."
)
public class ScrapingController {

    private final ScrapingService scrapingService;

    public ScrapingController(ScrapingService scrapingService) {
        this.scrapingService = scrapingService;
    }

    @GetMapping("/news")
    @Operation(
        summary = "Obter notícias recentes",
        description = "Retorna as 3 notícias mais recentes extraídas automaticamente do portal do campus.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Lista de notícias coletada com sucesso",
                content = @Content(array = @ArraySchema(schema = @Schema(
                    example = """
                    [
                      {
                        "title": "IFFar divulga calendário acadêmico 2025",
                        "link": "https://iffarroupilha.edu.br/noticia/123",
                        "banner": "https://iffarroupilha.edu.br/banner123.jpg",
                        "description": "Resumo da notícia..."
                      }
                    ]
                    """
                )))
            ),
            @ApiResponse(responseCode = "500", description = "Erro ao coletar notícias")
        }
    )
    public List<Map<String, String>> news() throws IOException {
        return scrapingService.getNews();
    }

    @GetMapping("/announcements")
    @Operation(
        summary = "Obter editais recentes",
        description = "Retorna editais e comunicados publicados recentemente no portal do campus.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Lista de comunicados coletada com sucesso",
                content = @Content(array = @ArraySchema(schema = @Schema(
                    example = """
                    [
                      {
                        "title": "Edital nº 05/2025 - Seleção de Bolsistas",
                        "link": "https://iffarroupilha.edu.br/edital/98765"
                      }
                    ]
                    """
                )))
            ),
            @ApiResponse(responseCode = "500", description = "Erro ao coletar comunicados")
        }
    )
    public List<Map<String, String>> announcements() throws IOException {
        return scrapingService.getAnnouncements();
    }
}
