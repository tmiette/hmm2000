package fr.umlv.hmm2000.warrior;

import java.util.ArrayList;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.war.BattlePositionMap;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;

public interface Container extends MapForegroundElement {

	public ArrayList<Warrior> getTroop();

	public boolean addWarrior(Warrior w) throws MaxNumberOfTroopsReachedException;

	public void removeWarrior(Fightable w);
	
	public BattlePositionMap getBattlePositionManager();
	
	public Player getPlayer();
	
	public String getLabel();
	
}
