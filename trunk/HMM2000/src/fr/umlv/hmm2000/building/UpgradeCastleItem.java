package fr.umlv.hmm2000.building;

public class UpgradeCastleItem implements CastleItem {

  @Override
  public String getSuggestion() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void perform() {

    castle.upgradeFactory(Castle.defaultWarrior);

  }

}
