package fr.umlv.hmm2000.warrior;

import fr.umlv.hmm2000.warrior.attack.elementary.ElementaryEnum;



public interface Attack {

	public double getDamage();

	public double getResistance();

	public ElementaryEnum getType();
}
