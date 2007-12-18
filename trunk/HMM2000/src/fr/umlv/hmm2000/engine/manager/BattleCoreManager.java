package fr.umlv.hmm2000.engine.manager;

import java.util.ArrayList;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.warrior.Container;
import fr.umlv.hmm2000.warrior.Heroe;
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
    this.roundManager = new BattleRoundCoreManager(attacker, defender,
        players[0], players[1]);
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
        attackerWarrior.performAttack(defenderWarrior);
        this.roundManager.hasAlreadyAttacked(attackerWarrior);
        this.roundManager.nextDay();
      } catch (WarriorDeadException e) {
        CoreEngine.uiManager().displayMessage("Le défenseur est mort.");
        CoreEngine.map().removeMapForegroundElement(l);
        //EncounterEvent event = new EncounterEvent(null, null, l, l);
        //CoreEngine.uiManager().eraseForegroundElement(event);
        System.out.println("attacker :" + this.attacker.getTroop().size());
        System.out.println("defender :" + this.defender.getTroop().size());
        System.out.println((Heroe) this.attacker);
        System.out.println((Heroe) this.defender);
        if (this.attacker.getTroop().size() == 0
            || this.defender.getTroop().size() == 0) {
          // TODO retirer le container du player
          CoreEngine.uiManager().displayMessage("Vous avez perdu le combat.");
          CoreEngine.backToWorldMap();
        }
      } catch (WarriorNotReachableException e) {
        CoreEngine.uiManager().displayMessage(
            "Vous ne pouvez pas attaquer cette unité.");
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
