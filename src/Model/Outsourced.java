package Model;

public class Outsourced extends Part{
    private String companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     *
     * @param companyName set to company Name
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     *
     * @return company name
     */
    public String getCompanyName() {
        return this.companyName;
    }

    /**
     *checks that part is valid and returns Error message in case its not
     */
    public static String validateOutsource(String name, String price, String stock, String min, String max, String companyName){
        System.out.println(name);
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
        if (companyName == null || companyName.length() == 0){
            return "companyName can not be empty";
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
     *Determine if string can be converted to int
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
