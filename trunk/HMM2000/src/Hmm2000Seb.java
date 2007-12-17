import java.io.FileNotFoundException;
import java.io.IOException;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.gui.LawrenceUIEngine;
import fr.umlv.hmm2000.map.InvalidPlayersNumberException;
import fr.umlv.hmm2000.war.BattleMap;
import fr.umlv.hmm2000.warrior.Heroe;
import fr.umlv.hmm2000.warrior.Level;
import fr.umlv.hmm2000.warrior.WarriorFactory;
import fr.umlv.hmm2000.warrior.profil.ProfilHeroe;

public class Hmm2000Seb {

	public static void main(String[] args) throws FileNotFoundException,
			IOException, InvalidPlayersNumberException {
		LawrenceUIEngine e = new LawrenceUIEngine();
		
		Heroe h1 = WarriorFactory.createHeroe(ProfilHeroe.ARCHER, new Player(1), "batman", Level.LEVEL_1);
		Heroe h2 = WarriorFactory.createHeroe(ProfilHeroe.ARCHER, new Player(2), "superman", Level.LEVEL_1);
		
		e.drawMap(new BattleMap(h1, h2));
	}

}
