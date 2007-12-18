package fr.umlv.hmm2000.warrior.skill;

import fr.umlv.hmm2000.warrior.Container;

public class SwapWarriorSkill implements SkillAction {

	private static SwapWarriorSkill instance;

	public static SwapWarriorSkill getInstance() {

		if (SwapWarriorSkill.instance == null) {
			SwapWarriorSkill.instance = new SwapWarriorSkill();
		}
		return SwapWarriorSkill.instance;
	}

	private SwapWarriorSkill() {

	}

	@Override
	public void perform(Container c) {

		// TODO Auto-generated method stub

	}

}
