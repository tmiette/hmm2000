package fr.umlv.hmm2000.map.element;

import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;
import fr.umlv.hmm2000.gui.Spritable;

public interface MapForegroundElement extends Spritable {

  public boolean encounter(Encounter encounter);

  public void nextDay(int day);

  public void accept(UIDisplayingVisitor visitor);
}
