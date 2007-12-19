package fr.umlv.hmm2000.warrior.skill;

import fr.umlv.hmm2000.warrior.FightableContainer;

public enum Skill {

	ATTACK_FIRST_LINE_UNITS("Attack all units on first line", AttackFirstLineSkill.getInstance()),
	ATTACK_SECOND_LINE_UNIT("Attack one unit in second line", AttackSecondLineUnitSkill.getInstance()),
	ATTACK_UNIT("Attack one unit", AttackUnitSkill.getInstance()),
	ATTACK_ALL("Attack all units", AttackAllSkill.getInstance()),
	ATTACK_ONE_MORE_TIME("Attack one unit one more time", AttackOneMoreTimeSkill.getInstance()),
	MOVE_UNIT("Move unit", SwapWarriorSkill.getInstance());
	
	private final String name;
	
	private final SkillAction action;
	
	private Skill(String name, SkillAction action) {

		this.name = name;
		this.action = action;
	}
	
	public void perform(FightableContainer container) {

		this.action.perform(container);
	}
}
