package fr.umlv.hmm2000.engine.guiinterface;

import fr.umlv.hmm2000.resource.Resource;
import fr.umlv.hmm2000.salesentity.SalesEntity;
import fr.umlv.hmm2000.warrior.Hero;
import fr.umlv.hmm2000.warrior.Warrior;

public interface UIDisplayingVisitor {

  public void visit(Hero heroe);

  public void visit(Resource resource);

  public void visit(SalesEntity salesEntity);

  public void visit(Warrior warrior);

}
