package main;
import javax.swing.JFrame;

import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.gui.LawrenceUIEngine;
import fr.umlv.hmm2000.gui.panel.StartPanel;

public class Hmm2000Tom {

  private static JFrame frame;

  public static void main(String[] args) {

    frame = new JFrame("HMM 2000");
    final HMMUserInterface hmmui = new LawrenceUIEngine(frame);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(810, 0, 400, 300);
    frame.setResizable(false);
    frame.setContentPane(StartPanel.createStartPanel(hmmui));
    frame.setVisible(true);

  }

}
