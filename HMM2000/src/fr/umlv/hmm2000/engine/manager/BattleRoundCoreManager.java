package fr.umlv.hmm2000.engine.manager;

import java.util.ArrayList;
import java.util.HashMap;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.FightableContainer;
import fr.umlv.hmm2000.util.Pair;

public class BattleRoundCoreManager extends DayCoreManager {

  private final Player player1;

  private final Player player2;

  private final FightableContainer player1Container;

  private final FightableContainer player2Container;

  private final HashMap<FightableContainer, Boolean> containers;

  private final HashMap<FightableContainer, Location> containersLocations;

  private final HashMap<Player, ArrayList<Fightable>> fightables;

  private final ArrayList<Fightable> player1Fightables;

  private final ArrayList<Fightable> player2Fightables;

  private final ArrayList<Pair<Location, Sprite>> sprites;

  private final BattleRoundIAManager iaManager;

  private int roundCounter;

  public BattleRoundCoreManager(FightableContainer attacker,
      FightableContainer defender, Player attackerPlayer, Player defenderPlayer) {

    super(attackerPlayer, defenderPlayer);
    this.sprites = new ArrayList<Pair<Location, Sprite>>();
    this.player1 = attackerPlayer;
    this.player2 = defenderPlayer;
    this.player1Container = attacker;
    this.player2Container = defender;
    this.containers = new HashMap<FightableContainer, Boolean>();
    this.containersLocations = new HashMap<FightableContainer, Location>();
    this.containersLocations.put(this.player1Container, CoreEngine.map()
        .getLocationForMapForegroundElement(this.player1Container));
    this.containersLocations.put(this.player2Container, CoreEngine.map()
        .getLocationForMapForegroundElement(this.player2Container));
    this.fightables = new HashMap<Player, ArrayList<Fightable>>();
    this.player1Fightables = new ArrayList<Fightable>();
    this.player2Fightables = new ArrayList<Fightable>();
    this.newRound();
    CoreEngine.fireSpriteAdded(this.containersLocations
        .get(this.player1Container), Sprite.YOURTURN);
    this.iaManager = initIAManager(this.player1, this.player2);
  }

  private BattleRoundIAManager initIAManager(Player p1, Player p2) {

    BattleRoundIAManager iaManager = null;
    if (this.player1.equals(Player.PLAYER_IA)) {
      iaManager = new BattleRoundIAManager(this.fightables.get(this.player1),
          this.fightables.get(this.player2));
    } else if (this.player2.equals(Player.PLAYER_IA)) {
      iaManager = new BattleRoundIAManager(this.fightables.get(this.player2),
          this.fightables.get(this.player1));
    }
    return iaManager;
  }

  @Override
  public void nextDay() {

    this.untagAlreadyPlayed();
    if (this.opponentContainer().getTroop().size() == 0) {
      CoreEngine.fireMessage(this.opponentContainer() + " loose the figth.",
          HMMUserInterface.WARNING_MESSAGE);
      CoreEngine.endBattle(this.currentContainer(), this.opponentContainer());
      return;
    }
    if (this.isStillAttacker(this.fightables.get(this.opponentPlayer()), this
        .currentContainer())) {
      this.nextBattlePlayer();
      if (this.currentPlayer().equals(Player.PLAYER_IA)) {
        this.iaManager.performRound();
        this.nextDay();
        return;
      }
    } else if (!this.isStillAttacker(this.fightables.get(this.currentPlayer()),
        this.opponentContainer())) {
      this.newRound();
      this.nextBattlePlayer();
      if (this.currentPlayer().equals(Player.PLAYER_IA)) {
        this.iaManager.performRound();
        this.nextDay();
        return;
      }
    }
    this.tagAlreadyPlayed();
  }

  private void nextBattlePlayer() {
    this.nextPlayer();
    CoreEngine.fireSpriteAdded(this.containersLocations.get(this
        .currentContainer()), Sprite.YOURTURN);
    CoreEngine.fireSpriteRemoved(this.containersLocations.get(this
        .opponentContainer()), Sprite.YOURTURN);
    CoreEngine.fireMessage(this.currentContainer() + " has to attack !",
        HMMUserInterface.INFO_MESSAGE);
  }

  private void newRound() {
    this.containers.put(this.player1Container, false);
    this.containers.put(this.player2Container, false);
    this.player1Fightables.clear();
    this.player2Fightables.clear();
    this.player1Fightables.addAll(this.player1Container.getTroop());
    this.player2Fightables.addAll(this.player2Container.getTroop());
    this.fightables.put(this.player1, this.player1Fightables);
    this.fightables.put(this.player2, this.player2Fightables);
    CoreEngine.fireMessage("Round " + ++this.roundCounter + ".",
        HMMUserInterface.INFO_MESSAGE);
  }

  public boolean hasAlreadyPlayed(Fightable f) {

    return !this.fightables.get(this.currentPlayer()).contains(f);
  }

  public boolean hasAlreadyPlayed(FightableContainer f) {

    return this.containers.get(f);
  }

  public void tagAsAlreadyPlayed(Fightable f) {

    this.fightables.get(this.currentPlayer()).remove(f);
  }

  public void tagAsNotAlreadyPlayed(Fightable f) {

    if (this.hasAlreadyPlayed(f)) {
      this.fightables.get(this.currentPlayer()).add(f);
    }
  }

  public void tagAsAlreadyPlayed(FightableContainer f) {

    this.containers.put(f, true);
  }

  public void kill(Fightable f) {

    this.fightables.get(this.opponentPlayer()).remove(f);
  }

  public void tagUnattackable(Fightable f) {

    this.sprites.clear();
    for (Fightable opponentFightable : this.opponentContainer().getTroop()) {
      if (!f.isAttackable(opponentFightable)) {
        Location l = CoreEngine.map().getLocationForMapForegroundElement(
            opponentFightable);
        this.sprites.add(new Pair<Location, Sprite>(l, Sprite.UNATTACKABLE));
      }
    }
    CoreEngine.fireSpritesAdded(this.sprites);
  }

  public void untagUnattackable() {

    this.sprites.clear();
    for (Fightable opponentFightable : this.opponentContainer().getTroop()) {
      Location l = CoreEngine.map().getLocationForMapForegroundElement(
          opponentFightable);
      this.sprites.add(new Pair<Location, Sprite>(l, Sprite.UNATTACKABLE));
    }
    CoreEngine.fireSpritesRemoved(this.sprites);
  }

  private void tagAlreadyPlayed() {

    this.sprites.clear();
    for (Fightable f : this.currentContainer().getTroop()) {
      if (!this.fightables.get(this.currentPlayer()).contains(f)) {
        this.sprites.add(new Pair<Location, Sprite>(CoreEngine.map()
            .getLocationForMapForegroundElement(f), Sprite.ALREADY));
      }
    }
    CoreEngine.fireSpritesAdded(this.sprites);
  }

  private void untagAlreadyPlayed() {
    this.sprites.clear();
    for (Fightable f : this.currentContainer().getTroop()) {
      this.sprites.add(new Pair<Location, Sprite>(CoreEngine.map()
          .getLocationForMapForegroundElement(f), Sprite.ALREADY));
    }
    for (Fightable f : this.opponentContainer().getTroop()) {
      this.sprites.add(new Pair<Location, Sprite>(CoreEngine.map()
          .getLocationForMapForegroundElement(f), Sprite.UNATTACKABLE));
    }
    CoreEngine.fireSpritesRemoved(this.sprites);
  }

  private boolean isStillAttacker(ArrayList<Fightable> attackers,
      FightableContainer defender) {

    for (Fightable attackerFightable : attackers) {
      for (Fightable opponentFightable : defender.getTroop()) {
        if (attackerFightable.isAttackable(opponentFightable)) {
          return true;
        }
      }
    }
    return false;
  }

  public Player opponentPlayer() {

    if (this.currentPlayer().equals(this.player1)) {
      return player2;
    } else {
      return player1;
    }
  }

  public FightableContainer currentContainer() {

    if (this.currentPlayer().equals(this.player1)) {
      return player1Container;
    } else {
      return player2Container;
    }
  }

  public FightableContainer opponentContainer() {

    if (this.currentPlayer().equals(this.player1)) {
      return player2Container;
    } else {
      return player1Container;
    }
  }

}
