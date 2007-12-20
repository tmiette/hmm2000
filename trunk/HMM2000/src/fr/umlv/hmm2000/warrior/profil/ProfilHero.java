package fr.umlv.hmm2000.warrior.profil;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.UnitFactory;
import fr.umlv.hmm2000.warrior.skill.AttackAllSkill;
import fr.umlv.hmm2000.warrior.skill.AttackFirstLineSkill;
import fr.umlv.hmm2000.warrior.skill.AttackOneMoreTimeSkill;
import fr.umlv.hmm2000.warrior.skill.AttackSecondLineUnitSkill;
import fr.umlv.hmm2000.warrior.skill.AttackUnitSkill;
import fr.umlv.hmm2000.warrior.skill.Skill;
import fr.umlv.hmm2000.warrior.skill.SwapWarriorSkill;

public enum ProfilHero {

	ARCHER(	Sprite.ARCHER,
					new Fightable[] {
							UnitFactory.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
							UnitFactory.createWarrior(ProfilWarrior.GRUNT, Level.LEVEL_1),
							UnitFactory.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
							UnitFactory.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
							UnitFactory.createWarrior(ProfilWarrior.GRUNT, Level.LEVEL_1),
							UnitFactory.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
							UnitFactory.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
							UnitFactory.createWarrior(ProfilWarrior.GRUNT, Level.LEVEL_1),
							UnitFactory.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
							UnitFactory.createWarrior(ProfilWarrior.GRUNT, Level.LEVEL_1),
							UnitFactory.createWarrior(ProfilWarrior.WIZZARD, Level.LEVEL_1) },
					new Skill[] {
							new AttackFirstLineSkill(new ElementAbility().addAbility(
									ElementaryEnum.FIRE, 10, 0), 10),
							new AttackSecondLineUnitSkill(new ElementAbility().addAbility(
									ElementaryEnum.FIRE, 10, 0), 10) }),
	LORD_OF_WAR(Sprite.LORDOFWAR,
							new Fightable[] {
									UnitFactory
											.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
									UnitFactory.createWarrior(ProfilWarrior.GRUNT, Level.LEVEL_1),
									UnitFactory
											.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
									UnitFactory
											.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
									UnitFactory
											.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
									UnitFactory.createWarrior(ProfilWarrior.GRUNT, Level.LEVEL_1),
									UnitFactory
											.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
									UnitFactory
											.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
									UnitFactory.createWarrior(ProfilWarrior.GRUNT, Level.LEVEL_1),
									UnitFactory
											.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
									UnitFactory.createWarrior(ProfilWarrior.WIZZARD,
											Level.LEVEL_1) },
							new Skill[] {
									new SwapWarriorSkill(),
									new AttackOneMoreTimeSkill() }),
	SORCERER(	Sprite.SORCERER,
						new Fightable[] {
								UnitFactory.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
								UnitFactory.createWarrior(ProfilWarrior.GRUNT, Level.LEVEL_1),
								UnitFactory.createWarrior(ProfilWarrior.WIZZARD, Level.LEVEL_1) },
						new Skill[] {
								new AttackAllSkill(new ElementAbility().addAbility(
										ElementaryEnum.FIRE, 10, 0), 0),
								new AttackUnitSkill(new ElementAbility().addAbility(
										ElementaryEnum.FIRE, 10, 0), 0) });

	private final Sprite sprite;

	private final Fightable[] units;

	private final Skill[] skills;

	private ProfilHero(	Sprite sprite,
											Fightable[] units,
											Skill[] skills) {

		this.sprite = sprite;
		this.units = units;
		this.skills = skills;
	}

	public Sprite getSprite() {

		return this.sprite;
	}

	public Fightable[] getUnits() {

		return this.units;
	}

	public Skill[] getSkills() {

		return this.skills;
	}
}
