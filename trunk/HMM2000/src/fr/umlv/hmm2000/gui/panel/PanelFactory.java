package fr.umlv.hmm2000.gui.panel;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.warrior.Hero;
import fr.umlv.hmm2000.warrior.Monster;
import fr.umlv.hmm2000.warrior.Warrior;

public class PanelFactory {

  public static JComponent getStartPanel(HMMUserInterface ui) {
    return StartPanel.createStartPanel(ui);
  }

  public static JPanel getHeroPanel(Hero hero) {
    final JPanel p = PanelHero.getPanel(hero);
    p.setBorder(BorderFactory.createTitledBorder(BorderFactory
        .createEtchedBorder(), "Hero :"));
    return p;
  }

  public static JPanel getWarriorPanel(Warrior warrior) {
    final JPanel p = PanelWarrior.getPanel(warrior);
    p.setBorder(BorderFactory.createTitledBorder(BorderFactory
        .createEtchedBorder(), "Warrior :"));
    return p;
  }

  public static JPanel getMonsterPanel(Monster monster) {
    final JPanel p = PanelMonster.getPanel(monster);
    p.setBorder(BorderFactory.createTitledBorder(BorderFactory
        .createEtchedBorder(), "Monster :"));
    return p;
  }

}
