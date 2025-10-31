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
    description = "Endpoints responsáveis por coletar notícias e comunicados diretamente do site do IFFarroupilha - Campus São Vicente do Sul."
)
public class ScrapingController {

    private final ScrapingService scrapingService;

    public ScrapingController(ScrapingService scrapingService) {
        this.scrapingService = scrapingService;
    }

    @GetMapping("/news")
    @Operation(
        summary = "Obter notícias recentes do campus",
        description = """
            Realiza web scraping no portal do IFFar São Vicente do Sul e retorna as **3 notícias mais recentes**. \
            Cada notícia contém título, link para o conteúdo completo, imagem (banner) e uma breve descrição.
            """,
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Lista de notícias coletada com sucesso",
                content = @Content(array = @ArraySchema(schema = @Schema(
                    example = """
                    [
                      {
                        "title": "IFFar divulga calendário acadêmico 2025",
                        "link": "https://www.iffarroupilha.edu.br/sao-vicente-do-sul/noticias/12345",
                        "banner": "https://www.iffarroupilha.edu.br/images/banner123.jpg",
                        "description": "O Instituto Federal Farroupilha divulgou o novo calendário acadêmico..."
                      }
                    ]
                    """
                )))
            ),
            @ApiResponse(responseCode = "500", description = "Erro ao coletar notícias do site")
        }
    )
    public List<Map<String, String>> news() throws IOException {
        return scrapingService.getNews();
    }

    @GetMapping("/announcements")
    @Operation(
        summary = "Obter comunicados (editais) do campus",
        description = """
            Coleta e retorna uma lista de **editais recentes** publicados no site do IFFar São Vicente do Sul. \
            Cada item contém o título (geralmente iniciando com 'Edital nº') e o link para o documento completo.
            """,
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Lista de comunicados coletada com sucesso",
                content = @Content(array = @ArraySchema(schema = @Schema(
                    example = """
                    [
                      {
                        "title": "Edital nº 05/2025 - Seleção de Bolsistas PIBID",
                        "link": "https://www.iffarroupilha.edu.br/sao-vicente-do-sul/editais/98765"
                      }
                    ]
                    """
                )))
            ),
            @ApiResponse(responseCode = "500", description = "Erro ao coletar comunicados do site")
        }
    )
    public List<Map<String, String>> announcements() throws IOException {
        return scrapingService.getAnnouncements();
    }
}
