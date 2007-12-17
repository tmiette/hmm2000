import java.io.FileNotFoundException;
import java.io.IOException;

import javax.print.attribute.standard.MediaSize.Engineering;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.gui.LawrenceUIEngine;
import fr.umlv.hmm2000.map.InvalidPlayersNumberException;
import fr.umlv.hmm2000.map.MapLevel;
import fr.umlv.hmm2000.war.BattleMap;
import fr.umlv.hmm2000.warrior.Heroe;
import fr.umlv.hmm2000.warrior.Level;
import fr.umlv.hmm2000.warrior.WarriorFactory;
import fr.umlv.hmm2000.warrior.profil.ProfilHeroe;

public class Hmm2000Seb {

	public static void main(String[] args) throws FileNotFoundException,
			IOException, InvalidPlayersNumberException {
		LawrenceUIEngine e = new LawrenceUIEngine();
		
		Player p1 = new Player(1);
			Player p2 = new Player(2);
		
		CoreEngine.startNewCoreEngine(MapLevel.MAP1, new LawrenceUIEngine(),
        p1, p2);
		
	}

}
