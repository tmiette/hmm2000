package fr.umlv.hmm2000.map.builder;

import java.io.LineNumberReader;

import fr.umlv.hmm2000.building.Castle;
import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.FightableContainer;
import fr.umlv.hmm2000.unit.Hero;
import fr.umlv.hmm2000.unit.UnitFactory;
import fr.umlv.hmm2000.unit.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.unit.profile.WarriorProfile;

/**
 * This class defines an initializer of castles elements.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class CastleInitializer implements MapForegroundElementInitializer {

  @Override
  public Castle initialize(LineNumberReader lnr, String[] data) {
    if (data.length >= 1) {
      try {
        Castle c = new Castle(Player.AI, CharacterTranslator
            .decodeWarriorProfile(data[0].charAt(0)));
        if (data.length >= 2) {
          for (String s : data[1].split(",")) {
            String[] subData = s.split(":");
            if (subData.length >= 2) {
              WarriorProfile p = CharacterTranslator
                  .decodeWarriorProfile(subData[0].charAt(0));
              c.buildFactory(p);
              int level = Integer.parseInt(subData[1]);
              for (int i = 1; i < level; i++) {
                c.upgradeFactory(p);
              }
            }
          }
        }
        if (data.length >= 3) {
          for (String s : data[2].split(",")) {
            decodeFightable(c, s.split(":"));
          }
        }
        if (data.length >= 4) {
          for (String s : data[3].split(",")) {
            decodeHero(c, s.split(":"));
          }
        }
        return c;
      } catch (IndexOutOfBoundsException e) {
      } catch (NumberFormatException e) {
        new IllegalArgumentException("Syntax error on line "
            + lnr.getLineNumber() + ".", e);
      }
    }
    return null;
  }

  /**
   * Decodes and add fightable units to the fightable container.
   * 
   * @param m
   *            the fightable container.
   * @param data
   *            the data array.
   */
  private void decodeFightable(FightableContainer c, String[] data) {
    if (data.length >= 2) {
      try {
        Fightable f = UnitFactory.createWarrior(CharacterTranslator
            .decodeWarriorProfile(data[0].charAt(0)), CharacterTranslator
            .decodeLevel(data[0].charAt(0)));
        c.addFightable(f);
      } catch (IndexOutOfBoundsException e) {
      } catch (NumberFormatException e) {
      } catch (MaxNumberOfTroopsReachedException e) {
        // do nothing
      }
    }
  }

  /**
   * Decodes and add heroes to the castle.
   * 
   * @param m
   *            the castle.
   * @param data
   *            the data array.
   */
  private void decodeHero(Castle c, String[] data) {
    if (data.length >= 1) {
      try {
        Hero h = UnitFactory.createHero(c.getPlayer(), CharacterTranslator
            .decodeHeroProfile(data[0].charAt(0)));
        c.addHero(h);
      } catch (IndexOutOfBoundsException e) {
      } catch (NumberFormatException e) {
        // do nothing
      }
    }
  }
}
