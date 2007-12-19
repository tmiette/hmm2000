package fr.umlv.hmm2000.warrior.profil;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.war.BattlePositionMap;
import fr.umlv.hmm2000.warrior.Fightable;

public enum ProfilWarrior {

  /*
   * Enum default values description : TYPE(physicalAttackValue,
   * physycalDefenseValue, health, speed, sprite, arrayAttacks)
   */
  FLIGHT(Sprite.FLIGHT, 10, 10, 100, 30,
      new ElementAbility().addAbility(ElementaryEnum.FIRE, 10, 10).addAbility(
          ElementaryEnum.LIGHTNING, 10, 10), new AttackBehaviour() {

        @Override
        public boolean isAttackable(Fightable attacker, Fightable defender) {
          return true;
        }

      }),
  GRUNT(Sprite.GRUNT, 10, 10, 20, 20,
      new ElementAbility().addAbility(ElementaryEnum.FIRE, 10, 10).addAbility(
          ElementaryEnum.LIGHTNING, 10, 10), new AttackBehaviour() {

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
  WIZZARD(Sprite.WIZZARD, 10, 10, 100, 10,
      new ElementAbility().addAbility(ElementaryEnum.FIRE, 10, 10).addAbility(
          ElementaryEnum.LIGHTNING, 10, 10), new AttackBehaviour() {

        @Override
        public boolean isAttackable(Fightable attacker, Fightable defender) {
          BattlePositionMap bpm = defender.getFightableContainer()
              .getBattlePositionManager();
          return bpm.isInFirstLine(defender);
        }

      });

  private final Sprite sprite;

  private final double physicalAttackValue;

  private final double physicalDefenseValue;

  private final double health;

  private final int speed;

  private final ElementAbility abilities;

  private final AttackBehaviour attackBahaviour;

  private ProfilWarrior(Sprite sprite, double physicalAttackValue,
      double physicalDefenseValue, double health, int speed,
      ElementAbility abilities, AttackBehaviour attackBahaviour) {

    this.sprite = sprite;
    this.physicalAttackValue = physicalAttackValue;
    this.physicalDefenseValue = physicalDefenseValue;
    this.health = health;
    this.speed = speed;
    this.abilities = abilities;
    this.attackBahaviour = attackBahaviour;
  }

  public Sprite getSprite() {
    return this.sprite;
  }

  public double getPhysicalAttackValue() {
    return this.physicalAttackValue;
  }

  public double getPhysicalDefenseValue() {
    return this.physicalDefenseValue;
  }

  public double getHealth() {
    return this.health;
  }

  public int getSpeed() {
    return this.speed;
  }

  public ElementAbility getAbilities() {
    return this.abilities;
  }

  public AttackBehaviour getAttackBahaviour() {
    return this.attackBahaviour;
  }

}
