package pl.com.it_crowd.datico;

import org.mockito.internal.progress.IOngoingStubbing;

import java.util.Collection;

public class DataStubbing<T> implements IOngoingStubbing {
// ------------------------------ FIELDS ------------------------------

    private DataInvocationContainer invocationContainer;

// --------------------------- CONSTRUCTORS ---------------------------

    public DataStubbing(DataInvocationContainer invocationContainer)
    {
        this.invocationContainer = invocationContainer;
    }

// -------------------------- OTHER METHODS --------------------------

    public DataStubbing<T> badValues(Collection<T> values)
    {
        invocationContainer.badValues(values);
        return this;
    }

    public DataStubbing<T> goodValues(Collection<T> values)
    {
        invocationContainer.goodValues(values);
        return this;
    }
}
