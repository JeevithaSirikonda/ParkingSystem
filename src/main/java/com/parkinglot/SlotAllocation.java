package com.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SlotAllocation {

    List<Integer> availableSlots = new ArrayList<>();;
    Map<Integer, Car> parkingSlots = new HashMap<Integer, Car>();

    void setAvailableSlots(int totalSlots);

    int AllocateSlot(Car car);

    boolean DeallocateSlot(int exitSlot);

    List<String> getRecord(String clr, int key2);

    int getSlotDetail(String regNum);

    void getAllSlotDetails();
}

