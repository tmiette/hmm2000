package fr.umlv.hmm2000;

public class WorldLevel {

	public enum Level {
		LEVEL_1(1), LEVEL_2(2), LEVEL_3(3);

		private double level;

		private Level(int n) {

			this.level = n;
		}

		public double getLevel() {

			return level;
		}

		public void setCurrentWorldLevel(double level) {

			this.level = level;
		}
	};

	private static double worldCurrentLevel = Level.LEVEL_1.getLevel();


	public static double getWorldCurrentLevel() {

		return worldCurrentLevel;
	}

	public static void setWorldCurrentLevel(Level level) {

		worldCurrentLevel = level.getLevel();
	}
}
