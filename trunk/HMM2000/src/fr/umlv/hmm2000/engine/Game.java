package fr.umlv.hmm2000.engine;

import java.util.ArrayList;

import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.unit.FightableContainer;

/**
 * This class manages which player win the game. When a player lost, this class
 * get the responsability to delete its units from the map.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class Game {

	// Game players
	private final ArrayList<Player> players;

	public Game(Player[] players) {

		this.players = new ArrayList<Player>(players.length);
		for (int i = 0; i < players.length; i++) {
			this.players.add(players[i]);
		}
	}

	/**
	 * Displays message to player who lost. If one player left in game, he wins.
	 * 
	 * @param player
	 *          who lost game
	 */
	public void playerLost(Player player) {

		// Removing all units from map (sprites included)
		removeAllPlayerUnitsFromMap(player, CoreEngine.map());
		// Displaying lost message
		CoreEngine.fireMessage(player + "lost.", HMMUserInterface.INFO_MESSAGE);
		this.players.remove(player);
		// Player wins
		if (this.players.size() == 1) {
			this.playerWon(this.players.get(0));
		}
	}

	/**
	 * Manages the behaviour of player who wins game.
	 * 
	 * @param player
	 *          who wins
	 */
	private void playerWon(Player player) {

		CoreEngine.fireMessage(player + " won the game !",
				HMMUserInterface.INFO_MESSAGE);
		// TODO on fait quoi une fois gagne
	}

	/**
	 * Removes all looser units from map.
	 * @param player looser
	 * @param map to clean
	 */
	private void removeAllPlayerUnitsFromMap(Player player, Map map) {

		Location location = null;
		for (int x = 0; x < map.getHeight(); x++) {
			for (int y = 0; y < map.getWidth(); y++) {
				location = new Location(x, y);
				MapForegroundElement mfe = map
						.getMapForegroundElementAtLocation(location);
				if (mfe instanceof FightableContainer) {
					FightableContainer f = (FightableContainer) mfe;
					if (f.getPlayer().equals(player)) {
						CoreEngine.endBattle(null, f);
					}
				}
			}
		}
	}
}
