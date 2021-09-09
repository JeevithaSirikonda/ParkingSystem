package com.parkinglot;

import java.util.*;


public class SlotAllocationImpl implements SlotAllocation {

    List<Integer> availableSlots = new ArrayList<>();
    Map<Integer, Car> parkingSlots = new HashMap<Integer, Car>();

    @Override
    public void setAvailableSlots(int totalSlots) {
        for (int i = 1; i <= totalSlots; i++) {
            availableSlots.add(i);
        }
    }

    @Override
    public int AllocateSlot(Car car) {
        int slotNumber = 0;
        if(!availableSlots.isEmpty()){
            Collections.sort(availableSlots);
            slotNumber = availableSlots.get(0);
        }
        if (slotNumber >= 1) {
            for (Map.Entry entry:parkingSlots.entrySet()){ //check for duplicate entry
                Car dupCar = (Car) entry.getValue();
                if(car.getRegNumber().equalsIgnoreCase(dupCar.getRegNumber())){
                    System.out.println("Car with Same RegNumber already parked in slot No: "+entry.getKey());
                    return 0;
                }
            }
            parkingSlots.put(slotNumber, car);
            availableSlots.remove(0);
        }
        return slotNumber;
    }

    @Override
    public boolean DeallocateSlot(int exitSlot) {
        boolean flag = false;
        if(parkingSlots.containsKey(exitSlot)){
            parkingSlots.remove(exitSlot, parkingSlots.get(exitSlot));
            availableSlots.add(exitSlot);
            flag = true;
        }
        return flag;

    }

    @Override
    public List getRecord(String clr, int key2) {
        List regNumbers = new ArrayList<>();
        List slotNumbers = new ArrayList<>();
        for (Map.Entry entry : parkingSlots.entrySet()) {
            Car car = (Car) entry.getValue();
            if (clr.equalsIgnoreCase(car.getColor())) {
                regNumbers.add(car.getRegNumber());
                slotNumbers.add(entry.getKey());
            }
        }
        if(key2 == 1){
            return regNumbers;
        }  else{
            return slotNumbers;
        }
    }

    @Override
    public int getSlotDetail(String regNum) {
        int slot = 0;
        Car car = new Car();
        for (Map.Entry entry : parkingSlots.entrySet()) {
            slot = (int) entry.getKey();
            car = (Car) entry.getValue();
            if (regNum.equalsIgnoreCase(car.getRegNumber())) {
                return slot;
            }
        }
        return 0;
    }

    public void getAllSlotDetails(){
        for(Map.Entry entry : parkingSlots.entrySet()){
            int SlotNumber = (int) entry.getKey();
            Car carr = (Car) entry.getValue();
            System.out.println(SlotNumber + " - "+ carr.getRegNumber()+", "+carr.getColor());
        }
    }
}
