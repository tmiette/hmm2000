package fr.umlv.hmm2000.warriors;

import java.util.ArrayList;

import fr.umlv.hmm2000.MovableElement;
import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warriors.attacks.Attack;

public abstract class Warrior extends MovableElement {

	private double health;

	private int stepCount;

	private final ArrayList<Attack> listOfAttacks;

	private Position position;

	public Warrior(Player player) {

		super(player);
		this.health = initHealth();
		this.stepCount = initStepCount();
		this.position = Position.NONE;
		this.listOfAttacks = initListOfAttacks();
	}

	public abstract ArrayList<Attack> initListOfAttacks();
	
	public abstract double initHealth();
	
	public abstract int initStepCount();

	public void attack(Warrior warrior, ArrayList<Attack> attacks) {

		double damage = 0;
		double defenseCount = 0;
		for (Attack attack : attacks) {
			if (attack.isPhysical()) {
				damage += attack.getDamageCount();
				defenseCount += attack.getResistanceCount();
			}
			else {
				damage += (attack.getDamageCount()
						* ((100 - attack.getResistanceCount()) / 100));
			}
		}
		damage = damage - defenseCount;
		warrior.setDamage(damage);

		System.out.println("Damage caused : " + damage);
		
		if (warrior.health <= 0) {
			System.out.println("Warrior : " + warrior + " is dead.");
		}
	}

	private void setDamage(double damage) {

		this.health -= damage;
	}

	public double getHealth() {

		return health;
	}

	@Override
	public int getStepCount() {

		return this.stepCount;
	}

	public void setStepCount(int stepCount) {

		this.stepCount = stepCount;
	}

	@Override
	public Sprite getSprite() {

		// TODO
		return null;
	}

	public ArrayList<Attack> getListOfAttacks() {
	
		return this.listOfAttacks;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Warrior : \n(Health = ");
		sb.append(this.health);
		sb.append(",StepCount = ");
		sb.append(this.stepCount);
		sb.append(",Attacks = ");
		sb.append(this.listOfAttacks.toString());
		sb.append(",Position = ");
		sb.append(this.position);
		sb.append(")");
		return sb.toString();
	}

}
