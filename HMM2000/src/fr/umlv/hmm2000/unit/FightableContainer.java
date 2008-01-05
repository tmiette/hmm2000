package fr.umlv.hmm2000.unit;

import java.util.List;

import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.map.battle.BattlePositionMap;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.unit.exception.MaxNumberOfTroopsReachedException;

public interface FightableContainer extends MapForegroundElement {

    // attack priorities constants
    public static final int PRIORITY_HIGH = 8;
    public static final int PRIORITY_MEDIUM = 4;
    public static final int PRIORITY_LOW = 2;
    public static final int PRIORITY_VERY_LOW = 1;
    
    public static final int MAX_TROOP_SIZE = 12;

    public List<Fightable> getTroop();

    public boolean addFightable(Fightable f)
	    throws MaxNumberOfTroopsReachedException;

    public void removeFightable(Fightable f);

    public BattlePositionMap getBattlePositionManager();

    public Player getPlayer();

    public void setPlayer(Player player);

    public int getAttackPriority();

}
