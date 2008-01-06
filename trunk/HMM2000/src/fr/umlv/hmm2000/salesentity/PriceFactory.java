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
   * @param profile
   *            the profile of the warrior.
   * @param level
   *            the level of the warrior
   * @return the price.
   */
  public static Price getWarriorPrice(WarriorProfile profile, Level level) {
    Price price = new Price();
    double gold = (int) profile.getHealth();
    gold = gold * (int) level.getRatio();
    gold = gold / 3;
    price.addResource(Kind.GOLD, (int)gold);
    return price;
  }

  /**
   * Returns a price for a warrior factory.
   * 
   * @param profile
   *            the profile of the warrior built in the factory.
   * @param level
   *            the level of the factory.
   * @return the price.
   */
  public static Price getWarriorFactoryPrice(WarriorProfile profile, Level level) {
    Price price = new Price();
    int gold = (2 * (int) profile.getHealth());
    gold = gold * (int) level.getRatio();
    price.addResource(Kind.GOLD, gold);
    return price;
  }

  /**
   * Returns a price for a hero.
   * 
   * @param profile
   *            the profile of the hero.
   * @param level
   *            the level of the hero.
   * @return the price.
   */
  public static Price getHeroPrice(HeroProfile profile) {
    Price price = new Price();
    int gold = (2 * (int) profile.getAttackPriority() + 3 * (int) profile
        .getUnits().length);
    gold = gold * 2;
    price.addResource(Kind.GOLD, gold);
    return price;
  }
}
