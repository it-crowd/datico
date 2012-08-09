package pl.com.it_crowd.datico;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

public interface DataSet {
// -------------------------- OTHER METHODS --------------------------

    Collection badValues(Method method);

    Collection goodValues(Method method);

    Set<Method> methods();
}
