package uolbackendd.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class CodinameServiceTest {

  @Mock
  private RestTemplate restTemplate;

  @Mock
  private ObjectMapper objectMapper;

  @Mock
  private Environment env;

  @Autowired
  private CodinameService service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
    public void testLoadJsonData() throws Exception {
        when(env.getProperty("avangers")).thenReturn("https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json");

        String respostaJsonMock = "{ \"vingadores\": [" +
        "{\"codinome\": \"Hulk\"}," +
        "{\"codinome\": \"Capitão América\"}," +
        "{\"codinome\": \"Pantera Negra\"}," +
        "{\"codinome\": \"Homem de Ferro\"}," +
        "{\"codinome\": \"Thor\"}," +
        "{\"codinome\": \"Feiticeira Escarlate\"}," +
        "{\"codinome\": \"Visão\"}" +
        "] }";
        when(restTemplate.getForObject("https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json", String.class)).thenReturn(respostaJsonMock);

        JsonNode jsonNodeMock = mock(JsonNode.class);
        when(objectMapper.readTree(respostaJsonMock)).thenReturn(jsonNodeMock);

        ArrayNode avengersMock = mock(ArrayNode.class);
        when(jsonNodeMock.get("vingadores")).thenReturn(avengersMock);

       List<String> codenames = service.loadJsonData();
        Assertions.assertEquals(List.of("Hulk", "Capitão América","Pantera Negra","Homem de Ferro","Thor", "Feiticeira Escarlate", "Visão"), codenames);
    }
}
