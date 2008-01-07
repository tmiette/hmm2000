package fr.umlv.hmm2000.map.builder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import fr.umlv.hmm2000.building.Castle;
import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.MapLevel;
import fr.umlv.hmm2000.map.WorldMap;
import fr.umlv.hmm2000.map.element.MapBackgroundEnum;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.unit.FightableContainer;
import fr.umlv.hmm2000.unit.Hero;

/**
 * This class permits to build a world map. This map is the default game map. It
 * is created by decoding a file which contains specifics characters
 * representing map background elements.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class MapBuilder {

  private static Player[] players;
  public static final ResourceInitializer resourcesInitializer = new ResourceInitializer();
  public static final CastleInitializer castleInitializer = new CastleInitializer();
  public static final SalesEntityInitializer salesInitializer = new SalesEntityInitializer();
  public static final MonsterInitializer monsterInitializer = new MonsterInitializer();
  public static final HeroInitializer heroInitializer = new HeroInitializer();

  /**
   * Gets the world map. The maplevel corresponds to the world map size and the
   * number of elements on it.
   * 
   * @param level
   *            map level
   * @param mapFile
   *            the map file.
   * @param players
   *            players of the game.
   * @return the world map created
   * @throws FileNotFoundException
   *             exception thrown if map file is missing
   * @throws IOException
   *             exception thrown if a problem occurs during the file parsing
   */
  public static WorldMap createMap(MapLevel level, String mapFile,
      Player... players) throws FileNotFoundException, IOException {

    MapBuilder.players = players;
    LineNumberReader lnr = new LineNumberReader(new FileReader(mapFile));
    String s;

    // Gets the width and the height of the map
    s = lnr.readLine();
    int nbRows = Integer.parseInt(s);
    s = lnr.readLine();
    int nbColumns = Integer.parseInt(s);

    // Initialize the map with background
    WorldMap map = new WorldMap(initMapMapBackgroundElements(lnr, nbRows,
        nbColumns));

    // Add all foreground elements to the map
    while ((s = lnr.readLine()) != null) {
      decodeDataLine(lnr, map, s.split(";"));
    }

    return map;
  }

  /**
   * Set the player of a fightable container if it exists.
   * 
   * @param c
   *            the fightable container.
   * @param player
   *            the id of the player.
   * @return if the player exists.
   */
  private static boolean setPlayer(FightableContainer c, int player) {
    if (players != null && player >= 0 && player < players.length) {
      c.setPlayer(players[player]);
      return true;
    }
    return false;
  }

  /**
   * Parses the map file to initialize background elements matrix of the map.
   * 
   * @param lnr
   *            the line reader.
   * @param line
   *            width of the map.
   * @param column
   *            height of the map.
   * @return the background elements matrix of the map.
   * @throws IOException
   *             if a problem occurs during the parsing.
   */
  private static MapBackgroundEnum[][] initMapMapBackgroundElements(
      LineNumberReader lnr, int line, int column) throws IOException {
    MapBackgroundEnum[][] background = new MapBackgroundEnum[line][column];
    String s;
    int counter = 0;
    while (counter != line) {
      if ((s = lnr.readLine()) != null) {
        decodeBackgroundLine(s, background[counter]);
        counter++;
      }
    }
    return background;
  }

  /**
   * Translates a string to a map background elements tableau.
   * 
   * @param s
   *            string to translate
   * @param line
   *            map background elements tableau containing characters translated
   */
  private static void decodeBackgroundLine(String s, MapBackgroundEnum[] line) {
    for (int i = 0; i < s.length(); i++) {
      line[i] = CharacterTranslator.decodeMapBackgroundEnum(s.charAt(i));
    }
  }

  /**
   * Decodes and adds a foreground element to the map.
   * 
   * @param lnr
   *            the line reader.
   * @param map
   *            the map.
   * @param line
   *            the line to read.
   */
  private static void decodeDataLine(LineNumberReader lnr, WorldMap map,
      String[] line) {
    // Tests if the line is valid
    if (line.length >= 4) {
      try {
        // Init elements parameters
        MapForegroundElement e = null;
        int x = Integer.parseInt(line[1]);
        int y = Integer.parseInt(line[2]);
        int p = Integer.parseInt(line[3]);
        String[] data = new String[line.length - 4];
        for (int i = 0; i < data.length; i++) {
          data[i] = line[i + 4];
        }

        // Init element depending on its class
        switch (line[0].charAt(0)) {
        case 'R':
          e = resourcesInitializer.initialize(lnr, data);
          break;
        case 'C':
          Castle c = castleInitializer.initialize(lnr, data);
          if (setPlayer(c, p)) {
            e = c;
          }
          break;
        case 'S':
          e = salesInitializer.initialize(lnr, data);
          break;
        case 'M':
          e = monsterInitializer.initialize(lnr, data);
          break;
        case 'H':
          Hero h = heroInitializer.initialize(lnr, data);
          if (setPlayer(h, p)) {
            e = h;
          }
          break;
        case 'P':
          try {
            if (players != null && p >= 0 && p < players.length) {
              for (int i = 0; i < data.length; i++) {
                String[] subData = data[i].split(",");
                if (subData.length >= 2) {
                  players[p].addResource(CharacterTranslator
                      .decodeResourceKind(subData[0].charAt(0)), Integer
                      .parseInt(subData[1]));
                }
              }
            }
          } catch (IndexOutOfBoundsException e1) {
          } catch (NumberFormatException e1) {
            new IllegalArgumentException("Syntax error on line "
                + lnr.getLineNumber() + ".", e1);
          }
        default:
          break;
        }

        // Adds the foreground element
        if (e != null && x >= 0 && x < map.getHeight() && y >= 0
            && y < map.getWidth()) {
          Location l = new Location(x, y);
          if (map.getMapBackgroundElementAtLocation(l).getWeight() != 0
              && map.getMapForegroundElementAtLocation(l) == null) {
            map.addMapForegroundElement(e, new Location(x, y));
          }
        }
      } catch (NumberFormatException e) {
        new IllegalArgumentException("Syntax error on line "
            + lnr.getLineNumber() + ".", e);
      }
    }
  }
}
