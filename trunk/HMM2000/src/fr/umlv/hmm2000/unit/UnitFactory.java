package fr.umlv.hmm2000.unit;

import java.util.HashMap;

import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.unit.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.unit.profile.HeroProfile;
import fr.umlv.hmm2000.unit.profile.Level;
import fr.umlv.hmm2000.unit.profile.MonsterProfile;
import fr.umlv.hmm2000.unit.profile.WarriorProfile;
import fr.umlv.hmm2000.util.Pair;

/**
 * This class permits to create specifics units
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class UnitFactory {

  // Profile saver for save operation
  private static HashMap<Integer, Pair<WarriorProfile, Level>> warriorProfiles = new HashMap<Integer, Pair<WarriorProfile, Level>>();
  private static HashMap<Integer, HeroProfile> heroProfiles = new HashMap<Integer, HeroProfile>();
  private static HashMap<Integer, MonsterProfile> monsterProfiles = new HashMap<Integer, MonsterProfile>();

  /**
   * Creates a warrior thanks to specific profile and level
   * 
   * @param profile
   *            enum which specify warrior type
   * @param level
   *            enum which specify warrior level
   * @return new warrior
   */
  public static Fightable createWarrior(WarriorProfile profile, Level level) {

    // applying ratio corresponding to level
    final double ratio = level.getRatio();
    Warrior w = new Warrior(profile.name(), profile.getSprite(), profile
        .getPhysicalAttackValue()
        * ratio, profile.getPhysicalDefenseValue() * ratio, profile.getHealth()
        * ratio, profile.getSpeed(), profile.getAbilities(), profile
        .getAttackBahaviour());
    UnitFactory.warriorProfiles.put(w.getId(), new Pair<WarriorProfile, Level>(
        profile, level));
    return w;
  }

  /**
   * Creates a hero associated to a player.
   * 
   * @param player
   *            hero owner
   * @param profile
   *            enum which specify the hero profile
   * @return new hero
   */
  public static Hero createHero(Player player, HeroProfile profile) {

    Hero h = new Hero(player, profile.getSprite(), profile.name(), profile
        .getSkills(), profile.getAttackPriority());
    for (Fightable fightable : profile.getUnits()) {
      try {
        h.addFightable(fightable);
      } catch (MaxNumberOfTroopsReachedException e) {
        // do nothing
      }
    }
    UnitFactory.heroProfiles.put(h.getId(), profile);
    return h;
  }

  /**
   * Creates a monster associated to a player
   * 
   * @param player
   *            monster owner
   * @param profile
   *            enum which specify the monster profile
   * @return new monster
   */
  public static Monster createMonster(Player player, MonsterProfile profile) {

    Monster m = new Monster(player, profile.getSprite(), profile.name(),
        profile.getAttackPriority());
    for (Fightable fightable : profile.getUnits()) {
      try {
        m.addFightable(fightable);
      } catch (MaxNumberOfTroopsReachedException e) {
        // do nothing
      }
    }
    UnitFactory.monsterProfiles.put(m.getId(), profile);
    return m;
  }

  /**
   * Returns the warrior profile and warrior level of a warrior who don't know
   * it.
   * 
   * @param w
   *            the warrior.
   * @return the warrior profile and warrior level.
   */
  public static Pair<WarriorProfile, Level> findWarriorProfile(Warrior w) {
    return UnitFactory.warriorProfiles.get(w.getId());
  }

  /**
   * Returns the hero profile of a warrior who don't know it.
   * 
   * @param h
   *            the hero.
   * @return the hero profile.
   */
  public static HeroProfile findHeroProfile(Hero h) {
    return UnitFactory.heroProfiles.get(h.getId());
  }

  /**
   * Returns the monster profile of a monster who don't know it.
   * 
   * @param m
   *            the monster.
   * @return the monster profile.
   */
  public static MonsterProfile findMonsterProfile(Monster m) {
    return UnitFactory.monsterProfiles.get(m.getId());
  }

}
