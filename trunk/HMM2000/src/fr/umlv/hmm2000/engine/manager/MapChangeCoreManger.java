package fr.umlv.hmm2000.engine.manager;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.event.MapChangeEvent;

public class MapChangeCoreManger {

  public MapChangeCoreManger() {
  }

  public void perform(MapChangeEvent event) {
    CoreEngine.map().changeMapBackgroundElement(event.getLocation(),
        event.getNewElement());
    CoreEngine.uiManager().changeBackgroundElement(event);
  }

}
