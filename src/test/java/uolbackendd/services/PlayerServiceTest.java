package uolbackendd.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import uolbackendd.entities.Player;
import uolbackendd.entities.dtos.PlayerDTO;
import uolbackendd.entities.enums.GroupType;
import uolbackendd.infra.CodinameHandler;
import uolbackendd.repositories.PlayerRepository;

@ActiveProfiles("test")
public class PlayerServiceTest {

  @Mock // cria o mock que é um simulação das classes reais
  private PlayerRepository repository;

  @Mock // cria o mock que é um simulação das classes reais
  private CodinameHandler handle;

  @Autowired
  @InjectMocks // aqui fiz para o Junit pra injetar os mocks e não as classes reais
  private PlayerService playerService;

  @Autowired
  EntityManager entityManager;

  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void createPlayerSuccess() {
    PlayerDTO playerDTO = new PlayerDTO("Yuri Neves", "yurineveves1934@gmail.com", "3333-3333", GroupType.AVENGERS);
    playerService.createPlayer(playerDTO);

    verify(repository, times(1)).save(any());
  }

  @Test
  void createPlayerError() {
    PlayerDTO playerDTO = new PlayerDTO("Yuri Neves", "yurineveves1934@gmail.com", "3333-3333", GroupType.AVENGERS);

    verify(repository, times(0)).save(any());
  }

  @Test
  void getAllPlayersSuccess() {
    List<Player> players = playerService.getAllPlayers();  

    verify(repository, times(1)).findAll();
  }

  @Test
  void getAllPlayersError() {
    verify(repository, times(0)).findAll();
  }

  private Player createPlayer(Player player) {
    this.entityManager.persist(player);
    return player;
  }
}
