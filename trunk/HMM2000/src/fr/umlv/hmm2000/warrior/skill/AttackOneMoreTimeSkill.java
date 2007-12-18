package fr.umlv.hmm2000.warrior.skill;

import fr.umlv.hmm2000.warrior.Container;

public class AttackOneMoreTimeSkill implements SkillAction {

	private static AttackOneMoreTimeSkill instance;

	public static AttackOneMoreTimeSkill getInstance() {

		if (AttackOneMoreTimeSkill.instance == null) {
			AttackOneMoreTimeSkill.instance = new AttackOneMoreTimeSkill();
		}
		return AttackOneMoreTimeSkill.instance;
	}

	private AttackOneMoreTimeSkill() {

	}

	@Override
	public void perform(Container c) {

		// TODO Auto-generated method stub

	}
}
