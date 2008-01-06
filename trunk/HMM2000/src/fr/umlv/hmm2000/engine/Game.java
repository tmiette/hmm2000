package fr.umlv.hmm2000.engine;

import java.util.ArrayList;

import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.unit.FightableContainer;


public class Game {

	private final ArrayList<Player> players;
	
	
	public Game(Player[] players) {

		this.players = new ArrayList<Player>(players.length);
		for (int i = 0; i < players.length; i++) {
			this.players.add(players[i]);
		}
	}
	
	public void playerLost(Player player) {

		removeAllPlayerUnitsFromMap(player, CoreEngine.map());
		CoreEngine.fireMessage(player + "lost.", HMMUserInterface.INFO_MESSAGE);
		this.players.remove(players);
		if (this.players.size() == 1) {
			this.playerWon(this.players.get(0));
		}
	}
	
	private void playerWon(Player player) {

		CoreEngine.fireMessage(player + " won the game !", HMMUserInterface.INFO_MESSAGE);
		//TODO on fait quoi une fois gagne
	}
	
	private void removeAllPlayerUnitsFromMap(Player player, Map map) {

		Location location = null;
		for (int x = 0; x < map.getHeight(); x++) {
			for (int y = 0; y < map.getWidth(); y++) {
				location = new Location(x, y);
				MapForegroundElement mfe = map.getMapForegroundElementAtLocation(location);
				if (mfe instanceof FightableContainer) {
					FightableContainer f = (FightableContainer)mfe;
					if (f.getPlayer().equals(player)) {
						map.removeMapForegroundElement(location);
					}
				}
			}
		}
	}
}
