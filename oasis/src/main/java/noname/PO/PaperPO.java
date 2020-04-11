package noname.PO;

import java.util.Date;

/**
 * 论文持久化类
 *
 * @author wch
 * @date 2020/3/3
 */

public class PaperPO {

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
     * */

    private String link;

    /**
     * 引用统计
     */

    private int citationCount;

    /**
     * 被引用统计
     */

    private int referenceCount;

    public PaperPO(int id, String title, String author, String publishTime, String conf, String affiliation, String content, String keywords, String link, int citationCount, int referenceCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishTime = publishTime;
        this.conf = conf;
        this.affiliation = affiliation;
        this.content = content;
        this.keywords = keywords;
        this.link = link;
        this.citationCount = citationCount;
        this.referenceCount = referenceCount;
    }

    public PaperPO(String title, String author, String publishTime, String conf, String affiliation, String content, String keyWord, String link, int citationCount, int referenceCount) {
        this.author = author;
        this.publishTime = publishTime;
        this.conf = conf;
        this.affiliation = affiliation;
        this.content = content;
        this.keywords=keyWord;
        this.citationCount=citationCount;
        this.link=link;
        this.title=title;
        this.referenceCount=referenceCount;
    }

    public PaperPO() {

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

    public String getAffiliation() { return affiliation; }

    public String getContent() {
        return content;
    }

    public String getKeywords(){return keywords;}

    public String getLink() {
        return link;
    }

    public int getCitationCount() {
        return citationCount;
    }

    public int getReferenceCount() {
        return referenceCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) { this.author = author; }

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

    public void setKeywords(String keyWord) {
        this.keywords = keyWord;
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
