package fr.umlv.hmm2000.map.element;

import fr.umlv.hmm2000.engine.guiinterface.Spritable;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;

/**
 * This class defines a foreground element on a map.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public interface MapForegroundElement extends Spritable {

  /**
   * Method called when the foreground element is met by another element.
   * 
   * @param encounter
   *            the encounter event.
   * @return if the foreground element is destroy after the encounter.
   */
  public boolean encounter(Encounter encounter);

  /**
   * Method called when a new day begins.
   * 
   * @param day
   *            the number of the current day.
   */
  public void nextDay(int day);

  /**
   * Method for the displaying visitor.
   * 
   * @param visitor
   *            the displaying visitor.
   */
  public void accept(UIDisplayingVisitor visitor);
  
  public String getSaveString();
}
