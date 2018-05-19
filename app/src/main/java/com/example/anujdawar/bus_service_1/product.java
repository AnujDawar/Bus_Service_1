package com.example.anujdawar.bus_service_1;

public class product
{
    int ID;
    String bus_number;

    public product(String bus_number)
    {
        ID++;
        this.bus_number = bus_number;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMyBusNumber() {
        return bus_number;
    }

    public void setMyBusNumber(String bus_number) {
        this.bus_number = bus_number;
    }
}
