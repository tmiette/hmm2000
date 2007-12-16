package fr.umlv.hmm2000.engine.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.MovableElement;
import fr.umlv.hmm2000.util.Pair;

public class MoveEvent {

	private final MovableElement source;

	private final ArrayList<Step> moves;

	public MoveEvent(MovableElement source,
			List<Pair<Location, Double>> balancedLocations) {
		this.source = source;
		this.moves = this.initMoves(balancedLocations);

	}

	public List<Step> getMoves() {
		return this.moves;
	}

	public MovableElement getSource() {
		return this.source;
	}

	private ArrayList<Step> initMoves(
			List<Pair<Location, Double>> balancedLocations) {

		ArrayList<Step> movesList = new ArrayList<Step>();

		Iterator<Pair<Location, Double>> iterator = balancedLocations
				.iterator();
		Location start = null;
		Location end = null;

		double stepCount = this.source.getStepCount();
		double weight;
		boolean recheable = true;

		while (iterator.hasNext()) {
			Pair<Location, Double> p = iterator.next();
			start = end;
			end = p.getFirstElement();
			weight = p.getSecondElement();
			if (stepCount < weight) {
				recheable = false;
			}
			if (start != null && end != null) {
				stepCount -= weight;
				movesList.add(new Step(start, end, weight, recheable,
						stepCount));
			}
		}
		return movesList;
	}

	public class Step {

		private final Location start;

		private final Location end;

		private final double weight;

		private final boolean recheable;

		private final double remainingStepCount;

		private Step(Location start, Location end, double weight,
				boolean recheable, double remainingCost) {
			this.start = start;
			this.end = end;
			this.weight = weight;
			this.recheable = recheable;
			this.remainingStepCount = remainingCost;
		}

		public Location getStart() {
			return this.start;
		}

		public Location getEnd() {
			return this.end;
		}

		public double getWeight() {
			return this.weight;
		}

		public boolean isRecheable() {
			return this.recheable;
		}

		public double getRemainingStepCount() {
			return this.remainingStepCount;
		}

		public MovableElement getSource() {
			return MoveEvent.this.source;
		}
	}

}
