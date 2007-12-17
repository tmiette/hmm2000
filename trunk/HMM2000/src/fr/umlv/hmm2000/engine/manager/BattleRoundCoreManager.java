package fr.umlv.hmm2000.engine.manager;

import fr.umlv.hmm2000.Player;

public class BattleRoundCoreManager extends DayCoreManager {

  public BattleRoundCoreManager(Player... players) {
    super(players);
  }

  @Override
  public void nextDay() {
    System.out.println("next round");
    this.nextPlayer();
  }

}
