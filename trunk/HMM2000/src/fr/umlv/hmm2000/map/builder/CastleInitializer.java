package fr.umlv.hmm2000.map.builder;

import java.io.LineNumberReader;

import fr.umlv.hmm2000.building.Castle;
import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.FightableContainer;
import fr.umlv.hmm2000.unit.UnitFactory;
import fr.umlv.hmm2000.unit.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.unit.profil.WarriorProfile;

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
        Castle c = new Castle(Player.AI, Translator
            .decodeWarriorProfile(data[0].charAt(0)));
        if (data.length >= 2) {
          for (String s : data[1].split(",")) {
            String[] subData = s.split(":");
            if (subData.length >= 2) {
              WarriorProfile p = Translator.decodeWarriorProfile(subData[0]
                  .charAt(0));
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
        Fightable f = UnitFactory.createWarrior(Translator
            .decodeWarriorProfile(data[0].charAt(0)), Translator
            .decodeLevel(data[0].charAt(0)));
        c.addFightable(f);
      } catch (IndexOutOfBoundsException e) {
      } catch (NumberFormatException e) {
      } catch (MaxNumberOfTroopsReachedException e) {
        // do nothing
      }
    }
  }

}
