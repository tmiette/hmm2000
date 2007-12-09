import java.io.FileNotFoundException;
import java.io.IOException;

import fr.umlv.hmm2000.Heroe;
import fr.umlv.hmm2000.Merchant;
import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.Resource;
import fr.umlv.hmm2000.Spell;
import fr.umlv.hmm2000.Resource.Kind;
import fr.umlv.hmm2000.gui.GameBoard;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.MapBuilder;

public class Hmm2000 {

	public static void main(String[] args) throws FileNotFoundException,
			IOException {

		Player p1 = new Player(1);
		Player p2 = new Player(2);
		Map map2 = MapBuilder.createMap("map/map1.map");
		map2.addMapForegroundElement(new Heroe(p1, "Batman"),
				new Location(0, 0));
		map2.addMapForegroundElement(new Heroe(p2, "SpiderMan"), new Location(
				1, 2));
		map2.addMapForegroundElement(new Resource(Kind.GOLD, 10, 100, 1, 5),
				new Location(2, 0));
		Merchant m = new Merchant("Robert");
		m.addSpell(Spell.TELEPORTATION, 2);
		m.addSpell(Spell.OBSTACLE_DESTRUCTION, 1);
		map2.addMapForegroundElement(m, new Location(2, 2));
		final GameBoard board = new GameBoard(map2);
		board.display();

	}

}
