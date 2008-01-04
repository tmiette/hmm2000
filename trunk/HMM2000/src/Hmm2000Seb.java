import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.warrior.UnitFactory;
import fr.umlv.hmm2000.warrior.profil.ProfilHero;
import fr.umlv.hmm2000.warrior.ui.FrameMain;
import fr.umlv.hmm2000.warrior.ui.PanelHero;

public class Hmm2000Seb {

	public static void main(String[] args) {

		FrameMain fm = new FrameMain();
		fm.addPanel(PanelHero.getPanel(UnitFactory.createHero(
				new Player(1), ProfilHero.ARCHER)));
		fm.getFrame().setVisible(true);
	}

}
