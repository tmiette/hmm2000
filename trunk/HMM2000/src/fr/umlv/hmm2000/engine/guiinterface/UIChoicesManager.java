package fr.umlv.hmm2000.engine.guiinterface;

import java.util.List;

import fr.umlv.hmm2000.building.CastleItem;
import fr.umlv.hmm2000.salesentity.Sellable;
import fr.umlv.hmm2000.util.Pair;
import fr.umlv.hmm2000.warrior.skill.Skill;

public interface UIChoicesManager {

  public Sellable submit(String message, List<Pair<Sellable, Integer>> items);

  public Skill submit(String message, List<Skill> skills);

  public CastleItem submit(String message, List<CastleItem> items);
}
