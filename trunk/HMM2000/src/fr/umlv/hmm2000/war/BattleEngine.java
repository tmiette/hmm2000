package fr.umlv.hmm2000.war;

import fr.umlv.hmm2000.engine.Engine;
import fr.umlv.hmm2000.engine.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.engine.manager.SelectionManager;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.warrior.Container;


public class BattleEngine {

	private final Map map;
	
  private final HMMUserInterface uiManager;
  
  private final Container c1;
  
  private final Container c2;
  
  private final SelectionManager selectionManager;
  
  private final LocationSelectionRequester locationRequester;
  
	public BattleEngine(Container c1, Container c2) {

		this.c1 = c1;
		this.c2 = c2;
		this.uiManager = Engine.currentEngine().uiManager();
		this.map = SebMapBuilder.createMap(c1, c2);
		this.selectionManager = Engine.currentEngine().selectionManager();
	}
	
	public void locationClicked(int x, int y, int button) {

		Location l = new Location(x, y);

    if (this.locationRequester != null) {
      this.locationRequester.submitLocation(l);
    } else {
      if (button == 1) {
        this.selectionManager.perform(l);
      } else if (button == 3) {
      }
    }
	}
	
	
}
