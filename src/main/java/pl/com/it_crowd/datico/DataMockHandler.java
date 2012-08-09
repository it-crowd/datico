package pl.com.it_crowd.datico;

import org.mockito.internal.MockitoInvocationHandler;
import org.mockito.internal.invocation.Invocation;
import org.mockito.internal.progress.MockingProgress;
import org.mockito.internal.progress.ThreadSafeMockingProgress;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

public class DataMockHandler implements MockitoInvocationHandler, DataSet {
// ------------------------------ FIELDS ------------------------------

    private DataInvocationContainer invocationContainer;

    private MockingProgress mockingProgress;

// --------------------------- CONSTRUCTORS ---------------------------

    public DataMockHandler()
    {
        mockingProgress = new ThreadSafeMockingProgress();
        invocationContainer = new DataInvocationContainer(mockingProgress);
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface DataSet ---------------------

    public Collection badValues(Method method)
    {
        return invocationContainer.badValues(method);
    }

    public Collection goodValues(Method method)
    {
        return invocationContainer.goodValues(method);
    }

    public Set<Method> methods()
    {
        return invocationContainer.methods();
    }

// --------------------- Interface MockitoInvocationHandler ---------------------

    public Object handle(Invocation invocation) throws Throwable
    {
        invocationContainer.setInvocationForPotentialStubbing(invocation);
        mockingProgress.reportOngoingStubbing(new DataStubbing(invocationContainer));
        return invocation.callRealMethod();
    }
}
