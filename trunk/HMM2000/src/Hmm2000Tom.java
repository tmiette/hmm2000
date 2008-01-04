import javax.swing.JFrame;

import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.gui.LawrenceUIEngine;
import fr.umlv.hmm2000.gui.panel.StartPanel;

public class Hmm2000Tom {

  public static void main(String[] args) {

    final JFrame frame = new JFrame("HMM 2000");
    final HMMUserInterface hmmui = new LawrenceUIEngine(frame);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);
    frame.setResizable(false);
    frame.setContentPane(StartPanel.createStratPanel(hmmui));
    frame.setVisible(true);

  }

}
