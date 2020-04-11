package noname.PO;

public class Affiliation_Paper_PO {
    private int affiliation_id;
    private int paper_id;
    private String name;
    private int c_count;
    private int year;

    public Affiliation_Paper_PO() {
    }

    public Affiliation_Paper_PO(int affiliation_id, int paper_id, String name, int c_count, int year) {
        this.affiliation_id = affiliation_id;
        this.paper_id = paper_id;
        this.name = name;
        this.c_count = c_count;
        this.year = year;
    }

    public int getAffiliationId() {
        return affiliation_id;
    }

    public int getPaperId() {
        return paper_id;
    }

    public String getName() {
        return name;
    }

    public int getC_count() {
        return c_count;
    }

    public int getYear() {
        return year;
    }

    public void setAffiliationId(int id) {
        this.affiliation_id = id;
    }

    public void setPaperId(int id) {
        this.paper_id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setC_count(int c_count) {
        this.c_count = c_count;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
