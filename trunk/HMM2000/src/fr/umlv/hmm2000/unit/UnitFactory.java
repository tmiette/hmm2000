package fr.umlv.hmm2000.unit;

import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.unit.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.unit.profile.HeroProfile;
import fr.umlv.hmm2000.unit.profile.Level;
import fr.umlv.hmm2000.unit.profile.MonsterProfile;
import fr.umlv.hmm2000.unit.profile.WarriorProfile;

/**
 * This class permits to create specifics units
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class UnitFactory {

    /**
     * Creates a warrior thanks to specific profile and level
     * 
     * @param profile
     *                enum which specify warrior type
     * @param level
     *                enum which specify warrior level
     * @return new warrior
     */
    public static Fightable createWarrior(WarriorProfile profile, Level level) {

	// applying ratio corresponding to level
	final double ratio = level.getRatio();
	return new Warrior(profile.name(), profile.getSprite(), profile
		.getPhysicalAttackValue()
		* ratio, profile.getPhysicalDefenseValue() * ratio, profile
		.getHealth()
		* ratio, profile.getSpeed(), profile.getAbilities(), profile
		.getAttackBahaviour());

    }

    /**
     * Creates a hero associated to a player.
     * 
     * @param player
     *                hero owner
     * @param profile
     *                enum which specify the hero profile
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
	return h;
    }

    /**
     * Creates a monster associated to a player
     * 
     * @param player
     *                monster owner
     * @param profile
     *                enum which specify the monster profile
     * @return new monster
     */
    public static Monster createMonster(Player player, MonsterProfile profile) {

	Monster m = new Monster(player, profile.getSprite(), profile.getAttackPriority());
	for (Fightable fightable : profile.getUnits()) {
	    try {
		m.addFightable(fightable);
	    } catch (MaxNumberOfTroopsReachedException e) {
		// do nothing
	    }
	}
	return m;
    }

}
