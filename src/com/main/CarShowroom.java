package com.main;

import java.util.*;
import java.util.regex.Pattern;

public class CarShowroom
{
    private String nameOfTheCarShowroom;
    private List<Vehicle> carList;
    private int maxCarShowroomCapacity;

    public CarShowroom(String newNameOfTheCarShowroom, int newMaxCarShowroomCapacity)
    {
        this.nameOfTheCarShowroom = newNameOfTheCarShowroom;
        this.maxCarShowroomCapacity = newMaxCarShowroomCapacity;
        this.carList = new ArrayList<>(); // na razie tworzymy pustą ArrayListę
    }

    public boolean addProduct(Vehicle vehicle)
    {
        if (vehicle != null) // jeżeli obiekt nie jest nullem
        {
            if (carList.size() == maxCarShowroomCapacity) // sprawdzamy pojemność ArrayListy
            {
                System.err.println("Capacity exceeded"); // tutaj są dodawane auta o podanym modelu
            }
            else if (carList.contains(vehicle))
            {
                carList.get(carList.indexOf(vehicle)).adjustAmountOfCars(vehicle.getAmountOfCars());
                return true;
            }

            return carList.add(vehicle); // po prostu dodajemy nowy model
        }

        return false; // jeżeli nic z powyższych to nie udało nam się dodać auta do listy
    }

    public void getProduct(Vehicle vehicle)
    {
        vehicle.adjustAmountOfCars(-1); // zliczamy ilość pojazdów w garażu

        if (vehicle.getAmountOfCars()==0) // jeżeli ilość samochodów jest równa 0 to go całkowicie usuwamy
        {
            carList.remove(vehicle);
        }
    }

    public boolean removeProduct(Vehicle vehicle)
    {
        if (vehicle!=null)
        {
            return carList.remove(vehicle);
        }
        return false;
    }

    public void search(String newModel)
    {
        for(Vehicle vehicle : carList)
        {
            if(vehicle.getModel().equalsIgnoreCase(newModel))
            {
                vehicle.print();
                return;
            }
        }

        System.out.println("Nie znaleziono danego przedmiotu: " + newModel);
    }

    public void searchPartial(String newModelPartial)
    {
        for(Vehicle vehicle : carList)
        {
            if(Pattern.compile(Pattern.quote(newModelPartial), Pattern.CASE_INSENSITIVE).matcher(vehicle.getModel()).find())
            {
                vehicle.print();
                return;
            }
        }

        System.out.println("Nie znaleziono danego przedmiotu");
    }

    public int countByCondition(ItemCondition newItemCondition)
    {
        int amoutOfCarsOfParticularCondition = 0;

        for (Vehicle vehicle : carList)
        {
            if(vehicle.getItemCondition().equals(newItemCondition))
            {
                amoutOfCarsOfParticularCondition += vehicle.getAmountOfCars();
            }
        }

        if(amoutOfCarsOfParticularCondition == 0)
        {
            System.out.println("Nie znaleziono danego przedmiotu");
            return 0;
        }
        else
        {
            return amoutOfCarsOfParticularCondition;
        }
    }

    public void summary()
    {
        for (Vehicle vehicle : carList)
        {
            vehicle.print();
        }
    }

    public  void sortByName()
    {
        Collections.sort(carList);
        for (Vehicle vehicle : carList)
        {
            vehicle.print();
        }
    }

    public void sortByAmount()
    {
        Collections.sort(carList, new ReverseSortByAmount());

        for (Vehicle vehicle: carList)
        {
            vehicle.print();
        }
    }

    public Vehicle maxElement() // mogliśmy stworzyć klasę z implementacją Comparatora, albo w maine wywołać z przypisaniem albo anonymous class tak jak tutaj
    {
        return Collections.max(carList, new Comparator<Vehicle>()
        {
            @Override
            public int compare(Vehicle firstVehicle, Vehicle secondVehicle)
            {
                return Integer.compare(firstVehicle.getAmountOfCars(), secondVehicle.getAmountOfCars());
            }
        });
    }

    public boolean isEmpty()
    {
        if (carList.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public double getPercentageFill()
    {
        double result = (double) carList.size()/getMaxCarShowroomCapacity();
        return result*100;
    }

    public List<Vehicle> getCarList() {
        return carList;
    }

    public int getMaxCarShowroomCapacity() {
        return maxCarShowroomCapacity;
    }

    public String getSRoomName() {
        return nameOfTheCarShowroom;
    }
}
