package fr.umlv.hmm2000.warrior;

import java.util.List;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.war.BattlePositionMap;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;

public interface FightableContainer extends MapForegroundElement {

	public static final int PRIORITY_HIGH = 1;
	public static final int PRIORITY_MEDIUM = 2;
	public static final int PRIORITY_LOW = 4;

  public static final int MAX_TROOP_SIZE = 12;
  
  public List<Fightable> getTroop();

  public boolean addFightable(Fightable f) throws MaxNumberOfTroopsReachedException;

  public void removeFightable(Fightable f);

  public BattlePositionMap getBattlePositionManager();

  public Player getPlayer();
  
  public void setPlayer(Player player);
  
  public int getAttackPriority();
  
}
