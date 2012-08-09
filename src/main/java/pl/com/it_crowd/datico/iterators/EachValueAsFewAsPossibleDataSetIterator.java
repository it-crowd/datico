package pl.com.it_crowd.datico.iterators;

import pl.com.it_crowd.datico.DataSet;

import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Generates data set for each value, good or bad, but once every value is used iteration is over.
 * Value order is random.
 * If there are 2 attributes a and b and they have corresponding values : {1,2,3}, {9,0}
 * then there will be 3 data sets generated:
 * {1;9 or 0}
 * {2;9 or 0}
 * {3;9 or 0}
 */
public class EachValueAsFewAsPossibleDataSetIterator<T> extends AbstractDataSetIterator<T> {
// ------------------------------ FIELDS ------------------------------

    private EachValueAsFewAsPossibleIterator iterator;

// --------------------------- CONSTRUCTORS ---------------------------

    public EachValueAsFewAsPossibleDataSetIterator(Class<? extends T> beanClass)
    {
        super(beanClass);
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    @Override
    protected Iterator<int[]> getIterator()
    {
        return iterator;
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface DataSetIterator ---------------------

    public void init(DataSet dataSet)
    {
        super.init(dataSet);
        final int[] sizes = new int[methods.size()];
        for (int i = 0; i < sizes.length; i++) {
            final Method method = methods.get(i);
            sizes[i] = goodValues.get(method).size() + badValues.get(method).size();
        }
        iterator = new EachValueAsFewAsPossibleIterator(sizes);
    }
}
