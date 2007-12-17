package fr.umlv.hmm2000.engine;

import fr.umlv.hmm2000.warrior.Container;

public class BattleCoreManager {

  private final Container attacker;

  private final Container defender;

  public BattleCoreManager(Container attacker, Container defender) {
    this.attacker = attacker;
    this.defender = defender;
  }

}
