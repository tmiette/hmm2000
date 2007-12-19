package fr.umlv.hmm2000.engine.manager;

import java.util.HashMap;
import java.util.List;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.FightableContainer;

public class BattleRoundCoreManager extends DayCoreManager {

  private final Player attackerPlayer;
  private final Player defenderPlayer;
  private final FightableContainer attacker;
  private final FightableContainer defender;

  private final HashMap<Player, List<Fightable>> warriors;

  public BattleRoundCoreManager(FightableContainer attacker,
      FightableContainer defender, Player attackerPlayer, Player defenderPlayer) {
    super(attackerPlayer, defenderPlayer);
    this.warriors = new HashMap<Player, List<Fightable>>();
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

  public boolean canAttack(Fightable f) {
    return this.warriors.get(this.attackerPlayer).contains(f);
  }

  public void hasAlreadyAttacked(Fightable f) {
    this.warriors.get(this.attackerPlayer).remove(f);
  }

}
