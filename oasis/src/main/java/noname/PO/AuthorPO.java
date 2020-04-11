package noname.PO;

public class AuthorPO {
    private int id;
    private String name;
    private double rank;
    /**
     * 学者所属机构
     */
    private String institution;

    /**
     * 研究方向
     */
    private String researchDirection;

    public AuthorPO() {
    }

    public AuthorPO(int id, String name, double rank, String institution, String researchDirection) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.institution = institution;
        this.researchDirection = researchDirection;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getRank() {
        return rank;
    }

    public String getInstitution() {
        return institution;
    }

    public String getResearchDirection() {
        return researchDirection;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setResearchDirection(String researchDirection) {
        this.researchDirection = researchDirection;
    }
}
