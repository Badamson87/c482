package Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * <P><B>
 *-RunTime Error
 *
 *    A run time error that I experienced during this project was when the program would attempt to parse a null number.
 * This would happen in the case of converting a string to number from and input field such as price when no input had been
 * given. My solution to the problem was first to build a list of checks for null values or empty strings, And then a validation
 * function. This function would take in a string and then using a try/catch block attempt to parse the string into an integer.
 * A boolean would be return from the function IsValidInt labeling it true or false.
 *
 * -Extend the program
 *
 *  I think a great way to extend the program would be to expand the search criteria to look up outsource company's that you
 * acquire parts from. This functionality would ideally would provide to the user the name and contact information of the company.
 * As well as a complete parts list of associated parts. This would make the process of ordering parts easy when your inventory
 * is low or out of stock. You could also create a warning message that automatically notifies the user when a part stock is low.
 * This reminder would help prevent wait times and improve production
 *
 * </B></P>
 */


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
