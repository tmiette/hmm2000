package fr.umlv.hmm2000.engine;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.engine.manager.EncounterManager;
import fr.umlv.hmm2000.engine.manager.MoveManager;
import fr.umlv.hmm2000.engine.manager.RoundManager;
import fr.umlv.hmm2000.engine.manager.SelectionManager;
import fr.umlv.hmm2000.map.InvalidPlayersNumberException;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.MainMap;

public class BattlePositionEngine {

  private final MainMap map;
  private final HMMUserInterface uiManager;
  private BattleSelectionManager selectionManager;
  private BattlePositionManager2 postionManager;

  private BattlePositionEngine(MainMap map, HMMUserInterface uiManager)
      throws InvalidPlayersNumberException {
    this.map = map;
    this.uiManager = uiManager;
    this.initialize();
  }

  private void initialize() {
    this.selectionManager = new BattleSelectionManager(this.map, this.uiManager);
    this.postionManager = new BattlePositionManager2(this.map, this.uiManager);
  }

  public void locationClicked(int x, int y, int button) {
    Location l = new Location(x, y);

    if (button == 1) {
      this.selectionManager.perform(l);
    } else if (button == 3) {
      this.postionManager.perform(l);
    }

  }

}
