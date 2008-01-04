package fr.umlv.hmm2000.gui.panel;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.umlv.hmm2000.resource.Resource;

public class ResourcePanel {

  private final AbstractUnitPanel abstractPanel;
  private final JLabel value;
  private final JLabel period;
  private final JLabel additon;
  private final JLabel counter;
  private final JLabel behaviour;
  private static ResourcePanel instance = new ResourcePanel();

  public ResourcePanel() {

    final JPanel centerPanel = new JPanel(new GridLayout(5, 2));
    this.value = new JLabel("value");
    this.period = new JLabel("period");
    this.additon = new JLabel("additon");
    this.counter = new JLabel("counter");
    this.behaviour = new JLabel("behaviour");
    centerPanel.add(new JLabel("Value : "));
    centerPanel.add(this.value);
    centerPanel.add(new JLabel("Period : "));
    centerPanel.add(this.period);
    centerPanel.add(new JLabel("Addition : "));
    centerPanel.add(this.additon);
    centerPanel.add(new JLabel("Counter : "));
    centerPanel.add(this.counter);
    centerPanel.add(new JLabel("Behaviour : "));
    centerPanel.add(this.behaviour);

    this.abstractPanel = new AbstractUnitPanel("Resource :", centerPanel);
  }

  public static JPanel getPanel(Resource resource) {
    refresh(resource);
    return instance.abstractPanel.getPanel();
  }

  private static void refresh(Resource resource) {
    instance.abstractPanel.refresh(new ImageIcon(resource.getSprite()
        .getIconPath()), resource.getKind().name(), null);
    instance.value.setText(resource.getCurrentValue()
        + " / "
        + resource.getMaxValue()
        + " - "
        + (int) ((double) resource.getCurrentValue()
            / (double) resource.getMaxValue() * 100.0) + "%");
    instance.period.setText(resource.getPeriod() + "");
    instance.additon.setText(resource.getAddition() + "");
    instance.counter.setText(resource.getPeriodCounter() + "");
    switch (resource.getBehaviour()) {
    case 0:
      instance.behaviour.setText("reloadable");
      break;
    case 1:
      instance.behaviour.setText("non reloadable");
      break;
    default:
      break;
    }

  }

}
