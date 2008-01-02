package fr.umlv.hmm2000.warrior.ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class FrameMain {

	private final JFrame frame;
	
	
	public FrameMain() {

		this.frame = new JFrame();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(new Dimension(300, 600));
	}
	
	public void addPanel(JPanel panel) {

		this.frame.getContentPane().add(panel);
	}
	
	
	public JFrame getFrame() {

		return this.frame;
	}
}
