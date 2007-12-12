package fr.umlv.hmm2000.engine.guiinterface;

import fr.umlv.hmm2000.HeroeToDelete;
import fr.umlv.hmm2000.resource.Resource;
import fr.umlv.hmm2000.salesentity.SalesEntity;
import fr.umlv.hmm2000.warriors.Warrior;

public interface UIDisplayingVisitor {

  public void visit(HeroeToDelete heroe);

  public void visit(Resource resource);

  public void visit(SalesEntity salesEntity);
  
  public void visit(Warrior warrior);

}
