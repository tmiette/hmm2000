package fr.umlv.hmm2000.unit.profil;

import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.FightableContainer;
import fr.umlv.hmm2000.unit.UnitFactory;
import fr.umlv.hmm2000.unit.skill.AttackAllFightablesSkill;
import fr.umlv.hmm2000.unit.skill.AttackLineOpponentFightableSkill;
import fr.umlv.hmm2000.unit.skill.AttackOneMoreTimeSkill;
import fr.umlv.hmm2000.unit.skill.AttackOpponentFightableSkill;
import fr.umlv.hmm2000.unit.skill.Skill;
import fr.umlv.hmm2000.unit.skill.SwapFightablesSkill;

/**
 * Represents default values for a hero. A monster owns an icon and warriors
 * troop
 * 
 * @author sebastienmouret
 * 
 */
public enum ProfilHero {

	ARCHER(	Sprite.ARCHER,
					new Fightable[] {
							UnitFactory.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
							UnitFactory.createWarrior(ProfilWarrior.GRUNT, Level.LEVEL_1),
							UnitFactory.createWarrior(ProfilWarrior.WIZZARD, Level.LEVEL_1) },
					new Skill[] { new AttackLineOpponentFightableSkill(
							new ElementAbility().addAbility(ElementaryEnum.FIRE, 25, 0), 10) },
					FightableContainer.PRIORITY_LOW),
	LORD_OF_WAR(Sprite.LORDOFWAR,
							new Fightable[] {
									UnitFactory
											.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
									UnitFactory
											.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
									UnitFactory.createWarrior(ProfilWarrior.WIZZARD,
											Level.LEVEL_1) },
							new Skill[] { new SwapFightablesSkill(),
									new AttackOneMoreTimeSkill() },
							FightableContainer.PRIORITY_MEDIUM),
	SORCERER(	Sprite.SORCERER,
						new Fightable[] {
								UnitFactory.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
								UnitFactory.createWarrior(ProfilWarrior.GRUNT, Level.LEVEL_1),
								UnitFactory.createWarrior(ProfilWarrior.WIZZARD, Level.LEVEL_1) },
						new Skill[] {
								new AttackAllFightablesSkill(new ElementAbility().addAbility(
										ElementaryEnum.FIRE, 15, 0), 0),
								new AttackOpponentFightableSkill(new ElementAbility()
										.addAbility(ElementaryEnum.FIRE, 50, 0), 0) },
						FightableContainer.PRIORITY_LOW);

	// Icon to display on map
	private final Sprite sprite;

	// His troop
	private final Fightable[] units;

	// Skills he can use during battle
	private final Skill[] skills;

	// Specify who start to attack
	private final int attackPriority;

	private ProfilHero(	Sprite sprite,
											Fightable[] units,
											Skill[] skills,
											int attackPriority) {

		this.sprite = sprite;
		this.units = units;
		this.skills = skills;
		this.attackPriority = attackPriority;
	}

	/**
	 * Gets icon representing hero
	 * 
	 * @return sprite
	 */
	public Sprite getSprite() {

		return this.sprite;
	}

	/**
	 * Gets troop owned by hero
	 * 
	 * @return troop
	 */
	public Fightable[] getUnits() {

		return this.units;
	}

	/**
	 * Gets default skills owned by hero
	 * 
	 * @return skills
	 */
	public Skill[] getSkills() {

		return this.skills;
	}

	/**
	 * Gets default attack priority
	 * 
	 * @return attack priority
	 */
	public int getAttackPriority() {

		return this.attackPriority;
	}
}
