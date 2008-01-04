package fr.umlv.hmm2000.gui.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.warrior.Hero;
import fr.umlv.hmm2000.warrior.skill.Skill;

public class PanelHero {

  private final JPanel mainPanel;
  private final JLabel sprite;
  private final JLabel name;
  private final JLabel troops;
  private final JLabel player;
  private final JLabel speed;
  private final JLabel stepCount;
  private final JLabel skills;
  private final JButton troopsButton;
  private static PanelHero instance;

  private PanelHero() {
    this.mainPanel = new JPanel(new BorderLayout());
    final JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
    final JPanel northEastPanel = new JPanel(new GridLayout(2, 1));
    this.sprite = new JLabel();
    this.name = new JLabel("name");
    this.player = new JLabel("player");
    final JPanel centerPanel = new JPanel(new GridLayout(4, 2));
    centerPanel.setBorder(BorderFactory.createTitledBorder("Features :"));
    this.troops = new JLabel("troops");
    this.speed = new JLabel("speed");
    this.stepCount = new JLabel("step");
    this.skills = new JLabel("skills");
    final JPanel southPanel = new JPanel();
    this.troopsButton = this.battlePositionButton();

    northEastPanel.add(this.name);
    northEastPanel.add(this.player);
    northPanel.add(this.sprite);
    northPanel.add(northEastPanel);
    this.mainPanel.add(northPanel, BorderLayout.NORTH);
    centerPanel.add(new JLabel("Units : "));
    centerPanel.add(this.troops);
    centerPanel.add(new JLabel("Speed : "));
    centerPanel.add(this.speed);
    centerPanel.add(new JLabel("Steps remaining : "));
    centerPanel.add(this.stepCount);
    centerPanel.add(new JLabel("Skills : "));
    centerPanel.add(this.skills);
    this.mainPanel.add(centerPanel, BorderLayout.CENTER);
    southPanel.add(this.troopsButton);
    this.mainPanel.add(southPanel, BorderLayout.SOUTH);
  }

  private JButton battlePositionButton() {
    final JButton b = new JButton("Troops");
    b.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        CoreEngine.manageBattlePosition();
      }
    });

    return b;
  }

  static JPanel getPanel(Hero hero) {
    if (PanelHero.instance == null) {
      PanelHero.instance = new PanelHero();
    }
    refresh(hero);
    return PanelHero.instance.mainPanel;
  }

  private static void refresh(Hero hero) {
    instance.sprite.setIcon(new ImageIcon(hero.getSprite().getIconPath()));
    instance.name.setText(hero.getName());
    instance.player.setText(hero.getPlayer().toString());
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
