package com.example.dummyjson.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class WebClientConfigTest {

    @Mock
    private Builder webClientBuilderMock;  // Mock para o WebClient.Builder

    @InjectMocks
    private WebClientConfig webClientConfig; // A classe que estamos testando

    @Mock
    private WebClient webClientMock;  // Mock para o WebClient

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    public void testWebClientBeanCreation() {
        // Configura o mock para o WebClient.Builder.
        when(webClientBuilderMock.baseUrl(WebClientConfig.BASE_URL)).thenReturn(webClientBuilderMock);
        when(webClientBuilderMock.build()).thenReturn(webClientMock);

        // Verifica se o WebClient foi criado corretamente.
        WebClient webClient = webClientConfig.webClient(webClientBuilderMock);

        // Verifica se o método build foi chamado no WebClient.Builder.
        verify(webClientBuilderMock).build();

        // Verifica se o WebClient não é nulo.
        assertThat(webClient).isNotNull();
    }
}
