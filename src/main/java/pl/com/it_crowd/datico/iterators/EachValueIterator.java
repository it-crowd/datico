package pl.com.it_crowd.datico.iterators;

import java.util.Iterator;
import java.util.Random;

public class EachValueIterator implements Iterator<int[]> {
// ------------------------------ FIELDS ------------------------------

    private int[] majorSizes;

    private int[] minorSizes;

    private final Random random;

    private int x;

    private int y;

// --------------------------- CONSTRUCTORS ---------------------------

    public EachValueIterator(int[] majorSizes, int[] minorSizes)
    {
        if (majorSizes == null || majorSizes.length == 0) {
            throw new IllegalArgumentException("Major sizes must not be empty");
        }
        if (minorSizes == null || minorSizes.length == 0) {
            throw new IllegalArgumentException("Minor sizes must not be empty");
        }
        this.majorSizes = majorSizes;
        this.minorSizes = minorSizes;
        if (majorSizes.length != minorSizes.length) {
            throw new IllegalArgumentException("Major and minor sizes lengths must be equal");
        }
        for (int value : minorSizes) {
            if (value <= 0) {
                throw new IllegalArgumentException("Minor sizes must be positive");
            }
        }
        random = new Random();
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface Iterator ---------------------

    public boolean hasNext()
    {
        return x < majorSizes.length && y <= majorSizes[majorSizes.length - 1];
    }

    public int[] next()
    {
        while (x < majorSizes.length && y >= majorSizes[x]) {
            y = 0;
            x++;
        }
        final int[] ints = new int[majorSizes.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = i == x ? y : random.nextInt(minorSizes[i]);
        }
        y++;
        while (x < majorSizes.length && y >= majorSizes[x]) {
            y = 0;
            x++;
        }
        return ints;
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }
}
