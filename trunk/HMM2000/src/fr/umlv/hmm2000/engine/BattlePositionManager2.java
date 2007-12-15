package fr.umlv.hmm2000.engine;

import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.war.BattlePositionMap;

public class BattlePositionManager2 {

  private final BattlePositionMap map;
  private final HMMUserInterface uiManager;
  private BattlePositionSelectionManager selectectionManager;

  public BattlePositionManager2(BattlePositionMap map,
      HMMUserInterface uiManager,
      BattlePositionSelectionManager selectectionManager) {
    this.map = map;
    this.uiManager = uiManager;
    this.selectectionManager = selectectionManager;
  }

  public void perform(Location l) {

    if (this.selectectionManager.getSelectedElement() != null
        && !this.selectectionManager.getSelectedLocation().equals(l)) {
      this.map.move(this.selectectionManager.getSelectedLocation(), l);
      this.uiManager.swap(this.selectectionManager.getSelectedLocation(), l);
      this.selectectionManager.perform(l);
    }
  }
}
