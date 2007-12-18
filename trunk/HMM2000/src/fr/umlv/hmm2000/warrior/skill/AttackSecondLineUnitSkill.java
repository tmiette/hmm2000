package fr.umlv.hmm2000.warrior.skill;

import fr.umlv.hmm2000.warrior.Container;

public class AttackSecondLineUnitSkill implements SkillAction {

	private static AttackSecondLineUnitSkill instance;

	public static AttackSecondLineUnitSkill getInstance() {

		if (AttackSecondLineUnitSkill.instance == null) {
			AttackSecondLineUnitSkill.instance = new AttackSecondLineUnitSkill();
		}
		return AttackSecondLineUnitSkill.instance;
	}

	private AttackSecondLineUnitSkill() {

	}

	@Override
	public void perform(Container c) {

		// TODO Auto-generated method stub

	}
}
