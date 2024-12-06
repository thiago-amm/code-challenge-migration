# Changelog

## [Unreleased]
### Added
- Novo endpoint `/actuator/health` para retornar o status de saúde do microsserviço.
- Parametrização da URL da API `dummyjson` por ambiente no projeto.

### Changed
- Atualização do `pom.xml` para usar Java 17+ e Spring Boot 3.2.5.
- Substituição de `RestTemplate` por `WebClient` para chamadas HTTP.
- Refatoração de código depreciado ou incompatível com Java 17 e Spring Boot 3.2.5.
- Atualização dos testes:
    - Substituídos os testes unitários baseados em JUnit 4 e Mockito por testes de integração usando `@SpringBootTest`.

### Fixed
- Garantia de que todos os testes existentes passam após a migração para o novo ambiente e framework.

## [1.1] - 2024-12-06
### Added
- Versão inicial do microsserviço.
