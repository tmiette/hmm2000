package fr.umlv.hmm2000.gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.umlv.hmm2000.warrior.Warrior;

public class PanelWarrior {

	private final JPanel panel;

	private final JPanel northPanel;

	private final JPanel centerPanel;

	private final JLabel sprite;

	private final JLabel health;

	private final JLabel att;

	private final JLabel def;

	private final JLabel speed;

	private static PanelWarrior instance;

	private PanelWarrior() {

		this.panel = new JPanel(new BorderLayout());

		this.northPanel = new JPanel();

		this.sprite = new JLabel();
		this.health = new JLabel();
		this.att = new JLabel();
		this.def = new JLabel();
		this.speed = new JLabel();

		this.centerPanel = new JPanel(new GridLayout(10, 1, 1, 1));

		this.centerPanel.add(this.sprite);
		this.centerPanel.add(this.health);
		this.centerPanel.add(this.att);
		this.centerPanel.add(this.def);
		this.centerPanel.add(this.speed);

		this.panel.add(this.northPanel, BorderLayout.NORTH);
		this.panel.add(this.centerPanel, BorderLayout.CENTER);
	}

	static JPanel getPanel(Warrior warrior) {

		if (PanelWarrior.instance == null) {
			PanelWarrior.instance = new PanelWarrior();
		}

		refresh(warrior);

		return PanelWarrior.instance.panel;
	}

	private static void refresh(Warrior warrior) {

		instance.sprite.setText("Sprite : " + warrior.getSprite().toString());
		instance.health.setText("Sant� : " + warrior.getCurrentHealth()
				/ warrior.getHealth() * 100 + "%");
		instance.att.setText("Attaque physique : "
				+ warrior.getPhysicalAttackValue());
		instance.def.setText("R�sistance physique : "
				+ warrior.getPhysicalDefenseValue());
		instance.speed.setText("Vitesse : " + warrior.getSpeed());
	}
}
