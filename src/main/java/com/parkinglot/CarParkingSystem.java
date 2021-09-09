package com.parkinglot;

import java.util.*;

public class CarParkingSystem {
    public static void main(String[] args) {

         int totalSlots = 5;
         int slotNumber = 0;

        /*List<Integer> availableSlots = new ArrayList<>(5);
        for (int i = 1; i <= totalSlots; i++) {
            availableSlots.add(i);
        }
        Map<Integer, Car> parkingSlots = new HashMap<Integer, Car>();*/

         Car car = new Car();
         SlotAllocation slotAllocation = new SlotAllocationImpl();
         slotAllocation.setAvailableSlots(totalSlots);
        while(true) {
         System.out.println("Enter 1 for car ENTRY");
         System.out.println("Enter 2 for car EXIT");
         System.out.println("Enter 3 for Slot Details");
         System.out.println("Enter 4 to EXIT");

         Scanner sc = new Scanner(System.in);
         int key = sc.nextInt();

         if (key == 1) { //car entry
             System.out.println("Enter RegNumber and Color of the Car");
             car = new Car(sc.next(), sc.next());
             slotNumber = slotAllocation.AllocateSlot(car);
             if (slotNumber >= 1) {
                 System.out.println("Car with " + car.getRegNumber()+", " + car.getColor() + " has been parked at slotNumber: " + slotNumber);
             } else {
                 System.out.println("Parking is full! No slots available right now.");
             }

         } else if (key == 2) { //car exit
             System.out.println("Enter parkingSlot Number that car was parked");
             int exitSlot = sc.nextInt();

             if(slotAllocation.DeallocateSlot(exitSlot)){
                 System.out.println("Slot Number " + exitSlot + " is available for parking.");
             }else{
                 System.out.println("Slot "+exitSlot+" is already empty");
             }



         } else if (key == 3) { // parking details
             /*for(Map.Entry entry : parkingSlots.entrySet()){
                 int key1 = (int) entry.getKey();
                 Car carr = (Car) entry.getValue();
                 System.out.println(key1 + " - "+ carr.getRegNumber()+", "+carr.getColor());
             }*/
             slotAllocation.getAllSlotDetails();
             System.out.println("You can get the details of");
             System.out.println("1. Registration numbers of all cars of a particular colour");
             System.out.println("2. Slot number in which a car with a given registration number is parked");
             System.out.println("3. Slot numbers of all slots where a car of a particular colour is parked.");
             int key2 = sc.nextInt();
             if (key2 == 1) {
                 System.out.println("Enter the Color");
                 String clr = sc.next();
                 List regNumbers = slotAllocation.getRecord(clr, key2);

                 System.out.println("Registration Numbers of "+clr+" colour cars: ");
                 for(var regNum: regNumbers){
                     System.out.println(regNum + "   ");
                 }

             } else if (key2 == 2) {
                 System.out.println("Enter the Registration number of the car to get the slot details");
                 String regNum = sc.next();
                 int slotOfRegNum = slotAllocation.getSlotDetail(regNum);
                 System.out.println("Car parked in slot : " + slotOfRegNum);

             } else if (key2 == 3) {
                 System.out.println("Enter the Color of the car to get the slot details");
                 String clrName = sc.next();
                 List slots = slotAllocation.getRecord(clrName, key2);
                 System.out.println(clrName+" color car is parked in slots: ");
                 for(var slot: slots){
                     System.out.print(slot +"   ");
                 }

             } else {
                 System.out.println("Please enter the right key from the above");
             }
         } else if(key == 4) {
             System.exit(0);
         } else {
                System.out.println("Please enter the right key from ablove");
         }

     }

    }
}
