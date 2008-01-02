package fr.umlv.hmm2000.warrior.skill;

public interface Skill {
  public String getName();

  public void perform();

  public String getToolTipText();
  
  public static final Skill defaultSkill = new Skill(){
    @Override
    public String getName() {
      return "Aucun skill.";
    }

    @Override
    public String getToolTipText() {
      return null;
    }

    @Override
    public void perform() {
      throw new UnsupportedOperationException();      
    }
  };
}
