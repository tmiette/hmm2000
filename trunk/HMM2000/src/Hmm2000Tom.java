import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.gui.LawrenceUIEngine;
import fr.umlv.hmm2000.map.InvalidPlayersNumberException;

public class Hmm2000Tom {

  public final static JFrame frame = new JFrame();;

  public static void main(String[] args) throws FileNotFoundException,
      IOException, InvalidPlayersNumberException {

    final HMMUserInterface hmmui = new LawrenceUIEngine();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);
    frame.setResizable(false);
    frame.setContentPane(StartPanel.createStratPanel(hmmui));
    frame.setVisible(true);

  }

}
