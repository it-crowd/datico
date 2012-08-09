package pl.com.it_crowd.datico.iterators;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;

public class EachValueAsFewAsPossibleIteratorTest {
// -------------------------- OTHER METHODS --------------------------

    @Test
    public void iteration()
    {
        EachValueAsFewAsPossibleIterator iterator = new EachValueAsFewAsPossibleIterator(new int[]{1, 2, 3});
        int i = 0;
        while (iterator.hasNext()) {
            System.out.println(Arrays.toString(iterator.next()));
            i++;
        }
        Assert.assertEquals(3, i);

        iterator = new EachValueAsFewAsPossibleIterator(new int[]{9, 2, 7});
        i = 0;
        while (iterator.hasNext()) {
            System.out.println(Arrays.toString(iterator.next()));
            i++;
        }
        Assert.assertEquals(9, i);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullParam()
    {
        new EachValueAsFewAsPossibleIterator(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroLength()
    {
        new EachValueAsFewAsPossibleIterator(new int[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroSize1()
    {
        new EachValueAsFewAsPossibleIterator(new int[]{0, 3, 7});
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroSize2()
    {
        new EachValueAsFewAsPossibleIterator(new int[]{3, 0, 7});
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroSize3()
    {
        new EachValueAsFewAsPossibleIterator(new int[]{0, 0, 0});
    }
}
