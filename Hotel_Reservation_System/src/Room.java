import java.util.ArrayList;
import java.util.List;

public class Room {
    public enum RoomType{Single, Double, Suite, Deluxe, Presidential}
    public enum RoomStatus{Available, Occupied, UnderMaintenance, Reserved}
    private String roomNumber;
    private RoomType type;
    private RoomStatus status;
    private int floor;
    private double pricePerNight;
    private int maxOccupancy;
    private List<String> amenities;

    Room(String roomNumber,RoomType type,int floor,double pricePerNight,int maxOccupancy){
        this.roomNumber = roomNumber;
        this.type = type;
        this.floor = floor;
        this.pricePerNight = pricePerNight;
        this.maxOccupancy = maxOccupancy;
        this.status = RoomStatus.Available;
        this.amenities = new ArrayList<>();
    }

    public static Room findRoomByType(List<Room> availableRooms, Room.RoomType roomType) {
        if (availableRooms == null || roomType == null) {
            return null;
        }
        for (Room room : availableRooms) {
            if (room.getType().equals(roomType)) {
                return room;
            }
        }
        return null;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getType() {
        return type;
    }
    public void setType(RoomType type) {
        this.type = type;
    }

    public int getFloor() {
        return floor;
    }
    public void setFloor(int floor) {
        if (floor<0 ){
            System.out.println("Invalid Floor");
        }else this.floor = floor;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }
    public void setPricePerNight(double pricePerNight) {
        if (pricePerNight<0){
            System.out.println("Invalid Price");
        }else this.pricePerNight = pricePerNight;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }
    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public List<String> getAmenities() {
        // Return a copy to avoid external mutation.
        return new ArrayList<>(amenities);
    }

    public boolean isAvailable(){
        return this.status == RoomStatus.Available;
    }

    public RoomStatus getStatus() {
        return status;
    }
    public void ChangeStatus(RoomStatus status) {
        this.status = status;
    }




}
