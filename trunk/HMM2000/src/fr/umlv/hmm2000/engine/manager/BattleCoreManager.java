package fr.umlv.hmm2000.engine.manager;

import java.util.ArrayList;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.FightableContainer;
import fr.umlv.hmm2000.warrior.exception.WarriorDeadException;
import fr.umlv.hmm2000.warrior.exception.WarriorNotReachableException;

public class BattleCoreManager {

  private final BattleRoundCoreManager roundManager;

  private final FightableContainer attacker;

  private final FightableContainer defender;

  private final ArrayList<Fightable> warriors;

  public BattleCoreManager(FightableContainer attacker,
      FightableContainer defender) {
    this.attacker = attacker;
    this.defender = defender;
    this.warriors = new ArrayList<Fightable>();
    this.warriors.addAll(attacker.getTroop());
    this.warriors.addAll(defender.getTroop());
    Player[] players = this.orderPlayer();
    this.roundManager = new BattleRoundCoreManager(attacker, defender,
        players[0], players[1]);
  }

  public void perform(Location l) {

    MapForegroundElement attackerElement = CoreEngine.selectionManager()
        .getSelectedElement();
    MapForegroundElement defenderElement = CoreEngine.map()
        .getMapForegroundElementAtLocation(l);
    Fightable attackerWarrior = (Fightable) attackerElement;
    Fightable defenderWarrior = (Fightable) defenderElement;

    if (attackerElement != null
        && defenderElement != null
        && this.roundManager.isCurrentPlayer(attackerWarrior
            .getFightableContainer().getPlayer())
        && !this.roundManager.isCurrentPlayer(defenderWarrior
            .getFightableContainer().getPlayer())
        && this.roundManager.hasAlreadyPlayed(attackerWarrior)) {
      try {
        attackerWarrior.performAttack(defenderWarrior);
        this.roundManager.tagAsAlreadyPlayed(attackerWarrior);
        this.roundManager.nextDay();
      } catch (WarriorDeadException e) {
        this.roundManager.tagAsAlreadyPlayed(attackerWarrior);
        this.roundManager.kill(defenderWarrior);
        CoreEngine.map().removeMapForegroundElement(l);
        this.roundManager.nextDay();
        // TODO deleguer remove sprite a la map
        CoreEngine.uiManager().eraseSprite(l, defenderWarrior.getSprite());
      } catch (WarriorNotReachableException e) {
        CoreEngine.fireMessage("Vous ne pouvez pas attaquer cette unité.");
      }
    } else {
      System.out.println(attackerWarrior + "ne peut pas attaquer");
    }

  }

  public void select() {
    this.roundManager.untagUnattackable();
    MapForegroundElement element = CoreEngine.selectionManager()
        .getSelectedElement();
    if (element instanceof Fightable) {
      Fightable f = (Fightable) element;
      if (this.roundManager.isCurrentPlayer(f.getFightableContainer()
          .getPlayer())) {
        this.roundManager.tagUnattackable((Fightable) element);
      }
    }
  }

  private Player[] orderPlayer() {
    Player[] players = new Player[2];
    Player attacker = this.attacker.getPlayer();
    Player defender = this.defender.getPlayer();

    players[0] = attacker;
    players[1] = defender;

    return players;
  }

}
