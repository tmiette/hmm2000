package fr.umlv.hmm2000.engine.manager;

import java.util.ArrayList;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.FightableContainer;
import fr.umlv.hmm2000.unit.Hero;
import fr.umlv.hmm2000.unit.exception.WarriorDeadException;
import fr.umlv.hmm2000.unit.exception.WarriorNotReachableException;
import fr.umlv.hmm2000.unit.skill.Skill;

public class BattleCoreManager {

  private final BattleRoundCoreManager roundManager;

  private FightableContainer attacker;

  private FightableContainer defender;

  private final ArrayList<Fightable> warriors;

  public BattleCoreManager(FightableContainer attacker,
      FightableContainer defender) {
    this.orderContainers(attacker, defender);
    this.warriors = new ArrayList<Fightable>();
    this.warriors.addAll(this.attacker.getTroop());
    this.warriors.addAll(this.defender.getTroop());
    this.roundManager = new BattleRoundCoreManager(this.attacker,
        this.defender, this.attacker.getPlayer(), this.defender.getPlayer());
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
            System.out.println("Joueur tue : " + defenderWarrior);
            this.roundManager.tagAsAlreadyPlayed(attackerWarrior);
            this.kill(l, defenderWarrior);
          } catch (WarriorNotReachableException e) {
            CoreEngine.fireMessage("You cannot attack this unit.",
                HMMUserInterface.WARNING_MESSAGE);
          }
        } else {
          CoreEngine.fireMessage(attackerWarrior
              + " cannot attack anymore in this round.",
              HMMUserInterface.WARNING_MESSAGE);
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
      if (this.roundManager.isCurrentPlayer(hero.getPlayer())
          && !this.roundManager.hasAlreadyPlayed(hero)) {
        ArrayList<Skill> skills = new ArrayList<Skill>();
        skills.add(Skill.defaultSkill);
        skills.addAll(hero.getSkills());
        Skill skill = CoreEngine.requestSkill(skills);
        if (skill != null && skill != Skill.defaultSkill) {
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

  private void orderContainers(FightableContainer c1, FightableContainer c2) {
    if (c1.getAttackPriority() >= c2.getAttackPriority()) {
      this.attacker = c1;
      this.defender = c2;
    } else {
      this.attacker = c2;
      this.defender = c1;
    }
  }

  public BattleRoundCoreManager roundManager() {
    return this.roundManager;
  }

}
