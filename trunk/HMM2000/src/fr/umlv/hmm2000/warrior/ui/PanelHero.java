package fr.umlv.hmm2000.warrior.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.umlv.hmm2000.warrior.Hero;


public class PanelHero {

	private final JPanel panel;
	private final JPanel northPanel;
	private final JPanel centerPanel;
	private final Hero hero;
	
	public PanelHero(Hero hero) {

		this.hero = hero;
		this.panel = new JPanel(new BorderLayout());
		
		this.northPanel = new JPanel();
		this.northPanel.add(battlePositionButton());
		
		this.centerPanel = new JPanel(new GridLayout(10, 1, 1, 1));
		this.centerPanel.add(new JLabel("Sprite : " + this.hero.getSprite().toString()));
		this.centerPanel.add(new JLabel("Nom : " + this.hero.getName()));
		this.centerPanel.add(new JLabel("Nombre de guerriers : " + this.hero.getTroop().size()));
		this.centerPanel.add(new JLabel("Joueur n¡ : " + this.hero.getPlayer().getId()));
		
		this.panel.add(this.northPanel, BorderLayout.NORTH);
		this.panel.add(this.centerPanel, BorderLayout.CENTER);
	}
	
	
	private JButton battlePositionButton() {

		JButton b = new JButton("BP");
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			
				// TODO Auto-generated method stub
				
			}
		});
		
		return b;
	}

	public JPanel getPanel() {

		return this.panel;
	}
	
}
