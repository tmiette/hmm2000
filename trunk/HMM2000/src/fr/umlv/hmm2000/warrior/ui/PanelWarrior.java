package fr.umlv.hmm2000.warrior.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.umlv.hmm2000.warrior.Warrior;

public class PanelWarrior {

	private final JPanel panel;

	private final JPanel northPanel;

	private final JPanel centerPanel;

	private final Warrior warrior;

	public PanelWarrior(Warrior warrior) {

		this.panel = new JPanel(new BorderLayout());

		this.warrior = warrior;
		this.northPanel = new JPanel();

		this.centerPanel = new JPanel(new GridLayout(10, 1, 1, 1));
		this.centerPanel.add(new JLabel("Sprite : "
				+ this.warrior.getSprite().toString()));
		this.centerPanel.add(new JLabel("Santé : " + this.warrior.getCurrentHealth()/this.warrior.getHealth() * 100 + "%"));
		this.centerPanel.add(new JLabel("Attaque physique : " + this.warrior.getPhysicalAttackValue()));
		this.centerPanel.add(new JLabel("Résistance physique : " + this.warrior.getPhysicalDefenseValue()));
		this.centerPanel.add(new JLabel("Vitesse : " + this.warrior.getSpeed()));

		this.panel.add(this.northPanel, BorderLayout.NORTH);
		this.panel.add(this.centerPanel, BorderLayout.CENTER);
	}
	
	public JPanel getPanel() {

		return this.panel;
	}
}
