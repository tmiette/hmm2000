package fr.umlv.hmm2000.unit.profil;

import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.map.battle.BattlePositionMap;
import fr.umlv.hmm2000.unit.Fightable;

/**
 * Represents warriors profil. Warriors are contained in container like Hero,
 * monster or castle. This profil contains default values to create new warrior.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public enum ProfilWarrior {

	/*
	 * Enum default values description : TYPE(sprite, physicalAttackValue,
	 * physycalDefenseValue, health, speed, abilities, attack behaviour)
	 */
	FLIGHT(	Sprite.FLIGHT,
					10,
					10,
					50,
					30,
					new ElementAbility().addAbility(ElementaryEnum.FIRE, 10, 10)
							.addAbility(ElementaryEnum.LIGHTNING, 10, 10),
					new AttackBehaviour() {

						@Override
						public boolean isAttackable(Fightable attacker, Fightable defender) {

							return true;
						}

					}),
	GRUNT(Sprite.GRUNT,
				10,
				10,
				50,
				20,
				new ElementAbility().addAbility(ElementaryEnum.FIRE, 10, 10)
						.addAbility(ElementaryEnum.LIGHTNING, 10, 10),
				new AttackBehaviour() {

					@Override
					public boolean isAttackable(Fightable attacker, Fightable defender) {

						BattlePositionMap bpmDefenser = defender.getFightableContainer()
								.getBattlePositionManager();
						BattlePositionMap bpmAttacker = attacker.getFightableContainer()
								.getBattlePositionManager();
						return bpmAttacker.isInFirstLine(attacker)
								&& bpmDefenser.isInFirstLine(defender);
					}

				}),
	WIZZARD(Sprite.WIZZARD,
					10,
					10,
					50,
					10,
					new ElementAbility().addAbility(ElementaryEnum.FIRE, 10, 10)
							.addAbility(ElementaryEnum.LIGHTNING, 10, 10),
					new AttackBehaviour() {

						@Override
						public boolean isAttackable(Fightable attacker, Fightable defender) {

							BattlePositionMap bpm = defender.getFightableContainer()
									.getBattlePositionManager();
							return bpm.isInFirstLine(defender);
						}

					});

	// Icon to display on map
	private final Sprite sprite;

	// Physical attack value
	private final double physicalAttackValue;

	// Physical defense value
	private final double physicalDefenseValue;

	// Default health
	private final double health;

	// Default speed
	private final int speed;

	// Abilities to attack other unit
	private final ElementAbility abilities;

	// Capacity to attack other unit
	private final AttackBehaviour attackBahaviour;

	private ProfilWarrior(Sprite sprite,
												double physicalAttackValue,
												double physicalDefenseValue,
												double health,
												int speed,
												ElementAbility abilities,
												AttackBehaviour attackBahaviour) {

		this.sprite = sprite;
		this.physicalAttackValue = physicalAttackValue;
		this.physicalDefenseValue = physicalDefenseValue;
		this.health = health;
		this.speed = speed;
		this.abilities = abilities;
		this.attackBahaviour = attackBahaviour;
	}

	/**
	 * Gets icon representing warrior
	 * 
	 * @return sprite
	 */
	public Sprite getSprite() {

		return this.sprite;
	}

	/**
	 * Gets warrior specific physical attack value
	 * 
	 * @return physical attack value
	 */
	public double getPhysicalAttackValue() {

		return this.physicalAttackValue;
	}

	/**
	 * Gets warrior specific physical defense value
	 * 
	 * @return physical defense value
	 */
	public double getPhysicalDefenseValue() {

		return this.physicalDefenseValue;
	}

	/**
	 * Gets warrior specific health
	 * 
	 * @return default health
	 */
	public double getHealth() {

		return this.health;
	}

	/**
	 * Gets warrior specific speed
	 * 
	 * @return warrior speed
	 */
	public int getSpeed() {

		return this.speed;
	}

	/**
	 * Gets abilities owned by warrior
	 * 
	 * @return abilites
	 */
	public ElementAbility getAbilities() {

		return this.abilities;
	}

	/**
	 * Gets warrior specific attack capacity
	 * 
	 * @return attack capacity
	 */
	public AttackBehaviour getAttackBahaviour() {

		return this.attackBahaviour;
	}

}
