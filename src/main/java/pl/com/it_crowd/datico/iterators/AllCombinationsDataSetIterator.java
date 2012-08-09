package pl.com.it_crowd.datico.iterators;

import pl.com.it_crowd.datico.DataSet;

import java.lang.reflect.Method;

/**
 * Generates data sets with all combinations of attributes.
 * If there are 2 attributes a and b and they have corresponding values : {1,2,3}, {9,0}
 * then there will be 6 data sets generated:
 * {1;9}
 * {2;9}
 * {3;9}
 * {1;0}
 * {2;0}
 * {3;0}
 */
public class AllCombinationsDataSetIterator<T> extends AbstractDataSetIterator<T> {
// ------------------------------ FIELDS ------------------------------

    private AllCombinationsIterator iterator;

// --------------------------- CONSTRUCTORS ---------------------------

    public AllCombinationsDataSetIterator(Class<? extends T> beanClass)
    {
        super(beanClass);
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    @Override
    public AllCombinationsIterator getIterator()
    {
        return iterator;
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface DataSetIterator ---------------------

    @Override
    public void init(DataSet dataSet)
    {
        super.init(dataSet);
        final int[] sizes = new int[methods.size()];
        for (int i = 0; i < sizes.length; i++) {
            final Method method = methods.get(i);
            sizes[i] = goodValues.get(method).size() + badValues.get(method).size();
        }
        iterator = new AllCombinationsIterator(sizes);
    }
}
