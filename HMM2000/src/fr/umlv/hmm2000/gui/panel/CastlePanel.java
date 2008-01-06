package fr.umlv.hmm2000.gui.panel;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.umlv.hmm2000.building.Castle;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.gui.LawrenceComponentFactory;
import fr.umlv.hmm2000.unit.Hero;
import fr.umlv.hmm2000.unit.profile.WarriorProfile;

public class CastlePanel {

  private final AbstractUnitPanel abstractPanel;
  private final JLabel troops;
  private final JLabel factories;
  private final JLabel heroes;
  private final JButton troopsButton;
  private final JButton castleButton;
  private static CastlePanel instance = new CastlePanel();

  public CastlePanel() {

    final JPanel centerPanel = new JPanel(new GridLayout(3, 2));
    this.troops = LawrenceComponentFactory.createLawrenceBasicLabel(null);
    this.factories = LawrenceComponentFactory.createLawrenceBasicLabel(null);
    this.heroes = LawrenceComponentFactory.createLawrenceBasicLabel(null);
    centerPanel.add(LawrenceComponentFactory
        .createLawrenceBoldLabel("Units : "));
    centerPanel.add(this.troops);
    centerPanel.add(LawrenceComponentFactory
        .createLawrenceBoldLabel("Factories : "));
    centerPanel.add(this.factories);
    centerPanel.add(LawrenceComponentFactory
        .createLawrenceBoldLabel("Heroes : "));
    centerPanel.add(this.heroes);

    this.troopsButton = LawrenceComponentFactory.createTroopsButton();
    this.castleButton = LawrenceComponentFactory.createCastleButton();

    this.abstractPanel = new AbstractUnitPanel("Castle :", centerPanel,
        this.troopsButton, this.castleButton);
  }

  public static JPanel getPanel(Castle castle) {
    refresh(castle);
    return instance.abstractPanel.getPanel();
  }

  private static void refresh(Castle castle) {
    instance.abstractPanel.refresh(new ImageIcon(castle.getSprite()
        .getIconPath()), "CASTLE", castle.getPlayer().toString());
    instance.troops.setText(castle.getTroop().size() + "");
    String factoriesString = new String("<html><body>");
    for (WarriorProfile profil : castle.getFactoryBuilt()) {
      factoriesString += profil.name() + "-"
          + castle.getFactoryLevel(profil).name() + "<br>";
    }
    factoriesString += "</body></html>";
    instance.factories.setText(factoriesString);

    String heroesString = new String("<html><body>");
    for (Hero hero : castle.getHeroes()) {
      heroesString += hero.getName() + "<br>";
    }
    heroesString += "</body></html>";
    instance.heroes.setText(heroesString);

    if (CoreEngine.roundManager().currentPlayer().equals(castle.getPlayer())) {
      instance.troopsButton.setEnabled(true);
      instance.castleButton.setEnabled(true);
    } else {
      instance.troopsButton.setEnabled(false);
      instance.castleButton.setEnabled(false);
    }
  }

}
