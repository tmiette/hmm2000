package fr.umlv.hmm2000.warrior;

import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.salesentity.Price;
import fr.umlv.hmm2000.warrior.exception.WarriorDeadException;
import fr.umlv.hmm2000.warrior.exception.WarriorNotReachableException;
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

  Warrior(String label, Sprite sprite,
      double physicalAttackValue, double physicalDefenseValue, double health,
      int speed, ElementAbility abilities, AttackBehaviour attackBehaviour) {

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
  public void acquire(EncounterEvent event) {
    // TODO
    /*
     * MovableElement element = event.getSender(); if (element instanceof
     * Container) { try { ((Container) element).addWarrior(this); } catch
     * (MaxNumberOfTroopsReachedException e) { for (Pair<Kind, Integer> pair :
     * this.getPrice().getResourcesList()) {
     * element.getPlayer().addResource(pair.getFirstElement(),
     * pair.getSecondElement()); } CoreEngine.uiManager().displayMessage("Vous
     * avez trop d'unité."); } }
     */
  }

  @Override
  public boolean encounter(EncounterEvent event) {
    // TODO
    return true;
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
    this.health -= damage;
    if (this.health <= 0) {
      throw new WarriorDeadException("Warrior is dead");
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Warrior n " + this.id);
    sb.append(" ");
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
