import java.io.FileNotFoundException;
import java.io.IOException;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.Engine;
import fr.umlv.hmm2000.gui.LawrenceUIEngine;
import fr.umlv.hmm2000.map.InvalidPlayersNumberException;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.MapBuilder;
import fr.umlv.hmm2000.map.MapLevel;
import fr.umlv.hmm2000.resource.Resource;
import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.salesentity.SalesEntity;
import fr.umlv.hmm2000.salesentity.SalesEntity.SalesEntityEnum;
import fr.umlv.hmm2000.salesentity.spell.Spell;
import fr.umlv.hmm2000.warrior.Heroe;
import fr.umlv.hmm2000.warrior.Level;
import fr.umlv.hmm2000.warrior.WarriorFactory;
import fr.umlv.hmm2000.warrior.profil.ProfilCreatures;
import fr.umlv.hmm2000.warrior.profil.ProfilHeroe;

public class Hmm2000Seb {

	public static void main(String[] args) throws FileNotFoundException,
			IOException, InvalidPlayersNumberException {

		Map map = MapBuilder.createMap(MapLevel.MAP1);

		Heroe h1 = WarriorFactory.createHeroe(ProfilHeroe.ARCHER,
																					new Player(1),
																					"batman",
																					Level.LEVEL_1);
		h1.addWarrior(WarriorFactory.createWarrior(	ProfilCreatures.FLIGHT,
																								h1.getPlayer(),
																								Level.LEVEL_1));
		h1.addWarrior(WarriorFactory.createWarrior(	ProfilCreatures.FLIGHT,
																								h1.getPlayer(),
																								Level.LEVEL_1));
		h1.addWarrior(WarriorFactory.createWarrior(	ProfilCreatures.FLIGHT,
																								h1.getPlayer(),
																								Level.LEVEL_1));

		Heroe h2 = WarriorFactory.createHeroe(ProfilHeroe.LORD_OF_WAR,
																					new Player(2),
																					"spiderman",
																					Level.LEVEL_1);
		h2.addWarrior(WarriorFactory.createWarrior(	ProfilCreatures.FLIGHT,
																								h2.getPlayer(),
																								Level.LEVEL_1));
		h2.addWarrior(WarriorFactory.createWarrior(	ProfilCreatures.FLIGHT,
																								h2.getPlayer(),
																								Level.LEVEL_1));
		h2.addWarrior(WarriorFactory.createWarrior(	ProfilCreatures.FLIGHT,
																								h2.getPlayer(),
																								Level.LEVEL_1));

		map.addMapForegroundElement(h1,
																new Location(	0,
																							0));
		map.addMapForegroundElement(h2,
																new Location(	1,
																							2));
		map.addMapForegroundElement(new Resource(	Kind.GOLD,
																							10,
																							100,
																							1,
																							5),
																new Location(	2,
																							0));

		SalesEntity m = new SalesEntity(SalesEntityEnum.MERCHANT);
		m.addProduct(	Spell.TELEPORTATION,
									2);
		m.addProduct(	Spell.OBSTACLE_DESTRUCTION,
									1);
		map.addMapForegroundElement(m,
																new Location(	2,
																							2));

		SalesEntity b = new SalesEntity(SalesEntityEnum.BARRACKS);
		b.addProduct(	Spell.TELEPORTATION,
									1);
		map.addMapForegroundElement(b,
																new Location(	2,
																							5));

		return map;

		Engine.startNewEngine(MapLevel.MAP1,
													new LawrenceUIEngine(),
													new Player(1),
													new Player(2));
	}
}
