package fr.umlv.hmm2000.engine.ai;

import java.util.List;

import fr.umlv.hmm2000.unit.Fightable;

public interface AICoreManager {

  public void performAttack();

  public abstract Fightable selectAttacker(List<Fightable> warriors);

  public abstract Fightable selectDefender(Fightable warrior,
      List<Fightable> warriors);
}
