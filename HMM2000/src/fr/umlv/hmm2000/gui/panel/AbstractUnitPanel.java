package fr.umlv.hmm2000.gui.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.umlv.hmm2000.gui.LawrenceComponentFactory;

/**
 * This class the panel which display default features for a unit.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class AbstractUnitPanel {

  private final JPanel mainPanel;
  private final JLabel sprite;
  private final JLabel name;
  private final JLabel player;
  private JPanel features;

  /**
   * Constructor a the panel.
   * 
   * @param title
   *            the name of the unit.
   * @param features
   *            the features panel of the unit.
   * @param buttons
   *            the buttons list.
   */
  protected AbstractUnitPanel(String title, JPanel features, JButton... buttons) {
    this.mainPanel = new JPanel(new BorderLayout());
    this.mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory
        .createEtchedBorder(), title));
    final JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
    final JPanel northEastPanel = new JPanel(new GridLayout(2, 1));
    this.sprite = new JLabel();
    this.sprite.setPreferredSize(new Dimension(80, 45));
    this.name = LawrenceComponentFactory.createLawrenceBoldLabel("name");
    this.name.setPreferredSize(new Dimension(150, 20));
    this.player = LawrenceComponentFactory.createLawrenceBoldLabel("player");
    this.player.setPreferredSize(new Dimension(150, 20));
    this.features = features;
    this.features.setBorder(BorderFactory.createTitledBorder("Features :"));
    final JPanel southPanel = new JPanel();
    for (JButton b : buttons) {
      southPanel.add(b);
    }
    northEastPanel.add(this.name);
    northEastPanel.add(this.player);
    northPanel.add(this.sprite);
    northPanel.add(northEastPanel);
    this.mainPanel.add(northPanel, BorderLayout.NORTH);
    this.mainPanel.add(this.features, BorderLayout.CENTER);
    this.mainPanel.add(southPanel, BorderLayout.SOUTH);

    this.refresh(null, "", "");
  }

  /**
   * Refresh the panel with new informations.
   * 
   * @param icon
   *            the new icon.
   * @param name
   *            the new name.
   * @param player
   *            the new player.
   */
  protected void refresh(ImageIcon icon, String name, String player) {
    this.sprite.setIcon(icon);
    this.name.setText(name);
    this.player.setText(player);
  }

  /**
   * Returns the panel.
   * 
   * @return the panel.
   */
  protected JPanel getPanel() {
    return this.mainPanel;
  }

}
