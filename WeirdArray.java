/**
   Course: CS 27500
   Name: Joseph Palicke
   Email: jpalicke@sbcglobal.net
   Assignment: 5

   WeirdArray.java for Homework 5
*/
import java.util.Iterator;

public class WeirdArray<T> implements Iterable<T>
{
   private Object[] theArray;


   public WeirdArray(int n)
   {
		theArray = new Object[n];
   }

   @SuppressWarnings("unchecked") public T getElement(int i) 
   {
      return (T) theArray[i];
   }

   public void setElement(int i, T e) 
   {
	   theArray[i] = (Object) e;
   }
   
   public int getLength() 
   {
	   return theArray.length;
   }

   /**
      Implement the Iterable<T> interface.
   */
   public Iterator<T> iterator()
   {
		return new WeirdIterator(this);
   }

}
