package fr.umlv.hmm2000.engine.guiinterface;

import fr.umlv.hmm2000.building.Castle;
import fr.umlv.hmm2000.resource.Resource;
import fr.umlv.hmm2000.salesentity.SalesEntity;
import fr.umlv.hmm2000.unit.Hero;
import fr.umlv.hmm2000.unit.Monster;
import fr.umlv.hmm2000.unit.Warrior;

/**
 * This interface defines the user interface displaying visitor which display
 * features of the foreground elements.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public interface UIDisplayingVisitor {

  /**
   * Displays a hero.
   * 
   * @param heroe
   *            the hero.
   */
  public void visit(Hero heroe);

  /**
   * Displays a resource.
   * 
   * @param resource
   *            the resource.
   */
  public void visit(Resource resource);

  /**
   * Displays a sales entity.
   * 
   * @param salesEntity
   *            the sales entity.
   */
  public void visit(SalesEntity salesEntity);

  /**
   * Displays a warrior.
   * 
   * @param warrior
   *            the warrior.
   */
  public void visit(Warrior warrior);

  /**
   * Displays a monster.
   * 
   * @param monster
   *            the monster.
   */
  public void visit(Monster monster);

  /**
   * Displays a castle.
   * 
   * @param castle
   *            the castle.
   */
  public void visit(Castle castle);

}
