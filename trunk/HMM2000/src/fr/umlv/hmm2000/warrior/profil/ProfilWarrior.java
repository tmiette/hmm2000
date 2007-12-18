package fr.umlv.hmm2000.warrior.profil;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warrior.ElementAbility;
import fr.umlv.hmm2000.warrior.Warrior;

public interface ProfilWarrior extends Profil {

	public double getHealth();

	public int getSpeed();

	public double getAttackValue();

	public double getDefenseValue();
	
	public Sprite getSprite();
	
	public boolean isAttackable(Warrior attacker, Warrior defender);
	
	public String getLabel();
	
	public ElementAbility getAbilities();
}
