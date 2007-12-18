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
import fr.umlv.hmm2000.warrior.Heroe;
import fr.umlv.hmm2000.warrior.Level;
import fr.umlv.hmm2000.warrior.WarriorFactory;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.warrior.profil.ProfilCreatures;
import fr.umlv.hmm2000.warrior.profil.ProfilHeroe;

public class MapBuilder {

  public static WorldMap createMap(MapLevel level)
      throws FileNotFoundException, IOException {

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

    return new WorldMap(background);
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

  public static WorldMap createMapTESTVERSION(MapLevel level, Player p1,
      Player p2) throws FileNotFoundException, IOException {

    WorldMap map = createMap(level);

    map.addMapForegroundElement(WarriorFactory.createHeroe(ProfilHeroe.ARCHER,
        p1, "batman", Level.LEVEL_1), new Location(0, 0));
    map.addMapForegroundElement(WarriorFactory.createHeroe(ProfilHeroe.SORCERER,
        p1, "batman", Level.LEVEL_1), new Location(14, 3));
    Heroe spiderman = WarriorFactory.createHeroe(ProfilHeroe.LORD_OF_WAR, p2,
        "spiderman", Level.LEVEL_1);
    try {
      spiderman.addWarrior(WarriorFactory.createWarrior(ProfilCreatures.FLIGHT,
          p2, Level.LEVEL_1));
      spiderman.addWarrior(WarriorFactory.createWarrior(ProfilCreatures.FLIGHT,
          p2, Level.LEVEL_3));
      spiderman.addWarrior(WarriorFactory.createWarrior(ProfilCreatures.WIZZARD,
          p2, Level.LEVEL_3));
    } catch (MaxNumberOfTroopsReachedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    map.addMapForegroundElement(spiderman, new Location(1, 2));

    map.addMapForegroundElement(new Resource(Kind.GOLD, 10, 100, 1, 5,
        Resource.NON_RELOADABLE), new Location(2, 0));
    map.addMapForegroundElement(new Resource(Kind.GOLD, 30, 1000, 7, 30,
        Resource.RELOADABLE), new Location(2, 6));

    SalesEntity m = new SalesEntity(SalesEntityEnum.MERCHANT);
    m.addProduct(Spell.TELEPORTATION, 2);
    m.addProduct(Spell.OBSTACLE_DESTRUCTION, 1);
    map.addMapForegroundElement(m, new Location(2, 2));

    SalesEntity b = new SalesEntity(SalesEntityEnum.BARRACKS);
    b.addProduct(WarriorFactory.createWarrior(ProfilCreatures.WIZZARD,
        null, Level.LEVEL_3), 1);
    b.addProduct(WarriorFactory.createWarrior(ProfilCreatures.FLIGHT,
        null, Level.LEVEL_2), 2);
    map.addMapForegroundElement(b, new Location(2, 5));

    return map;
  }

}
