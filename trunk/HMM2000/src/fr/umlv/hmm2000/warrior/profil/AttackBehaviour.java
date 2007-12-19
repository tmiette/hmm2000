package fr.umlv.hmm2000.warrior.profil;

import fr.umlv.hmm2000.warrior.Fightable;

public interface AttackBehaviour {

  public boolean isAttackable(Fightable attacker, Fightable defender);

}
