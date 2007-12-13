package fr.umlv.hmm2000.warriors;

import java.util.ArrayList;

import fr.umlv.hmm2000.war.BattlePositionManager;
import fr.umlv.hmm2000.warriors.exception.MaxNumberOfTroopsReachedException;

public interface Container {

	public ArrayList<Warrior> getTroop();

	public void addWarrior(Warrior w) throws MaxNumberOfTroopsReachedException;

	public void removeWarrior(Warrior w);
	
	public BattlePositionManager getBattlePositionManager();
}
