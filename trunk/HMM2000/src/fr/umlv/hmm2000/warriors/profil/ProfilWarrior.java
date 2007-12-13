package fr.umlv.hmm2000.warriors.profil;

import java.util.HashMap;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warriors.element.Element;
import fr.umlv.hmm2000.warriors.element.ElementEnum;

public interface ProfilWarrior {

	public double getHealth();

	public int getSpeed();

	public double getAttackValue();

	public double getDefenseValue();
	
	public Sprite getSprite();
	
	public HashMap<ElementEnum, Element> getElements();
}
