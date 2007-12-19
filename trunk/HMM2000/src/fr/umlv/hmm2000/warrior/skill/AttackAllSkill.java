package fr.umlv.hmm2000.warrior.skill;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.warrior.FightableContainer;
import fr.umlv.hmm2000.warrior.exception.WarriorDeadException;
import fr.umlv.hmm2000.warrior.exception.WarriorNotReachableException;
import fr.umlv.hmm2000.warrior.profil.ElementAbility;


public class AttackAllSkill implements SkillAction {
	
	private final ElementAbility abilities;
	
	private final double attackValue;

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
