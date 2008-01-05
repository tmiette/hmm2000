package fr.umlv.hmm2000.engine.guiinterface;

import fr.umlv.hmm2000.building.Castle;
import fr.umlv.hmm2000.resource.Resource;
import fr.umlv.hmm2000.salesentity.SalesEntity;
import fr.umlv.hmm2000.unit.Hero;
import fr.umlv.hmm2000.unit.Monster;
import fr.umlv.hmm2000.unit.Warrior;

public interface UIDisplayingVisitor {

  public void visit(Hero heroe);

  public void visit(Resource resource);

  public void visit(SalesEntity salesEntity);

  public void visit(Warrior warrior);
  
  public void visit(Monster monster);
  
  public void visit(Castle castle);

}
