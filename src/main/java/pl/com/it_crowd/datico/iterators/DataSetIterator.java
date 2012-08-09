package pl.com.it_crowd.datico.iterators;

import pl.com.it_crowd.datico.DataSet;

import java.util.Iterator;

public interface DataSetIterator<T> extends Iterator<T> {
// -------------------------- OTHER METHODS --------------------------

    void init(DataSet dataSet);
}
