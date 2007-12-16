package fr.umlv.hmm2000.warrior;

import java.util.ArrayList;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.war.BattlePositionMap;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.warrior.profil.Profil;

public interface Container extends Profil {

	public ArrayList<Warrior> getTroop();

	public boolean addWarrior(Warrior w) throws MaxNumberOfTroopsReachedException;

	public void removeWarrior(Warrior w);
	
	public BattlePositionMap getBattlePositionManager();
	
	public Player getPlayer();
	
	public String getLabel();
}
