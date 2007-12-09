package fr.umlv.hmm2000.gui;

import fr.umlv.hmm2000.Heroe;
import fr.umlv.hmm2000.Merchant;
import fr.umlv.hmm2000.Resource;
import fr.umlv.hmm2000.manager.UIDisplayingVisitor;

public class LawrenceDisplayingVisitor implements UIDisplayingVisitor {

	@Override
	public void visit(Heroe heroe) {
		System.out.println(heroe.getName());
		System.out.println(heroe.getPlayer().getResources());
	}

	@Override
	public void visit(Resource resource) {
		System.out.println(resource.getKind() + " :"
				+ resource.getCurrentValue() + "/" + resource.getMaxValue());
	}

	@Override
	public void visit(Merchant merchant) {
		System.out.println(merchant.getName());
		System.out.println(merchant.getSpells());
	}

}
