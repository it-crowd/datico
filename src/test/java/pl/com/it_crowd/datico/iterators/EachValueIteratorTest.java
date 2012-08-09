package pl.com.it_crowd.datico.iterators;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;

public class EachValueIteratorTest {
// -------------------------- OTHER METHODS --------------------------

    @Test(expected = IllegalArgumentException.class)
    public void differentLengths1()
    {
        new EachValueIterator(new int[]{2, 2, 3}, new int[]{4, 10});
    }

    @Test(expected = IllegalArgumentException.class)
    public void differentLengths2()
    {
        new EachValueIterator(new int[]{2, 2, 3}, new int[]{4, 10, 4, 5});
    }

    @Test
    public void iteration()
    {
        EachValueIterator iterator = new EachValueIterator(new int[]{2, 2}, new int[]{4, 10});
        int i = 0;
        while (iterator.hasNext()) {
            System.out.println(Arrays.toString(iterator.next()));
            i++;
        }
        Assert.assertEquals(4, i);

        iterator = new EachValueIterator(new int[]{2, 2, 5}, new int[]{3, 1, 3});
        i = 0;
        while (iterator.hasNext()) {
            System.out.println(Arrays.toString(iterator.next()));
            i++;
        }
        Assert.assertEquals(9, i);

        iterator = new EachValueIterator(new int[]{0, 2, 5}, new int[]{2, 1, 2});
        i = 0;
        while (iterator.hasNext()) {
            System.out.println(Arrays.toString(iterator.next()));
            i++;
        }
        Assert.assertEquals(7, i);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullParam1()
    {
        new EachValueIterator(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullParam2()
    {
        new EachValueIterator(new int[]{1}, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullParam3()
    {
        new EachValueIterator(null, new int[]{1});
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroLength1()
    {
        new EachValueIterator(new int[]{}, new int[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroLength2()
    {
        new EachValueIterator(new int[]{}, new int[]{1});
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroLength3()
    {
        new EachValueIterator(new int[]{1}, new int[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroMinorSize1()
    {
        new EachValueIterator(new int[]{2, 2}, new int[]{0, 10});
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroMinorSize2()
    {
        new EachValueIterator(new int[]{2, 2}, new int[]{1, 0});
    }
}
