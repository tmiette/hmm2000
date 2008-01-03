package fr.umlv.hmm2000.building;

public class UpgradeCastleItem implements CastleItem {

	private final Castle castle;
	
	
	public UpgradeCastleItem(Castle castle) {

		this.castle = castle;
	}
	
  @Override
  public String getSuggestion() {
    
  	return "Améliorer le chateau";
  }

  @Override
  public void perform() {

    UpgradeCastleItem.this.castle.upgradeFactory(Castle.defaultWarrior);

  }

}
