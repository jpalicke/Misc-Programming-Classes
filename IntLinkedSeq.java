/**
   Course: CS 27500
   Name: Joseph Palicke
   Email: jpalicke@sbcglobal.net
   Assignment: 3

*/
// This is an assignment for students to complete after reading Chapter 4
// of "Data Structures and Other Objects Using Java" by Michael Main.

/***************************************************************************
* This class is a homework assignment;
* A <CODE>IntLinkedSeq</CODE> is a collection of <CODE>int</CODE> numbers.
* The sequence can have a special "current element," which is specified and
* accessed through four methods (start, getCurrent, advance, and isCurrent).
*
* <dl>
* <dt><b>Limitations:</b>
* <dd> Beyond Int.MAX_VALUE</CODE> elements, the <CODE>size</CODE> method
*      does not work.
*
* <dt><b>Note:</b>
* <dd> This file contains only blank implementations ("stubs")
*      because this is a Programming Project for students.
***************************************************************************/


public class IntLinkedSeq
{
   private int manyItems;  // how many nodes are in this sequence
   private IntNode head;   // reference to the first node of the sequence
   private IntNode tail;   // reference to the last node of the sequence
   private IntNode cursor; // reference to the "current" node (null
                           // if there isn't one)

   /**
   * Initialize an empty sequence.
   *
   * @param none
   * @postcondition
   *   This sequence is empty. It does not have a current element.
   **/
   public IntLinkedSeq( )
   {
		
		// Sequence is empty, so manyItems = 0.
		// 3 working nodes set to null
		
		this.manyItems = 0;
		this.head = null;
		this.tail = null;
		this.cursor = null;

   }


   /**
   * Copy Constructor
   *
   * @param otherSequence
   *   Sequence to make a deep copy of.
   *
   * @postcondition
   *   This sequence is a DEEP copy of the parameter sequence.
   *   Subsequent changes to the copy will not affect the original, nor vice versa.
   *   The copy should have its cursor set in the appropriate
   *   place within its linked list.
   **/
   public IntLinkedSeq(IntLinkedSeq otherSequence)
   {
		//use the addAll method to make sure that this is a deep copy
		int count = 1;
      this.addAll( otherSequence );
		this.manyItems = otherSequence.size();
		
		//checking for where the current pointer was in the original and setting
		//it in the copy.
		
		for (IntNode selection = otherSequence.head; selection == otherSequence.cursor; selection = selection.getLink()) 
		{
			count++;
		}
		if (otherSequence.isCurrent()) 
		{
			this.setCurrent(count);
		}
   }



   /**
   * Add a new element to this sequence, after the current element.
   *
   * @param element
   *   the new element that is being added
   * @postcondition
   *   A new copy of the element has been added to this sequence.
   *   If there was a current element, then the new element is placed
   *   after the current element. If there was no current element,
   *   then the new element is placed at the end of the sequence.
   *   In all cases, the new element becomes the new current element
   *   of this sequence.
   **/
   public void addAfter(int element)
   {
		IntNode newNode;
		
		//checks if the cursor is set or not.  If not, sends element to
		//add last to tack the element on the end, and then sets cursor
		//to tail
		
		if (this.cursor == null) 
		{
			this.addLast(element);
			this.cursor = this.tail;
		} 
		
		//checks if cursor is at the END of the list
		//if so, adds value at the very end of the list
		//and makes sure the cursor is again at the end
		
		else if (this.cursor.getLink() == null) 
		{
			newNode = new IntNode(element, this.cursor.getLink());
			this.cursor.setLink(newNode);
			this.cursor = this.cursor.getLink();
			this.manyItems++;
			this.tail = this.cursor;
		} 
		else 
		{
			
			//create a new node, set link of the cursor to the new node
			//advance the cursor, and increment the number of items.
			
			newNode = new IntNode(element, this.cursor.getLink());
			this.cursor.setLink(newNode);
			this.advance();
			this.manyItems++;
		}
	}


   /**
   * Add a new element to this sequence, before the current element.
   *
   * @param element
   *   the new element that is being added
   * @postcondition
   *   A new copy of the element has been added to this sequence.
   *   If there was a current element, then the new element is placed
   *   before the current element. If there was no current element,
   *   then the new element is placed at the start of the sequence.
   *   In all cases, the new element becomes the new current element
   *   of this sequence.
   **/
   public void addBefore(int element)
   {
		IntNode curMinusOne = this.head;
		int counter = 0;
		
		//if the list is empty or has 1 or 2 items, just add element to the beginning of the list
		
		if (this.cursor == null || this.size() == 0 || this.size() == 1) 
		{
			this.addFirst(element);
			this.cursor = this.head;	
		} 
		else 
		{
			
			//parse through list, check if you've hit a cursor, if so, break while loop
			
			while (curMinusOne.getLink() != null) 
			{
				if (curMinusOne.getLink() == this.cursor) 
				{
					break;
				}
				curMinusOne = curMinusOne.getLink();
			}
			
			//add element after cursor, increment cursor, and increment number of items
			
			curMinusOne.setLink(new IntNode(element, this.cursor));
			this.cursor = curMinusOne.getLink();
			this.manyItems++;

		}
   }


   /**
   * Place the contents of another sequence at the end of this sequence.
   *
   * @param addend
   *   a sequence whose contents will be placed at the end of this sequence
   * @precondition
   *   The parameter, addend, is not null.
   * @postcondition
   *   The elements from addend have been placed at the end of
   *   this sequence. The current element of this sequence remains where it
   *   was, and the addend is also unchanged.
   **/
   public void addAll(IntLinkedSeq addend)
   {
		IntLinkedSeq copyAddend = new IntLinkedSeq();
		IntNode selection;
		
		//make sure list passed isn't empty
		
		if (addend != null) 
		{
			selection = addend.head;
			
			//step through addend, copy to copyAddend element by element
			//for deep copy purposes
			
			while (selection != null) 
			{
				copyAddend.addAfter(selection.getData());
				selection = selection.getLink();
			}
			
			//if current list is empty, just use copyAddend as the list
			
			if (this.size() == 0) 
			{
				this.head = copyAddend.head;
				this.tail = copyAddend.tail;
			} 
			else 
			{
				
				//sets tail link to the beginning of copied list, reset tail link, and increment
				//manyitems
				
				this.tail.setLink(copyAddend.head);
				this.tail = copyAddend.tail;
				this.manyItems = this.manyItems + copyAddend.size();
			}
		}

   }


   /**
   * Add a new element to the beginning of this sequence.
   *
   * @param element
   *   the new element that is being added
   * @postcondition
   *   A new copy of the element has been added to the beginning
   *   of this sequence. The current element is unchanged.
   **/
   public void addFirst(int element)
   {
		
		//Check if the list is empty.  If empty, set element at front
		//and set head and tail pointers
		
		IntNode newNode;
		
		if (this.head == null) 
		{
			newNode = new IntNode(element,null);
			this.head = newNode;
			this.tail = newNode;
		}

		//if list has 1 item, add to front, set head to new node
		//and set tail to the old
		
		else if (this.size() == 1) 
		{
			newNode = new IntNode(element,this.head);
			this.head = newNode;
			this.tail = newNode.getLink();
		} 
		
		//pass element to front of list, reset head
		
		else 
		{
			newNode = new IntNode(element,this.head);
			this.head = newNode;
		}
		
		//increment item count
		
		this.manyItems++;

   }


   /**
   * Add a new element to the end of this sequence.
   *
   * @param element
   *   the new element that is being added
   * @postcondition
   *   A new copy of the element has been added to the end
   *   of this sequence. The current element is unchanged.
   **/
   public void addLast(int element)
   {
		// If current list is empty, add to list, set head and tail,
		//increment count.  If list is size 1, do the same but reset tail
		//to the old head node.  Otherwise add to end, reset tail node to the
		//node just added, and increment.
		
		IntNode newNode;

		
		if (this.size() == 0) {
			this.head = new IntNode(element, null);
			this.tail = this.head;
		} else if (this.size() == 1) {
			newNode = new IntNode(element,null);
			this.head.setLink(newNode);
			this.tail = newNode;
		} else {
			newNode = new IntNode(element,null);
			this.tail.setLink(newNode);
			this.tail = newNode;
		}
		this.manyItems++;
   }


   /**
   * Set the current element at the front of this sequence.
   *
   * @param none
   * @postcondition
   *   The front element of this sequence is now the current element
   *  (but if this sequence has no elements at all, then there is
   *  no current element).
   **/
   public void start( )
   {
		this.cursor = this.head;
   }


   /**
   * Accessor method to determine whether this sequence has a specified
   * current element that can be retrieved with the getCurrent() method.
   *
   * @param none
   * @return
   *   true (there is a current element) or
   *   false (there is no current element at the moment)
   **/
   public boolean isCurrent( )
   {
		boolean answer = true;
		if (this.cursor == null) 
		{
			answer = false;
		}

      return answer;
	
	}


   /**
   * Accessor method to get the value in the current element of this sequence.
   *
   * @param none
   * @precondition
   *   isCurrent() returns true.
   * @return
   *   the value in the cursor element of this sequence
   * @exception IllegalStateException
   *   Indicates that there is no current element,
   *   so getCurrentValue() may not be called.
   **/
   public int getCurrentValue( )
   {
		return this.cursor.getData(); 
	}
	


   /**
   * Mutator method to set the value in the current element of this sequence.
   *
   * @param element
   *   the new value that is to be placed in the current element
   * @precondition
   *   isCurrent() returns true.
   * @exception IllegalStateException
   *   Indicates that there is no current element,
   *   so setCurrentValue() may not be called.
   **/
   public void setCurrentValue(int element)
   {
		this.cursor.setData(element);
	}


   /**
   * Remove the current element from this sequence.
   *
   * @param none
   * @precondition
   *   isCurrent() returns true.
   * @postcondition
   *   The current element has been removed from this sequence, and the
   *   following element (if there is one) is now the new current element.
   *   If there was no following element, then there is now no current
   *   element.
   * @exception IllegalStateException
   *   Indicates that there is no current element,
   *   so removeCurrent() may not be called.
   **/
   public void removeCurrent( )
   {
		IntNode curMinusOne = this.head;
		
		//step through list until the curminusone pointer is right before the
		//cursor pointer.  Once the node before the cursor is identified
		//it's an easy process to remove the cursor node

		while (curMinusOne.getLink() != this.cursor) 
		{
			curMinusOne = curMinusOne.getLink();
		}
		this.advance();
		curMinusOne.setLink(this.cursor);
		this.manyItems--;
   }


   /**
   * Remove the element at the beginning of this sequence.
   *
   * @param none
   * @precondition
   *   The sequence is not empty.
   * @postcondition
   *   The first element of the sequence is deleted from the sequence.
   *   If this was the current element, then the current element status
   *   is invalidated.
   * @exception IllegalStateException
   *   Indicates that the sequence is empty,
   *   so removeFirst() may not be called.
   **/
   public void removeFirst( )
   {
		// 3 cases here.  Empty list, throw exception.
		//list size 1, basically the entire list is set to null
		//otherwise just set head to head.getLink()
		
		if (this.size() == 0) 
		{
			throw new IllegalStateException();
		} 
		else if (this.size() == 1) 
		{
			this.head = null;
			this.tail = null;
			this.cursor = null;
			this.manyItems--;
		}
		else 
		{
			this.head = this.head.getLink();
			this.manyItems--;
		}

   }


   /**
   * Remove the element at the end of this sequence.
   *
   * @param none
   * @precondition
   *   The sequence is not empty.
   * @postcondition
   *   The last element of the sequence is deleted from the sequence.
   *   If this was the current element, then the current element status
   *   is invalidated.
   * @exception IllegalStateException
   *   Indicates that the sequence is empty,
   *   so removeLast() may not be called.
   **/
   public void removeLast( )
   {
		
		//3 cases here again.  Throw exception for the empty list
		//list size 1, just delete entire list
		//otherwise parse, find the next to last item and set to tail
		
		IntNode lastMinusOne = this.head;
		
		if (this.size() == 0) 
		{
			throw new IllegalStateException();
		}
		else if (this.size() == 1) 
		{
			this.head = null;
			this.tail = null;
			this.manyItems--;
		}
		else if (this.size() != 0 && this.size() != 1) 
		{	
			while (lastMinusOne.getLink() != this.tail && lastMinusOne.getLink() != null)
			{
				lastMinusOne = lastMinusOne.getLink();
			}	
			lastMinusOne.setLink(null);
			this.tail = lastMinusOne;
			this.manyItems--;
		}
   }


   /**
   * Move forward, so that the current element is now the next element
   * in this sequence.
   *
   * @param none
   * @precondition
   *   isCurrent() returns true.
   * @postcondition
   *   If the current element was already the end element of this
   *   sequence (with nothing after it), then there is no longer
   *   any current element. Otherwise, the new element is the
   *   element immediately after the  original current element.
   * @exception IllegalStateException
   *   Indicates that there is no current element,
   *   so advance() may not be called.
   **/
   public void advance( )
   {
		// Checks if there is a current node, if so, makes current node
		// point at the item held in current.getLink()  Throws exception
		// if cursor isn't set
		
		if (!this.isCurrent()) 
		{
			throw new IllegalStateException();
		} 
		else 
		{
			this.cursor = this.cursor.getLink();
		}

   }


   /**
   * Set the current element to be the i'th element of this sequence
   * (starting with the 0'th element at the head).
   *
   * @param i
   *   the index of the element to make the current element
   * @precondition
   *   The parameter i is greater than or equal to 0
   *   and i is less than size (i >= 0 && i < size).
   * @postcondition
   *   The current element is the i'th element of the sequence
   *   (where the head element is the 0'th element)
   * @exception IndexOutOfBoundsException
   *   Indicates that i is less than 0 or greater than
   *   size-1 (i < 0 || i >= size).
   **/
   public void setCurrent(int i)
   {
		//sets current equal to head and index = 0.
		//While index does not equal i, it sets current
		//to point at the item that was in current's link
		//and increments index.  Stops when index == i.
		
		int index = 0;
		this.cursor = head;
		while (index != i) 
		{
			this.cursor = this.cursor.getLink();
			index++;
		}

   }


   /**
   * Set the state of this sequence so that it does not have a cursor element.
   *
   * @param none
   * @postcondition
   *   After calling this method, calling isCurrent() returns false.
   **/
   public void invalidateCurrent( )
   {
		this.cursor = null;
   }


   /**
   * Method to determine if a particular element
   * is in this sequence.
   *
   * @param target
   *   the element that needs to be found in this sequence
   * @return
   *   true if the target element is in this sequence, false otherwise
   **/
   public boolean contains(int target)
   {
		boolean answer = false;
		IntNode selection = this.head;
		while(selection != null) 
		{
			if (selection.getData() == target) 
			{
				answer = true;
			}
			selection = selection.getLink();
		}

      return answer;
   }


   /**
   * Returns the index of the first occurrence of the specified element in
   * this sequence, or -1 if this sequence does not contain the element.
   *
   * @param target
   *   the element that needs to be found in this sequence
   * @return
   *   the index of the first occurrence of target in this sequence, or -1
   *   if this sequence does not contain target
   **/
   public int indexOf(int target)
   {
		int answer = 0;
		boolean found = false;
		IntNode selection = this.head;
		
		//step through list, keep count of number of steps.
		//if hit is found, found == true, and the number of steps gets returned
		//if no hit is found, answer is reset to -1.
		
		while(selection != null) 
		{
			if (selection.getData() != target && found == false) 
			{
				answer++;
			}
			else if (selection.getData() == target && found == false) 
			{
				found = true;
			}
			else if (selection.getLink() == null && found == false) 
			{
				answer = -1;
			}
			selection = selection.getLink();
		}

      return answer;

   }


   /**
   * Determine the number of elements in this sequence.
   *
   * @param none
   * @return
   *   the number of elements in this sequence
   **/
   public int size( )
   {
      return manyItems;
   }



   /**
   * Create a new sequence that contains all the elements from
   * this sequence followed by another sequence. The returned
   * sequence should not be backed by this sequence or the other
   * sequence (so changes to the returned sequence are not reflected
   * in this sequence or the other sequence).
   *
   * @param s2
   *   the second sequence
   * @precondition
   *   s2 is not null.
   * @return
   *   a new sequence that has the elements of this followed by the
   *   elements of s2 (with no current element)
   **/
   public IntLinkedSeq catenation(IntLinkedSeq s2)
   {
		
		//copy current list into answer, then use the addall method
		//to add passed sequence onto end.
		
		IntLinkedSeq answer = new IntLinkedSeq(this);
		answer.addAll(s2);

      return answer;
   }


   /**
   * Create a new sequence that contains all the elements from
   * this sequence that are between the indices fromIndex, inclusive,
   * and toIndex, exclusive. (If fromIndex and toIndex are equal,
   * the returned sequence is empty.) The returned sequence should not
   * be backed by this sequence (so changes to the returned sequence
   * are not reflected in this sequence).
   * The new sequence should not have a current element.
   *
   * @param fromIndex
   *    low endpoint (inclusive) of the sub sequence
   * @param toIndex
   *   high endpoint (exclusive) of the sub sequence
   * @precondition
   *   fromIndex is less than or equal to toIndex (fromIndex <= toIndex),
   *   fromIndex is greater than or equal to zero (fromIndex >= 0),
   *   toIndex is less than or equal to size (toIndex <= size)
   * @return
   *   a new sequence that contains all the elements from this
   *   sequence that are between the indices fromIndex, inclusive,
   *   and toIndex, exclusive.
   * @exception IllegalArgumentException
   *   if the endpoint indices are out of order (fromIndex > toIndex)
   * @exception IndexOutOfBoundsException
   *   endpoint index value out of range (fromIndex < 0 || toIndex > size)
   **/
   public IntLinkedSeq subSeq(int fromIndex, int toIndex)
   {
		int curIndex = 0;
		IntLinkedSeq answer = new IntLinkedSeq();
		IntNode selection = this.head;
		
		//check if from and to are in range, then steps through list.
		//copies data at indices given to answer, and returns answer
		//also, before returning answer, the cursor is invalidated in answer.
		
		if (fromIndex <= toIndex && fromIndex >= 0 && toIndex <= this.size()) 
		{
			while (selection != null) 
			{
				if (curIndex >= fromIndex && curIndex < toIndex) 
				{
					answer.addAfter(selection.getData());
				}
				selection = selection.getLink();
				curIndex++;
			}
		}
		answer.invalidateCurrent();
      return answer; // Replace this return statement with your own code:
   }


   /**
   * Create a new sequence that contains all the elements from
   * this sequence in the reverse order. The returned sequence
   * should not be backed by this sequence (so changes to the
   * returned sequence are not reflected in this sequence).
   * The new sequence should not have a current element.
   *
   * @param none
   * @return
   *   a new sequence that has the elements of this
   *   in their reverse order (with no current element)
   **/
   public IntLinkedSeq reverse( )
   {
		IntLinkedSeq answer = new IntLinkedSeq();
		IntNode selection = this.head;
	
		//step through list, add first with each value, return answer
	
		while (selection != null) 
		{
			answer.addFirst(selection.getData());
			selection = selection.getLink();
		}
      return answer;
   }


   /**
   * Returns an array containing all of the elements in this sequence.
   *
   * @param none
   * @return
   *   an array containing all the elements in this sequence
   **/
   public int[] toArray( )
   {
		int[] answer = new int[this.size()];
		IntNode selection = this.head;
		int index = 0;
		
		//step through list, add each node's data to array.
		
		while (selection != null) 
		{
			answer[index] = selection.getData();
			index++;
			selection = selection.getLink();
		}

      return answer;
   }



   /**
   * Returns a String containing a representation of all the elements
   * in this sequence. Notice that a special symbol is used to denote
   * the current element.
   *
   * @param none
   * @return
   *   a String containing all the elements in this sequence
   **/
   public String toString( )
   {
      String result = "[";
      IntNode ptr = head;
      while (ptr != null)
      {
         result += ptr.getData();
         if (ptr == cursor)
         {
            result += "*"; // use a special symbol at the cursor
         }
         ptr = ptr.getLink();
         if (ptr != null)
         {
            result += ", ";
         }
      }
      result += "]";
      return result;
   }

}//IntLinkedSeq