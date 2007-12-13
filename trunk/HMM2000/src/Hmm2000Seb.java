import java.io.FileNotFoundException;
import java.io.IOException;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.Engine;
import fr.umlv.hmm2000.gui.LawrenceUIEngine;
import fr.umlv.hmm2000.map.InvalidPlayersNumberException;
import fr.umlv.hmm2000.map.MapLevel;

public class Hmm2000Seb {

  public static void main(String[] args) throws FileNotFoundException,
      IOException, InvalidPlayersNumberException {
    Engine.startNewEngine(MapLevel.MAP1, new LawrenceUIEngine(), new Player(1),
        new Player(2));
  }
}
