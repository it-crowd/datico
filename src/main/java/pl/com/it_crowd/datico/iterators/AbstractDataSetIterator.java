package pl.com.it_crowd.datico.iterators;

import pl.com.it_crowd.datico.DataSet;
import pl.com.it_crowd.datico.util.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class AbstractDataSetIterator<T> implements DataSetIterator<T> {
// ------------------------------ FIELDS ------------------------------

    protected Map<Method, List> badValues = new HashMap<Method, List>();

    protected Class<? extends T> beanClass;

    protected Map<Method, List> goodValues = new HashMap<Method, List>();

    protected List<Method> methods;

// --------------------------- CONSTRUCTORS ---------------------------

    public AbstractDataSetIterator(Class<? extends T> beanClass)
    {
        this.beanClass = beanClass;
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface DataSetIterator ---------------------

    public void init(DataSet dataSet)
    {
        methods = new ArrayList<Method>(dataSet.methods());
        for (Method method : methods) {
//            noinspection unchecked
            goodValues.put(method, new ArrayList(dataSet.goodValues(method)));
//            noinspection unchecked
            badValues.put(method, new ArrayList(dataSet.badValues(method)));
        }
    }

// --------------------- Interface Iterator ---------------------

    public boolean hasNext()
    {
        return getIterator().hasNext();
    }

    public T next()
    {
        final int[] indexes = getIterator().next();
        T next = newInstance();
        for (int i = 0; i < indexes.length; i++) {
            Reflections.invokeAndWrap(methods.get(i), next, getValue(i, indexes[i]));
        }
        return next;
    }

    public void remove()
    {
        getIterator().remove();
    }

    protected abstract Iterator<int[]> getIterator();

    protected Object getValue(int methodIndex, int valueIndex)
    {
        final Method method = methods.get(methodIndex);
        final List badValues = this.badValues.get(method);
        final List goodValues = this.goodValues.get(method);
        return valueIndex >= badValues.size() ? goodValues.get(valueIndex - badValues.size()) : badValues.get(valueIndex);
    }

    protected T newInstance()
    {
        try {
            return beanClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Cannot instantiate class " + beanClass.getCanonicalName(), e);
        }
    }
}
