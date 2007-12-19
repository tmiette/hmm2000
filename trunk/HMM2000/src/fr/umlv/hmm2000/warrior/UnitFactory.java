package fr.umlv.hmm2000.warrior;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.warrior.profil.Level;
import fr.umlv.hmm2000.warrior.profil.ProfilHero;
import fr.umlv.hmm2000.warrior.profil.ProfilWarrior;

public class UnitFactory {

  public static Fightable createWarrior(ProfilWarrior profil, Level level) {

    final double ratio = level.getRatio();
    return new Warrior(profil.name(), profil.getSprite(), profil
        .getPhysicalAttackValue()
        * ratio, profil.getPhysicalDefenseValue() * ratio, profil.getHealth()
        * ratio, profil.getSpeed(), profil.getAbilities(), profil
        .getAttackBahaviour());

  }
  
  public static Hero createHero(Player player, ProfilHero profil) {
    Hero h = new Hero(player, profil.getSprite(), profil.name());
    for (Fightable fightable : profil.getUnits()) {
    	fightable.setFightableContainer(h);
			try {
				h.addFightable(fightable);
			}
			catch (MaxNumberOfTroopsReachedException e) {
				//do nothing
			}
		}
    return h;
  }

}
