package fr.umlv.hmm2000.unit;

import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.salesentity.Sellable;
import fr.umlv.hmm2000.unit.exception.WarriorDeadException;
import fr.umlv.hmm2000.unit.exception.WarriorNotReachableException;
import fr.umlv.hmm2000.unit.profil.AttackBehaviour;
import fr.umlv.hmm2000.unit.profil.ElementAbility;

public interface Fightable extends Sellable, MapForegroundElement {

  public double getPhysicalAttackValue();

  public void setPhysicalAttackValue(double attackValue);

  public void setPhysicalDefenseValue(double defenseValue);

  public FightableContainer getFightableContainer();

  public double getPhysicalDefenseValue();

  public double getHealth();

  public double getCurrentHealth();

  public void performAttack(Fightable defender) throws WarriorDeadException,
      WarriorNotReachableException;

  public void setFightableContainer(FightableContainer container);

  public void hurt(double damage) throws WarriorDeadException;

  public double getId();

  public int getSpeed();

  public AttackBehaviour getAttackBehaviour();
  
  public ElementAbility getAbilities();
  
  public boolean isAttackable(Fightable defender);

}