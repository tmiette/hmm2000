package fr.umlv.hmm2000.map;


public class InvalidPlayersNumberException extends Exception {

  private static final long serialVersionUID = 5755943569723380902L;

  public InvalidPlayersNumberException(MapLevel level, int nbPlayers) {
    super("The number of players " + nbPlayers + " is invalid for the map "
        + level.getName() + ".\nThis map require between "
        + level.getMinPlayerNumber() + " and " + level.getMaxPlayerNumber()
        + " player(s).");
  }

}
