package fr.umlv.hmm2000.warriors.creatures;

import java.util.ArrayList;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.warriors.Warrior;
import fr.umlv.hmm2000.warriors.attacks.Attack;

public class Creature extends Warrior {

	public Creature(Player player) {

		super(player);
	}

	@Override
	public double initHealth() {

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Attack> initListOfAttacks() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int initStepCount() {

		// TODO Auto-generated method stub
		return 0;
	}
}
