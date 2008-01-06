package fr.umlv.hmm2000.gui.panel;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.umlv.hmm2000.gui.LawrenceComponentFactory;
import fr.umlv.hmm2000.unit.Monster;

/**
 * This class the panel which display monster features.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class MonsterPanel {

  private final AbstractUnitPanel abstractPanel;
  private final JLabel troops;
  private static MonsterPanel instance = new MonsterPanel();
  
  /**
   * Default constructor.
   */
  public MonsterPanel() {

    final JPanel centerPanel = new JPanel(new GridLayout(1, 2));
    this.troops = LawrenceComponentFactory.createLawrenceBasicLabel(null);
    centerPanel.add(LawrenceComponentFactory
        .createLawrenceBoldLabel("Units : "));
    centerPanel.add(this.troops);
    this.abstractPanel = new AbstractUnitPanel("Monster :", centerPanel);
  }

  /**
   * Returns the panel.
   * 
   * @param monster
   *            monster.
   * @return the panel.
   */
  public static JPanel getPanel(Monster monster) {
    refresh(monster);
    return instance.abstractPanel.getPanel();
  }

  /**
   * Refresh the panel with a new monster.
   * 
   * @param monster
   *            the monster.
   */
  private static void refresh(Monster monster) {
    instance.abstractPanel.refresh(new ImageIcon(monster.getSprite()
        .getIconPath()), "MONSTER", monster.getPlayer().toString());
    instance.troops.setText(monster.getTroop().size() + "");
  }

}
