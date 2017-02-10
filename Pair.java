/**
   Course: CS 27500
   Name: Joseph Palicke
   Email: jpalicke@sbcglobal.net
   Assignment: 4
   
   Generic Pair Class for HW 4

*/

public class Pair<FirstType,SecondType>
{
	private FirstType first;
	private SecondType second;
	
	//default constructor
	
	public Pair()
	{
		this.first = null;
		this.second = null;
	}
	
	//constructor with both elements defined
	
	public Pair(FirstType first, SecondType second)
	{
		this.first = first;
		this.second = second;
	}
	
	//copy constructor
	
	public Pair(Pair<FirstType,SecondType> thing)
	{
		this(thing.getFirst(),thing.getSecond());
	}
	
	//gets and sets
	
	public FirstType getFirst()
	{
		return this.first;
	}
	
	public SecondType getSecond()
	{
		return this.second;
	}
	
	public Pair setFirst(FirstType first)
	{
		this.first = first;
		return this;
	}
	
	public Pair setSecond(SecondType second)
	{
		this.second = second;
		return this;
	}
	
	//transpose() creates new Pair, and sets the second element of the current
	//Pair as the first element of the second, and vice versa.  Transpose then
	//returns this new pair.
	
	public Pair<SecondType,FirstType> transpose()
	{
		return new Pair<SecondType,FirstType>(this.getSecond(),this.getFirst());
	}
	
	//These two generic methods take in a thing of type Element and create a new pair
	//with the first element being of type Element, and the second being of type SecondType.
	//thing is set to the first element, and the second element of the this object is made
	//the second of the new Pair which gets returned.
	
	public <Element> Pair<Element,SecondType> replaceFirst(Element thing)
	{
		return new Pair<Element,SecondType>(thing, this.getSecond());
	}
	
	//Just like replaceFirst, just acting on the second element instead of the first
	
	public <Element> Pair<FirstType,Element> replaceSecond(Element thing)
	{
		return new Pair<FirstType,Element>(this.getFirst(),thing);
	}
	
	//overrides Object.equals() , makes sure the object isn't null, is an actual pair
	//and then checks that the first element of the current object is equal to the
	//first element of o, and the second...yadda yadda.
	
	//I have no idea why this seems to fail on lines 77-81 of TestPairs.java
	//Did I write this wrong? (Probably) Am I expecting too much of the compiler to unravel
	//things back to the individual Integers in the nested pair?
	
	public boolean equals(Pair<FirstType,SecondType> o)
	{

		boolean answer = false;
		
		if (o != null && 
			 o instanceof Pair && 
			 this.getFirst().equals(o.getFirst()) && 
			 this.getSecond().equals(o.getSecond()))
		{
			answer = true;
		}
		
		return answer;
	}
	
	//simple toString() method since no particular syntax was noted in the instructions
	
	public String toString()
	{
		return this.getFirst() + " " + this.getSecond();
	}
}
