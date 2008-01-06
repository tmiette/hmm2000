package fr.umlv.hmm2000.gui.panel;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.umlv.hmm2000.gui.LawrenceComponentFactory;
import fr.umlv.hmm2000.salesentity.SalesEntity;
import fr.umlv.hmm2000.salesentity.Sellable;
import fr.umlv.hmm2000.util.Pair;

/**
 * This class the panel which display sales entities features.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class SalesEntityPanel {

  private final AbstractUnitPanel abstractPanel;
  private final JLabel items;

  private static SalesEntityPanel instance = new SalesEntityPanel();

  /**
   * Default constructor.
   */
  public SalesEntityPanel() {

    final JPanel centerPanel = new JPanel(new GridLayout(1, 2));
    this.items = LawrenceComponentFactory.createLawrenceBasicLabel(null);
    centerPanel.add(LawrenceComponentFactory
        .createLawrenceBoldLabel("Items : "));
    centerPanel.add(this.items);

    this.abstractPanel = new AbstractUnitPanel("Sales entity :", centerPanel);
  }

  /**
   * Returns the panel.
   * 
   * @param sales
   *            the sales entity.
   * @return the panel.
   */
  public static JPanel getPanel(SalesEntity sales) {
    refresh(sales);
    return instance.abstractPanel.getPanel();
  }

  /**
   * Refresh the panel with a new sales entity.
   * 
   * @param sales
   *            the sales entity.
   */
  private static void refresh(SalesEntity sales) {
    instance.abstractPanel.refresh(new ImageIcon(sales.getSprite()
        .getIconPath()), sales.getType().name(), null);

    String itemsString = new String("<html><body>");
    for (Pair<Sellable, Integer> pair : SalesEntity.createItemsList(sales
        .getItems())) {
      if (pair.getSecondElement() != 0) {
        itemsString += pair.getFirstElement().getLabel() + " - "
            + pair.getFirstElement().getPrice() + "-"
            + +pair.getSecondElement() + "<br>";
      }
    }
    itemsString += "</body></html>";
    instance.items.setText(itemsString);

  }

}
