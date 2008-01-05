package fr.umlv.hmm2000.unit.profil;

public enum Level {

  LEVEL_3(1.5, null),
  LEVEL_2(1.2, Level.LEVEL_3),
  LEVEL_1(1.0, Level.LEVEL_2);

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