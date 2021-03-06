package usantatecla;

public class Interval {

	private Min min;
	private Max max;

	public Interval(Min min, Max max) {
		assert min.value <= max.value;
		this.min = min;
		this.max = max;
	}

	public boolean include(double value) {
			return this.min.isWithin(value) && this.max.isWithin(value);
	}

	public boolean includedInBothIntervals(Interval interval, double value) {
		if (this.include(value) && interval.include(value))
			return true;
		/**
		 * TODO: Aquí falta comprobar, en caso de que esté en uno de los intervalos,
		 *  si no es extremo del otro intervalo (OK) y si lo es,
		 *  si dicho extremo es cerrado (OK). Si es abierto no intersecta
		 */

	}

	public boolean hasAtLeastOneCommonValue(Interval interval) {
		return this.includedInBothIntervals(interval, interval.min.value)
				|| this.includedInBothIntervals(interval, interval.max.value)
				|| interval.includedInBothIntervals(this, this.min.value)
				|| interval.includedInBothIntervals(this, this.max.value);
	}

	public boolean isIntersected(Interval interval) {
		return this.equals(interval) || this.hasAtLeastOneCommonValue(interval);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((max == null) ? 0 : max.hashCode());
		result = prime * result + ((min == null) ? 0 : min.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Interval other = (Interval) obj;
		if (max == null) {
			if (other.max != null)
				return false;
		} else if (!max.equals(other.max))
			return false;
		if (min == null) {
			if (other.min != null)
				return false;
		} else if (!min.equals(other.min))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.min.toString() + ", " + max.toString();
	}	

}