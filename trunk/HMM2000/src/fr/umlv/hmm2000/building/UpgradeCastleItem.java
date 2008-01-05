package fr.umlv.hmm2000.building;

import fr.umlv.hmm2000.PriceFactory;
import fr.umlv.hmm2000.warrior.profil.Level;

public class UpgradeCastleItem implements CastleItem {

	private final Castle castle;
	
	
	public UpgradeCastleItem(Castle castle) {

		this.castle = castle;
	}
	
  @Override
  public String getSuggestion() {
    
  	return "Am�liorer le chateau";
  }

  @Override
  public void perform() {

  	Level level = castle.getFactoryLevel(Castle.defaultWarrior);
		if (castle.getPlayer().spend(
				PriceFactory.getWarriorFactoryPrice(Castle.defaultWarrior, level))) {
			UpgradeCastleItem.this.castle.upgradeFactory(Castle.defaultWarrior);
		}
		// TODO pas assez argent
    
  }

}
