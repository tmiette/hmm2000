package fr.umlv.hmm2000.gui.panel;

import javax.swing.JComponent;

import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.warrior.Hero;
import fr.umlv.hmm2000.warrior.Monster;
import fr.umlv.hmm2000.warrior.Warrior;

public class PanelFactory {

  public static JComponent getStartPanel(HMMUserInterface ui) {
    return StartPanel.createStartPanel(ui);
  }

  public static JComponent getHeroPanel(Hero hero) {
    return PanelHero.getPanel(hero);
  }

  public static JComponent getWarriorPanel(Warrior warrior) {
    return PanelWarrior.getPanel(warrior);
  }

  public static JComponent getMonsterPanel(Monster monster) {
    return PanelMonster.getPanel(monster);
  }

}
