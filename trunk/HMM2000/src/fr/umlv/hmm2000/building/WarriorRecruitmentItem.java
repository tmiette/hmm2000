package fr.umlv.hmm2000.building;

import java.util.ArrayList;

import fr.umlv.hmm2000.warrior.UnitFactory;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.warrior.profil.ProfilWarrior;

public class WarriorRecruitmentItem implements CastleItem {

	private final Castle castle;
	
	
	public WarriorRecruitmentItem(Castle castle) {

		this.castle = castle;
	}
	
  @Override
  public String getSuggestion() {
    return "Recruter un guerrier";
  }

  @Override
  public void perform() {

  	ArrayList<CastleItem> items = new ArrayList<CastleItem>();
    items.add(CastleItem.defaultItem);
    for (final ProfilWarrior profil : ProfilWarrior.values()) {
      items.add(new CastleItem() {
        @Override
        public String getSuggestion() {
          return profil.name();
        }

        @Override
        public void perform() {
          try {
						WarriorRecruitmentItem.this.castle.addFightable(UnitFactory.createWarrior(profil, WarriorRecruitmentItem.this.castle.getFactoryLevel(profil)));
					}
					catch (MaxNumberOfTroopsReachedException e) {
						// TODO Auto-generated catch block
					}
        }

      });
    }

  }

}
