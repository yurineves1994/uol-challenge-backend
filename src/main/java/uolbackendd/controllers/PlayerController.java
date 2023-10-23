package uolbackendd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import uolbackendd.entities.Player;
import uolbackendd.entities.dtos.PlayerDTO;
import uolbackendd.services.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {

  @Autowired
  private PlayerService service;

  @PostMapping
  public ResponseEntity<List<Player>> createPlayer(@RequestBody @Valid PlayerDTO dto){
      return new ResponseEntity<>(service.createPlayer(dto), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Player>> getAllPlayers(){
      return new ResponseEntity<>(service.getAllPlayers(), HttpStatus.OK);
  }

}