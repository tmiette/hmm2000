package fr.umlv.hmm2000.engine;

import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.map.InvalidPlayersNumberException;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.war.BattlePositionMap;

public class BattlePositionEngine {

  private static BattlePositionEngine currentEngine;
  private final BattlePositionMap map;
  private final HMMUserInterface uiManager;
  private BattlePositionSelectionManager selectionManager;
  private BattlePositionManager2 postionManager;

  public static void createEngine(BattlePositionMap map,
      HMMUserInterface uiManager) throws InvalidPlayersNumberException {
    BattlePositionEngine engine = new BattlePositionEngine(map, uiManager);
    BattlePositionEngine.currentEngine = engine;
  }

  public static BattlePositionEngine currentEngine() {
    return BattlePositionEngine.currentEngine;
  }

  public BattlePositionEngine(BattlePositionMap map, HMMUserInterface uiManager)
      throws InvalidPlayersNumberException {
    this.map = map;
    this.uiManager = uiManager;
    this.initialize();
    this.uiManager.drawMap(this.map);
  }

  private void initialize() {
    this.selectionManager = new BattlePositionSelectionManager(this.map,
        this.uiManager);
    this.postionManager = new BattlePositionManager2(this.map, this.uiManager,
        this.selectionManager);
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
