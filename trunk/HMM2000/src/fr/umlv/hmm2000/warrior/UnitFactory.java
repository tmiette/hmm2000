package fr.umlv.hmm2000.warrior;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.warrior.profil.Level;
import fr.umlv.hmm2000.warrior.profil.ProfilHero;
import fr.umlv.hmm2000.warrior.profil.ProfilWarrior;

public class UnitFactory {

  public static Warrior createWarrior(ProfilWarrior profil, Level level) {

    final double ratio = level.getRatio();
    return new Warrior(profil.name(), profil.getSprite(), profil
        .getPhysicalAttackValue()
        * ratio, profil.getPhysicalDefenseValue() * ratio, profil.getHealth()
        * ratio, profil.getSpeed(), profil.getAbilities(), profil
        .getAttackBahaviour());

  }
  
  public static List<Fightable> createWarriors(ProfilWarrior profil, Level level) {

		ArrayList<Fightable> list = new ArrayList<Fightable>();
		list.add(UnitFactory.createWarrior(profil, level));
		return list;
	}

  public static Hero createHero(Player player, ProfilHero profil) {
    return new Hero(player, profil.getSprite(), profil.name());

  }

}
