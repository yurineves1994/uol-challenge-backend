package uolbackendd.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uolbackendd.entities.Player;
import uolbackendd.entities.dtos.PlayerDTO;
import uolbackendd.entities.enums.GroupType;
import uolbackendd.infra.CodinameHandler;
import uolbackendd.repositories.PlayerRepository;

@Service
public class PlayerService {

  @Autowired
  private PlayerRepository repository;

  @Autowired
  private CodinameHandler handle;

  public List<Player> createPlayer(PlayerDTO dto) {
    Player newPlayer = new Player(dto);
    String codiname = getCodiname(dto.groupType());
    newPlayer.setCodiname(codiname);
    repository.save(newPlayer);
    return this.getAllPlayers();
  }

  public List<Player> getAllPlayers() {
    return repository.findAll();
  }

  private String getCodiname(GroupType groupType) {
    return handle.findCodeName(groupType);
  }

}