package fr.umlv.hmm2000.salesentity.spell;

import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;

/**
 * This interface represents an action which can be performed.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public interface SpellAction {

  /**
   * The action performing method.
   * 
   * @param encounter
   *            the encounter event.
   */
  public void perform(Encounter encounter);
}
