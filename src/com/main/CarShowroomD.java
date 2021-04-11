package com.main;

import javax.imageio.stream.ImageInputStream;

public class CarShowroomD
{
    CarShowroomContainer carShowroomC = new CarShowroomContainer();

    CarShowroom carSh1A = new CarShowroom("AUDI",3);
    CarShowroom carSh2B = new CarShowroom("BMW",5);
    CarShowroom carSh3O = new CarShowroom("OPEL",7);

    public CarShowroomD()
    {
        carShowroomC.addCenter(carSh1A.getSRoomName(), carSh1A);
        carShowroomC.addCenter(carSh2B.getSRoomName(), carSh2B);
        carShowroomC.addCenter(carSh3O.getSRoomName(), carSh3O);

        Vehicle vehicle = new Vehicle("Audi","RS7",ItemCondition.USED,1500,2017,1500,2000,1);
        carSh1A.addProduct(vehicle);

        vehicle = new Vehicle("Audi","A3",ItemCondition.USED,32000,2018,3000,3000,1);
        carSh1A.addProduct(vehicle);

        vehicle = new Vehicle("Audi","Q1",ItemCondition.NEW,2000,2020,0,2500,1);
        carSh3O.addProduct(vehicle);

        vehicle = new Vehicle("BMW","M5",ItemCondition.USED,1600,2019,10,1800,1);
        carSh2B.addProduct(vehicle);

        vehicle = new Vehicle("BMW","M4",ItemCondition.NEW,250000,2020,0,1800,1);
        carSh2B.addProduct(vehicle);

        vehicle = new Vehicle("Opel","Vectra",ItemCondition.USED,800,2016,2000,1800,1);
        carSh3O.addProduct(vehicle);

    }

}
