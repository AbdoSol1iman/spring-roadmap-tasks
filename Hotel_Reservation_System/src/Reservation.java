import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    enum ReservationStatus{Pending, Confirmed, CheckedIn, CheckedOut, Cancelled }
    private ReservationStatus status;
    private String reservationId;
    private Guest guest;
    private Room room;
    private LocalDate checkInDate;
    //    LocalDate checkInDate = LocalDate.of(2024, 1, 1);
    private LocalDate checkOutDate;
    private List<Service> services = new ArrayList<>();
    private int totalGuests                ;

    public final DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");


    Reservation(Guest guest,Room room,LocalDate checkInDate,LocalDate checkOutDate,int totalGuests){
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalGuests = totalGuests;
        reservationId = UUID.randomUUID().toString();
        this.status = ReservationStatus.Confirmed;

    }
    public String getReservationId(){
        return reservationId;
    }
    public void setReservationId(String reservationId){
        this.reservationId = reservationId;
    }
    public ReservationStatus getStatus() {
        return status;
    }
    public Guest getGuest() {
        return guest;
    }
    public Room getRoom() {
        return room;
    }
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }


    public long getNumberOfNights(){
        return ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }
    public double getRoomCost(){

        return room.getPricePerNight() * getNumberOfNights();
    }
    public double getServicesCost(){
        double total = 0;
        for(Service s: services){
            total += s.getPrice();
        }
        return total;
    }
    public double getTotal(){
        double totalService = getRoomCost() + getServicesCost();
        double discountPercent = guest.getDiscountRate();
        double total = totalService - (totalService * discountPercent / 100.0);
        double rounded = Math.round(total * 100.0) / 100.0;
        return rounded;
    }
    public void addService(Service service){
        if (service == null) {
            return;
        }
        services.add(service);
    }
    public void checkIn(){
        this.status = ReservationStatus.CheckedIn;
        this.room.ChangeStatus(Room.RoomStatus.Occupied);
    }
    public double checkOut(){
        this.status = ReservationStatus.CheckedOut;
        this.room.ChangeStatus(Room.RoomStatus.Available);
        return getTotal();
    }
    public void cancel(){
        this.status = ReservationStatus.Cancelled;
        if (this.room != null && this.room.getStatus() == Room.RoomStatus.Reserved) {
            this.room.ChangeStatus(Room.RoomStatus.Available);
        }
    }
    void getReservationDetails() {
        System.out.println("=== Reservation Details ===");
        System.out.println("Reservation Id: " + reservationId);
        System.out.println("Guest: " + guest.getName()+" ("+guest.getGuestId()+")");
        System.out.println("Email: " + guest.getEmail()+", Phone: " + guest.getPhone());
        System.out.println("Room: "+this.room.getRoomNumber()+" ("+this.room.getType()+") - Floor "+this.room.getFloor());
        System.out.println("Check-in: "+this.checkInDate.format(myFormat));
        System.out.println("Check-out: "+this.checkOutDate.format(myFormat));
        System.out.println("Nights: "+getNumberOfNights());
        System.out.println("Number of guests: "+totalGuests);
        System.out.println("Statues: "+ this.status);


        System.out.println("\nServices:");
        for (Service s: services) {
            System.out.println("- "+s.getName()+": $"+s.getPrice());
        }

    }




}
