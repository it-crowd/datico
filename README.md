datico
======

Dataset generation for tests inspied by and based on Mockito.

If you want to test input data against validators, simple database constraints or similar you can define good and bad values for each attribute
and have datasets built dynamically.

    //Prepare the bean (i.e. JPA entity)
    Bean mocko = Datico.mock(Bean.class);

    //Assign good and bad attribute values
    Datico.assign(mocko.getId()).goodValues(Arrays.asList(0L, 1L, 2L, 9L)).badValues(Arrays.asList(5L));
    Datico.assign(mocko.getAge()).goodValues(Arrays.asList(18, 19, 29)).badValues(Arrays.asList(17, 30));
    Datico.assign(mocko.getName()).goodValues(Arrays.asList("John", "The guy with long name")).badValues(Arrays.asList("Jo", "The guy with too long name"));

    //Get dataset iterator; There are 2 stock iterators EachValueAsFewAsPossibleDataSetIterator and AllCombinationsDataSetIterator
    //You may create your own iterators
    Iterator<Bean> iterator = Datico.iterator(mocko, new AllCombinationsDataSetIterator<Bean>(mocko.getClass()));
    int i = 0;
    while (iterator.hasNext()) {
        Bean bean = iterator.next();
        //Let's do the monkey
        i++;
    }
    Assert.assertEquals(100, i);