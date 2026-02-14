public class Guest {
    private String guestId;
    private String name;
    private String email;
    private String phone;
    private String idNumber;
    private int loyaltyPoints;

    Guest(String guestId, String name, String email, String phone, String idNumber, int loyaltyPoints) {
        this.guestId = guestId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.idNumber = idNumber;
        this.loyaltyPoints = loyaltyPoints;
    }
    public String getGuestId() {
        return guestId;
    }
    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdNumber() {
        return idNumber;
    }
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }
    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = Math.max(0, loyaltyPoints);
    }

    public void getGuestInfo(){
        System.out.println("*************************************");
        System.out.println("Guest ID: " + guestId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("IdNumber: " + idNumber);
        System.out.println("Loyalty Points: " + loyaltyPoints);
        System.out.println("*************************************");
    }


    public void addLoyaltyPoints(int points){
        this.loyaltyPoints += points;
        System.out.println("Loyalty Points: " + loyaltyPoints);
    }

    public double getDiscountRate(){
        // Loyalty points map directly to a percentage value.
        return Math.max(0, this.loyaltyPoints) * 0.01;
    }

}
