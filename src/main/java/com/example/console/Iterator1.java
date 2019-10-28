package com.example.console;


import java.util.Iterator;

//use Iterator's next() to implement hasNext and getObect - get next element
public class Iterator1<E> {
    private Iterator<E> iterator;
    private E nextElement = null;

    public Iterator1(Iterator<E> iterator)
    {
        this.iterator = iterator;
    }

    public boolean hasNext()
    {
        try
        {
            if(nextElement != null)
                return true;
            nextElement = iterator.next();
            return nextElement != null;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public E getNextObject()
    {
        E tmp = null;
        //hasNext() might not be called
        if(nextElement == null)
        {
            hasNext();
        }

        if(nextElement != null)
        {
            tmp = nextElement;
            nextElement = null;
        }

        return tmp;
    }
}
