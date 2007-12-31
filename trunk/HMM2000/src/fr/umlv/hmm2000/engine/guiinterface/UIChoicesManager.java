package fr.umlv.hmm2000.engine.guiinterface;

import java.util.List;

import fr.umlv.hmm2000.salesentity.Sellable;
import fr.umlv.hmm2000.util.Pair;
import fr.umlv.hmm2000.warrior.skill.Skill;

public interface UIChoicesManager {

	public Sellable submit(List<Pair<Sellable, Integer>> items);
	
	public Skill submit (List<Skill> skills);
}
