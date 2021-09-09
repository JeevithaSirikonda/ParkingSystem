package com.parkinglot.test;

import com.parkinglot.Car;
import com.parkinglot.SlotAllocation;
import com.parkinglot.SlotAllocationImpl;
import org.junit.BeforeClass;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CarParkingSystemTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(SlotAllocationImplTest.class);
        for(Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }
        System.out.println("Result: "+result.wasSuccessful());
    }

}