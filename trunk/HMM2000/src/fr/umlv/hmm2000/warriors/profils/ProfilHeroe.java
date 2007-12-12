package fr.umlv.hmm2000.warriors.profils;

import java.util.ArrayList;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warriors.Warrior;
import fr.umlv.hmm2000.warriors.WarriorFactory;

public enum ProfilHeroe implements ProfilWarrior {

	ARCHER(	10,
					10,
					100,
					20,
					null,
					WarriorFactory.createWarriors(ProfilCreatures.GRUNT)),
	LORD_OF_WAR(10,
							10,
							100,
							20,
							null,
							WarriorFactory.createWarriors(ProfilCreatures.FLIGHT)),
	SORCERER(	10,
						10,
						100,
						20,
						null,
						WarriorFactory.createWarriors(ProfilCreatures.FLIGHT));

	private double attackValue;

	private double defenseValue;

	private double health;

	private int speed;

	private Sprite sprite;

	private ArrayList<Warrior> troop;

	private ProfilHeroe(double attackValue,
											double defenseValue,
											double health,
											int speed,
											Sprite sprite,
											ArrayList<Warrior> defaultWarriors) {

		this.attackValue = attackValue;
		this.defenseValue = defenseValue;
		this.health = health;
		this.speed = speed;
		this.sprite = sprite;
		this.troop = defaultWarriors;
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
	public int getSpeed() {

		return this.speed;
	}

	@Override
	public Sprite getSprite() {

		return this.sprite;
	}

	public ArrayList<Warrior> getTroop() {

		return this.troop;
	}

	public void setTroop(ArrayList<Warrior> troop) {

		this.troop = troop;
	}


}
