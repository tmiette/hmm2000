package fr.umlv.hmm2000.engine.manager;

import java.util.ArrayList;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.Engine;
import fr.umlv.hmm2000.map.element.MapForegroundElement;

public class RoundManager {

  private Player currentPlayer;

  private int currentPlayerIndex;

  private int currentDay;

  private final ArrayList<Player> players;

  public RoundManager(Player... players) {
    this.players = new ArrayList<Player>();
    for (Player p : players) {
      this.players.add(p);
    }
    this.currentPlayerIndex = 0;
    this.currentPlayer = this.players.get(0);
    this.currentDay = 1;
  }

  public Player currentPlayer() {
    return this.currentPlayer;
  }

  public int currentDay() {
    return this.currentDay;
  }

  public void nextDay() {
    this.currentPlayerIndex++;
    if (this.currentPlayerIndex == this.players.size()) {
      this.currentPlayerIndex = 0;
      this.currentDay++;
      for (MapForegroundElement element : Engine.currentEngine().map()
          .getMapForegroundElements()) {
        element.nextDay(this.currentDay);
      }
    }
    this.currentPlayer = this.players.get(this.currentPlayerIndex);
  }

  public boolean isCurrentPlayer(Player player) {
    return this.currentPlayer.equals(player);
  }

}
