package fr.umlv.hmm2000.manager;

import fr.umlv.hmm2000.Heroe;
import fr.umlv.hmm2000.Merchant;
import fr.umlv.hmm2000.Resource;

public interface UIDisplayingVisitor {

	public void visit(Heroe heroe);

	public void visit(Resource resource);

	public void visit(Merchant merchant);

}
