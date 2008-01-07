package fr.umlv.hmm2000.engine.manager;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.map.element.MapForegroundElement;

/**
 * This class is the manager of the days.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class DayCoreManager {

  private Player currentPlayer;

  private int currentPlayerIndex;

  private int currentDay;

  private final ArrayList<Player> players;

  /**
   * Constructor of the days manager.
   * 
   * @param players
   *            number of players in the game.
   */
  public DayCoreManager(Player... players) {
    this.players = new ArrayList<Player>();
    for (Player p : players) {
      this.players.add(p);
    }
    this.currentPlayerIndex = 0;
    this.currentPlayer = this.players.get(0);
    this.currentDay = 1;
  }

  /**
   * Returns the current player.
   * 
   * @return the current player.
   */
  public Player currentPlayer() {
    return this.currentPlayer;
  }

  /**
   * Returns the current day number.
   * 
   * @return the current day number.
   */
  public int currentDay() {
    return this.currentDay;
  }

  /**
   * Go to the following day.
   */
  public void nextDay() {
    this.nextPlayer();
    if (this.currentPlayerIndex == 0) {
      CoreEngine.fireMessage("Next day.", HMMUserInterface.INFO_MESSAGE);
      for (MapForegroundElement element : CoreEngine.map()
          .getMapForegroundElements()) {
        element.nextDay(this.currentDay);
      }
    }
  }

  /**
   * Returns if a player is the current player.
   * 
   * @param player
   *            the player.
   * @return if a player is the current player.
   */
  public boolean isCurrentPlayer(Player player) {
    return this.currentPlayer.equals(player);
  }

  /**
   * Go to the next player.
   */
  protected void nextPlayer() {
    this.currentPlayerIndex++;
    if (this.currentPlayerIndex == this.players.size()) {
      this.currentPlayerIndex = 0;
      this.currentDay++;
    }
    this.currentPlayer = this.players.get(this.currentPlayerIndex);
  }

  /**
   * Removes a player from the day manager when it looses.
   * 
   * @param player
   *            the player.
   */
  public void removePlayer(Player player) {
    if (isCurrentPlayer(player)) {
      this.nextDay();
      this.players.remove(player);
    } else {
      this.players.remove(player);
    }
  }

  /**
   * Returns the list of remaining players.
   * 
   * @return the list of remaining players.
   */
  public List<Player> getPlayers() {
    return this.players;
  }
}
