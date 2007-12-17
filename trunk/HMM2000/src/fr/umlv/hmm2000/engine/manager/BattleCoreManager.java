package fr.umlv.hmm2000.engine.manager;

import java.util.ArrayList;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.warrior.Container;
import fr.umlv.hmm2000.warrior.Warrior;
import fr.umlv.hmm2000.warrior.exception.WarriorDeadException;
import fr.umlv.hmm2000.warrior.exception.WarriorNotReachableException;

public class BattleCoreManager {

  private final BattleRoundCoreManager roundManager;

  private final Container attacker;

  private final Container defender;

  private final ArrayList<Warrior> warriors;

  public BattleCoreManager(Container attacker, Container defender) {
    this.attacker = attacker;
    this.defender = defender;
    this.warriors = new ArrayList<Warrior>();
    this.warriors.addAll(attacker.getTroop());
    this.warriors.addAll(defender.getTroop());
    Player[] players = this.orderPlayer();
    this.roundManager = new BattleRoundCoreManager(attacker, defender, players[0],
        players[1]);
  }

  public void perform(Location l) {

    MapForegroundElement attackerElement = CoreEngine.selectionManager()
        .getSelectedElement();
    MapForegroundElement defenderElement = CoreEngine.map()
        .getMapForegroundElementAtLocation(l);
    Warrior attackerWarrior = (Warrior) attackerElement;
    Warrior defenderWarrior = (Warrior) defenderElement;

    if (attackerElement != null && defenderElement != null
        && this.roundManager.isCurrentPlayer(attackerWarrior.getPlayer())
        && !this.roundManager.isCurrentPlayer(defenderWarrior.getPlayer())) {
      try {
        attackerWarrior.performAttack(defenderWarrior, null);
      } catch (WarriorDeadException e) {
        CoreEngine.uiManager().displayMessage("Le défenseur est mort.");
        CoreEngine.map().removeMapForegroundElement(l);
      } catch (WarriorNotReachableException e) {
        CoreEngine.uiManager().displayMessage(
            "Vous ne pouvez pas attaquer cette unité.");
      }
      this.roundManager.nextDay();
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
