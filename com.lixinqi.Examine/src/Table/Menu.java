package Table;

public class Menu {
    private int id;
    private String food;
    private double price;
    private String describe;
    private int stock;

    public Menu(int id, String food, double price, String describe, int stock) {
        this.id = id;
        this.food = food;
        this.price = price;
        this.describe = describe;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "id: " + id +
                "\tfood:\t" + food  +
                "\tprice:\t" + price +
                "\tdescribe:\t" + describe +
                "\tstock:\t" + stock ;
    }
}