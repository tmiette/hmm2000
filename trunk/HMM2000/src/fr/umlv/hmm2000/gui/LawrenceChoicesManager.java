package fr.umlv.hmm2000.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import fr.umlv.hmm2000.building.CastleItem;
import fr.umlv.hmm2000.engine.guiinterface.UIChoicesManager;
import fr.umlv.hmm2000.salesentity.Sellable;
import fr.umlv.hmm2000.util.Pair;
import fr.umlv.hmm2000.warrior.skill.Skill;

public class LawrenceChoicesManager implements UIChoicesManager {

  private static int readInt(int min, int max) {

    Scanner scanner = new Scanner(System.in);
    try {
      int value = scanner.nextInt();
      while (value < min || value > max) {
        System.out.println("Choix incorrect.");
        value = scanner.nextInt();
      }
      return value;
    } catch (Exception e) {
      return 0;
    }
  }

  @Override
  public Sellable submit(String message, List<Pair<Sellable, Integer>> items) {
    ArrayList<String> choices = new ArrayList<String>();
    for (Pair<Sellable, Integer> pair : items) {
      choices.add(pair.getFirstElement().getLabel() + " - price : "
          + pair.getFirstElement().getPrice() + " - quantity : "
          + pair.getSecondElement());
    }

    String purchaseString = (String) JOptionPane.showInputDialog(null,
        message, "Make a purchase", JOptionPane.QUESTION_MESSAGE,
        LawrenceComponentFactory.createImageIcon("buy2.gif"), choices.toArray(),
        choices.get(0));

    int purchaseIndex = choices.indexOf(purchaseString);

    if (purchaseIndex == -1) {
      return null;
    } else {
      return items.get(purchaseIndex).getFirstElement();
    }
  }

  @Override
  public Skill submit(String message, List<Skill> skills) {
    int i = 0;
    for (Skill skill : skills) {
      System.out.println(i++ + "-" + skill.getName() + " : "
          + skill.getToolTipText());
    }

    int skillIndex = LawrenceChoicesManager.readInt(0, skills.size() - 1);
    return skills.get(skillIndex);
  }

  @Override
  public CastleItem submit(String message, List<CastleItem> items) {
    int i = 0;
    for (CastleItem item : items) {
      System.out.println(i++ + "-" + item.getSuggestion());
    }

    int itemIndex = LawrenceChoicesManager.readInt(0, items.size() - 1);
    return items.get(itemIndex);
  }
}
