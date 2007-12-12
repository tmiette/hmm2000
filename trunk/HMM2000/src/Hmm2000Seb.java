import java.io.FileNotFoundException;
import java.io.IOException;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.Engine;
import fr.umlv.hmm2000.engine.guiinterface.UIEngine;
import fr.umlv.hmm2000.gui.GameBoard;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.MapBuilder;
import fr.umlv.hmm2000.resource.Resource;
import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.salesentity.SalesEntity;
import fr.umlv.hmm2000.salesentity.SalesEntity.SalesEntityEnum;
import fr.umlv.hmm2000.salesentity.spell.Spell;
import fr.umlv.hmm2000.warriors.WarriorFactory;
import fr.umlv.hmm2000.warriors.profils.ProfilHeroe;

public class Hmm2000Seb {

  public static void main(String[] args) throws FileNotFoundException,
      IOException {

    Player p1 = new Player(1);
    Player p2 = new Player(2);
    Map map2 = MapBuilder.createMap("map/map1.map");
    map2.addMapForegroundElement(WarriorFactory.createWarrior(
        ProfilHeroe.ARCHER, p1, "batman"), new Location(0, 0));
    map2.addMapForegroundElement(WarriorFactory.createWarrior(
        ProfilHeroe.LORD_OF_WAR, p2, "spiderman"), new Location(1, 2));
    map2.addMapForegroundElement(new Resource(Kind.GOLD, 10, 100, 1, 5),
        new Location(2, 0));

    SalesEntity m = new SalesEntity(SalesEntityEnum.MERCHANT);
    m.addProduct(Spell.TELEPORTATION, 2);
    m.addProduct(Spell.OBSTACLE_DESTRUCTION, 1);
    map2.addMapForegroundElement(m, new Location(2, 2));

    SalesEntity b = new SalesEntity(SalesEntityEnum.BARRACKS);
    b.addProduct(Spell.TELEPORTATION, 1);
    map2.addMapForegroundElement(b, new Location(2, 5));

    UIEngine board = new GameBoard(map2);
    Engine.createEngine(map2, board.uiEventManager());
    board.start();

  }

}
