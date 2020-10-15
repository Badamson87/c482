package Model;

public class Outsourced extends Part{
    private String companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
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
}
