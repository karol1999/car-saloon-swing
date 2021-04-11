package com.main;
import java.util.Comparator;

public class ReverseSortByAmount implements Comparator<Vehicle>
{
    @Override
    public int compare(Vehicle firstVehicle, Vehicle secondVehicle)
    {
        return Integer.compare(firstVehicle.getAmountOfCars(),secondVehicle.getAmountOfCars());
    }
}
