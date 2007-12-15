package fr.umlv.hmm2000.engine.guiinterface;

import java.util.List;

import fr.umlv.hmm2000.salesentity.Sellable;
import fr.umlv.hmm2000.util.Pair;

public interface UIChoicesManager {

	public Sellable submit(List<Pair<Sellable, Integer>> items);
}
