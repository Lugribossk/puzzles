package bo.gotthardt.util;

import com.google.common.collect.DiscreteDomains;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Range;
import com.google.common.collect.Ranges;

/**
 * Utility class for creating ranges of numbers to use with foreach iteration.
 *
 * @author Bo Gotthardt
 */
public class Interval {

    /**
     * Generate a range of integers, including the endpoints.
     *
     * @param fromInclusive the first number
     * @param toInclusive the last number
     * @return the numbers, in ascending order
     */
    public static ImmutableSortedSet<Integer> range(int fromInclusive, int toInclusive) {
        Range<Integer> range = Ranges.closed(fromInclusive, toInclusive);
        return range.asSet(DiscreteDomains.integers());
    }

    /**
     * Generate a range of integers from zero (inclusive) to an endpoint (exclusive).
     *
     * @param toExclusive the last number, not included
     * @return the numbers, in ascending order
     */
    public static ImmutableSortedSet<Integer> zeroUpTo(int toExclusive) {
        Range<Integer> range = Ranges.closedOpen(0, toExclusive);
        return range.asSet(DiscreteDomains.integers());
    }

    /**
     * Private constructor.
     */
    private Interval() {
        throw new AssertionError("Static utility class.");
    }
}
