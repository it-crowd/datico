package pl.com.it_crowd.datico.iterators;

import java.util.Arrays;
import java.util.Iterator;

public class AllCombinationsIterator implements Iterator<int[]> {
// ------------------------------ FIELDS ------------------------------

    private boolean hasNext = true;

    private int[] positions;

    private int[] sizes;

// --------------------------- CONSTRUCTORS ---------------------------

    public AllCombinationsIterator(int[] sizes)
    {
        if (sizes == null || sizes.length == 0) {
            throw new IllegalArgumentException("Sizes must not be empty");
        }
        this.sizes = Arrays.copyOf(sizes, sizes.length);
        positions = new int[sizes.length];
        for (int value : sizes) {
            if (value <= 0) {
                throw new IllegalArgumentException("Each size must be positive");
            }
        }
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface Iterator ---------------------

    public boolean hasNext()
    {
        return hasNext;
    }

    public int[] next()
    {
        final int[] ints = Arrays.copyOf(positions, positions.length);
        positions[0]++;
        for (int i = 0; i < positions.length; i++) {
            if (positions[i] >= sizes[i]) {
                if (i == positions.length - 1) {
                    hasNext = false;
                    break;
                }
                positions[i] = 0;
                positions[i + 1]++;
            }
        }
        return ints;
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }
}