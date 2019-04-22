package RaynEngine.core.particles;

import java.util.List;

/**
 * A simple implementation of an insertion sort. I implemented this very quickly
 * the other day so it may not be perfect or the most efficient! Feel free to
 * implement your own sorter instead.
 * 
 * @author Karl
 *
 */
public class InsertionSort {

	/**
	 * Sorts a list of RaynEngine.core.particles so that the RaynEngine.core.particles with the highest distance
	 * from the camera are first, and the RaynEngine.core.particles with the shortest distance
	 * are last.
	 * 
	 * @param list
	 *            - the list of RaynEngine.core.particles needing sorting.
	 */
	public static void sortHighToLow(List<Particle> list) {
		for (int i = 1; i < list.size(); i++) {
			Particle item = list.get(i);
			if (item.getDistance() > list.get(i - 1).getDistance()) {
				sortUpHighToLow(list, i);
			}
		}
	}

	private static void sortUpHighToLow(List<Particle> list, int i) {
		Particle item = list.get(i);
		int attemptPos = i - 1;
		while (attemptPos != 0 && list.get(attemptPos - 1).getDistance() < item.getDistance()) {
			attemptPos--;
		}
		list.remove(i);
		list.add(attemptPos, item);
	}

}
