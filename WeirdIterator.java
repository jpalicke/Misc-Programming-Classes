/**
   Course: CS 27500
   Name: Joseph Palicke
   Email: jpalicke@sbcglobal.net
   Assignment: 5

   WeirdIterator.java for Homework 5
*/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class WeirdIterator<T> implements Iterator<T>
{
   
   private int cursor;
   private int next;
   private int step;
   
   private WeirdArray<T> workingArray;

   public WeirdIterator(WeirdArray<T> wa)
   {
		
		int step = 1;
		int cursor = 0;
		
		workingArray = wa;


   }


   public boolean hasNext()
   {
		boolean answer = false;
		//next = 0;
		
		// If the length of the array is even, the iterator sequence will
		// always end at the end of the array, IE at the N-1 end, where N
		// is the size of the array.
		// If the length of the array is odd, the iterator sequence will
		// always end at the BEGINNING of the array, IE at the 0 end.
		
		// First step is to compute what the index of the next jump will be.
		// You can almost think of the way WeirdIterator parses through the
		// array as spiraling through, odd and even sized arrays rotating
		// in different directions.  Best way to really show this is two examples.
		
		// Think of an array with 6 slots.  Indexes will go from 0 to 5,
		// and the iterator will follow the sequence 2, 3, 1, 4, 0, 5.  So,
		// the from the start index 2, you add one to get to 3, subtract two
		// to get to 1, add three to get to 4, subtract 4 to get to 0, and add
		// five to get to the end of the array.  Therefore, each odd number
		// operation (1st, 3rd, etc) adds the number of the operation to the
		// old index (so when the index is 1, the third operation is to ADD 3 to 1)
		// while even operations subtract.
		
		// Now think of an array with 5 slots.  That sequence will go 2, 1, 3, 4, 0
		// Note that the difference between the two, besides how the start index is
		// calculated, is that in this case odd operations subtract and even ones
		// add.
		
		// hasNext() in this particular implementation has a dual purpose.  1, it 
		// checks if there is a next position in the array to jump to.  2, it computes
		// said position and stores it in the variable next.
		
		if (workingArray.getLength() % 2 == 0) // even sized array
		{
			if (step % 2 == 0 && step != 0) // even operation
			{
				next = cursor - step;
			}
			else if (step % 2 != 0 && step != 0) // odd operation
			{
				next = cursor + step;
			} 
			else
			{
				next = (workingArray.getLength() / 2) - 1; // step == 0 case, start of iterator
			}
			
			if (step < workingArray.getLength()) { answer = true; }
			
		} 
		else  // odd sized array
		{	
			if (step % 2 == 0 && step != 0) // even step
			{
				next = cursor + step;
			}
			else if (step % 2 != 0 && step != 0) // odd step
			{
				next = cursor - step;
			}
			else // first step
			{
				next = (workingArray.getLength() - 1) / 2;
			}
			
			if (workingArray.getLength() - step > 0) { answer = true; }	
		}  
   return answer;
   
   }


   public T next()
   {
		
		// all the work here is really done by hasNext calculating the next variable
		// this just sets cursor to next and increments step.
		
		if ( hasNext() )
		{
			cursor = next;
			step++;
			 
			return workingArray.getElement(cursor);
		}
		else throw new NoSuchElementException();
   }


   public void remove()
   {
      throw new UnsupportedOperationException();
   }
}
