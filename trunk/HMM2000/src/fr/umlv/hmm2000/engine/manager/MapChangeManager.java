package fr.umlv.hmm2000.engine.manager;

import fr.umlv.hmm2000.engine.Engine;
import fr.umlv.hmm2000.engine.event.MapChangeEvent;
import fr.umlv.hmm2000.engine.guiinterface.UIEngine;
import fr.umlv.hmm2000.map.Map;

public class MapChangeManager {

  private final UIEngine uiManager;

  private final Map map;

  public MapChangeManager() {
    this.map = Engine.currentEngine().map();
    this.uiManager = Engine.currentEngine().uiManager();
  }

  public void perform(MapChangeEvent event) {
    this.map.changeMapBackgroundElement(event.getLocation(), event
        .getNewElement());
    this.uiManager.changeBackgroundElement(event);
  }

}
