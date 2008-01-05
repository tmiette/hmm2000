package fr.umlv.hmm2000.map;

public class Location {

	private final int x;

	private final int y;

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof Location)) {
			return false;
		}

		Location l = (Location) o;
		return (this.x == l.x && this.y == l.y);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Location[x=");
		sb.append(this.x);
		sb.append(", y=");
		sb.append(this.y);
		sb.append("]");
		return sb.toString();
	}

}
