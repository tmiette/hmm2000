package fr.umlv.hmm2000.warriors.profils;

import fr.umlv.hmm2000.gui.Sprite;

public interface ProfilWarrior {

	public double getHealth();

	public int getSpeed();

	public double getAttackValue();

	public double getDefenseValue();
	
	public Sprite getSprite();
}
