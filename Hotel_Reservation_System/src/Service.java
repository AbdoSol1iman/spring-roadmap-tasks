public class Service implements IChargeable {

    String serviceId  ;
    String name       ;
    double price       ;
    String description;

    Service (String serviceId, String name, double price, String description) {
        this.serviceId = serviceId;
        this.name = name;
        this.price = price;
        this.description = description;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescribtion() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceId() {
        return serviceId;
    }
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price=price;
    }

}
