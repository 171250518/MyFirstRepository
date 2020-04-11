package noname.VO;

public class AuthorVO {
    private int id;
    /**
     * 学者姓名
     */
    private String name;

    /**
     * 学者所属机构
     */
    private String institution;

    /**
     * 研究方向
     */
    private String researchDirection;

    /**
     * 论文数
     */
    private int paper_num;

    /**
     * 论文被引用数
     */
    private int c_count;

    /**
     * 活跃度
     */
    private double rank;

    public AuthorVO() {
    }

    public AuthorVO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public AuthorVO(int id, String name, String institution, String researchDirection, int paper_num, int c_count) {
        this.id = id;
        this.name = name;
        this.institution = institution;
        this.researchDirection = researchDirection;
        this.paper_num = paper_num;
        this.c_count = c_count;
    }
    public AuthorVO(int id, String name, String institution, String researchDirection, int paper_num, int c_count,double rank) {
        this.id = id;
        this.name = name;
        this.institution = institution;
        this.researchDirection = researchDirection;
        this.paper_num = paper_num;
        this.c_count = c_count;
        this.rank=rank;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInstitution() {
        return institution;
    }

    public String getResearchDirection() {
        return researchDirection;
    }

    public int getPaper_num() {
        return paper_num;
    }

    public int getC_count() {
        return c_count;
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

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setResearchDirection(String researchDirection) {
        this.researchDirection = researchDirection;
    }

    public void setPaper_num(int paper_num) {
        this.paper_num = paper_num;
    }

    public void setC_count(int c_count) {
        this.c_count = c_count;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }
}
