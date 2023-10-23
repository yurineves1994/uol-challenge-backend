package uolbackendd.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uolbackendd.entities.enums.GroupType;
import uolbackendd.services.CodinameService;

@Component
public class CodinameHandler {

  @Autowired
  private CodinameService service;

  public String findCodeName(GroupType groupType) {
    if (groupType == GroupType.AVENGERS) {
      String firstMatch = service.getAvangersCodinameList().stream().findFirst().orElseThrow();
      this.service.getAvangersCodinameList().remove(firstMatch);
      return firstMatch;
    }
    String firstMatch = service.getJusticeLeagueList().stream().findFirst().orElseThrow();
    this.service.getJusticeLeagueList().remove(firstMatch);
    return firstMatch;
  }

}
