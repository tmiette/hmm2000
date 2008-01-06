package fr.umlv.hmm2000.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;

/**
 * This class represent the frame used with lawrence in the same time of the
 * default lawrence frame.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class LawrenceJFrame {

  private final JPanel mainPanel;
  private JPanel centerPanel;
  private final JLabel dayCount;
  private final JLabel currentPlayer;
  private final JLabel currentResources;
  private final JTextArea textArea;

  /**
   * Constructor of the frame with its default attributes.
   * 
   * @param frame
   *            the swing frame in which the lawrence frame is displayed.
   */
  public LawrenceJFrame(JFrame frame) {
    frame.setSize(400, 600);
    this.mainPanel = new JPanel(new BorderLayout());
    final JPanel northPanel = new JPanel(new BorderLayout());
    northPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory
        .createEtchedBorder(), "Infos :"));
    final JPanel northNorthPanel = new JPanel(new GridLayout(3, 1));
    final JLabel mapLevel = LawrenceComponentFactory
        .createLawrenceBoldLabel("Map : " + CoreEngine.mapLevel().toString());
    final JLabel nbPlayers = LawrenceComponentFactory
        .createLawrenceBoldLabel("Players : " + CoreEngine.numberOfPlayers());
    this.dayCount = LawrenceComponentFactory.createLawrenceBoldLabel(null);
    northNorthPanel.add(mapLevel);
    northNorthPanel.add(nbPlayers);
    northNorthPanel.add(this.dayCount);
    ;
    final JPanel northCenterPanel = new JPanel(new GridLayout(2, 1));
    northCenterPanel.setBorder(BorderFactory
        .createTitledBorder("Current player :"));
    this.currentPlayer = LawrenceComponentFactory.createLawrenceBoldLabel(null);
    this.currentPlayer.setHorizontalAlignment(JLabel.CENTER);
    this.currentResources = LawrenceComponentFactory
        .createLawrenceBoldLabel(null);
    this.currentResources.setHorizontalAlignment(JLabel.CENTER);
    northCenterPanel.add(this.currentPlayer);
    northCenterPanel.add(this.currentResources);
    final JPanel northSouthPanel = new JPanel();

    final JButton quitButton = LawrenceComponentFactory.createLawrenceButton(
        "Quit", "quit20x20.png", new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            System.exit(0);
          }
        });
    final JButton nextDayButton = LawrenceComponentFactory
        .createLawrenceButton("Next day", "forward20x20.png",
            new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                CoreEngine.nextDay();
                refreshNorthPanel();
              }
            });
    final JButton backWorldMapButton = LawrenceComponentFactory
        .createLawrenceButton("Back", "rewind20x20.png", new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            CoreEngine.backToWorldMap();
            refreshNorthPanel();
          }
        });
    northSouthPanel.add(quitButton);
    northSouthPanel.add(nextDayButton);
    northSouthPanel.add(backWorldMapButton);
    northPanel.add(northNorthPanel, BorderLayout.NORTH);
    northPanel.add(northCenterPanel, BorderLayout.CENTER);
    northPanel.add(northSouthPanel, BorderLayout.SOUTH);
    this.refreshNorthPanel();

    this.centerPanel = new JPanel(new BorderLayout());
    this.centerPanel.setBackground(Color.WHITE);
    final JPanel southPanel = new JPanel(new BorderLayout());
    southPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory
        .createEtchedBorder(), "Messages :"));
    this.textArea = new JTextArea("Welcome to HMM 2000.");
    this.textArea.setAutoscrolls(true);
    this.textArea.setEditable(false);
    this.textArea.setFont(new Font(null, Font.BOLD, 12));
    final JScrollPane scroll = new JScrollPane(this.textArea,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll.setPreferredSize(new Dimension(400, 100));
    southPanel.add(scroll, BorderLayout.CENTER);
    this.mainPanel.add(northPanel, BorderLayout.NORTH);
    this.mainPanel.add(this.centerPanel, BorderLayout.CENTER);
    this.mainPanel.add(southPanel, BorderLayout.SOUTH);

    frame.setContentPane(this.mainPanel);
  }

  /**
   * Resfresh the informations panel of lawrence frame when the features panel
   * change.
   */
  private void refreshNorthPanel() {
    this.dayCount.setText("Day : " + CoreEngine.roundManager().currentDay());
    this.currentPlayer.setText(CoreEngine.roundManager().currentPlayer()
        .toString());
    this.currentResources.setText(CoreEngine.roundManager().currentPlayer()
        .getResources().toString());
  }

  /**
   * Change the features panel of the lawrence frame.
   * 
   * @param panel
   *            the new panel.
   */
  public void displayCenterPanel(JPanel panel) {
    this.centerPanel.removeAll();
    this.refreshNorthPanel();
    this.centerPanel.add(panel);
    this.mainPanel.paintImmediately(this.mainPanel.getBounds());
  }

  /**
   * Displays a message in the text area of the lawrence frame.
   * 
   * @param message
   *            the message.
   * @param level
   *            the level of the message.
   */
  public void displayMessage(String message, int level) {
    switch (level) {
    case HMMUserInterface.INFO_MESSAGE:
      this.textArea.setForeground(Color.BLACK);
      break;
    case HMMUserInterface.WARNING_MESSAGE:
      this.textArea.setForeground(Color.RED);
      break;
    case HMMUserInterface.ERROR_MESSAGE:
      this.textArea.setForeground(Color.BLUE);
      break;
    default:
      this.textArea.setForeground(Color.BLACK);
      break;
    }
    this.textArea.append("\n");
    this.textArea.append(message);
  }
}