package fr.umlv.hmm2000.map.builder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.MapLevel;
import fr.umlv.hmm2000.map.element.MapBackgroundEnum;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.util.Pair;

/**
 * This class enables to save a map.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class MapSaver {

  /**
   * Save a map to a save file.
   * 
   * @param level
   *            the map level.
   * @param map
   *            the map.
   * @param players
   *            players remaining.
   * @throws IOException
   *             if the save operation failed.
   */
  public static void save(MapLevel level, Map map, List<Player> players)
      throws IOException {

    // Creates the save file
    File f = new File("./map/sav/" + level.name() + "-" + players.size()
        + ".sav");
    f.createNewFile();
    BufferedWriter buff = new BufferedWriter(new FileWriter(f));

    // Writes game informations
    buff.write((String.valueOf(map.getHeight())));
    buff.write('\n');
    buff.write((String.valueOf(map.getWidth())));
    buff.write('\n');

    // Writes background information
    for (int i = 0; i < map.getHeight(); i++) {
      for (int j = 0; j < map.getWidth(); j++) {
        buff.write(CharacterTranslator
            .encodeMapBackgroundEnum((MapBackgroundEnum) map
                .getMapBackgroundElementAtLocation(new Location(i, j))));
      }
      buff.write('\n');
    }

    // Write player informations
    for (Player player : players) {
      buff.write("P;-1;-1;" + player.getId());
      for (Pair<Kind, Integer> pair : player.getResources()
          .notNullResourceList()) {
        buff.write(";"
            + CharacterTranslator.encodeResourceKind(pair.getFirstElement())
            + "," + pair.getSecondElement());
      }
      buff.write('\n');
    }

    // Write foreground informations
    for (MapForegroundElement element : map.getMapForegroundElements()) {
      buff.write(element.getSaveString());
      buff.write('\n');
    }
    buff.flush();
    buff.close();
  }
}
