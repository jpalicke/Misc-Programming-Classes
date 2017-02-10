//Joseph R. Palicke
//CS27500 Fall 2015
//TestDrive.java v. 1.0
//8.31.2015

import java.util.Scanner;

public class TestDrive {

     public static void main( String[] args ) {
       
       Scanner user_input = new Scanner( System.in ); 
       
       //initialize variables to 1 just in case
    
       double fuelEfficency = 1;
       double fuelVolume = 1;
       double travelDistance = 1;
    
       //initialize state to 0 to make sure switch statement is entered at the right point
    
       int state = 0;
       
       //make the compiler happy
       
       Car newCar = null;
       
       while (true) {
    
         // implement state machine
    
         switch (state) {
           case 0:
             System.out.print("Enter Fuel Efficency: ");
             fuelEfficency = Double.parseDouble(user_input.next());
             newCar = new Car(fuelEfficency);
             if (fuelEfficency != 0) { 
               state = 1;
             } else
               return;
             break;
           case 1:
             System.out.print("Enter amount of fuel: ");
             fuelVolume = Double.parseDouble(user_input.next());
             if (fuelVolume != 0) { 
               newCar.addFuel(fuelVolume);
               state = 2;
             } else
               state = 0;
             break;
           case 2:
             System.out.print("Enter distance to travel: ");
             travelDistance = Double.parseDouble(user_input.next());
             if (travelDistance != 0) { 
               System.out.print("Distance actually traveled = ");
               System.out.println(newCar.drive(travelDistance));
               System.out.print("Current fuelLevel = ");
               System.out.println(newCar.getFuelLevel());
               System.out.print("Current odometer = ");
               System.out.println(newCar.getOdometer());
               state = 2;
             } else
               state = 1;
             break;
           default:
             return;
         }//end case
       }//end while
     } //end main
}//end TestDrive