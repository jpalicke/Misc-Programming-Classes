/******************************************************************************
* Joseph R. Palicke
* CS275 Fall 15
* HW 2
* 9/16/15
* An IntArraySet is a collection of int numbers.
* A number may appear only one time in a set.
*
* @note
*   (1) The capacity of one of these sets can change after it's created, but
*   the maximum capacity is limited by the amount of free memory on the
*   machine. The constructor, addItem, clone,
*   and union will result in an OutOfMemoryError
*   when free memory is exhausted.
*   <p>
*   (2) A set's capacity cannot exceed the maximum integer 2,147,483,647
*   (Integer.MAX_VALUE). Any attempt to create a larger capacity
*   results in a failure due to an arithmetic overflow.
*   <p>
*   (3) Because of the slow linear algorithms of this
*   class, large sets will have poor performance.
******************************************************************************/
public class IntArraySet implements Cloneable
{
   // Invariant of the IntArraySet class:
   //   1. The number of elements in the set is in the instance variable
   //      manyItems, which is no more than data.length.
   //   2. For an empty set, we do not care what is stored in any of data;
   //      for a non-empty set, the elements in the set are stored in data[0]
   //      through data[manyItems-1], and we don’t care what’s in the
   //      rest of data.
   
   private int[ ] data;
   private int manyItems;

   /**
   * Initialize an empty set with an initial capacity of 10.  Note that the
   * addItem method works efficiently (without needing more
   * memory) until this capacity is reached.
   * @param - none
   * @postcondition
   *   This set is empty and has an initial capacity of 10.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for:
   *   new int[10].
   **/
   public IntArraySet( )
   {
		final int DEFAULT_CAPACITY = 10;
		this.data = new int[DEFAULT_CAPACITY];
		this.manyItems = 0;
   }


   /**
   * Initialize an empty set with a specified initial capacity. Note that the
   * addItem method works efficiently (without needing more
   * memory) until this capacity is reached.
   * @param initialCapacity
   *   the initial capacity of this set
   * @precondition
   *   initialCapacity is non-negative.
   * @postcondition
   *   This set is empty and has the given initial capacity.
   * @exception IllegalArgumentException
   *   Indicates that initialCapacity is negative.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for: new int[initialCapacity].
   **/
   public IntArraySet(int initialCapacity)
   {	
		this.data = new int[initialCapacity];
		this.manyItems = 0;
   }


   /**
   * Add a new element to this set. If the given element is already
   * in the set, do nothing. If the new element would take this
   * set beyond its current capacity, then the capacity is increased
   * before adding the new element.
   * @param element
   *   the new element that is being inserted
   * @postcondition
   *   A copy of the element has been added to this set, if it is not already in the set.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for increasing the set's capacity.
   * @note
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause the set to fail with an
   *   arithmetic overflow.
   **/
   public void add(int element)
   { 
  // check if array has enough space, if not, increase by 1
  
	if (manyItems == this.getCapacity()) {
		this.ensureCapacity(manyItems + 1);
	}
   
   //checks if current object contains element, if not, it adds the element
   //at index manyItems, and increments manyItems
   
	if (this.contains(element) == false) {
		this.data[manyItems] = element;
		this.manyItems++;
	}
   }


   /**
   * Add new elements to this set. If any of the given elements is already
   * in the set, that element is ignored. If the new elements would take this
   * set beyond its current capacity, then the capacity is increased
   * in order to add the new elements.
   * @param elements
   *   (a variable-arity argument)
   *   one or more new elements that are being inserted
   * @postcondition
   *   A copy of each new element has been added to this set.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for increasing the set's capacity.
   * @note
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause the set to fail with an
   *   arithmetic overflow.
   **/
   public void addMany(int... elements)
   {
	   
		//ensures there's enough room for the combined object in the current object
		//if not, it extends the array in the current object.  After that it's
		//a pretty straight forward walk through the array, checking each value in
		//set2 for an existing duplicate in the current object, and if one doesn't
		//exist, current value is added
	   
		if (this.manyItems + elements.length > data.length) {
			this.ensureCapacity(this.manyItems + elements.length + 1);
		}
		for (int i = 0; i < elements.length; i++) {
			if (this.contains(elements[i]) == false) {
			this.add(elements[i]);
			}
		}
   }


   /**
   * Add to this set any element of another set that is not already in this set.
   * The result is this set unioned with the other set.
   * @param set2
   *   a set whose elements will be unioned with this set
   * @precondition
   *   The parameter, set2, is not null.
   * @postcondition
   *   The elements from set2 have been unioned with this set.
   * @exception NullPointerException
   *   Indicates that set2 is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory to increase the size of this set.
   * @note
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause an arithmetic overflow
   *   that will cause the set to fail. Such large collections should use
   *   a different set implementation.
   **/
   public void addAll(IntArraySet set2)
   {
		
		//ensures there's enough room for the combined object in the current object
		//if not, it extends the array in the current object.  After that it's
		//a pretty straight forward walk through the array, checking each value in
		//set2 for an existing duplicate in the current object, and if one doesn't
		//exist, current value is added
		
		if (this.manyItems + set2.manyItems > this.getCapacity()) {
		this.ensureCapacity(this.size() + set2.size() + 1);
	}
  
		for (int i = 0; i < set2.size(); i++) {
			if (this.contains(set2.data[i]) == false) {
			this.add(set2.data[i]);
			}
		}
	}


   /**
   * Remove from this set any element of another set that is in this set.
   * The result is the other set's elements subtracted from this set.
   * @param set2
   *   a set whose elements will be subtracted from this set
   * @precondition
   *   The parameter, set2, is not null.
   * @postcondition
   *   The elements from set2 have been subtracted with this set.
   * @exception NullPointerException
   *   Indicates that set2 is null.
   **/
   public void subtractAll(IntArraySet set2)
   {
	   
		//I had to clone an object to use here as kind of "scratch space"
		//I tried working on the class object itself, but because the
		//test bench feeds set1 to itself, I was running into a strange
		//problem where the upper bound of the for loop would be lowering as
		//items were being deleted out of the array.  this would cause
		//the function to never get all the way through the array.  Incidentally
		//I ran into the same issue with keepCommonElements
	   
		IntArraySet tempSet = set2.clone();
		for (int i = 0; i < tempSet.size(); i++) {
			this.remove(tempSet.data[i]);
		}
   }


   /**
   * Remove from this set any of its elements that are not contained in another set.
   * The result is this set intersected with the other set
   * @param set2
   *   a set whose elements will be intersected with this set
   * @precondition
   *   The parameter, set2, is not null.
   * @postcondition
   *   This set contains the intersection of itself with set2.
   * @exception NullPointerException
   *   Indicates that set2 is null.
   **/
   public void keepCommonElements(IntArraySet set2)
   {
		
		//I had to clone an object to use here as kind of "scratch space"
		//I tried working on the class object itself, but because the
		//test bench feeds set1 to itself, I was running into a strange
		//problem where the upper bound of the for loop would be lowering as
		//items were being deleted out of the array.  this would cause
		//the function to never get all the way through the array.  Incidentally
		//I ran into the same issue with subtractAll
		
		IntArraySet tempSet = this.clone();
		for (int i = 0; i < tempSet.size(); i++) {
			if (!set2.contains(tempSet.data[i])) {
				this.remove(tempSet.data[i]);
			}
		}
   }


   /**
   * Generate a copy of this set.
   * @param - none
   * @return
   *   The return value is a copy of this set. Subsequent changes to the
   *   copy will not affect the original, nor vice versa.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/
   public IntArraySet clone( )
   {  // Clone an IntArraySet object.
		
		//creates new intarrayset clown.  if it can create a clone in clown
		//a clone is created in clown.  if no such clown clone can be created
		//it throws an exception.  I tried not doing this, but the compiler
		//was rather insistent.  Finally, the array containing the data for
		//the current object is cloned onto the array in the clown clone object.
		
		IntArraySet clown;
  
		try {
			clown = (IntArraySet) super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			throw new RuntimeException("This class does not implement Cloneable. ");
		}
		clown.data = data.clone();
  
		return clown;
   }


   /**
   * Method to determine if a particular element
   * is in this set.
   * @param target
   *   the element that needs to be found in this set
   * @return
   *   true if the target element is in this set, false otherwise
   **/
   public boolean contains(int target)
   {
		//steps through array and looks for a hit.  if found returns true.
		
		for (int i = 0; i < this.size(); i++) {
			if (target == this.data[i]){
			return true;
			}
		}
	return false;
	}


   /**
   * Change the current capacity of this set.
   * @param minimumCapacity
   *   the new capacity for this set
   * @postcondition
   *   This set's capacity has been changed to at least minimumCapacity.
   *   If the capacity was already at or greater than minimumCapacity,
   *   then the capacity is left unchanged.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for: new int[minimumCapacity].
   **/
   public void ensureCapacity(int minimumCapacity)
   {
		int[] newArray;
		
		//if the capacity called for is greater than the current capacity of
		//the array, it creates bigger array of size minimumCapacity and 
		//makes it the array for the current object
  
		if (this.getCapacity() < minimumCapacity) {
			newArray = new int[minimumCapacity];
			System.arraycopy(this.data, 0, newArray, 0, manyItems);
			this.data = newArray;
		}
  
   }


   /**
   * Accessor method to get the current capacity of this set.
   * The add method works efficiently (without needing
   * more memory) until this capacity is reached.
   * @param - none
   * @return
   *   the current capacity of this set
   **/
   public int getCapacity( )
   {
	   
		return this.data.length;
   }


   /**
   * Remove a specified element from this set.
   * @param target
   *   the element to be removed from the set
   * @postcondition
   *   If target was found in the set, then the
   *   target has been removed and the method returns true.
   *   Otherwise the set remains unchanged and the method returns false.
   **/
   public boolean remove(int target)
   {
	   
	//remove checks if the current object contains the target.  if not, it returns false
	//if so, it steps through the array inside this object and compares over and over to
	//look for the collision between the value given and the value in the array.  at that
	//point, it makes two arrays, one for all the values BELOW the value in which a hit
	//was found, and one for all the values ABOVE.  these two arrays are copied into the
	//object array.  manyItems is then decremented, and the function returns true.
	   
	if (this.contains(target) == true) {
		for (int i = 0; i < this.size(); i++) {
			if (data[i] == target) {
				int[] arrayUnder = new int[i];
				int[] arrayOver = new int[data.length - i - 1]; 
				System.arraycopy(data, 0, arrayUnder, 0, i);
				System.arraycopy(data, i + 1, arrayOver, 0, data.length - i - 1);
				System.arraycopy(arrayUnder, 0, data, 0, arrayUnder.length);
				System.arraycopy(arrayOver, 0, data, i, arrayOver.length);
				manyItems--;
				return true;
			}
		}
	}
	return false;
   
   }


   /**
   * Determine the number of elements in this set.
   * @param - none
   * @return
   *   the number of elements in this set
   **/
   public int size( )
   {   
		return manyItems;
   }


   /**
   * Reduce the current capacity of this set to its actual size (i.e., the
   * number of elements it contains).
   * @param - none
   * @postcondition
   *   This set's capacity has been changed to its current size.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for altering the capacity.
   **/
   public void trimToSize( )
   {
	   
		// trims array.  if data.length is greater than manyItems
		//creates an array of size manyItems, and makes that array
		//the new array for the current object
	   
		int[] newArray;
  
		if (data.length > manyItems) {
			newArray = new int[manyItems];
			System.arraycopy(data, 0, newArray, 0, manyItems);
			data = newArray;
		}
   }


   /**
   * Create a new set that contains all the elements from this set and one other set.
   * @param set2
   *   the second set in the union
   * @precondition
   *   set2 is not null, and
   *   getCapacity( ) + set2.getCapacity( ) &lt;= Integer.MAX_VALUE.
   * @return
   *   the union of this set and set2
   * @exception NullPointerException.
   *   Indicates that the argument is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the new set.
   * @note
   *   An attempt to create a set with a capacity beyond
   *   Integer.MAX_VALUE will cause an arithmetic overflow
   *   that will cause the set to fail. Such large collections should use
   *   a different set implementation.
   **/
   public IntArraySet union(IntArraySet set2)
   {
      // If set2 is null, then a NullPointerException is thrown.
      // In the case that the total number of items is beyond
      // Integer.MAX_VALUE, there will be an arithmetic overflow and
      // the set will fail.
		
		//Clones current object, runs union with set2, and returns object
		
		IntArraySet unionOf = this.clone();
		unionOf.addAll(set2);
		return unionOf;
   }


   /**
   * Create a new set that contains all the elements that are in both this set and one other set.
   * @param set2
   *   the second set in the intersection
   * @precondition
   *   set2 is not null
   * @postcondition
   *   the returned set is smaller than either this set or set2
   * @return
   *   the intersection of this set and set2
   * @exception NullPointerException.
   *   Indicates that the argument is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the new set.
   **/
   public IntArraySet intersection(IntArraySet set2)
   {
      // Clones current IntArraySet, runs an intersection between it and
	  // set2.  Returns IntArraySet object.
	  // If set2 is null, then a NullPointerException is thrown.
		IntArraySet intersectionOf = this.clone();
		intersectionOf.keepCommonElements(set2);
		return intersectionOf; 

   }


   /**
   * Create a new set that contains all the elements from this set except those from the other set.
   * @param set2
   *   the second set in the subtraction
   * @precondition
   *   set2 is not null
   * @postcondition
   *   the returned set is smaller than this set
   * @return
   *   the subtraction of set2 from this set
   * @exception NullPointerException.
   *   Indicates that the argument is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the new set.
   **/
   public IntArraySet minus(IntArraySet set2)
   {
	  //  Pretty straightforward.  Clones the current set, then uses subtract all between
	  //  clone and set2, and returns the cloned set.
      // If set2 is null, then a NullPointerException is thrown.
		IntArraySet minusSet2 = this.clone();
		minusSet2.subtractAll(set2);
		return minusSet2; 
   }


   public String toString()
   {
      String result = "{";
      for (int i = 0; i < manyItems; i++)
      {
         if (i > 0)
         {
            result += " ";
         }
         result += data[i];
         if (i < manyItems-1)
         {
            result += ",";
         }
      }
      result += "}";
      return result;
   }

}//IntArraySet