package fr.umlv.hmm2000.warriors.heroes;

import java.util.ArrayList;

import fr.umlv.hmm2000.warriors.attacks.Attack;
import fr.umlv.hmm2000.warriors.profils.ProfilHeroes;


public class LordOfWar extends Heroe {

	

	public LordOfWar() {

		super();
	}

	@Override
	public double initHealth() {

		return ProfilHeroes.LORD_OF_WAR.getHealth();
	}

	@Override
	public ArrayList<Attack> initListOfAttacks() {

		return ProfilHeroes.LORD_OF_WAR.getListOfAttacks();
	}

	@Override
	public int initStepCount() {

		return ProfilHeroes.LORD_OF_WAR.getStepCount();
	}


}
