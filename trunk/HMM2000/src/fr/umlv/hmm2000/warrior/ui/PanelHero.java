package fr.umlv.hmm2000.warrior.ui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.umlv.hmm2000.warrior.Hero;


public class PanelHero {

	private final JPanel panel;
	private final JPanel info;
	private final Hero hero;
	
	public PanelHero(Hero hero) {

		this.panel = new JPanel(new GridLayout(3, 1));
		this.info = new JPanel(new GridLayout(10, 1));
		this.hero = hero;
		this.panel.add(new JLabel("Sprite : " + this.hero.getSprite().toString()));
		this.panel.add(new JLabel("Nom : " + this.hero.getName()));
		this.info.add(new JLabel("Nombre de guerriers : " + this.hero.getTroop().size()));
		this.info.add(new JLabel("Joueur n¡ : " + this.hero.getPlayer().getId()));
		this.panel.add(this.info);
	}
	
	
	public JPanel getPanel() {

		return this.panel;
	}
	
}
