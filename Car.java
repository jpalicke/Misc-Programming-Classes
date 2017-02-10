//Joseph R. Palicke
//CS27500 Fall 2015
//Car.java v. 1.0
//8.31.2015

public class Car
{
   //State Variables
   
   //initializing variables just in case.
   
   private double fuelEfficency = 10;
   private double fuelLevel = 0;
   private double odometer = 0;
   
   //Methods
   
   //Constructor
   public Car(double fuelEfficency) {
	  this.fuelEfficency = fuelEfficency;
	  this.fuelLevel = 0;
      this.odometer = 0;
    }
 
	//Methods for getting state of variables
 
	public double getFuelEfficiency() {
	   return this.fuelEfficency;
	}
	public double getOdometer() {
	   return this.odometer;
	}
	public double getFuelLevel () {
       return this.fuelLevel;
	}
 
	//adding fuel
 
	public double addFuel(double gallons) {
	   this.fuelLevel = this.fuelLevel + gallons;
	   return this.fuelLevel;
	}
 
	//go somewhere
 
	public double drive(double miles) {
	   double range = this.fuelEfficency * this.fuelLevel;
  
	   if (range < miles) {
		  this.odometer = this.odometer + range;
		  this.fuelLevel = 0;
		  return range;
		} else {
			 double fuelSpent = miles / this.fuelEfficency;
		   	 this.odometer = this.odometer + miles;
			 this.fuelLevel = this.fuelLevel - fuelSpent;
			 return miles;
		}
	}
   
}
 
 
   
   