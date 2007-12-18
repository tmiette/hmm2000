package fr.umlv.hmm2000.engine.manager;

import java.util.ArrayList;
import java.util.HashMap;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.warrior.Container;
import fr.umlv.hmm2000.warrior.Warrior;

public class BattleRoundCoreManager extends DayCoreManager {

  private final Player attackerPlayer;
  private final Player defenderPlayer;
  private final Container attacker;
  private final Container defender;

  private final HashMap<Player, ArrayList<Warrior>> warriors;

  public BattleRoundCoreManager(Container attacker, Container defender,
      Player attackerPlayer, Player defenderPlayer) {
    super(attackerPlayer, defenderPlayer);
    this.warriors = new HashMap<Player, ArrayList<Warrior>>();
    this.attackerPlayer = attackerPlayer;
    this.defenderPlayer = defenderPlayer;
    this.attacker = attacker;
    this.defender = defender;
    this.newRound();
  }

  @Override
  public void nextDay() {
    if (this.warriors.get(this.defenderPlayer).size() != 0) {
      this.nextPlayer();
      System.out.println("player suivant");
    }
    if (this.warriors.get(this.attackerPlayer).size() == 0) {
      this.newRound();
      System.out.println("reaload du round");
    }
  }

  private void newRound() {
    this.warriors.put(this.attackerPlayer, this.attacker.getTroop());
    this.warriors.put(this.defenderPlayer, this.defender.getTroop());
  }

  public boolean canAttack(Warrior warrior) {
    return this.warriors.get(this.attackerPlayer).contains(warrior);
  }

  public void hasAlreadyAttacked(Warrior warrior) {
    this.warriors.get(this.attackerPlayer).remove(warrior);
  }

}
