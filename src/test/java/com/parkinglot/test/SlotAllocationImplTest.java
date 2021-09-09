package com.parkinglot.test;

import com.parkinglot.Car;
import com.parkinglot.SlotAllocation;
import com.parkinglot.SlotAllocationImpl;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SlotAllocationImplTest {

    SlotAllocation slotAllocation = new SlotAllocationImpl();

    static Car car1, car2,car3,car4,car5,car6,car7;

    @BeforeClass
    public static void beforeClass() throws Exception {
        car1 = new Car("REG1", "BLUE");
        car2 = new Car("REG2", "RED");
        car3 = new Car("REG3", "GREEN");
        car4 = new Car("REG4", "BLUE");
        car5 = new Car("REG5", "RED");
        car6 = new Car("REG6", "BLUE");
        car7 = new Car("REG2","YELLOW");
        System.out.println("Inside BeforeClass");
    }

    @Test
    @Order(1)
    public void TestAllocation() {
        slotAllocation.setAvailableSlots(5);
        assertEquals(1, slotAllocation.AllocateSlot(car1));
        assertEquals(2, slotAllocation.AllocateSlot(car2));
        assertEquals(3, slotAllocation.AllocateSlot(car3));
        assertEquals(4, slotAllocation.AllocateSlot(car4));
        assertEquals(5, slotAllocation.AllocateSlot(car5));
        assertEquals(0, slotAllocation.AllocateSlot(car6));
        assertEquals(0, slotAllocation.AllocateSlot(car7));
        System.out.println("slots allocated");
        slotAllocation.getAllSlotDetails();

        //DeallocateSlot()
        assertTrue(slotAllocation.DeallocateSlot(3));
        assertFalse(slotAllocation.DeallocateSlot(3));
        System.out.println("Car removed from slot 3");

        //testing getRecord()
        List regNumbers = slotAllocation.getRecord("RED", 1);
        assertArrayEquals(new String[]{"REG2", "REG5"}, regNumbers.toArray());

        List slotNumbers = slotAllocation.getRecord("BLUE", 3);
        assertArrayEquals(new Integer[]{1,4}, slotNumbers.toArray());

        //testing getSlotDetail()
        assertEquals(4, slotAllocation.getSlotDetail("REG4"));
        assertEquals(0, slotAllocation.getSlotDetail("REG3"));

        slotAllocation.getAllSlotDetails();
    }

}