package fr.umlv.hmm2000.engine;

import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;

public class BattlePositionManager2 {

  private final Map map;
  private final HMMUserInterface uiManager;
  private BattleSelectionManager selectectionManager;

  public BattlePositionManager2(Map map, HMMUserInterface uiManager,
      BattleSelectionManager selectectionManager) {
    this.map = map;
    this.uiManager = uiManager;
    this.selectectionManager = selectectionManager;
  }

  public void perform(Location l) {

    if(this.selectectionManager.getSelectedElement() != null){
      
      thi
      this.selectectionManager.perform(l);
    }
  }
}
