package fr.umlv.hmm2000.unit.profil;

import fr.umlv.hmm2000.unit.Fightable;

public interface AttackBehaviour {

  public boolean isAttackable(Fightable attacker, Fightable defender);

}
