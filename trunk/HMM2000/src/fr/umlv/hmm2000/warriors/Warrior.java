package fr.umlv.hmm2000.warriors;

import java.util.ArrayList;

import fr.umlv.hmm2000.MovableElement;
import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warriors.attacks.Attack;
import fr.umlv.hmm2000.warriors.profils.ProfilWarrior;

public class Warrior extends MovableElement implements ProfilWarrior {

	private double health;

	private int speed;

	// List of attacks he can execute
	private ArrayList<Attack> listOfAttacks;

	// Container that contains him (heroe, castle)
	private Container container;

	private Sprite sprite;

	private double defenseValue;

	private double attackValue;

	Warrior(Player player, double health, int speed,
			ArrayList<Attack> listOfAttacks, Container container, Sprite sprite,
			double defenseValue, double attackValue) {

		super(player);
		this.health = health;
		this.speed = speed;
		this.listOfAttacks = listOfAttacks;
		this.container = container;
		this.sprite = sprite;
		this.defenseValue = defenseValue;
		this.attackValue = attackValue;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Warrior : \n(Health = ");
		sb.append(this.health);
		sb.append(",StepCount = ");
		sb.append(this.speed);
		sb.append(",Attacks = ");
		sb.append(this.listOfAttacks.toString());
		sb.append(")");
		return sb.toString();
	}

	@Override
	public Sprite getSprite() {

		return this.sprite;
	}

	@Override
	public double getAttackValue() {

		return this.attackValue;
	}

	@Override
	public double getDefenseValue() {

		return this.defenseValue;
	}

	@Override
	public double getHealth() {

		return this.health;
	}

	@Override
	public ArrayList<Attack> getListOfAttacks() {

		return this.listOfAttacks;
	}

	@Override
	public int getSpeed() {

		return this.speed;
	}

	public void setSpeed(int speed) {

		this.speed = speed;
	}

	public void setContainer(Container c) {

		this.container = c;
	}

}
