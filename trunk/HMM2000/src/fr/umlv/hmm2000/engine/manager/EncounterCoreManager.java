package fr.umlv.hmm2000.engine.manager;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.event.EncounterEvent;

public class EncounterCoreManager {

  public EncounterCoreManager() {
  }

  public boolean perform(EncounterEvent event) {

    if (event.getRecipient().encounter(event)) {
      CoreEngine.map().removeMapForegroundElement(event.getRecipientLocation());
      CoreEngine.uiManager().eraseForegroundElement(event);
      return true;
    }

    return false;
  }
}
