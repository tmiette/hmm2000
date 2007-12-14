package fr.umlv.hmm2000.warrior;

import java.util.ArrayList;

import fr.umlv.hmm2000.war.BattlePosition;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;

public interface Container {

	public ArrayList<Warrior> getTroop();

	public boolean addWarrior(Warrior w) throws MaxNumberOfTroopsReachedException;

	public void removeWarrior(Warrior w);
	
	public BattlePosition getBattlePositionManager();
}
