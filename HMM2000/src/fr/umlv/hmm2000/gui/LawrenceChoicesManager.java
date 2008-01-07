package fr.umlv.hmm2000.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import fr.umlv.hmm2000.building.CastleItem;
import fr.umlv.hmm2000.engine.guiinterface.UIChoicesManager;
import fr.umlv.hmm2000.salesentity.Sellable;
import fr.umlv.hmm2000.unit.skill.Skill;
import fr.umlv.hmm2000.util.Pair;

/**
 * This class represent the user interface choice manager used with lawrence.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class LawrenceChoicesManager implements UIChoicesManager {

  @Override
  public int askQuestion(String title) {
    return JOptionPane.showConfirmDialog(null, title, "Question",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION ? UIChoicesManager.YES_RESPONSE
        : UIChoicesManager.NO_RESPONSE;
  }

  @Override
  public Sellable submit(String message, List<Pair<Sellable, Integer>> items) {
    ArrayList<String> choices = new ArrayList<String>();
    for (Pair<Sellable, Integer> pair : items) {
      if (pair.getFirstElement().getPrice() != null) {
        choices.add(pair.getFirstElement().getLabel() + " - price : "
            + pair.getFirstElement().getPrice() + " - quantity : "
            + pair.getSecondElement());
      } else {
        choices.add(pair.getFirstElement().getLabel());
      }
    }

    String purchaseString = (String) JOptionPane.showInputDialog(null, message,
        "Make a purchase", JOptionPane.QUESTION_MESSAGE,
        LawrenceComponentFactory.createImageIcon("treasure32x32.gif"), choices
            .toArray(), choices.get(0));

    int purchaseIndex = choices.indexOf(purchaseString);

    if (purchaseIndex == -1) {
      return null;
    } else {
      return items.get(purchaseIndex).getFirstElement();
    }
  }

  @Override
  public Skill submit(String message, List<Skill> skills) {
    ArrayList<String> choices = new ArrayList<String>();
    for (Skill skill : skills) {
      choices.add(skill.getName() + " - " + skill.getToolTipText());
    }

    String skillString = (String) JOptionPane.showInputDialog(null, message,
        "Use a skill", JOptionPane.QUESTION_MESSAGE, LawrenceComponentFactory
            .createImageIcon("aura32x32.gif"), choices.toArray(), choices
            .get(0));

    int skillIndex = choices.indexOf(skillString);

    if (skillIndex == -1) {
      return null;
    } else {
      return skills.get(skillIndex);
    }

  }

  @Override
  public CastleItem submit(String message, List<CastleItem> items) {
    ArrayList<String> choices = new ArrayList<String>();
    for (CastleItem item : items) {
      choices.add(item.getSuggestion());
    }

    String itemString = (String) JOptionPane.showInputDialog(null, message,
        "Manage castle", JOptionPane.QUESTION_MESSAGE, LawrenceComponentFactory
            .createImageIcon("castle32x32.gif"), choices.toArray(), choices
            .get(0));

    int itemIndex = choices.indexOf(itemString);

    if (itemIndex == -1) {
      return null;
    } else {
      return items.get(itemIndex);
    }

  }
}
