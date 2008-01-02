package fr.umlv.hmm2000.warrior.ui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import fr.umlv.hmm2000.warrior.Warrior;

public class PanelWarrior {

	private final JPanel panel;

	private final Warrior warrior;

	public PanelWarrior(Warrior warrior) {

		this.panel = new JPanel(new GridLayout(3, 1));
		this.warrior = warrior;
	}
}
