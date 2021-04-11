package com.main;

import java.awt.print.Book;
import java.util.*;

public class CarShowroomContainer
{
    private Map<String,CarShowroom> salony;

    public CarShowroomContainer() {
        this.salony = new HashMap<>();
    }

    public void addCenter(String name, CarShowroom carShowroom){
        salony.put(name,carShowroom);
    }

    public void removeCenter(String name){
        salony.remove(name);
    }

    public List<CarShowroom> findEmpty()
    {
        List<CarShowroom> salons= new ArrayList<>();
        for (Map.Entry<String, CarShowroom> entry : salony.entrySet())
        {
            if (entry.getValue().isEmpty())
            {
                salons.add(entry.getValue());
            }
        }
        return salons;
    }

    public void summary()
    {
        for (Map.Entry<String, CarShowroom> entry : salony.entrySet())
        {
            System.out.println("Nazwa salonu: "+entry.getKey()+"Procentowe zapełninie: "+
                    entry.getValue().getPercentageFill()+"%");
        }
    }

    public String [] arrayOfShowrooms()
    {
        String [] array=new String[salony.size()];
        int i=0;
        for (Map.Entry<String,CarShowroom> entry : salony.entrySet())
        {
            array[i]=entry.getKey();
            i++;
        }
        return array;
    }

    public String [] arrayOfSortedShowrooms()
    {
        int i=0;
        String [] array=new String[salony.size()];
        ArrayList<CarShowroom> valueList = new ArrayList<>(salony.values());
        Collections.sort(valueList, new Comparator<CarShowroom>()
        {
            @Override
            public int compare(CarShowroom o1, CarShowroom o2)
            {
                return Double.compare(o2.getPercentageFill(),o1.getPercentageFill());
            }
        });
        for (CarShowroom showroom : valueList)
        {
            array[i]=showroom.getSRoomName();
            i++;
        }
        return array;
    }


    public CarShowroom selectedShowroom(String name)
    {
        for (Map.Entry<String,CarShowroom> entry : salony.entrySet())
        {
            if (entry.getKey().equals(name))
            {
                return entry.getValue();
            }
        }
        return null;
    }
}
