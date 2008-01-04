package fr.umlv.hmm2000.gui.panel;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.umlv.hmm2000.building.Castle;

public class CastlePanel {
  private final AbstractUnitPanel abstractPanel;
  private final JLabel troops;
  private static CastlePanel instance = new CastlePanel();

  public CastlePanel() {

    final JPanel centerPanel = new JPanel(new GridLayout(1, 2));
    this.troops = new JLabel("troops");
    centerPanel.add(new JLabel("Units : "));
    centerPanel.add(this.troops);
    this.abstractPanel = new AbstractUnitPanel("Monster :", centerPanel);
  }

  public static JPanel getPanel(Castle castle) {
    refresh(castle);
    return instance.abstractPanel.getPanel();
  }

  private static void refresh(Castle castle) {
    instance.abstractPanel.refresh(new ImageIcon(castle.getSprite()
        .getIconPath()), "CASTLE", castle.getPlayer().toString());
  }
}
