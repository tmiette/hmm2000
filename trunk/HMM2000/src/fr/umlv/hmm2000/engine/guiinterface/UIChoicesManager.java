package fr.umlv.hmm2000.engine.guiinterface;

import java.util.List;

import fr.umlv.hmm2000.building.item.CastleItem;
import fr.umlv.hmm2000.salesentity.Sellable;
import fr.umlv.hmm2000.unit.skill.Skill;
import fr.umlv.hmm2000.util.Pair;

/**
 * This interface defines the user interface choices manager which enables the
 * user to perform a choices in a list of choices.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public interface UIChoicesManager {

  /**
   * Positive response.
   */
  public static final int YES_RESPONSE = 1;

  /**
   * Negative response.
   */
  public static final int NO_RESPONSE = 2;

  /**
   * Asks a question to the user which required a positive or negative response.
   * 
   * @param title
   *            the question.
   * @return the response.
   */
  public int askQuestion(String title);

  /**
   * Asks a choice to the user in a list of sellable items.
   * 
   * @param message
   *            the question.
   * @param items
   *            list of sellable items.
   * @return the item selected.
   */
  public Sellable submit(String message, List<Pair<Sellable, Integer>> items);

  /**
   * Asks a choice to the user in a list of skills items.
   * 
   * @param message
   *            the question.
   * @param skills
   *            list of skills items.
   * @return the item selected.
   */
  public Skill submit(String message, List<Skill> skills);

  /**
   * Asks a choice to the user in a list of castle items.
   * 
   * @param message
   *            the question.
   * @param items
   *            list of castle items.
   * @return the item selected.
   */
  public CastleItem submit(String message, List<CastleItem> items);
}
