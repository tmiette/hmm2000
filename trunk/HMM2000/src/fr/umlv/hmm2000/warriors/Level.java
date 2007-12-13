package fr.umlv.hmm2000.warriors;

public enum Level {

	LEVEL_1(1) {

		public double getRatio() {

			return 0.20;
		}
	},
	LEVEL_2(2) {

		public double getRatio() {

			return 0.50;
		}
	},
	LEVEL_3(3) {

		public double getRatio() {

			return 0.70;
		}
	};

	private double ratio;

	private Level(int level) {

		this.ratio = level;
	}

	public double getRatio() {

		return this.ratio;
	}
}
