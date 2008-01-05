package fr.umlv.hmm2000.unit;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;
import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.salesentity.Price;
import fr.umlv.hmm2000.unit.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.unit.exception.WarriorDeadException;
import fr.umlv.hmm2000.unit.exception.WarriorNotReachableException;
import fr.umlv.hmm2000.util.Pair;
import fr.umlv.hmm2000.warrior.profil.AttackBehaviour;
import fr.umlv.hmm2000.warrior.profil.ElementAbility;

public class Warrior implements Fightable {

  private static double WARRIORS_COUNT = 0;

  private final double id;

  private FightableContainer container;

  private final String label;

  private final Sprite sprite;

  private double physicalAttackValue;

  private double physicalDefenseValue;

  private double health;

  private double currentHealth;

  private int speed;

  private ElementAbility abilities;

  private AttackBehaviour attackBehaviour;

  Warrior(String label, Sprite sprite, double physicalAttackValue,
      double physicalDefenseValue, double health, int speed,
      ElementAbility abilities, AttackBehaviour attackBehaviour) {

    this.id = Warrior.WARRIORS_COUNT++;
    this.label = label;
    this.sprite = sprite;
    this.physicalAttackValue = physicalDefenseValue;
    this.physicalDefenseValue = physicalAttackValue;
    this.health = health;
    this.currentHealth = health;
    this.speed = speed;
    this.abilities = abilities;
    this.attackBehaviour = attackBehaviour;
  }

  @Override
  public void accept(UIDisplayingVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public void acquire(Encounter encounter) {
    try {
      encounter.getSender().addFightable(this);
    } catch (MaxNumberOfTroopsReachedException e) {
      for (Pair<Kind, Integer> pair : this.getPrice().getResourcesList()) {
        encounter.getSender().getPlayer().addResource(pair.getFirstElement(),
            pair.getSecondElement());
      }
      CoreEngine.fireMessage(encounter.getSender()
          + " cannot recruit unit anymore.", HMMUserInterface.WARNING_MESSAGE);
    }
  }

  @Override
  public boolean encounter(Encounter encounter) {
    // do nothing
    return false;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Warrior)) {
      return false;
    }
    return ((Warrior) obj).id == this.id;
  }

  @Override
  public double getPhysicalAttackValue() {
    return this.physicalAttackValue;
  }

  @Override
  public FightableContainer getFightableContainer() {
    return this.container;
  }

  @Override
  public double getPhysicalDefenseValue() {
    return this.physicalDefenseValue;
  }

  @Override
  public double getHealth() {
    return this.health;
  }

  @Override
  public double getCurrentHealth() {
    return this.currentHealth;
  }

  @Override
  public AttackBehaviour getAttackBehaviour() {
    return this.attackBehaviour;
  }

  @Override
  public double getId() {
    return this.id;
  }

  @Override
  public String getLabel() {
    return this.label;
  }

  @Override
  public Price getPrice() {
    Price price = new Price();
    // TODO trouver une methode de calcul du prix
    int gold = (2 * (int) this.health + 3 * (int) this.physicalDefenseValue) / 10;
    price.addResource(Kind.GOLD, gold);
    return price;
  }

  @Override
  public Sprite getSprite() {
    return this.sprite;
  }

  @Override
  public int hashCode() {
    return (int) this.id;
  }

  @Override
  public void nextDay(int day) {
    // do nothing
  }

  @Override
  public void performAttack(Fightable defender) throws WarriorDeadException,
      WarriorNotReachableException {

    if (!this.attackBehaviour.isAttackable(this, defender)) {
      throw new WarriorNotReachableException("This warrior is not reachable");
    }
    double elementaryDamage = this.abilities.getDamage(defender.getAbilities());
    defender.hurt(this.physicalAttackValue + elementaryDamage
        - defender.getPhysicalDefenseValue());
  }

  @Override
  public boolean isAttackable(Fightable defender) {

    return this.attackBehaviour.isAttackable(this, defender);
  }

  @Override
  public void setPhysicalAttackValue(double attackValue) {
    this.physicalAttackValue = attackValue;
  }

  @Override
  public void setFightableContainer(FightableContainer container) {
    this.container = container;
  }

  @Override
  public void setPhysicalDefenseValue(double defenseValue) {
    this.physicalDefenseValue = defenseValue;
  }

  @Override
  public void hurt(double damage) throws WarriorDeadException {
    if (damage > 0) {
      this.currentHealth -= damage;
      if (this.currentHealth <= 0) {
        throw new WarriorDeadException("Warrior is dead");
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.label);
    sb.append(this.id);
    return sb.toString();
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public ElementAbility getAbilities() {

    return this.abilities;
  }

}
