package fr.umlv.hmm2000.gui.panel;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.gui.LawrenceComponentFactory;
import fr.umlv.hmm2000.unit.Hero;
import fr.umlv.hmm2000.unit.skill.Skill;

public class HeroPanel {

  private final AbstractUnitPanel abstractPanel;
  private final JLabel troops;
  private final JLabel speed;
  private final JLabel stepCount;
  private final JLabel skills;
  private final JButton troopsButton;
  private static HeroPanel instance = new HeroPanel();

  public HeroPanel() {

    final JPanel centerPanel = new JPanel(new GridLayout(4, 2));
    this.troops = LawrenceComponentFactory.createLawrenceBasicLabel(null);
    this.speed = LawrenceComponentFactory.createLawrenceBasicLabel(null);
    this.stepCount = LawrenceComponentFactory.createLawrenceBasicLabel(null);
    this.skills = LawrenceComponentFactory.createLawrenceBasicLabel(null);
    centerPanel.add(LawrenceComponentFactory
        .createLawrenceBoldLabel("Units : "));
    centerPanel.add(this.troops);
    centerPanel.add(LawrenceComponentFactory
        .createLawrenceBoldLabel("Speed : "));
    centerPanel.add(this.speed);
    centerPanel.add(LawrenceComponentFactory
        .createLawrenceBoldLabel("Steps remaining : "));
    centerPanel.add(this.stepCount);
    centerPanel.add(LawrenceComponentFactory
        .createLawrenceBoldLabel("Skills : "));
    centerPanel.add(this.skills);

    this.troopsButton = LawrenceComponentFactory.createTroopsButton();

    this.abstractPanel = new AbstractUnitPanel("Hero :", centerPanel,
        this.troopsButton);
  }

  public static JPanel getPanel(Hero hero) {
    refresh(hero);
    return instance.abstractPanel.getPanel();
  }

  private static void refresh(Hero hero) {
    instance.abstractPanel.refresh(
        new ImageIcon(hero.getSprite().getIconPath()), hero.getName(), hero
            .getPlayer().toString());
    instance.troops.setText(hero.getTroop().size() + "");
    instance.speed.setText(hero.getSpeed() + "");
    instance.stepCount.setText(hero.getStepCount() + "");
    String skillsString = new String("<html><body>");
    for (Skill skill : hero.getSkills()) {
      skillsString += skill.getName() + "<br>";
    }
    skillsString += "</body></html>";

    instance.skills.setText(skillsString);
    if (CoreEngine.roundManager().currentPlayer().equals(hero.getPlayer())) {
      instance.troopsButton.setEnabled(true);
    } else {
      instance.troopsButton.setEnabled(false);
    }
  }

}
