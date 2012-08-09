package pl.com.it_crowd.datico.iterators;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class EachValueAsFewAsPossibleIterator implements Iterator<int[]> {
// ------------------------------ FIELDS ------------------------------

    private boolean hasNext = true;

    private final Random random;

    private int[] sizes;

    private List<Set<Integer>> usedIndexes;

    private int x;

    private int y;

// --------------------------- CONSTRUCTORS ---------------------------

    public EachValueAsFewAsPossibleIterator(int[] sizes)
    {
        if (sizes == null || sizes.length == 0) {
            throw new IllegalArgumentException("Sizes must not be empty");
        }
        this.sizes = sizes;
        usedIndexes = new ArrayList<Set<Integer>>(sizes.length);
        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i] <= 0) {
                throw new IllegalArgumentException("Each size must be positive");
            }
            usedIndexes.add(i, new HashSet<Integer>());
        }
        random = new Random();
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface Iterator ---------------------

    public boolean hasNext()
    {
        return hasNext && x < sizes.length && y < sizes[x];
    }

    public int[] next()
    {
        while (x < sizes.length && y >= sizes[x]) {
            y = 0;
            x++;
        }
        hasNext = false;
        final int[] ints = new int[sizes.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = random(i);
            if (usedIndexes.get(i).size() < sizes[i]) {
                hasNext = true;
            }
        }
        y++;
        while (x < sizes.length && y >= sizes[x]) {
            y = 0;
            x++;
        }
        return ints;
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

    private int random(int index)
    {
        int value = random.nextInt(sizes[index]);
        int initValue = value;
        int step = value == 0 || (value < sizes[index] && random.nextBoolean()) ? 1 : -1;
        final Set<Integer> indexes = usedIndexes.get(index);
        while (indexes.size() < sizes[index] && indexes.contains(value)) {
            value += step;
        }
        if (value < 0 || value >= sizes[index]) {
            step *= -1;
            value = initValue;
            while (indexes.size() < sizes[index] && indexes.contains(value)) {
                value += step;
            }
        }
        indexes.add(value);
        return value;
    }
}
