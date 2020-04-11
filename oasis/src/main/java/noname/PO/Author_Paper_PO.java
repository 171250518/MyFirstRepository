package noname.PO;

public class Author_Paper_PO {
    private int author_id;
    private int paper_id;
    private String name;
    private int c_count;
    private int year;

    public Author_Paper_PO() {
    }

    public Author_Paper_PO(int author_id, int paper_id, String name, int c_count, int year) {
        this.author_id = author_id;
        this.paper_id = paper_id;
        this.name = name;
        this.c_count = c_count;
        this.year = year;
    }

    public int getAuthorId() {
        return author_id;
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

    public void setAuthorId(int id) {
        this.author_id = id;
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
