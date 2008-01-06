package fr.umlv.hmm2000.unit.profile;

import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.FightableContainer;
import fr.umlv.hmm2000.unit.UnitFactory;

/**
 * Represents default values for monster unit. A monster owns an icon and
 * warriors troop
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public enum MonsterProfile {

	TROLL(Sprite.TROLL,
				FightableContainer.PRIORITY_VERY_LOW,
				new Fightable[] {
						UnitFactory.createWarrior(WarriorProfile.FLIGHT, Level.LEVEL_1) }),
	ZOMBIE(	Sprite.DEFAULT,
					FightableContainer.PRIORITY_VERY_LOW,
					new Fightable[] {
							UnitFactory.createWarrior(WarriorProfile.FLIGHT, Level.LEVEL_1) }),
	MUMMY(Sprite.DEFAULT,
				FightableContainer.PRIORITY_VERY_LOW,
				new Fightable[] {
						UnitFactory.createWarrior(WarriorProfile.FLIGHT, Level.LEVEL_1) });

	// Icon to display on map
	private final Sprite sprite;

	// His troop
	private final Fightable[] units;

	// Specify who start to attack
	private final int attackPriority;

	private MonsterProfile(Sprite sprite,
												int attackPriority,
												Fightable[] units) {

		this.sprite = sprite;
		this.units = units;
		this.attackPriority = attackPriority;
	}

	/**
	 * Gets icon representing monster
	 * 
	 * @return sprite
	 */
	public Sprite getSprite() {

		return this.sprite;
	}

	/**
	 * Gets troop owned by monster
	 * 
	 * @return
	 */
	public Fightable[] getUnits() {

		return this.units;
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
