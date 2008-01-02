package fr.umlv.hmm2000.engine.manager;

import java.util.ArrayList;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.FightableContainer;
import fr.umlv.hmm2000.warrior.Hero;
import fr.umlv.hmm2000.warrior.exception.WarriorDeadException;
import fr.umlv.hmm2000.warrior.exception.WarriorNotReachableException;
import fr.umlv.hmm2000.warrior.skill.Skill;

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

    if (attackerElement != null
        && !CoreEngine.selectionManager().getSelectedLocation().equals(l)) {

      MapForegroundElement defenderElement = CoreEngine.map()
          .getMapForegroundElementAtLocation(l);

      if (attackerElement instanceof Fightable && defenderElement != null
          && defenderElement instanceof Fightable) {

        Fightable attackerWarrior = (Fightable) attackerElement;
        Fightable defenderWarrior = (Fightable) defenderElement;

        if (this.roundManager.isCurrentPlayer(attackerWarrior
            .getFightableContainer().getPlayer())
            && !this.roundManager.isCurrentPlayer(defenderWarrior
                .getFightableContainer().getPlayer())
            && !this.roundManager.hasAlreadyPlayed(attackerWarrior)) {
          try {
            attackerWarrior.performAttack(defenderWarrior);
            this.roundManager.tagAsAlreadyPlayed(attackerWarrior);
            this.roundManager.nextDay();
          } catch (WarriorDeadException e) {
            this.roundManager.tagAsAlreadyPlayed(attackerWarrior);
            this.kill(l, defenderWarrior);
          } catch (WarriorNotReachableException e) {
            CoreEngine.fireMessage("Vous ne pouvez pas attaquer cette unité.");
          }
        } else {
          CoreEngine.fireMessage(attackerWarrior + "ne peut pas attaquer.");
        }
      }
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
    } else if (element instanceof Hero) {
      Hero hero = (Hero) element;
      System.out.println(hero.getPlayer());
      System.out.println(this.roundManager.currentPlayer());
      if (this.roundManager.isCurrentPlayer(hero.getPlayer())
          && !this.roundManager.hasAlreadyPlayed(hero)) {
        ArrayList<Skill> skills = new ArrayList<Skill>();
        skills.add(Skill.defaultSkill);
        skills.addAll(hero.getSkills());
        Skill skill = CoreEngine.requestSkill(skills);
        if (skill != Skill.defaultSkill) {
          this.roundManager.tagAsAlreadyPlayed(hero);
          skill.perform();
        }
      }
    }
  }

  public void kill(Location l, Fightable f) {
    this.roundManager.kill(f);
    CoreEngine.map().removeMapForegroundElement(l);
    CoreEngine.fireSpriteRemoved(l, f.getSprite());
    this.roundManager.nextDay();
  }

  private Player[] orderPlayer() {
    Player[] players = new Player[2];
    Player attacker = this.attacker.getPlayer();
    Player defender = this.defender.getPlayer();

    /*
     * if (this.attacker.getAttackPriority() <
     * this.defender.getAttackPriority()) { players[0] = defender; players[1] =
     * attacker; } else { players[0] = attacker; players[1] = defender; }
     */
    players[0] = attacker;
    players[1] = defender;
    return players;
  }

  public BattleRoundCoreManager roundManager() {
    return this.roundManager;
  }

}
