package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    // things below this line are for compile while writing and need to be removed
    private Part dummyPart;
    private Product dummyProduct;

    public Inventory() {
    }


    /**
     * @param newPart added to observable part list
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * @param newProduct added to observable product list
     */
    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * @param partID
     * @return part by ID
     */
    public static ObservableList<Part> lookUpPart(int partID) {
        ObservableList<Part> filteredParts = FXCollections.observableArrayList();
        for (Part part : allParts)
        {
            String id = String.valueOf(part.getId());
            String searchId = String.valueOf(partID);
            if (id.contains(searchId)) {
                filteredParts.add(part);
            }
        }
        return filteredParts;
    }

    /**
     * @param productID
     * @return Product by ID
     */
    public Product lookUpProduct(int productID) {
        // todo
        return this.dummyProduct;
    }

    /**
     * @param partName
     * @return part by part name
     */
    public static ObservableList<Part> lookUpPart(String partName) {
        ObservableList<Part> filteredParts = FXCollections.observableArrayList();
        for (Part part : allParts)
        {
            String name = part.getName().toLowerCase();
            if (name.contains(partName.toLowerCase())) {
                filteredParts.add(part);
            }
        }
        return filteredParts;
    }

    /**
     * @param productName
     * @return Product by Name
     */
    public Product lookUpProduct(String productName) {
        // todo
        return this.dummyProduct;
    }

    /**
     *
     * @param index Index of selected part
     * @param selectedPart part to be updated
     */
    //public void updatePart(int index, Part selectedPart){
        // todo
    //}

    /**
     *
     * @param oldPart un-updated part to be removed
     * @param updatedPart updated part to be added
     */
    public static void updatePart(Part updatedPart, Part oldPart){
        addPart(updatedPart);
        deletePart(oldPart);
    }

    /**
     *
     * @param index Index of selected product
     * @param selectedProduct product to be updated
     */
    public void updateProduct(int index, Product selectedProduct){
        // todo
    }

    /**
     *
     * @param selectedPart
     * @return successful deletion of part
     */
    public static boolean deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
        return true;
    }

    /**
     *
     * @param selectedProduct
     * @return successful deletion of product
     */
    public boolean deleteProduct(Product selectedProduct) {
        allProducts.remove(selectedProduct);
        return true;
    }

    /**
     *
     * @return ObservableList of parts
     */
    public static ObservableList<Part> getAllParts() { return allParts; }

    /**
     *
     * @return ObservableList of products
     */
    public static ObservableList<Product> getAllProducts() { return allProducts; }
}
