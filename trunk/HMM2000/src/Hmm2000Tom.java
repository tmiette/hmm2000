import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

import fr.umlv.hmm2000.map.InvalidPlayersNumberException;

public class Hmm2000Tom {

  public static void main(String[] args) throws FileNotFoundException,
      IOException, InvalidPlayersNumberException {

    final JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(400, 300);
    f.setResizable(false);
    f.setContentPane(StartPanel.getPanel());
    f.setVisible(true);

  }
}
