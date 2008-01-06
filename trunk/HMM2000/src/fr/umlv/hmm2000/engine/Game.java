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
   *            who lost game
   */
  public void loose(Player player) {
    // Removing all units from map (sprites included)
    removeAllPlayerFightableContainer(player, CoreEngine.map());
    // Displaying lost message
    CoreEngine.fireMessage(player + " looses the game.",
        HMMUserInterface.WARNING_MESSAGE);
    this.players.remove(player);
    CoreEngine.roundManager().removePlayer(player);
    // Player wins
    if (this.players.size() == 1) {
      this.win(this.players.get(0));
    }
  }

  /**
   * Manages the behaviour of player who wins game.
   * 
   * @param player
   *            who wins
   */
  private void win(Player player) {
    CoreEngine.fireMessage(player + " wins the game !",
        HMMUserInterface.WARNING_MESSAGE);
  }

  /**
   * Removes all looser units from map.
   * 
   * @param player
   *            looser
   * @param map
   *            to clean
   */
  private void removeAllPlayerFightableContainer(Player player, Map map) {
    for (MapForegroundElement element : map.getMapForegroundElements()) {
      if (element instanceof FightableContainer) {
        FightableContainer f = (FightableContainer) element;
        if (f.getPlayer().equals(player)) {
          Location l = CoreEngine.map().getLocationForMapForegroundElement(f);
          CoreEngine.map().removeMapForegroundElement(l);
          CoreEngine.fireSpriteRemoved(l, f.getSprite());
        }
      }
    }
  }
}
