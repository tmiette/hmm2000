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
	private final JLabel sprite;
	private final JLabel name;
	private final JLabel sizeTroop;
	private final JLabel player;
	private static PanelHero instance;
	
	private PanelHero() {

		panel = new JPanel(new BorderLayout());
		
		this.northPanel = new JPanel();
		this.northPanel.add(battlePositionButton());
		
		this.centerPanel = new JPanel(new GridLayout(10, 1, 1, 1));
		
		this.sprite = new JLabel();
		this.name = new JLabel();
		this.sizeTroop = new JLabel();
		this.player = new JLabel();
		this.centerPanel.add(this.sprite);
		this.centerPanel.add(this.name);
		this.centerPanel.add(this.sizeTroop);
		this.centerPanel.add(this.player);
		
		panel.add(this.northPanel, BorderLayout.NORTH);
		panel.add(this.centerPanel, BorderLayout.CENTER);
		
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

	public static JPanel getPanel(Hero hero) {

		if (PanelHero.instance == null) {
			PanelHero.instance = new PanelHero();
		}
		refresh(hero);
		
		return PanelHero.instance.panel;
	}
	
	private static void refresh(Hero hero) {

		instance.sprite.setText("Sprite : " + hero.getSprite().toString());
		instance.name.setText("Nom : " + hero.getName());
		instance.sizeTroop.setText("Nombre de guerriers : " + hero.getTroop().size());
		instance.player.setText("Joueur n¡ : " + hero.getPlayer().getId());
	}
	
}
