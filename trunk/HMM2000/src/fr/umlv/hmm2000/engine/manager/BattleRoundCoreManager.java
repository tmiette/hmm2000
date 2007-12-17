package fr.umlv.hmm2000.engine.manager;

import java.util.ArrayList;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.warrior.Container;
import fr.umlv.hmm2000.warrior.Warrior;

public class BattleRoundCoreManager extends DayCoreManager {

  private final Container attacker;
  private final Container defender;

  private final ArrayList<Warrior> warriors;

  public BattleRoundCoreManager(Container attacker, Container defender,
      Player attackerPlayer, Player defenderPlayer) {
    super(attackerPlayer, defenderPlayer);
    this.warriors = new ArrayList<Warrior>();
    this.attacker = attacker;
    this.defender = defender;
    this.newRound();
  }

  @Override
  public void nextDay() {
    System.out.println("next round");
    this.nextPlayer();
    if(this.warriors.size() == 0){
      this.newRound();
    }
  }

  private void newRound() {
    this.warriors.addAll(this.attacker.getTroop());
    this.warriors.addAll(this.defender.getTroop());
  }

  public boolean canAttack(Warrior warrior) {
    return this.warriors.contains(warrior);
  }

  public void hasAlreadyAttacked(Warrior warrior) {
    this.warriors.remove(warrior);
  }

}
