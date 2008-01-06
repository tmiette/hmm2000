package fr.umlv.hmm2000.map;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import fr.umlv.hmm2000.building.Castle;
import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.map.element.MapBackgroundEnum;
import fr.umlv.hmm2000.resource.Resource;
import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.salesentity.SalesEntity;
import fr.umlv.hmm2000.salesentity.SalesEntity.SalesEntityEnum;
import fr.umlv.hmm2000.salesentity.spell.Spell;
import fr.umlv.hmm2000.unit.Hero;
import fr.umlv.hmm2000.unit.UnitFactory;
import fr.umlv.hmm2000.unit.profil.Level;
import fr.umlv.hmm2000.unit.profil.ProfilHero;
import fr.umlv.hmm2000.unit.profil.ProfilMonster;
import fr.umlv.hmm2000.unit.profil.ProfilWarrior;

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

	/**
	 * Gets the world map. The maplevel corresponds to the world map size and the
	 * number of elements on it.
	 * 
	 * @param level
	 *          map level
	 * @return the world map created
	 * @throws FileNotFoundException
	 *           exception thrown if map file is missing
	 * @throws IOException
	 *           exception thrown if a probleme occurs during the file parsing
	 */
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

	/**
	 * Translates a string to a map background elements tableau.
	 * 
	 * @param s
	 *          string to translate
	 * @param line
	 *          map background elements tableau containing characters
	 *          translated
	 */
	private static void decodeLine(String s, MapBackgroundEnum[] line) {

		for (int i = 0; i < s.length(); i++) {
			line[i] = getMapBackgroundEnum(s.charAt(i));
		}
	}

	/**
	 * Gets the map background element corresponding to a given character.
	 * 
	 * @param c
	 * @return
	 */
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

		Castle c1 = new Castle(p1);
		Castle c2 = new Castle(p2);
		map.addMapForegroundElement(c1, new Location(14, 4));
		map.addMapForegroundElement(c2, new Location(14, 5));

		map.addMapForegroundElement(UnitFactory.createMonster(Player.AI,
				ProfilMonster.TROLL), new Location(0, 0));
		map.addMapForegroundElement(
				UnitFactory.createHero(p1, ProfilHero.SORCERER), new Location(14, 3));
		Hero spiderman = UnitFactory.createHero(p2, ProfilHero.LORD_OF_WAR);
		/*
		 * try {
		 * spiderman.addFightable(UnitFactory.createWarrior(ProfilWarrior.FLIGHT,
		 * Level.LEVEL_1));
		 * spiderman.addFightable(UnitFactory.createWarrior(ProfilWarrior.FLIGHT,
		 * Level.LEVEL_3));
		 * spiderman.addFightable(UnitFactory.createWarrior(ProfilWarrior.WIZZARD,
		 * Level.LEVEL_3)); } catch (MaxNumberOfTroopsReachedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

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
		b.addProduct(UnitFactory
				.createWarrior(ProfilWarrior.WIZZARD, Level.LEVEL_3), 1);
		b.addProduct(
				UnitFactory.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_2), 2);
		map.addMapForegroundElement(b, new Location(2, 5));

		return map;
	}

}