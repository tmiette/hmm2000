package fr.umlv.hmm2000.warrior.skill;

import fr.umlv.hmm2000.warrior.Container;

public class AttackUnitSkill implements SkillAction {

	private static AttackUnitSkill instance;

	public static AttackUnitSkill getInstance() {

		if (AttackUnitSkill.instance == null) {
			AttackUnitSkill.instance = new AttackUnitSkill();
		}
		return AttackUnitSkill.instance;
	}

	private AttackUnitSkill() {

	}

	@Override
	public void perform(Container c) {

		// TODO Auto-generated method stub

	}
}
