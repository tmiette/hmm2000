package fr.umlv.hmm2000.building;

public class HeroRecruitmentItem implements CastleItem {

  private final Castle castle;

  public HeroRecruitmentItem(Castle castle) {
    this.castle = castle;
  }

  @Override
  public String getSuggestion() {
    return "Recruter un héros";
  }

  @Override
  public void perform() {
    System.err.println(this.getSuggestion());
  }

}
