package fr.umlv.hmm2000.war;

import fr.umlv.hmm2000.engine.Engine;
import fr.umlv.hmm2000.engine.guiinterface.UIEngine;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.warrior.Container;


public class BattleEngine {

	private final Map map;
	
  private final UIEngine uiManager;
  
  private final Container c1;
  
  private final Container c2;

	public BattleEngine(Container c1, Container c2) {

		// TODO Auto-generated constructor stub
		
		this.c1 = c1;
		this.c2 = c2;
		this.uiManager = Engine.currentEngine().uiManager();
		this.map = SebMapBuilder.createMap(c1, c2);
	}
	
	public void locationClicked(int x, int y, int button) {

		
	}
	
	
}
