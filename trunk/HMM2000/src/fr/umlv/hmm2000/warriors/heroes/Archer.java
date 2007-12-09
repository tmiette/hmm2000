package fr.umlv.hmm2000.warriors.heroes;

import java.util.ArrayList;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.warriors.attacks.Attack;
import fr.umlv.hmm2000.warriors.profils.ProfilHeroes;

public class Archer extends Heroe {

	public Archer(Player player) {

		super(player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Attack> initListOfAttacks() {

		return ProfilHeroes.ARCHER.getListOfAttacks();
	}

	@Override
	public double initHealth() {

		return ProfilHeroes.ARCHER.getHealth();
	}

	@Override
	public int initStepCount() {

		return ProfilHeroes.ARCHER.getStepCount();
	}
}
