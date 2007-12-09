package fr.umlv.hmm2000.warriors.profils;

import java.util.ArrayList;

import fr.umlv.hmm2000.warriors.attacks.Attack;
import fr.umlv.hmm2000.warriors.attacks.Fire;
import fr.umlv.hmm2000.warriors.attacks.Lightning;
import fr.umlv.hmm2000.warriors.attacks.Strike;
import fr.umlv.hmm2000.warriors.attacks.Water;

public enum ProfilHeroes {

	ARCHER {

		public ArrayList<Attack> getListOfAttacks() {

			ArrayList<Attack> attacks = new ArrayList<Attack>();
			attacks.add(new Fire(50, 50));
			attacks.add(new Lightning(50, 50));
			attacks.add(new Water(50, 50));
			attacks.add(new Strike(50, 50));
			return attacks;
		}

		public double getHealth() {

			return 100;
		}

		public int getStepCount() {

			return 10;
		}
	},
	LORD_OF_WAR {

		public ArrayList<Attack> getListOfAttacks() {

			ArrayList<Attack> attacks = new ArrayList<Attack>();
			attacks.add(new Fire(50, 50));
			attacks.add(new Lightning(50, 50));
			attacks.add(new Water(50, 50));
			attacks.add(new Strike(50, 50));
			return attacks;
		}

		public double getHealth() {

			return 100;
		}

		public int getStepCount() {

			return 10;
		}
	},
	SORCERER {

		public ArrayList<Attack> getListOfAttacks() {

			ArrayList<Attack> attacks = new ArrayList<Attack>();
			attacks.add(new Fire(50, 50));
			attacks.add(new Lightning(50, 50));
			attacks.add(new Water(50, 50));
			attacks.add(new Strike(50, 50));
			return attacks;
		}

		public double getHealth() {

			return 100;
		}

		public int getStepCount() {

			return 10;
		}
	};

	private final ArrayList<Attack> defaultAttacks;
	
	private final double health;
	
	public static final double DEFAULT_HEALTH = 100;
	
	private final int stepCount;
	
	public static final int DEFAULT_STEPCOUNT = 20;

	private ProfilHeroes() {

		this.defaultAttacks = new ArrayList<Attack>();
		this.defaultAttacks.add(new Strike(50, 50));
		this.health = DEFAULT_HEALTH;
		this.stepCount = DEFAULT_STEPCOUNT;
	}

	public ArrayList<Attack> getListOfAttacks() {

		return this.defaultAttacks;
	}
	
	public double getHealth() {

		return this.health;
	}

	public int getStepCount() {

		return this.stepCount;
	}
}
