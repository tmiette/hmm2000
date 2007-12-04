import java.io.FileNotFoundException;
import java.io.IOException;

import fr.umlv.hmm2000.GoldResource;
import fr.umlv.hmm2000.characters.heroes.Heroe;
import fr.umlv.hmm2000.gui.GameBoard;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.MapBackgroundEnum;
import fr.umlv.hmm2000.map.MapBuilder;

public class Hmm2000 {

	public static void main(String[] args) throws FileNotFoundException,
			IOException {

		MapBackgroundEnum[][] background = {
				{ MapBackgroundEnum.PLAIN, MapBackgroundEnum.PATH,
						MapBackgroundEnum.PATH, MapBackgroundEnum.TREE },
				{ MapBackgroundEnum.PATH, MapBackgroundEnum.WATER,
						MapBackgroundEnum.PATH, MapBackgroundEnum.TREE },
				{ MapBackgroundEnum.MOUNTAIN, MapBackgroundEnum.PLAIN,
						MapBackgroundEnum.PLAIN, MapBackgroundEnum.TREE } };

		Map map = new Map(background);
		map.addMapElement(new Heroe("Batman"), new Location(0, 0));
		map.addMapElement(new GoldResource(), new Location(0, 2));
		Map map2 = MapBuilder.createMap("map/map1.map");
		map2.addMapElement(new Heroe("Batman"), new Location(0, 0));
		map2.addMapElement(new Heroe("SpiderMan"), new Location(1, 2));
		map2.addMapElement(new GoldResource(), new Location(2, 0));
		final GameBoard board = new GameBoard(map2);
		board.display();

	}

}
