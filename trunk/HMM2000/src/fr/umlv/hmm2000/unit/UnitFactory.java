package fr.umlv.hmm2000.unit;

import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.unit.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.unit.profil.Level;
import fr.umlv.hmm2000.unit.profil.ProfilHero;
import fr.umlv.hmm2000.unit.profil.ProfilMonster;
import fr.umlv.hmm2000.unit.profil.ProfilWarrior;

/**
 * This class permits to create specifics units
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class UnitFactory {

    /**
     * Creates a warrior thanks to specific profil and level
     * 
     * @param profil
     *                enum which specify warrior type
     * @param level
     *                enum which specify warrior level
     * @return new warrior
     */
    public static Fightable createWarrior(ProfilWarrior profil, Level level) {

	// applying ratio corresponding to level
	final double ratio = level.getRatio();
	return new Warrior(profil.name(), profil.getSprite(), profil
		.getPhysicalAttackValue()
		* ratio, profil.getPhysicalDefenseValue() * ratio, profil
		.getHealth()
		* ratio, profil.getSpeed(), profil.getAbilities(), profil
		.getAttackBahaviour());

    }

    /**
     * Creates a hero associated to a player.
     * 
     * @param player
     *                hero owner
     * @param profil
     *                enum which specify the hero profil
     * @return new hero
     */
    public static Hero createHero(Player player, ProfilHero profil) {

	Hero h = new Hero(player, profil.getSprite(), profil.name(), profil
		.getSkills(), profil.getAttackPriority());
	for (Fightable fightable : profil.getUnits()) {
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
     * @param profil
     *                enum which specify the monster profil
     * @return new monster
     */
    public static Monster createMonster(Player player, ProfilMonster profil) {

	Monster m = new Monster(player, profil.getSprite(), profil.getAttackPriority());
	for (Fightable fightable : profil.getUnits()) {
	    try {
		m.addFightable(fightable);
	    } catch (MaxNumberOfTroopsReachedException e) {
		// do nothing
	    }
	}
	return m;
    }

}
