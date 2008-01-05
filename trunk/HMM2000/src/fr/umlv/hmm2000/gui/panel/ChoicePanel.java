package fr.umlv.hmm2000.gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ChoicePanel {

  private final JPanel mainPanel;

  public ChoicePanel(String title, List<String> choices, JButton button) {
    this.mainPanel = new JPanel(new BorderLayout());
    this.mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory
        .createEtchedBorder(), title));

    final JPanel centerPanel = new JPanel(new GridLayout(choices.size(), 1));
    final ButtonGroup group = new ButtonGroup();
    for (String s : choices) {
      final JRadioButton r = new JRadioButton(s, true);
      centerPanel.add(r);
      group.add(r);
    }

    final JPanel southPanel = new JPanel();
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

      }
    });
    southPanel.add(button);

    this.mainPanel.add(centerPanel, BorderLayout.CENTER);
    this.mainPanel.add(southPanel, BorderLayout.SOUTH);
  }

  public JPanel getPanel() {
    return this.mainPanel;
  }

}
