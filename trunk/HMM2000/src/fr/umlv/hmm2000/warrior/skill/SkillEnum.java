package fr.umlv.hmm2000.warrior.skill;

public enum SkillEnum {

	ATTACK_ONE_MORE_TIME("Attack one more time", ),
	ATTACK_FIRST_LINE_UNITS("Attack units on first line"),
	ATTACK_SECOND_LINE_UNIT("Attack unit on second line"),
	ATTACK_ANY_UNIT("Attack any unit"),
	SWAP_UNIT("Move an unit"),
	ATTACK_ALL_UNITS("Attack all units");
	
	private final String label;
	
	private SkillEnum(String label) {

		this.label = label;
	}
	
}
