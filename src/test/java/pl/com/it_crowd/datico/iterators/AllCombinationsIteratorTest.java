package pl.com.it_crowd.datico.iterators;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;

public class AllCombinationsIteratorTest {
// -------------------------- OTHER METHODS --------------------------

    @Test
    public void iteration()
    {
        AllCombinationsIterator iterator = new AllCombinationsIterator(new int[]{2, 2, 4});
        int i = 0;
        while (iterator.hasNext()) {
            System.out.println(Arrays.toString(iterator.next()));
            i++;
        }
        Assert.assertEquals(16, i);

        iterator = new AllCombinationsIterator(new int[]{2, 2});
        i = 0;
        while (iterator.hasNext()) {
            System.out.println(Arrays.toString(iterator.next()));
            i++;
        }
        Assert.assertEquals(4, i);

        iterator = new AllCombinationsIterator(new int[]{1, 1});
        i = 0;
        while (iterator.hasNext()) {
            System.out.println(Arrays.toString(iterator.next()));
            i++;
        }
        Assert.assertEquals(1, i);

        iterator = new AllCombinationsIterator(new int[]{3, 3, 5});
        i = 0;
        while (iterator.hasNext()) {
            System.out.println(Arrays.toString(iterator.next()));
            i++;
        }
        Assert.assertEquals(45, i);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullParam()
    {
        new AllCombinationsIterator(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroLength()
    {
        new AllCombinationsIterator(new int[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroSize()
    {
        new AllCombinationsIterator(new int[]{1, 0});
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroSize2()
    {
        new AllCombinationsIterator(new int[]{0, 0});
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroSize3()
    {
        new AllCombinationsIterator(new int[]{0, 1});
    }
}
