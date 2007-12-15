package fr.umlv.hmm2000.warrior.profil;

import java.util.HashMap;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warrior.Warrior;
import fr.umlv.hmm2000.warrior.attack.elementary.Element;
import fr.umlv.hmm2000.warrior.attack.elementary.ElementaryEnum;

public interface ProfilWarrior {

	public double getHealth();

	public int getSpeed();

	public double getAttackValue();

	public double getDefenseValue();
	
	public Sprite getSprite();
	
	public HashMap<ElementaryEnum, Element> getElements();
	
	public void attack(Warrior warrior, Element attack);
}
