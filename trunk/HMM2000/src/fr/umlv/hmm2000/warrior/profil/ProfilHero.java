package fr.umlv.hmm2000.warrior.profil;

import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.FightableContainer;
import fr.umlv.hmm2000.warrior.UnitFactory;
import fr.umlv.hmm2000.warrior.skill.AttackAllFightablesSkill;
import fr.umlv.hmm2000.warrior.skill.AttackLineOpponentFightableSkill;
import fr.umlv.hmm2000.warrior.skill.AttackOneMoreTimeSkill;
import fr.umlv.hmm2000.warrior.skill.AttackOpponentFightableSkill;
import fr.umlv.hmm2000.warrior.skill.Skill;
import fr.umlv.hmm2000.warrior.skill.SwapFightablesSkill;

public enum ProfilHero {

  ARCHER(Sprite.ARCHER, new Fightable[] {
      UnitFactory.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
      UnitFactory.createWarrior(ProfilWarrior.GRUNT, Level.LEVEL_1),
      UnitFactory.createWarrior(ProfilWarrior.WIZZARD, Level.LEVEL_1) },
      new Skill[] { new AttackLineOpponentFightableSkill(new ElementAbility()
          .addAbility(ElementaryEnum.FIRE, 25, 0), 10) },
      FightableContainer.PRIORITY_LOW),
  LORD_OF_WAR(Sprite.LORDOFWAR, new Fightable[] {
      UnitFactory.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
      UnitFactory.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
      UnitFactory.createWarrior(ProfilWarrior.WIZZARD, Level.LEVEL_1) },
      new Skill[] { new SwapFightablesSkill(), new AttackOneMoreTimeSkill() },
      FightableContainer.PRIORITY_MEDIUM),
  SORCERER(Sprite.SORCERER, new Fightable[] {
      UnitFactory.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
      UnitFactory.createWarrior(ProfilWarrior.GRUNT, Level.LEVEL_1),
      UnitFactory.createWarrior(ProfilWarrior.WIZZARD, Level.LEVEL_1) },
      new Skill[] {
          new AttackAllFightablesSkill(new ElementAbility().addAbility(
              ElementaryEnum.FIRE, 15, 0), 0),
          new AttackOpponentFightableSkill(new ElementAbility().addAbility(
              ElementaryEnum.FIRE, 50, 0), 0) },
      FightableContainer.PRIORITY_LOW);

  private final Sprite sprite;

  private final Fightable[] units;

  private final Skill[] skills;

  private final int attackPriority;

  private ProfilHero(Sprite sprite, Fightable[] units, Skill[] skills,
      int attackPriority) {

    this.sprite = sprite;
    this.units = units;
    this.skills = skills;
    this.attackPriority = attackPriority;
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

  public int getAttackPriority() {

    return this.attackPriority;
  }
}
