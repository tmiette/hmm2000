package fr.umlv.hmm2000.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import fr.umlv.hmm2000.engine.CoreEngine;

/**
 * This class is a factory for graphical components used with lawrence.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class LawrenceComponentFactory {

  private static final Font BOLD_FONT = new Font(null, Font.BOLD, 12);
  private static final Font BASIC_FONT = new Font(null, Font.PLAIN, 12);

  /**
   * Creates a lawrence button.
   * 
   * @param text
   *            the text of the button.
   * @param iconName
   *            the icon of the button.
   * @param listener
   *            the action listener of the button
   * @return the button.
   */
  public static JButton createLawrenceButton(String text, String iconName,
      ActionListener listener) {
    final JButton b = new JButton(text, LawrenceComponentFactory
        .createImageIcon(iconName));
    b.addActionListener(listener);
    return b;
  }

  /**
   * Creates a lawrence label with bold font.
   * 
   * @param text
   *            the text of the label
   * @return the label.
   */
  public static JLabel createLawrenceBoldLabel(String text) {
    final JLabel l = new JLabel(text);
    l.setFont(LawrenceComponentFactory.BOLD_FONT);
    return l;
  }

  /**
   * Creates a lawrence label.
   * 
   * @param text
   *            the text of the label
   * @return the label.
   */
  public static JLabel createLawrenceBasicLabel(String text) {
    final JLabel l = new JLabel(text);
    l.setFont(LawrenceComponentFactory.BASIC_FONT);
    return l;
  }

  /**
   * Creates a lawrence icon.
   * 
   * @param iconName
   *            the path of the icon image.
   * @return the icon.
   */
  public static ImageIcon createImageIcon(String iconName) {
    return new ImageIcon(LawrenceComponentFactory.class.getResource("/icons/"
        + iconName));
  }

  /**
   * Creates a lawrence button with a default listener used to manage the troops
   * of a fightable container.
   * 
   * @return the button.
   */
  public static JButton createTroopsButton() {
    return LawrenceComponentFactory.createLawrenceButton("Troops",
        "sword20x20.gif", new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            CoreEngine.manageBattlePosition();
          }
        });
  }

  /**
   * Creates a lawrence button with a default listener used to manage the
   * castle.
   * 
   * @return the button.
   */
  public static JButton createCastleButton() {
    return LawrenceComponentFactory.createLawrenceButton("Castle",
        "castle20x20.gif", new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            CoreEngine.manageCastle();
          }
        });
  }

}
