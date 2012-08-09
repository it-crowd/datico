package pl.com.it_crowd.datico;

import org.mockito.internal.invocation.Invocation;
import org.mockito.internal.progress.MockingProgress;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataInvocationContainer {
// ------------------------------ FIELDS ------------------------------

    private Method methodForStubbing;

    private Map<Method, ValueSet> methodValueSetMap = new HashMap<Method, ValueSet>();

    private MockingProgress mockingProgress;

// --------------------------- CONSTRUCTORS ---------------------------

    public DataInvocationContainer(MockingProgress mockingProgress)
    {
        this.mockingProgress = mockingProgress;
    }

// -------------------------- OTHER METHODS --------------------------

    public <T> void badValues(Collection<T> values)
    {
        ValueSet valueSet = methodValueSetMap.get(methodForStubbing);
        if (valueSet == null) {
            valueSet = new ValueSet<T>();
            methodValueSetMap.put(methodForStubbing, valueSet);
        }
        valueSet.badValues(values);
    }

    public Collection<Object> badValues(Method method)
    {
        final ValueSet<Object> valueSet = methodValueSetMap.get(method);
        return valueSet == null ? null : valueSet.badValues();
    }

    public <T> void goodValues(Collection<T> values)
    {
        ValueSet valueSet = methodValueSetMap.get(methodForStubbing);
        if (valueSet == null) {
            valueSet = new ValueSet<T>();
            methodValueSetMap.put(methodForStubbing, valueSet);
        }
        valueSet.goodValues(values);
    }

    public Collection goodValues(Method method)
    {
        final ValueSet valueSet = methodValueSetMap.get(method);
        return valueSet == null ? null : valueSet.goodValues();
    }

    public Set<Method> methods()
    {
        return methodValueSetMap.keySet();
    }

    public void setInvocationForPotentialStubbing(Invocation invocation)
    {
        Method method = invocation.getMethod();
        final String methodName = method.getName();
        final boolean startsWithGet = methodName.startsWith("get");
        final boolean startsWithIs = methodName.startsWith("is");
        if (("toString".equals(methodName) && invocation.getArgumentsCount() == 0) || !startsWithGet && !startsWithIs) {
            return;
        }
        try {
            method = method.getDeclaringClass().getDeclaredMethod("set" + methodName.substring(startsWithGet ? 3 : 2), method.getReturnType());
        } catch (NoSuchMethodException e) {
            throw new NoCorrespondingSetterException("Cannot find corresponding setter for " + methodName, e);
        }
        this.methodForStubbing = method;
    }
}
