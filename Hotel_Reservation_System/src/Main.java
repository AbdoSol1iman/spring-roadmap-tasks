import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

class Main{
    public static void main (String[] args){
        System.out.println("\t\t...Hotel Reservation System...");
        Hotel hotel=new Hotel("Marshal hotel","el mashaya,Mansoura");

        hotel.addRoom(new Room("101", Room.RoomType.Single,1,89.99,1));
        hotel.addRoom(new Room("201", Room.RoomType.Double,2,129.99,2));
        hotel.addRoom(new Room("301", Room.RoomType.Suite,3,419.99,4));
        hotel.addRoom(new Room("401", Room.RoomType.Deluxe,4,349.99,3));

        hotel.addService(new Service("S001","Room Service",25.00,"24-hour room service "));
        hotel.addService(new Service("S002", "Spa Treatment", 100.00, "90-minute massage"));
        hotel.addService(new Service("S003", "Airport Shuttle", 50.00, "Round trip airport transfer"));
        hotel.addService(new Service("S004", "Breakfast Buffet", 20.00, "Continental breakfast"));

        Guest guest1 = new Guest("G001", "Abdo Soliman", "pyramidfourth40@gmail.com", "1092721374", "ID123456", 250);
        Guest guest2 = new Guest("G002", "Nour Abdelmqsoud", "nour23@gmail.com", "+1556335858", "ID789012", 100);

        hotel.registerGuest(guest1);
        hotel.registerGuest(guest2);

        LocalDate checkIn =LocalDate.now().plusDays(7);
        LocalDate checkOut = checkIn .plusDays(3) ;

        List<Room> availableRooms = hotel.getAvailableRooms(checkIn, checkOut);

        System.out.println("Available rooms for " + checkIn + " to " + checkOut + ":");
        for (Room room : availableRooms) {
            System.out.println("- Room " + room.getRoomNumber() + " (" + room.getType() + ") - $" + room.getPricePerNight() + "/night");
        }
        Room selectedRoom = Room.findRoomByType(availableRooms, Room.RoomType.Suite);
        Reservation reservation = hotel.createReservation(guest1, selectedRoom, checkIn, checkOut, 2);

        System.out.println("\nReservation created: " + reservation.getReservationId());


        reservation.addService(Hotel.findServiceByName(hotel.getAvailableServices(), "Breakfast Buffet"));
        reservation.addService(Hotel.findServiceByName(hotel.getAvailableServices(), "Airport Shuttle"));

        reservation.getReservationDetails();

        System.out.println("\nReservation Summary:");
        System.out.println("Room Cost (" + reservation.getNumberOfNights() + " nights): $" + reservation.getRoomCost());
        System.out.println("Services Cost: $" + reservation.getServicesCost());
        System.out.println("Guest Discount: " + (guest1.getDiscountRate() ) + "%");
        System.out.println("Total: $" + reservation.getTotal());

        hotel.checkInGuest(reservation.getReservationId());
        System.out.println("\nGuest checked in. Room " + selectedRoom.getRoomNumber() + " status: " + selectedRoom.getStatus());

        hotel.displayHotelStatus();

        hotel.checkOutGuest(reservation.getReservationId());
        System.out.println("\nGuest checked out. Final bill: $" + reservation.getTotal());

        BigDecimal revenue = hotel.getRevenue(LocalDate.now(), LocalDate.now().plusDays(30));
        System.out.println("\nProjected 30-day revenue: $" + revenue);


    }


}