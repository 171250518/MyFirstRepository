package noname.PO;

public class Conference_Paper_PO {
    private int conference_id;
    private int paper_id;
    private String conference_name;
    private int c_count;
    private int year;

    public Conference_Paper_PO() {
    }

    public Conference_Paper_PO(int conference_id, int paper_id, String conference_name, int c_count, int year) {
        this.conference_id = conference_id;
        this.paper_id = paper_id;
        this.conference_name = conference_name;
        this.c_count = c_count;
        this.year = year;
    }

    public int getConferenceId() {
        return conference_id;
    }

    public int getPaperId() {
        return paper_id;
    }

    public String getName() {
        return conference_name;
    }

    public int getC_count() {
        return c_count;
    }

    public int getYear() {
        return year;
    }

    public void setConferenceId(int id) {
        this.conference_id = id;
    }

    public void setPaperId(int id) {
        this.paper_id = id;
    }

    public void setName(String name) {
        this.conference_name = name;
    }

    public void setC_count(int c_count) {
        this.c_count = c_count;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
