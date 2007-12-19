package fr.umlv.hmm2000.warrior.skill;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.warrior.Container;
import fr.umlv.hmm2000.warrior.FightableContainer;
import fr.umlv.hmm2000.warrior.Heroe;
import fr.umlv.hmm2000.warrior.Warrior;
import fr.umlv.hmm2000.warrior.exception.WarriorDeadException;
import fr.umlv.hmm2000.warrior.exception.WarriorNotReachableException;


public class AttackAllSkill implements SkillAction {

	private static AttackAllSkill instance;

  public static AttackAllSkill getInstance() {
    if (AttackAllSkill.instance == null) {
    	AttackAllSkill.instance = new AttackAllSkill();
    }
    return AttackAllSkill.instance;
  }

  private AttackAllSkill() {
  }
	
	@Override
	public void perform(FightableContainer c) {

		
		Map map = CoreEngine.map();
		for (MapForegroundElement mfe : map.getMapForegroundElements()) {
			try {
				
				((Heroe)c).performAttack((Warrior)mfe);
			}
			catch (WarriorDeadException e) {
				map.removeMapForegroundElement(map.getLocationForMapForegroundElement(mfe));
			}
			catch (WarriorNotReachableException e) {
				//TODO nothing
			}
		}
		
	}

}
