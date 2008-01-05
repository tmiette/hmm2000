package fr.umlv.hmm2000.gui;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class LawrenceComponentFactory {

  private static final Font BOLD_FONT = new Font(null, Font.BOLD, 12);
  private static final Font BASIC_FONT = new Font(null, Font.PLAIN, 12);

  public static JButton createLawrenceButton(String text, String iconName,
      ActionListener listener) {
    final JButton b = new JButton(text, LawrenceComponentFactory
        .createImageIcon(iconName));
    b.addActionListener(listener);
    return b;
  }

  public static JLabel createLawrenceBoldLabel(String text) {
    final JLabel l = new JLabel(text);
    l.setFont(LawrenceComponentFactory.BOLD_FONT);
    return l;
  }

  public static JLabel createLawrenceBasicLabel(String text) {
    final JLabel l = new JLabel(text);
    l.setFont(LawrenceComponentFactory.BASIC_FONT);
    return l;
  }

  public static ImageIcon createImageIcon(String iconName) {
    return new ImageIcon(LawrenceComponentFactory.class.getResource("/icons/"
        + iconName));
  }

}