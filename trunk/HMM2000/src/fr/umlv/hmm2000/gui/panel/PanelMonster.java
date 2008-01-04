package fr.umlv.hmm2000.gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.umlv.hmm2000.warrior.Monster;

public class PanelMonster {

	private final JPanel panel;

	private final JPanel northPanel;

	private final JPanel centerPanel;

	private final JLabel sprite;

	private final JLabel sizeTroop;

	private static PanelMonster instance;

	private PanelMonster() {

		panel = new JPanel(new BorderLayout());

		this.northPanel = new JPanel();
		this.northPanel.add(battlePositionButton());

		this.centerPanel = new JPanel(new GridLayout(10, 1, 1, 1));

		this.sprite = new JLabel();
		this.sizeTroop = new JLabel();
		this.centerPanel.add(this.sprite);
		this.centerPanel.add(this.sizeTroop);

		panel.add(this.northPanel, BorderLayout.NORTH);
		panel.add(this.centerPanel, BorderLayout.CENTER);

	}

	private JButton battlePositionButton() {

		JButton b = new JButton("BP");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

			}
		});

		return b;
	}

	public static JPanel getPanel(Monster monster) {

		if (PanelMonster.instance == null) {
			PanelMonster.instance = new PanelMonster();
		}
		refresh(monster);

		return PanelMonster.instance.panel;
	}

	private static void refresh(Monster monster) {

		instance.sprite.setText("Sprite : " + monster.getSprite().toString());
		instance.sizeTroop.setText("Nombre de guerriers : "
				+ monster.getTroop().size());
	}
}
