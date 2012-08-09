package pl.com.it_crowd.datico;

import junit.framework.Assert;
import org.junit.Test;
import pl.com.it_crowd.datico.iterators.AllCombinationsDataSetIterator;
import pl.com.it_crowd.datico.iterators.EachValueAsFewAsPossibleDataSetIterator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class DaticoTest {
// -------------------------- OTHER METHODS --------------------------

    @Test
    public void booleanGetter()
    {
        final IsGetterBean mocko = Datico.mock(IsGetterBean.class);

        System.out.println(mocko);
        System.out.println(("--------------"));

        Datico.assign(mocko.isActive()).goodValues(Arrays.asList(true)).badValues(Arrays.asList(false));

        int i;
        Iterator<IsGetterBean> iterator;
        iterator = Datico.iterator(mocko, new EachValueAsFewAsPossibleDataSetIterator<IsGetterBean>(mocko.getClass()));
        i = 0;
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            i++;
        }
        Assert.assertEquals(2, i);
    }

    @Test
    public void emptyGoodValues()
    {
        final Bean mocko = Datico.mock(Bean.class);
        int i;
        Iterator<Bean> iterator;

        Datico.assign(mocko.getId()).goodValues(Collections.<Long>emptyList()).badValues(Arrays.asList(0L, 4L));

        iterator = Datico.iterator(mocko, new EachValueAsFewAsPossibleDataSetIterator<Bean>(mocko.getClass()));
        i = 0;
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            i++;
        }
        Assert.assertEquals(2, i);

        iterator = Datico.iterator(mocko, new AllCombinationsDataSetIterator<Bean>(mocko.getClass()));
        i = 0;
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            i++;
        }
        Assert.assertEquals(2, i);

        Datico.assign(mocko.getId()).badValues(Collections.<Long>emptyList()).goodValues(Arrays.asList(0L, 4L));

        iterator = Datico.iterator(mocko, new EachValueAsFewAsPossibleDataSetIterator<Bean>(mocko.getClass()));
        i = 0;
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            i++;
        }
        Assert.assertEquals(2, i);

        iterator = Datico.iterator(mocko, new AllCombinationsDataSetIterator<Bean>(mocko.getClass()));
        i = 0;
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            i++;
        }
        Assert.assertEquals(2, i);
    }

    @Test
    public void iteration()
    {
        final Bean mocko = Datico.mock(Bean.class);
        Datico.assign(mocko.getId()).goodValues(Arrays.asList(1L, 2L, 3L)).badValues(Arrays.asList(0L, 4L));
        Datico.assign(mocko.getId()).goodValues(Arrays.asList(0L, 1L, 2L, 9L)).badValues(Arrays.asList(5L));
        int i;
        Iterator<Bean> iterator;

        iterator = Datico.iterator(mocko, new EachValueAsFewAsPossibleDataSetIterator<Bean>(mocko.getClass()));
        i = 0;
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            i++;
        }
        Assert.assertEquals(5, i);

        System.out.println(("--------------"));
        iterator = Datico.iterator(mocko, new AllCombinationsDataSetIterator<Bean>(mocko.getClass()));
        i = 0;
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            i++;
        }
        Assert.assertEquals(5, i);

        Datico.assign(mocko.getAge()).goodValues(Arrays.asList(18, 19, 29)).badValues(Arrays.asList(17, 30));

        System.out.println(("--------------"));
        iterator = Datico.iterator(mocko, new EachValueAsFewAsPossibleDataSetIterator<Bean>(mocko.getClass()));
        i = 0;
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            i++;
        }
        Assert.assertEquals(5, i);

        System.out.println(("--------------"));
        iterator = Datico.iterator(mocko, new AllCombinationsDataSetIterator<Bean>(mocko.getClass()));
        i = 0;
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            i++;
        }
        Assert.assertEquals(25, i);

        Datico.assign(mocko.getName()).goodValues(Arrays.asList("John", "The guy with long name")).badValues(Arrays.asList("Jo", "The guy with too long name"));

        System.out.println(("--------------"));
        iterator = Datico.iterator(mocko, new AllCombinationsDataSetIterator<Bean>(mocko.getClass()));
        i = 0;
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            i++;
        }
        Assert.assertEquals(100, i);
    }

    @Test(expected = NoCorrespondingSetterException.class)
    public void noCorrespondingSetter()
    {
        final NoSetterBean mocko = Datico.mock(NoSetterBean.class);
        System.out.println(mocko);
        Datico.assign(mocko.getId()).goodValues(Arrays.asList(1L, 2L, 3L)).badValues(Arrays.asList(0L, 4L));
    }

    @Test
    public void noGoodValues()
    {
        final Bean mocko = Datico.mock(Bean.class);
        Datico.assign(mocko.getId()).badValues(Arrays.asList(0L, 4L));
    }

// -------------------------- INNER CLASSES --------------------------

    public static class Bean {
// ------------------------------ FIELDS ------------------------------

        private int age;

        private long id;

        private String name;

// --------------------- GETTER / SETTER METHODS ---------------------

        public int getAge()
        {
            return age;
        }

        public void setAge(int age)
        {
            this.age = age;
        }

        public long getId()
        {
            return id;
        }

        public void setId(long id)
        {
            this.id = id;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

// ------------------------ CANONICAL METHODS ------------------------

        @Override
        public String toString()
        {
            return "Bean{" +
                "age=" + age +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
        }
    }

    public static class IsGetterBean {
// ------------------------------ FIELDS ------------------------------

        private boolean active;

// --------------------- GETTER / SETTER METHODS ---------------------

        public boolean isActive()
        {
            return active;
        }

        public void setActive(boolean active)
        {
            this.active = active;
        }

// ------------------------ CANONICAL METHODS ------------------------

        @Override
        public String toString()
        {
            return "IsGetterBean{" +
                "active=" + active +
                '}';
        }
    }

    public static class NoSetterBean {
// ------------------------------ FIELDS ------------------------------

        private long id;

// --------------------------- CONSTRUCTORS ---------------------------

        public NoSetterBean()
        {

        }

        public NoSetterBean(long id)
        {
            this.id = id;
        }

// --------------------- GETTER / SETTER METHODS ---------------------

        public long getId()
        {
            return id;
        }

// ------------------------ CANONICAL METHODS ------------------------

        @Override
        public String toString()
        {
            return "NoSetterBean{" +
                "id=" + id +
                '}';
        }
    }
}
