package fr.umlv.hmm2000.gui.panel;

import javax.swing.JComponent;
import javax.swing.JPanel;

import fr.umlv.hmm2000.building.Castle;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.resource.Resource;
import fr.umlv.hmm2000.salesentity.SalesEntity;
import fr.umlv.hmm2000.unit.Hero;
import fr.umlv.hmm2000.unit.Monster;
import fr.umlv.hmm2000.unit.Warrior;

public class PanelFactory {

  public static JComponent getStartPanel(HMMUserInterface ui) {
    return StartPanel.createStartPanel(ui);
  }

  public static JPanel getHeroPanel(Hero hero) {
    return HeroPanel.getPanel(hero);
  }

  public static JPanel getWarriorPanel(Warrior warrior) {
    return WarriorPanel.getPanel(warrior);
  }

  public static JPanel getResourcePanel(Resource resource) {
    return ResourcePanel.getPanel(resource);
  }

  public static JPanel getMonsterPanel(Monster monster) {
    return MonsterPanel.getPanel(monster);
  }

  public static JPanel getSalesEntityPanel(SalesEntity sales) {
    return SalesEntityPanel.getPanel(sales);
  }

  public static JPanel getCastlePanel(Castle castle) {
    return CastlePanel.getPanel(castle);
  }

}
