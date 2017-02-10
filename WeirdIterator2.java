/**
   Course: CS 27500
   Name: Joseph Palicke
   Email: jpalicke@sbcglobal.net
   Assignment:  5

   WeirdIterator2.java for Homework 5
*/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class WeirdIterator2<T> implements Iterator<T>
{
   
   private int cursor;
   private int next;
   private int step;

   
   private WeirdArray<T> workingArray;


   public WeirdIterator2(WeirdArray<T> wa)
   {
		step = 0;
		cursor = 0;
		next = 0;
		workingArray = wa;

   }

	// this iterator is a bit easier to implement.  there are still two cases
	// odd and even.  for the odd cases, step/2 ends up directly being the index
	// for next, while the even cases this ends up being lastIndex - step/2
	// other than that, just checks that the next array index will be in range
	// (as long as step is in range of the array indicies, we're good) and returns
	// true if it is.
   
   public boolean hasNext()
   {
		boolean answer = false;
		int lastIndex = workingArray.getLength() - 1;
		
		if (step >= 0 && step < workingArray.getLength())
		{
			if (step % 2 == 0) // even operation
			{
				next = step/2;
			}
			else // odd operation
			{
				next = lastIndex - step/2;
			}
			answer = true;
		}			
		
		return answer;
		
   }


   // again, all the work here is done in hasNext to avoid duplicating it.
   
   public T next()
   {
      if ( hasNext() )
      {
		cursor = next;
		step++;
		
		return workingArray.getElement(cursor);
      }
      else
         throw new NoSuchElementException();
   }


   public void remove()
   {
      throw new UnsupportedOperationException();
   }
}