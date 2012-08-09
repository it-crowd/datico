package pl.com.it_crowd.datico;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ValueSet<T> {
// ------------------------------ FIELDS ------------------------------

    private Set<T> badValues = new HashSet<T>();

    private Set<T> goodValues = new HashSet<T>();

    private final Set<T> unmodifiableBadValues;

    private final Set<T> unmodifiableGoodValues;

// --------------------------- CONSTRUCTORS ---------------------------

    public ValueSet()
    {
        unmodifiableGoodValues = Collections.unmodifiableSet(goodValues);
        unmodifiableBadValues = Collections.unmodifiableSet(badValues);
    }

// -------------------------- OTHER METHODS --------------------------

    public Set<T> badValues()
    {
        return unmodifiableBadValues;
    }

    public ValueSet badValues(Collection<T> values)
    {
        badValues.clear();
        badValues.addAll(values);
        return this;
    }

    public Set<T> goodValues()
    {
        return unmodifiableGoodValues;
    }

    public ValueSet goodValues(Collection<T> values)
    {
        goodValues.clear();
        goodValues.addAll(values);
        return this;
    }
}
