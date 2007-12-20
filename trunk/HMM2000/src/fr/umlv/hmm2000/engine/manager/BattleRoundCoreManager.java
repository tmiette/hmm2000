package fr.umlv.hmm2000.engine.manager;

import java.util.ArrayList;
import java.util.HashMap;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.FightableContainer;

public class BattleRoundCoreManager extends DayCoreManager {

  private final Player player1;
  private final Player player2;
  private final FightableContainer player1Container;
  private final FightableContainer player2Container;
  private final HashMap<FightableContainer, Location> containersLocations;
  private final HashMap<Player, ArrayList<Fightable>> fightables;
  private final ArrayList<Fightable> player1Fightables;
  private final ArrayList<Fightable> player2Fightables;

  public BattleRoundCoreManager(FightableContainer attacker,
      FightableContainer defender, Player attackerPlayer, Player defenderPlayer) {
    super(attackerPlayer, defenderPlayer);
    this.player1 = attackerPlayer;
    this.player2 = defenderPlayer;
    this.player1Container = attacker;
    this.player2Container = defender;
    this.containersLocations = new HashMap<FightableContainer, Location>();
    this.containersLocations.put(this.player1Container, CoreEngine.map()
        .getLocationForMapForegroundElement(this.player1Container));
    this.containersLocations.put(this.player2Container, CoreEngine.map()
        .getLocationForMapForegroundElement(this.player2Container));
    this.fightables = new HashMap<Player, ArrayList<Fightable>>();
    this.player1Fightables = new ArrayList<Fightable>();
    this.player2Fightables = new ArrayList<Fightable>();
    this.newRound();
    CoreEngine.uiManager().displaySprite(
        this.containersLocations.get(this.player1Container), Sprite.YOURTURN);
  }

  @Override
  public void nextDay() {
    if (this.opponentContainer().getTroop().size() == 0) {
      System.out.println(opponentPlayer() + " a perdu le combat");
      CoreEngine.endBattle(this.currentContainer(), this.opponentContainer());
    }
    if (this.fightables.get(opponentPlayer()).size() != 0) {
      this.untagFightable();
      this.nextPlayer();
      this.tagFightable();
      CoreEngine.uiManager().displaySprite(
          this.containersLocations.get(this.currentContainer()),
          Sprite.YOURTURN);
      CoreEngine.uiManager().eraseSprite(
          this.containersLocations.get(this.opponentContainer()),
          Sprite.YOURTURN);
      System.out.println(this.currentPlayer() + " doit jouer avec");
    }
    if (this.fightables.get(this.currentPlayer()).size() == 0) {
      this.newRound();
      System.out.println("On recommence le round");
      System.out.println(this.currentPlayer() + " doit jouer");
    }
  }

  private void newRound() {
    this.player1Fightables.clear();
    this.player2Fightables.clear();
    this.player1Fightables.addAll(this.player1Container.getTroop());
    this.player2Fightables.addAll(this.player2Container.getTroop());
    this.fightables.put(this.player1, this.player1Fightables);
    this.fightables.put(this.player2, this.player2Fightables);
  }

  public boolean hasAlreadyPlayed(Fightable f) {
    return this.fightables.get(this.currentPlayer()).contains(f);
  }

  public void tagAsAlreadyPlayed(Fightable f) {
    this.fightables.get(this.currentPlayer()).remove(f);
  }

  public void kill(Fightable f) {
    this.fightables.get(this.opponentPlayer()).remove(f);
  }

  public void tagAttackable(Fightable f) {
    for (Fightable opponentFightable : this.opponentContainer().getTroop()) {
      if (!f.isAttackable(opponentFightable)) {
        Location l = CoreEngine.map().getLocationForMapForegroundElement(
            opponentFightable);
        CoreEngine.uiManager().displaySprite(l, Sprite.UNATTACKABLE);
      }
    }
  }

  public void untagAttackable() {
    for (Fightable opponentFightable : this.opponentContainer().getTroop()) {
      Location l = CoreEngine.map().getLocationForMapForegroundElement(
          opponentFightable);
      CoreEngine.uiManager().eraseSprite(l, Sprite.UNATTACKABLE);
    }
  }

  private void tagFightable() {
    for (Fightable f : this.currentContainer().getTroop()) {
      if (!this.fightables.get(this.currentPlayer()).contains(f)) {
        CoreEngine.uiManager().displaySprite(
            CoreEngine.map().getLocationForMapForegroundElement(f),
            Sprite.ALREADY);
      }
    }
  }

  private void untagFightable() {
    for (Fightable f : this.currentContainer().getTroop()) {
      CoreEngine.uiManager().eraseSprite(
          CoreEngine.map().getLocationForMapForegroundElement(f),
          Sprite.ALREADY);
    }
    for (Fightable f : this.opponentContainer().getTroop()) {
      CoreEngine.uiManager().eraseSprite(
          CoreEngine.map().getLocationForMapForegroundElement(f),
          Sprite.UNATTACKABLE);
    }
  }

  private Player opponentPlayer() {
    if (this.currentPlayer().equals(this.player1)) {
      return player2;
    } else {
      return player1;
    }
  }

  private FightableContainer currentContainer() {
    if (this.currentPlayer().equals(this.player1)) {
      return player1Container;
    } else {
      return player2Container;
    }
  }

  private FightableContainer opponentContainer() {
    if (this.currentPlayer().equals(this.player1)) {
      return player2Container;
    } else {
      return player1Container;
    }
  }

}
