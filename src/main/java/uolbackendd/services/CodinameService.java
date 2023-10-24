package uolbackendd.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import jakarta.annotation.PostConstruct;

import lombok.Getter;

@Getter
@Service
public class CodinameService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private Environment env;

  private List<String> avangersCodinameList;
  private List<String> justiceLeagueList;

  ObjectMapper objectMapper = new ObjectMapper();

  @PostConstruct
  @Cacheable("codenames")
  public void loadAllData() {
    try {
      this.avangersCodinameList = loadJsonData();
      this.justiceLeagueList = loadXmlData();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private List<String> loadJsonData() throws Exception {
    String codenameResponse = restTemplate.getForObject(env.getProperty("avangers"), String.class);
    JsonNode jsonNode = objectMapper.readTree(codenameResponse);

    ArrayNode avengers = (ArrayNode) jsonNode.get("vingadores");

    List<String> codenames = new ArrayList<>();

    for (JsonNode item : avengers) {
      codenames.add(item.get("codinome").asText());
    }

    return codenames;
  }

  private List<String> loadXmlData() throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse(env.getProperty("justice.league"));

    NodeList codenameList = document.getElementsByTagName("codinome");

    List<String> codenames = new ArrayList<>();

    for (int item = 0; item < codenameList.getLength(); item++) {
      Element codinameElement = (Element) codenameList.item(item);
      String codename = codinameElement.getTextContent();
      codenames.add(codename);
    }

    return codenames;
  }

}