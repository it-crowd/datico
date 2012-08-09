package pl.com.it_crowd.datico;

import org.mockito.cglib.proxy.Callback;
import org.mockito.cglib.proxy.Factory;
import org.mockito.internal.MockitoInvocationHandler;
import org.mockito.internal.creation.MethodInterceptorFilter;
import org.mockito.internal.creation.MockSettingsImpl;
import org.mockito.internal.creation.jmock.ClassImposterizer;
import org.mockito.internal.progress.IOngoingStubbing;
import org.mockito.internal.progress.MockingProgress;
import org.mockito.internal.progress.ThreadSafeMockingProgress;
import pl.com.it_crowd.datico.iterators.DataSetIterator;

import java.util.Iterator;

public class Datico {
// ------------------------------ FIELDS ------------------------------

    static final Datico INSTANCE = new Datico();

    private final MockingProgress mockingProgress = new ThreadSafeMockingProgress();

// -------------------------- STATIC METHODS --------------------------

    public static <T> DataStubbing<T> assign(@SuppressWarnings("UnusedParameters") T methodCall)
    {
        final IOngoingStubbing stubbing = INSTANCE.mockingProgress.pullOngoingStubbing();
        if (!(stubbing instanceof DataStubbing)) {
            throw new IllegalStateException(String.format("Not a DataStubbing: %s (%s)", stubbing, (stubbing == null ? null : stubbing.getClass())));
        }
        //noinspection unchecked
        return (DataStubbing<T>) stubbing;
    }

    private static <T> MethodInterceptorFilter getInterceptor(T mock)
    {
        if (!(mock instanceof Factory)) {
            return null;
        }
        Factory factory = (Factory) mock;
        Callback callback = factory.getCallback(0);
        if (callback instanceof MethodInterceptorFilter) {
            return (MethodInterceptorFilter) callback;
        }
        return null;
    }

    public static <T> Iterator<T> iterator(T mock, DataSetIterator<T> iterator)
    {
        final MockitoInvocationHandler handler = getInterceptor(mock).getHandler();
        if (!(handler instanceof DataSet)) {
            throw new IllegalArgumentException("Argument is not a Datico mock");
        }
        iterator.init((DataSet) handler);
        return iterator;
    }

    public static <T> T mock(Class<T> clazz)
    {
        MethodInterceptorFilter filter = new MethodInterceptorFilter(new DataMockHandler(), new MockSettingsImpl());
        return ClassImposterizer.INSTANCE.imposterise(filter, clazz);
    }
}
