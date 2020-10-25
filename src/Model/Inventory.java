package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

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
    public static void addProduct(Product newProduct) {
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
    public static ObservableList<Product> lookUpProduct(String productName) {
        ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
        for (Product product : allProducts)
        {
            String name = product.getName().toLowerCase();
            if (name.contains(productName.toLowerCase())) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    /**
     * @param ProductId
     * @return Product filtered list by ID
     */
    public static ObservableList<Product> lookUpProduct(Integer ProductId) {
        ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
        for (Product product : allProducts)
        {
            String id = String.valueOf(product.getId());
            String searchId = String.valueOf(ProductId);
            if (id.contains(searchId)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

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
     * @param oldProduct un-updated part to be removed
     * @param newProduct updated part to be added
     */
    public static void updateProduct(Product newProduct, Product oldProduct){
        deleteProduct(oldProduct);
        addProduct(newProduct);
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
    public static boolean deleteProduct(Product selectedProduct) {
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
