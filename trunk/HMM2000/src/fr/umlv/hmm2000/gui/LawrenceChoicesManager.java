package fr.umlv.hmm2000.gui;

import java.util.List;
import java.util.Scanner;

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
  public Sellable submit(List<Pair<Sellable, Integer>> items) {
    int i = 0;
    for (Pair<Sellable, Integer> pair : items) {
      System.out.println(i++ + "-" + pair.getFirstElement().getLabel()
          + ", q: " + pair.getSecondElement() + ", p: "
          + pair.getFirstElement().getPrice());
    }

    int purchaseIndex = LawrenceChoicesManager.readInt(0, items.size() - 1);

    Pair<Sellable, Integer> pairSelected = items.get(purchaseIndex);

    if (pairSelected == null) {
      return null;
    } else {
      Sellable item = pairSelected.getFirstElement();
      return item;
    }
  }

  @Override
  public Skill submit(List<Skill> skills) {
    int i = 0;
    for (Skill skill : skills) {
      System.out.println(i++ + "-" + skill.getName() + " : "
          + skill.getToolTipText());
    }

    int skillIndex = LawrenceChoicesManager.readInt(0, skills.size() - 1);
    return skills.get(skillIndex);
  }

  @Override
  public CastleItem submit(List<CastleItem> items) {
    int i = 0;
    for (CastleItem item : items) {
      System.out.println(i++ + "-" + item.getSuggestion());
    }

    int itemIndex = LawrenceChoicesManager.readInt(0, items.size() - 1);
    return items.get(itemIndex);
  }
}
