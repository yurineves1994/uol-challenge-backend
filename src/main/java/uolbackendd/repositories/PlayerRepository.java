package uolbackendd.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uolbackendd.entities.Player;

public interface PlayerRepository extends JpaRepository<Player, String> {
  Optional<Player> findPlayerByEmail(String email);
}
