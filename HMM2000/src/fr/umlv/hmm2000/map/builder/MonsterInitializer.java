package fr.umlv.hmm2000.map.builder;

import java.io.LineNumberReader;

import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.FightableContainer;
import fr.umlv.hmm2000.unit.Monster;
import fr.umlv.hmm2000.unit.UnitFactory;
import fr.umlv.hmm2000.unit.exception.MaxNumberOfTroopsReachedException;

/**
 * This class defines an initializer of monsters elements.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class MonsterInitializer implements MapForegroundElementInitializer {

  @Override
  public Monster initialize(LineNumberReader lnr, String[] data) {
    if (data.length >= 1) {
      try {
        Monster m = UnitFactory.createMonster(Player.AI, CharacterTranslator
            .decodeMonsterProfile(data[0].charAt(0)));
        for (int i = 1; i < data.length; i++) {
          decodeFightable(m, data[i].split(","));
        }
        return m;
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

}
