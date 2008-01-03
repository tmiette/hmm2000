package fr.umlv.hmm2000.building;

import java.util.ArrayList;

import fr.umlv.hmm2000.warrior.profil.ProfilWarrior;

public class UpgradeFactoryItem implements CastleItem {

	private final Castle castle;
	
	public UpgradeFactoryItem(Castle castle) {

		this.castle = castle;
	}
	
  @Override
  public String getSuggestion() {
    return "Quelle batiment souhaitez vous améliorer";
  }

  @Override
  public void perform() {

  	ArrayList<CastleItem> items = new ArrayList<CastleItem>();
    items.add(CastleItem.defaultItem);
    for (final ProfilWarrior profil : ProfilWarrior.values()) {
    	if (UpgradeFactoryItem.this.castle.canBuyWarrior(profil)) {
    		items.add(new CastleItem() {
          @Override
          public String getSuggestion() {
            return "Usine de " + profil.name();
          }

          @Override
          public void perform() {
            //TODO gerer largent
          	UpgradeFactoryItem.this.castle.upgradeFactory(profil);
          }

        });
			}
      
    }

  }

}
