package com.company;

import java.util.*;

public class OfficeFloor {
    private final String floorName;
    private List<Seat> seats = new ArrayList<>();  //list of seat objects can be Arraylist/LinkedList
//    private Collection<Seat> seats = new ArrayList<>();  //list of seat objects can be HSET/LSET/LIST/Q/DEQ

    public OfficeFloor(String floorName, int tables, int seatsPerTable) {  //constructor to create seats and store in List
        this.floorName = floorName;

        int totalTables = 'A' + (tables-1);
        for (char table = 'A'; table<=totalTables;table++){
            for(int seatNumber=1; seatNumber<=seatsPerTable; seatNumber++){
                Seat seat = new Seat(table + String.format("%02d", seatNumber)); // creating a seat Object
                seats.add(seat);
            }
        }
    }

    public String getFloorName() {   //getter for RoomName
        return floorName;
    }
    public boolean reserveSeat (String seatNumber){
        Seat requestedSeat = new Seat(seatNumber);
        int foundSeat = Collections.binarySearch(seats, requestedSeat, null);
        if(foundSeat >= 0){
            return seats.get(foundSeat).reserve();
        }else {
            System.out.println("There is no seat " + seatNumber);
            return false;
        }
    }
    public boolean cancelSeat (String seatNumber) {
        Seat requestedSeat = new Seat(seatNumber);
        int foundSeat = Collections.binarySearch(seats, requestedSeat, null);
        if (foundSeat >= 0) {
            return seats.get(foundSeat).cancel();
        } else {
            System.out.println("There is no seat " + seatNumber);
            return false;
        }

    }

//        for(Seat seat: seats){
//            System.out.println(".");
//            if(seat.getSeatNumber().equals(seatNumber)){
//                requestedSeat = seat;
//                break;
//            }
//        }
//        if (requestedSeat == null){
//            System.out.println("There is no seat " + seatNumber);
//            return false;
//        }
//        return requestedSeat.reserve();

    //for Testing
    public void getSeats(){
        for(Seat seat : seats){
            System.out.println(seat.getSeatNumber());
        }
    }
    private class Seat implements Comparable<Seat>{  //to be able to apply binary search and use compareTo
        private final String seatNumber;
        private boolean reserved = false;

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        @Override
        public int compareTo(Seat seat) {  //comparison to fulfill the Interface (built into String Class)
            return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        }

        public boolean reserve(){   //booking the seat
            if(!this.reserved){
                this.reserved = true;
                System.out.println("Seat " + seatNumber + " is reserved");
                return true;
            }else {
                return false;
            }
        }
        public boolean cancel(){  //cancel booking of the seat
            if(this.reserved){
                this.reserved = false;
                System.out.println("Reservation of seat " + seatNumber + " is cancelled");
                return true;
            }else {
                System.out.println("The seat " + seatNumber + " is not yet booked");
                return false;
            }
        }

        public String getSeatNumber() {
            return seatNumber;
        }
    }
}
//Timetable
//https://www.youtube.com/watch?v=Pz0CF3DMNec
