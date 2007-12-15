package fr.umlv.hmm2000.warrior;

public enum Level {

	LEVEL_1 {

		public double getRatio() {

			return 0;
		}
	},
	LEVEL_2 {

		public double getRatio() {

			return 0.20;
		}
	},
	LEVEL_3 {

		public double getRatio() {

			return 0.50;
		}
	};

	public double getRatio() {

		return 0;
	}
}
