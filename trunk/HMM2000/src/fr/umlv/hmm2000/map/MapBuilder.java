package fr.umlv.hmm2000.map;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.map.element.MapBackgroundEnum;
import fr.umlv.hmm2000.resource.Resource;
import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.salesentity.SalesEntity;
import fr.umlv.hmm2000.salesentity.SalesEntity.SalesEntityEnum;
import fr.umlv.hmm2000.salesentity.spell.Spell;
import fr.umlv.hmm2000.warrior.Level;
import fr.umlv.hmm2000.warrior.WarriorFactory;
import fr.umlv.hmm2000.warrior.profil.ProfilHeroe;

public class MapBuilder {

  public static MainMap createMap(MapLevel level) throws FileNotFoundException,
      IOException {

    LineNumberReader lnr = new LineNumberReader(new FileReader(level
        .getMapFile()));
    String s;

    s = lnr.readLine();
    int nbRows = Integer.parseInt(s);
    s = lnr.readLine();
    int nbColumns = Integer.parseInt(s);

    MapBackgroundEnum[][] background = new MapBackgroundEnum[nbRows][nbColumns];

    while ((s = lnr.readLine()) != null) {
      decodeLine(s, background[lnr.getLineNumber() - 3]);
    }

    return new MainMap(background);
  }

  private static void decodeLine(String s, MapBackgroundEnum[] line) {
    for (int i = 0; i < s.length(); i++) {
      line[i] = getMapBackgroundEnum(s.charAt(i));
    }
  }

  private static MapBackgroundEnum getMapBackgroundEnum(char c) {
    switch (c) {
    case 'M':
      return MapBackgroundEnum.MOUNTAIN;
    case 'P':
      return MapBackgroundEnum.PATH;
    case 'L':
      return MapBackgroundEnum.PLAIN;
    case 'T':
      return MapBackgroundEnum.TREE;
    case 'W':
      return MapBackgroundEnum.WATER;
    default:
      return null;
    }
  }

  public static MainMap createMapTESTVERSION(MapLevel level, Player p1, Player p2)
      throws FileNotFoundException, IOException {

    MainMap map = createMap(level);

    map.addMapForegroundElement(WarriorFactory.createWarrior(
        ProfilHeroe.ARCHER, p1, "batman", Level.LEVEL_1), new Location(0, 0));
    map.addMapForegroundElement(WarriorFactory.createWarrior(
        ProfilHeroe.LORD_OF_WAR, p2, "spiderman", Level.LEVEL_1), new Location(
        1, 2));
    map.addMapForegroundElement(new Resource(Kind.GOLD, 10, 100, 1, 5,
        Resource.NON_RELOADABLE), new Location(2, 0));
    map.addMapForegroundElement(new Resource(Kind.GOLD, 30, 1000, 7, 30,
        Resource.RELOADABLE), new Location(2, 6));
    
    SalesEntity m = new SalesEntity(SalesEntityEnum.MERCHANT);
    m.addProduct(Spell.TELEPORTATION, 2);
    m.addProduct(Spell.OBSTACLE_DESTRUCTION, 1);
    map.addMapForegroundElement(m, new Location(2, 2));

    SalesEntity b = new SalesEntity(SalesEntityEnum.BARRACKS);
    b.addProduct(Spell.TELEPORTATION, 1);
    map.addMapForegroundElement(b, new Location(2, 5));

    return map;
  }

}
