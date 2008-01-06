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

/**
 * This class is a factory for all different panels used in the lawrence frame.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class PanelFactory {

  /**
   * Returns the start panel.
   * 
   * @param ui
   *            the user interface manager.
   * @return the start panel.
   */
  public static JComponent getStartPanel(HMMUserInterface ui) {
    return StartPanel.createStartPanel(ui);
  }

  /**
   * Returns the hero panel.
   * 
   * @param hero
   *            the hero.
   * 
   * @return the panel.
   */
  public static JPanel getHeroPanel(Hero hero) {
    return HeroPanel.getPanel(hero);
  }

  /**
   * Returns the warrior panel.
   * 
   * @param warrior
   *            the warrior.
   * 
   * @return the panel.
   */
  public static JPanel getWarriorPanel(Warrior warrior) {
    return WarriorPanel.getPanel(warrior);
  }

  /**
   * Returns the resource panel.
   * 
   * @param resource
   *            the resource.
   * 
   * @return the panel.
   */
  public static JPanel getResourcePanel(Resource resource) {
    return ResourcePanel.getPanel(resource);
  }

  /**
   * Returns the monster panel.
   * 
   * @param monster
   *            the monster.
   * 
   * @return the panel.
   */
  public static JPanel getMonsterPanel(Monster monster) {
    return MonsterPanel.getPanel(monster);
  }

  /**
   * Returns the sales entity panel.
   * 
   * @param sales
   *            entity the sales entity.
   * 
   * @return the panel.
   */
  public static JPanel getSalesEntityPanel(SalesEntity sales) {
    return SalesEntityPanel.getPanel(sales);
  }

  /**
   * Returns the castle panel.
   * 
   * @param castle
   *            the castle.
   * 
   * @return the panel.
   */
  public static JPanel getCastlePanel(Castle castle) {
    return CastlePanel.getPanel(castle);
  }

}
