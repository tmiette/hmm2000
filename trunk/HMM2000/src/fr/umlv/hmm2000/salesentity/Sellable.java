package fr.umlv.hmm2000.salesentity;

import fr.umlv.hmm2000.engine.event.EncounterEvent;

public interface Sellable {

  public String getLabel();

  public Price getPrice();

  public void acquire(EncounterEvent event);

}
