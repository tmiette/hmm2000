package fr.umlv.hmm2000.warrior.profil;

public enum Level {

  LEVEL_1(1.0),
  LEVEL_2(1.2),
  LEVEL_3(1.5);

  private final double ratio;

  private Level(double ratio) {
    this.ratio = ratio;
  }

  public double getRatio() {
    return this.ratio;
  }
}