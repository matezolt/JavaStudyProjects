package com.company;

public class Main {

    public static void main(String[] args) {
        OfficeFloor floor = new OfficeFloor("FirstFloor", 4, 6);

        floor.getSeats();                             // available seats displayed

        if (floor.reserveSeat("D02")) {
            System.out.println("You booked the seat for the day" + " on the " + floor.getFloorName());
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
        if(floor.cancelSeat("D02")){
            System.out.println("You cancelled the seat booking");
        }

    }
}
