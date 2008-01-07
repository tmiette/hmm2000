package fr.umlv.hmm2000.engine.guiinterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.gui.LawrenceComponentFactory;
import fr.umlv.hmm2000.map.InvalidPlayersNumberException;
import fr.umlv.hmm2000.map.MapLevel;

/**
 * This class initialize the welcoming panel.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class GraphicalGameStarter {

  public static JComponent createStartPanel(final HMMUserInterface ui) {

    // layered pane
    final JLayeredPane pane = new JLayeredPane();

    // background icon
    final JLabel bgIcon = new JLabel();
    bgIcon.setIcon(LawrenceComponentFactory
        .createImageIcon("background400x300.gif"));

    // main panel
    final JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.setOpaque(false);

    // label with the welcoming image
    final JLabel headerIcon = new JLabel();
    headerIcon.setIcon(LawrenceComponentFactory
        .createImageIcon("header380x70.gif"));
    mainPanel.add(headerIcon, BorderLayout.NORTH);

    // panel with map levels informations
    final JPanel centerPanel = new JPanel(new BorderLayout(0, 10));
    centerPanel.setOpaque(false);

    // label with header text
    final JLabel headerLabel = LawrenceComponentFactory
        .createLawrenceBoldLabel("Select a map");
    headerLabel.setHorizontalAlignment(JLabel.CENTER);
    centerPanel.add(headerLabel, BorderLayout.NORTH);

    // panel with the list and the combobox
    JPanel infosPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
    infosPanel.setOpaque(false);

    // panel with difficulty and players details
    final JPanel detailsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
    detailsPanel.setOpaque(false);
    final JLabel difficultyLabel = LawrenceComponentFactory
        .createLawrenceBoldLabel(null);
    difficultyLabel.setPreferredSize(new Dimension(50, 20));
    final JComboBox playersComboBox = new JComboBox();
    detailsPanel.add(LawrenceComponentFactory
        .createLawrenceBoldLabel("Difficulty : "));
    detailsPanel.add(difficultyLabel);
    detailsPanel.add(LawrenceComponentFactory
        .createLawrenceBoldLabel("Players : "));
    detailsPanel.add(playersComboBox);

    // creates the list of map levels available
    final ArrayList<MapLevel> nameList = new ArrayList<MapLevel>();
    for (MapLevel mapLevel : MapLevel.values()) {
      nameList.add(mapLevel);
    }
    final JList list = new JList(nameList.toArray());
    list.setPrototypeCellValue("123456789012345");
    list.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        final MapLevel level = (MapLevel) list.getSelectedValue();
        difficultyLabel.setText(level.getDifficulty().toString());
        playersComboBox.removeAllItems();
        for (int i = level.getMinPlayerNumber(); i <= level
            .getMaxPlayerNumber(); i++) {
          playersComboBox.addItem(i);
        }
      }
    });
    list.setSelectedIndex(0);
    // put the list in a scroll pane
    final JScrollPane scroll = new JScrollPane(list,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    infosPanel.add(scroll);
    infosPanel.add(detailsPanel);
    centerPanel.add(infosPanel);
    mainPanel.add(centerPanel, BorderLayout.CENTER);

    // panel with action buttons
    final JPanel buttonsPanel = new JPanel();
    buttonsPanel.setOpaque(false);
    final JButton quitButton = LawrenceComponentFactory.createLawrenceButton(
        "Quit", "quit20x20.png", "Quit the game.", new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            System.exit(0);
          }
        });

    final JButton startButton = LawrenceComponentFactory.createLawrenceButton(
        "Start", "aura20x20.png", "Start the game.", new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              final Player[] players = new Player[(Integer) playersComboBox
                  .getSelectedItem()];
              for (int i = 0; i < players.length; i++) {
                players[i] = new Player(i);
              }
              final MapLevel level = (MapLevel) list.getSelectedValue();
              CoreEngine.startNewCoreEngine(level, level.getMapFile(), ui,
                  players);
            } catch (FileNotFoundException e1) {
              // the map file does not exist
              throw new AssertionError(e1);
            } catch (InvalidPlayersNumberException e1) {
              // this map can be played with the number of player
              throw new AssertionError(e1);
            } catch (IOException e1) {
              // io exception
              throw new AssertionError(e1);
            }
          }
        });

    final JButton saveButton = LawrenceComponentFactory.createLawrenceButton(
        "Saved", "aura20x20.png", "Start a saved game.", new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              CoreEngine.startSavedCoreEngine("MAP1-2", ui);
            } catch (InvalidSavedMapFileException e1) {
              System.err.println(e1.getMessage());
            }
          }
        });

    buttonsPanel.add(quitButton);
    buttonsPanel.add(startButton);
    buttonsPanel.add(saveButton);
    mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

    // set up the background icon and main panel
    bgIcon.setBounds(0, 0, 400, 300);
    mainPanel.setBounds(0, 0, 400, 270);
    pane.add(mainPanel, Integer.valueOf(2));
    pane.add(bgIcon, Integer.valueOf(1));

    return pane;
  }

}
