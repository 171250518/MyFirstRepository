package noname.VO;

import noname.PO.PaperPO;

import java.util.Date;

/**
 * 论文可视化类
 *
 * @author wch
 * @date 2020/3/3
 */

public class PaperVO {
    private int id;

    /**
     * 论文标题
     */
    private String title;

    /**
     * 论文作者
     */
    private String author;

    /**
     * 发布时间
     */
    private String publishTime;

    /**
     * 发布会议
     */
    private String conf;

    /**
     * 发布机构
     */
    private String affiliation;

    /**
     * 论文内容
     */
    private String content;

    /**
     * 研究关键词
     */
    private String keywords;
    /**
     * PDF链接
     */

    private String link;

    /**
     * 引用统计
     */

    private int citationCount;

    /**
     * 被引用统计
     */

    private int referenceCount;

    public PaperVO() { }

    public PaperVO(int id, String title, String publishTime, String link, int citationCount) {
        this.id = id;
        this.title = title;
        this.publishTime = publishTime;
        this.link = link;
        this.citationCount = citationCount;
    }

    public PaperVO(int id, String title, String author, String publishTime, String conf, String affiliation, String content, String keyWord, String link, int citationCount, int referenceCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishTime = publishTime;
        this.conf = conf;
        this.affiliation= affiliation;
        this.content = content;
        this.keywords = keyWord;
        this.link = link;
        this.citationCount = citationCount;
        this.referenceCount = referenceCount;
    }

    public PaperVO(PaperPO paperPO) {
        this.id = paperPO.getId();
        this.author = paperPO.getAuthor();
        this.conf = paperPO.getConf();
        this.content = paperPO.getContent();
        this.citationCount = paperPO.getCitationCount();
        this.keywords = paperPO.getKeywords();
        this.publishTime = paperPO.getPublishTime();
        this.link = paperPO.getLink();
        this.title = paperPO.getTitle();
        this.referenceCount = paperPO.getReferenceCount();
        this.affiliation = paperPO.getAffiliation();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public String getConf() {
        return conf;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getContent() {
        return content;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getLink() {
        return link;
    }

    public int getCitationCount() {
        return citationCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReferenceCount() {
        return referenceCount;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setCitationCount(int citationCount) {
        this.citationCount = citationCount;
    }

    public void setReferenceCount(int referenceCount) {
        this.referenceCount = referenceCount;
    }

    public void setLink(String link) {
        this.link = link;

    }
}