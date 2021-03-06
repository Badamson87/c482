package Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * this class handles the creation and management of a product
 */
public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Product constructor
     * @param id New Product ID
     * @param name New Product Name
     * @param price New Product price
     * @param stock New Product Stock value
     * @param min new Product Min value
     * @param max new product max value
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @param part added as associated Product
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /**
     * @param selectedAssociatedPart removed from associated Product
     * @return successful deletion of associated part
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
       return associatedParts.remove(selectedAssociatedPart);
    }

    /**
      * @return observable list of associated parts
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }
}
