package fr.umlv.hmm2000.warrior.profil;

public enum Level {

  LEVEL_1(1.0, Level.LEVEL_2),
  LEVEL_2(1.2, Level.LEVEL_3),
  LEVEL_3(1.5, null);

  private final double ratio;
  
  private final Level next;

  private Level(double ratio, Level next) {
    this.ratio = ratio;
    this.next = next;
  }

  public double getRatio() {
    return this.ratio;
  }
  
  public Level getNextLevel() {

		return this.next;
	}
}