package fr.umlv.hmm2000.warrior;

import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.warrior.exception.WarriorDeadException;
import fr.umlv.hmm2000.warrior.exception.WarriorNotReachableException;
import fr.umlv.hmm2000.warrior.profil.AttackBehaviour;

public interface Fightable extends MapForegroundElement {

  public double getPhysicalAttackValue();

  public void setPhysicalAttackValue(double attackValue);

  public void setPhysicalDefenseValue(double defenseValue);

  public FightableContainer getFightableContainer();

  public double getPhysicalDefenseValue();

  public double getHealth();

  public double getCurrentHealth();

  public void performAttack(Warrior defender) throws WarriorDeadException,
      WarriorNotReachableException;

  public void setFightableContainer(FightableContainer container);

  public void hurt(double damage) throws WarriorDeadException;

  public double getId();

  public int getSpeed();

  public AttackBehaviour getAttackBehaviour();

}