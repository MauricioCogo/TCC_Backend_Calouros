Trabalho de Conclusão de Curso (TCC)
Sistema Auxiliar para Orientação de Calouros do IFFar - SVS

========================================
CAPÍTULO 1 — INTRODUÇÃO
========================================

Este documento descreve o repositório BACK-END do projeto. O backend centraliza toda a lógica de negócio, realiza web scraping de notícias e editais, consome dados de clima, gerencia informações institucionais e oferece uma API REST consumida pelo aplicativo mobile.

========================================
CAPÍTULO 2 — TECNOLOGIAS UTILIZADAS
========================================

- Java 17
- Spring Boot
- Spring Web / Data JPA
- Spring Security
- PostgreSQL
- Maven
- Jsoup (web scraping)
- Springdoc OpenAPI (Swagger)

========================================
CAPÍTULO 3 — FUNCIONALIDADES DO SISTEMA
========================================

- CRUD de setores
- CRUD de responsáveis
- CRUD de informações gerais
- Web scraping de notícias
- Web scraping de editais
- Consulta de clima via OpenWeatherMap
- Autenticação para painel administrativo
- API REST documentada em Swagger

========================================
CAPÍTULO 4 — ESTRUTURA DO PROJETO
========================================

src/main/java/
  controller/   -> endpoints REST
  service/      -> lógica de negócio
  repository/   -> acesso ao BD
  entity/       -> modelos JPA
  config/       -> segurança, CORS e Swagger
  scraper/      -> web scraping

========================================
CAPÍTULO 5 — VARIÁVEIS DE AMBIENTE
========================================

Adicionar no application.properties ou .env:

SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/tcc
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=senha
WEATHER_API_KEY=SUA_CHAVE
SCRAPER_USER_AGENT="Mozilla/5.0"

========================================
CAPÍTULO 6 — EXECUÇÃO DO PROJETO
========================================

Para rodar o backend:

mvn spring-boot:run

Ou:

java -jar target/backend.jar

========================================
CAPÍTULO 7 — ENDPOINTS PRINCIPAIS
========================================

GET /api/sectors
GET /api/responsibles
GET /api/news
GET /api/announcements
GET /api/weather
POST /api/sectors (admin)
POST /api/info (admin)

========================================
CAPÍTULO 8 — TRABALHOS FUTUROS
========================================

- Painel administrativo aprimorado
- Otimização do scraping
- Cache de resultados
- Suporte a notificações para o app
