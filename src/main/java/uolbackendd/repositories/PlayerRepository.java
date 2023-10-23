package uolbackendd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uolbackendd.entities.Player;

public interface PlayerRepository extends JpaRepository<Player, String> {}
