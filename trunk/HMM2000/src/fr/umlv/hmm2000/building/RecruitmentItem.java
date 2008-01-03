package fr.umlv.hmm2000.building;

public class RecruitmentItem implements CastleItem {

  private final Castle castle;

  public RecruitmentItem(Castle castle) {
    this.castle = castle;
  }

  @Override
  public String getSuggestion() {
    return "Recruter une créature";
  }

  @Override
  public void perform() {
    System.err.println(this.getSuggestion());
  }

}
