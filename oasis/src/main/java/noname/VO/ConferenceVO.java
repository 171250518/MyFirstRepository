package noname.VO;

public class ConferenceVO {
    /**
     * 会议id
     */
    private int id;
    /**
     * 会议名
     */
    private String name;
    /**
     * 会议活跃度
     */
    private double rank;
    /**
     * 会议时间
     */
    private String time;
    /**
     * 会议论文数
     */
    private int paper_num;
    /**
     * 会议论文被引用数
     */
    private int citation;

    public ConferenceVO() {
    }

    public ConferenceVO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ConferenceVO(int id, String name, double rank, String time, int paper_num, int citation) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.time = time;
        this.paper_num = paper_num;
        this.citation = citation;
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

    public String getTime() {
        return time;
    }

    public int getPaper_num() {
        return paper_num;
    }

    public int getCitation() {
        return citation;
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

    public void setTime(String time) {
        this.time = time;
    }

    public void setPaper_num(int paper_num) {
        this.paper_num = paper_num;
    }

    public void setCitation(int citation) {
        this.citation = citation;
    }
}
