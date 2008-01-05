package fr.umlv.hmm2000.gui.panel;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.umlv.hmm2000.gui.LawrenceComponentFactory;
import fr.umlv.hmm2000.unit.Warrior;
import fr.umlv.hmm2000.util.Pair;
import fr.umlv.hmm2000.warrior.profil.ElementaryEnum;

public class WarriorPanel {

  private final AbstractUnitPanel abstractPanel;
  private final JLabel health;
  private final JLabel speed;
  private final JLabel attack;
  private final JLabel defense;
  private final JLabel abilities;
  private static WarriorPanel instance = new WarriorPanel();

  private WarriorPanel() {

    final JPanel centerPanel = new JPanel(new GridLayout(5, 2));
    this.health = LawrenceComponentFactory.createLawrenceBasicLabel(null);
    this.speed = LawrenceComponentFactory.createLawrenceBasicLabel(null);
    this.attack = LawrenceComponentFactory.createLawrenceBasicLabel(null);
    this.defense = LawrenceComponentFactory.createLawrenceBasicLabel(null);
    this.abilities = LawrenceComponentFactory.createLawrenceBasicLabel(null);
    centerPanel.add(LawrenceComponentFactory
        .createLawrenceBoldLabel("Health : "));
    centerPanel.add(this.health);
    centerPanel.add(LawrenceComponentFactory
        .createLawrenceBoldLabel("Speed : "));
    centerPanel.add(this.speed);
    centerPanel.add(LawrenceComponentFactory
        .createLawrenceBoldLabel("Physical attack : "));
    centerPanel.add(this.attack);
    centerPanel.add(LawrenceComponentFactory
        .createLawrenceBoldLabel("Physical defense : "));
    centerPanel.add(this.defense);
    centerPanel.add(LawrenceComponentFactory
        .createLawrenceBoldLabel("Abilities : "));
    centerPanel.add(this.abilities);

    this.abstractPanel = new AbstractUnitPanel("Warrior :", centerPanel);
  }

  static JPanel getPanel(Warrior warrior) {
    refresh(warrior);
    return WarriorPanel.instance.abstractPanel.getPanel();
  }

  private static void refresh(Warrior warrior) {
    instance.abstractPanel.refresh(new ImageIcon(warrior.getSprite()
        .getIconPath()), warrior.getLabel(), warrior.getFightableContainer()
        .getPlayer().toString());
    instance.health.setText(warrior.getCurrentHealth()
        + " / "
        + warrior.getHealth()
        + " - "
        + (int) ((double) warrior.getCurrentHealth()
            / (double) warrior.getHealth() * 100.0) + " %");
    instance.speed.setText(warrior.getSpeed() + "");
    instance.attack.setText(warrior.getPhysicalAttackValue() + "");
    instance.defense.setText(warrior.getPhysicalDefenseValue() + "");
    String abilitiesString = new String("<html><body>");
    for (ElementaryEnum element : ElementaryEnum.values()) {
      Pair<Integer, Integer> pair = warrior.getAbilities().getAbility(element);
      if (pair.getFirstElement() != 0 && pair.getSecondElement() != 0) {
        abilitiesString += element + "-" + pair + "<br>";
      }
    }
    abilitiesString += "</body></html>";
    instance.abilities.setText(abilitiesString);
  }
}
