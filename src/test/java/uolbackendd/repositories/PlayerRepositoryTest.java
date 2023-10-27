package uolbackendd.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import uolbackendd.entities.Player;
import uolbackendd.entities.dtos.PlayerDTO;
import uolbackendd.entities.enums.GroupType;

@DataJpaTest // diz para o spring que essa classe é uma classe de teste que vai testar um repository
@ActiveProfiles("test") // diz para o spring que queremos usar application-test nos testes
public class PlayerRepositoryTest {

  @Autowired
  PlayerRepository playerRepository; // injeta o repositorio para testar os metodos do mesmo

  @Autowired
  EntityManager entityManager; // usado para fazer a persistencia no banco de dados

  @Test
  @DisplayName("Should sucess Player successfully from DB!")
  void findPlayerByEmailSucess() {
    String email = "yurineves1934@gmail.com";
    PlayerDTO player = new PlayerDTO("Yuri Neves", email, "3333-3333", GroupType.AVENGERS);
    this.createPlayer(player); // cria o Player no banco de dados

    Optional<Player> result = this.playerRepository.findPlayerByEmail(email);

    assertThat(result.isPresent()).isTrue();
  }

  @Test
  @DisplayName("Should not get Player from DB when user not exists!")
  void findPlayerByEmailError() {
    String email = "yurineves1934@gmail.com";

    Optional<Player> result = this.playerRepository.findPlayerByEmail(email);

    assertThat(result.isEmpty()).isTrue();
  }

  // através do EntityManager é possivel persistir o dado no banco de dados para realizar o teste
  private Player createPlayer(PlayerDTO player) {
    Player newPlayer = new Player(player);
    this.entityManager.persist(newPlayer);
    return newPlayer;
  }

}
