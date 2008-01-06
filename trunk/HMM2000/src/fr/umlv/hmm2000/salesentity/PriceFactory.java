package fr.umlv.hmm2000.salesentity;

import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.unit.profile.HeroProfile;
import fr.umlv.hmm2000.unit.profile.Level;
import fr.umlv.hmm2000.unit.profile.WarriorProfile;

/**
 * This class is a factory different for prices.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class PriceFactory {

  /**
   * Returns a price for a warrior.
   * 
   * @param profil
   *            the profile of the warrior.
   * @param level
   *            the level of the warrior
   * @return the price.
   */
  public static Price getWarriorPrice(WarriorProfile profil, Level level) {
    Price price = new Price();
    int gold = (2 * (int) profil.getHealth() + 3 * (int) profil
        .getPhysicalDefenseValue()) / 10;
    gold = gold * (int) level.getRatio();
    price.addResource(Kind.GOLD, gold);
    return price;
  }

  /**
   * Returns a price for a warrior factory.
   * 
   * @param profil
   *            the profile of the warrior built in the factory.
   * @param level
   *            the level of the factory.
   * @return the price.
   */
  public static Price getWarriorFactoryPrice(WarriorProfile profil, Level level) {
    Price price = new Price();
    int gold = (2 * (int) profil.getHealth() + 3 * (int) profil
        .getPhysicalDefenseValue()) / 10;
    gold = gold * (int) level.getRatio();
    gold = gold * 2;
    price.addResource(Kind.GOLD, gold);
    return price;
  }

  /**
   * Returns a price for a hero.
   * 
   * @param profil
   *            the profile of the hero.
   * @param level
   *            the level of the hero.
   * @return the price.
   */
  public static Price getHeroPrice(HeroProfile profil) {
    Price price = new Price();
    int gold = (2 * (int) profil.getAttackPriority() + 3 * (int) profil
        .getUnits().length) / 10;
    gold = gold * 2;
    price.addResource(Kind.GOLD, gold);
    return price;
  }
}
