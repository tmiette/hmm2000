package fr.umlv.hmm2000.warriors.heroes;

import java.util.ArrayList;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.warriors.attacks.Attack;
import fr.umlv.hmm2000.warriors.profils.ProfilHeroes;



public class Sorcerer extends Heroe {

	public Sorcerer(Player player) {

		super(player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Attack> initListOfAttacks() {
	
		return ProfilHeroes.SORCERER.getListOfAttacks();
	}

	@Override
	public double initHealth() {

		return ProfilHeroes.SORCERER.getHealth();
	}

	@Override
	public int initStepCount() {

		return ProfilHeroes.SORCERER.getStepCount();
	}

}
