import java.math.BigDecimal;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;


public class Hotel {
    private String hotelName;
    private String address;
    private List<Room> rooms=new ArrayList<>();
    private List<Reservation> reservations=new ArrayList<>();
    private List<Guest> guests=new ArrayList<>();
    private List<Service> availableServices=new ArrayList<>();

    Hotel(String hotelName,String  address){
        this.hotelName=hotelName;
        this.address=address;
    }
    public List<Service> getAvailableServices() {
        return new ArrayList<>(availableServices);
    }
    public void setAvailableServices(List<Service> availableServices) {
        this.availableServices = new ArrayList<>(availableServices);
    }

    public String getHotelName() {
        return hotelName;
    }
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public void addRoom(Room room ){
        if(rooms.contains(room)){
            System.out.println("Room already exists");
        }
        else{rooms.add(room);}
    }

    public void registerGuest(Guest guest){
        if(guests.contains(guest)){
            System.out.println("Guest already exists");
        }else guests.add(guest);
    }

    public void addService(Service service){
        if(availableServices.contains(service)){
            System.out.println("service already exists");
        }else availableServices.add(service);
    }

    public List<Room> getAvailableRooms(LocalDate checkIn,LocalDate checkOut){
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getStatus() != Room.RoomStatus.Available) {
                continue;
            }
            boolean overlaps = false;
            for (Reservation reservation : reservations) {
                if (reservation.getRoom() != room || reservation.getStatus() == Reservation.ReservationStatus.Cancelled) {
                    continue;
                }
                boolean noOverlap = checkOut.isBefore(reservation.getCheckInDate())
                        || checkOut.equals(reservation.getCheckInDate())
                        || checkIn.isAfter(reservation.getCheckOutDate())
                        || checkIn.equals(reservation.getCheckOutDate());
                if (!noOverlap) {
                    overlaps = true;
                    break;
                }
            }
            if (!overlaps) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    static public Service findServiceByName(List<Service>services,String ServiceName){
        if (ServiceName == null) {
            return null;
        }
        for(Service service:services){
            if(service.getName().equalsIgnoreCase(ServiceName)){
                return service;
            }
        }
        return null;
    }

    int NoOfAvailableRooms=getAvailableRooms(LocalDate.now(),LocalDate.now().plusYears(1)).size();
    int NoOfOccupiedRooms=rooms.size()-NoOfAvailableRooms;

    public List<Room> getAvailableRoomsByType(Room.RoomType Type,LocalDate checkIn,LocalDate checkOut){
        List<Room> availableRoomsByType=new ArrayList<>();
        for (Room room : getAvailableRooms(checkIn, checkOut)) {
            if(room.getType().equals(Type)){
                availableRoomsByType.add(room);
            }
        }
        return availableRoomsByType;
    }

    public Reservation createReservation(Guest guest,Room room,LocalDate checkIn,LocalDate checkOut,int NumberOfGuests){
        if (room == null || guest == null) {
            return null;
        }
        if (room.getStatus() != Room.RoomStatus.Available) {
            return null;
        }
        Reservation reservation = new Reservation(guest,room,checkIn,checkOut,NumberOfGuests);
        reservations.add(reservation);
        room.ChangeStatus(Room.RoomStatus.Reserved);
        return reservation;
    }

    public void cancelReservation(String reservationId){
        Reservation reservation=getReservationsById(reservationId);
        if (reservation != null) {
            reservation.cancel();
        }
    }

    public void checkInGuest(String reservationId){
        Reservation reservation = getReservationsById(reservationId);
        if (reservation != null) {
            reservation.checkIn();
        }
    }

    public void checkOutGuest(String reservationId){
        Reservation reservation = getReservationsById(reservationId);
        if (reservation != null) {
            reservation.checkOut();
        }
    }

    public Reservation getReservationsById(String reservationId){
        Reservation searchedReservation = null;
        for (Reservation reservation : reservations) {
            if(reservation.getReservationId().equals(reservationId)){
                searchedReservation=reservation;
            }
        }
        return searchedReservation;
    }

    public Reservation getReservationsByGuest(String guestId){
        Reservation searchedReservation = null;
        for (Reservation reservation : reservations) {
            if(reservation.getGuest().getGuestId().equals(guestId)){
                searchedReservation=reservation;
            }
        }
        return searchedReservation;
    }

    public int getCurrentOccupancy(){
        int available = 0;
        int occupied = 0;
        for (Room room : rooms) {
            if (room.getStatus() == Room.RoomStatus.Available) {
                available++;
            } else if (room.getStatus() == Room.RoomStatus.Occupied) {
                occupied++;
            }
        }
        if (available + occupied == 0) {
            return 0;
        }
        return (int) Math.round((occupied * 100.0) / (available + occupied));
    }

    public BigDecimal  getRevenue(LocalDate startDate,LocalDate  endDate){
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation reservation : reservations) {
            if (reservation.getStatus() == Reservation.ReservationStatus.Cancelled) {
                continue;
            }
            boolean noOverlap = endDate.isBefore(reservation.getCheckInDate())
                    || endDate.equals(reservation.getCheckInDate())
                    || startDate.isAfter(reservation.getCheckOutDate())
                    || startDate.equals(reservation.getCheckOutDate());
            if (!noOverlap) {
                total = total.add(BigDecimal.valueOf(reservation.getTotal()));
            }
        }
        return total;
    }

    int NoOfUnderMaintenance(){
        int num=0;
        for (Room room:rooms) {
            if(room.getStatus() == Room.RoomStatus.UnderMaintenance){
                num++;
            }
        }
        return num;
    }

    public void displayHotelStatus(){
        System.out.println("=== "+hotelName +" ===");
        System.out.println("Total Rooms: "+rooms.size());
        int available = 0;
        int occupied = 0;
        for (Room room : rooms) {
            if (room.getStatus() == Room.RoomStatus.Available) {
                available++;
            } else if (room.getStatus() == Room.RoomStatus.Occupied) {
                occupied++;
            }
        }
        System.out.print("Available: "+available);
        System.out.println("("+(rooms.size() == 0 ? 0 : (available * 100 / rooms.size()))+"%)");
        System.out.print("Occupied: "+occupied);
        System.out.println("("+(rooms.size() == 0 ? 0 : (occupied * 100 / rooms.size()))+"%)");
        System.out.println("UnderMaintenance: "+NoOfUnderMaintenance());
        System.out.println("Current Occupancy: "+getCurrentOccupancy());
        System.out.println("Active Reservations: ");
        for (Reservation reservation : reservations) {
            if (reservation.getStatus() != Reservation.ReservationStatus.Cancelled) {
                System.out.println("- "+reservation.getReservationId()+" ("+reservation.getStatus()+")");
            }
        }
    }






}