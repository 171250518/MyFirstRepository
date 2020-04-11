package noname.PO;

public class AffiliationPO {
    /**
     * 机构id
     */
    private int id;
    /**
     * 机构名
     */
    private String name;
    /**
     * 机构论文量
     */
    private int paper_number;
    /**
     * 机构论文被引用量
     */
    private int citation_count;
    /**
     * 机构作者
     */
    private String authors;
    /**
     * 机构作者数
     */
    private int author_num;
    /**
     * 机构活跃度
     */
    private double rank;

    public AffiliationPO() { }

    public AffiliationPO(int id, String name, int paper_number, int citation_count, String authors, int author_num) {
        this.id = id;
        this.name = name;
        this.paper_number = paper_number;
        this.citation_count = citation_count;
        this.authors = authors;
        this.author_num = author_num;
    }

    public AffiliationPO(int id, String name, int paper_number, int citation_count, String authors, int author_num, double rank) {
        this.id = id;
        this.name = name;
        this.paper_number = paper_number;
        this.citation_count = citation_count;
        this.authors = authors;
        this.author_num = author_num;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPaper_number() {
        return paper_number;
    }

    public int getCitation_count() {
        return citation_count;
    }

    public String getAuthors() {
        return authors;
    }

    public int getAuthor_num() {
        return author_num;
    }

    public double getRank() {
        return rank;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPaper_number(int paper_number) {
        this.paper_number = paper_number;
    }

    public void setCitation_count(int citation_count) {
        this.citation_count = citation_count;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setAuthor_num(int author_num) {
        this.author_num = author_num;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }
}
