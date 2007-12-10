package fr.umlv.hmm2000.warriors.profils;

import java.util.ArrayList;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warriors.attacks.Attack;

public interface ProfilWarrior {

	public ArrayList<Attack> getListOfAttacks();

	public double getHealth();

	public int getSpeed();

	public double getAttackValue();

	public double getDefenseValue();
	
	public Sprite getSprite();
}
