package fr.umlv.hmm2000.engine.manager;

import java.util.ArrayList;
import java.util.HashMap;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.engine.ai.AICoreManager;
import fr.umlv.hmm2000.engine.ai.BestAttackerWorstDefenderAICoreManager;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.FightableContainer;
import fr.umlv.hmm2000.util.Pair;

/**
 * This class is the rounds manager in a battle.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class BattleRoundCoreManager extends DayCoreManager {

  private final Player player1;

  private final Player player2;

  private FightableContainer player1Container;

  private FightableContainer player2Container;

  private final HashMap<FightableContainer, Boolean> containers;

  private final HashMap<FightableContainer, Location> containersLocations;

  private final HashMap<Player, ArrayList<Fightable>> fightables;

  private final ArrayList<Fightable> player1Fightables;

  private final ArrayList<Fightable> player2Fightables;

  private final ArrayList<Pair<Location, Sprite>> sprites;

  private final AICoreManager iaManager;

  private int roundCounter;

  private boolean aiAlreadyWin;

  /**
   * Constructor of the manager.
   * 
   * @param attacker
   *            the attacker fightable container.
   * @param defender
   *            the defender fightable container.
   * @param attackerPlayer
   *            the attacker player.
   * @param defenderPlayer
   *            the defender player.
   */
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
    this.iaManager = initAIManager(this.player1, this.player2);
  }

  /**
   * Initializes an Artificial Intelligence manager if either player1 or player2
   * is an AI player
   * 
   * @param p1
   *            player1
   * @param p2
   *            player2
   * @return the AI manager
   */
  private AICoreManager initAIManager(Player p1, Player p2) {

    if (this.player1.equals(Player.AI)) {
      return new BestAttackerWorstDefenderAICoreManager(this.fightables
          .get(this.player1), (ArrayList<Fightable>) this.player2Container
          .getTroop());
    } else {
      return new BestAttackerWorstDefenderAICoreManager(this.fightables
          .get(this.player2), (ArrayList<Fightable>) this.player1Container
          .getTroop());
    }

  }

  @Override
  public void nextDay() {

    if (this.opponentContainer().getTroop().size() == 0) {
      if (this.aiAlreadyWin) {
        return;
      }

      CoreEngine.fireMessage(this.opponentContainer() + " loose the figth.",
          HMMUserInterface.WARNING_MESSAGE);
      CoreEngine.endBattle(this.currentContainer(), this.opponentContainer());

      if (this.currentPlayer().equals(Player.AI)) {
        this.aiAlreadyWin = true;
      }
      return;
    }

    this.untagAlreadyPlayed();
    if (this.isStillAttacker(this.fightables.get(this.opponentPlayer()), this
        .currentContainer())) {
      this.nextBattlePlayer();
    } else if (!this.isStillAttacker(this.fightables.get(this.currentPlayer()),
        this.opponentContainer())) {
      this.newRound();
      this.nextBattlePlayer();
    }

    if (this.currentPlayer().equals(Player.AI)) {
      this.iaManager.performAttack();
      this.nextDay();
      return;
    }

    this.tagAlreadyPlayed();
  }

  /**
   * Go to the next player in the battle.
   */
  private void nextBattlePlayer() {

    this.nextPlayer();
    CoreEngine.fireSpriteAdded(this.containersLocations.get(this
        .currentContainer()), Sprite.YOURTURN);
    CoreEngine.fireSpriteRemoved(this.containersLocations.get(this
        .opponentContainer()), Sprite.YOURTURN);
    CoreEngine.fireMessage(this.currentContainer() + " has to attack !",
        HMMUserInterface.INFO_MESSAGE);
  }

  /**
   * Starts a new round.
   */
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

  /**
   * Returns if a unit has already played during the current round.
   * 
   * @param f
   *            the unit
   * @return if a unit has already played during the current round.
   */
  public boolean hasAlreadyPlayed(Fightable f) {
    return !this.fightables.get(this.currentPlayer()).contains(f);
  }

  /**
   * Returns if a fightable container has already played during the current
   * round.
   * 
   * @param f
   *            the fightable container
   * @return if a fightable container has already played during the current
   *         round.
   */
  public boolean hasAlreadyPlayed(FightableContainer f) {

    return this.containers.get(f);
  }

  /**
   * Marks a unit has already player.
   * 
   * @param f
   *            the unit.
   */
  public void tagAsAlreadyPlayed(Fightable f) {
    this.fightables.get(this.currentPlayer()).remove(f);
  }

  /**
   * Marks a unit has no already player.
   * 
   * @param f
   *            the unit.
   */
  public void tagAsNotAlreadyPlayed(Fightable f) {
    if (this.hasAlreadyPlayed(f)) {
      this.fightables.get(this.currentPlayer()).add(f);
    }
  }

  /**
   * Marks a fightable container has already player.
   * 
   * @param f
   *            the unit.
   */
  public void tagAsAlreadyPlayed(FightableContainer f) {
    this.containers.put(f, true);
  }

  /**
   * Remove a unit from the manger when it is killed.
   * 
   * @param f
   *            the unit.
   */
  public void kill(Fightable f) {
    this.fightables.get(this.opponentPlayer()).remove(f);
  }

  /**
   * Add a sprite to all units which are not attackable for an attacker unit.
   * 
   * @param f
   *            the attacker unit.
   */
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

  /**
   * Erase a sprite to all units which are not attackable.
   */
  public void untagUnattackable() {

    this.sprites.clear();
    for (Fightable opponentFightable : this.opponentContainer().getTroop()) {
      Location l = CoreEngine.map().getLocationForMapForegroundElement(
          opponentFightable);
      this.sprites.add(new Pair<Location, Sprite>(l, Sprite.UNATTACKABLE));
    }
    CoreEngine.fireSpritesRemoved(this.sprites);
  }

  /**
   * Add a sprite to all unit which have already played.
   */
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

  /**
   * Remove a sprite to all unit which have already played.
   */
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

  /**
   * Returns if the attacker fightable container has a unit which can attack.
   * 
   * @param attackers
   *            attacker units.
   * @param defender
   *            defender units
   * @return if the attacker fightable container has a unit which can attack.
   */
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

  /**
   * Returns the opponent player.
   * 
   * @return the opponent player.
   */
  public Player opponentPlayer() {

    if (this.currentPlayer().equals(this.player1)) {
      return player2;
    } else {
      return player1;
    }
  }

  /**
   * Returns the current player.
   * 
   * @return the current player.
   */
  public FightableContainer currentContainer() {

    if (this.currentPlayer().equals(this.player1)) {
      return player1Container;
    } else {
      return player2Container;
    }
  }

  /**
   * Returns the opponent fightable container.
   * 
   * @return the opponent fightable container.
   */
  public FightableContainer opponentContainer() {

    if (this.currentPlayer().equals(this.player1)) {
      return player2Container;
    } else {
      return player1Container;
    }
  }

}
