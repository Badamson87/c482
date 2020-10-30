package Model;

/**
 * This class extends part to create in House parts
 */
public class InHouse extends Part {
    private int machineId;

    /**
     * Constructor to create a new In house part
     * @param id part id
     * @param name Part Name
     * @param price Part Price
     * @param stock Part Stock
     * @param min Part Min value
     * @param max Part Max value
     * @param machineId Part Machine id
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     *
     * @param machineId set to machine ID
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     *
     * @return the machine ID
     */
    public int getMachineId() {
        return this.machineId;
    }


    /**
     *
     * @param name is checked as valid
     * @param price is checked as valid
     * @param stock is checked as valid
     * @param min is checked as valid
     * @param max is checked as valid
     * @param machineId is checked as valid
     * @return if part is valid or not
     */
    public static String validateInHouse(String name, String price, String stock, String min, String max, String machineId){
        if (name == null || name.length() == 0){
                return "Name field can not be empty";
            }
            if (stock == null || stock.length() == 0) {
                return "Inv field can not be empty";
            }
            if (price == null || price.length() == 0){
                return "Price field can not be empty";
            }
            if (min == null || min.length() == 0){
                return "Min field can not be empty";
            }
            if (max == null || max.length() == 0) {
                return "Max field can not be empty";
            }
            if (machineId == null || machineId.length() == 0){
                return "MachineId can not be empty";
            }
            if (validateIsDouble(price) == false) {
                return "Price field must be a number";
            }
            if (validateIsInt(stock) == false) {
                return "Inv field must be a number";
            }
            if (validateIsInt(min) == false){
                return "Min field must be a number";
            }
            if (validateIsInt(max) == false) {
                return "Max field must be a number";
            }
            if (validateIsInt(machineId) == false){
                return "Machine ID field must be a number";
            }
            if (Integer.parseInt(min) > Integer.parseInt(max) || Integer.parseInt(min) == Integer.parseInt(max)) {
                return "Min Must be less than Max";
            }
        return "";
    }

    /**
     *Determine if string can be converted to int
     */
     static boolean validateIsInt(String string){
        try {
         Integer.parseInt(string);
         return true;
         }
        catch (NumberFormatException e) {
         return false;
         }
    }

    /**
     *Determine if string can be converted to Double
     */
    static boolean validateIsDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
