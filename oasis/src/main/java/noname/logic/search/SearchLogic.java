package noname.logic.search;


import noname.VO.Response;

/**
 *搜索逻辑接口
 * @author ffj
 * @date  2019.2.29
 **/

public interface SearchLogic {

    /**
     * 根据作者搜索论文
     * @param author 论文作者
     * @return Response对象
     */
    Response searchPaperByAuthor(String author);

    /**
     * 根据发布机构搜索论文
     * @param affiliation 论文发布机构
     * @return Response对象
     */
    Response searchPaperByAffiliation(String affiliation);

    /**
     * 根据发布会议搜索论文
     * @param conf 论文发布会议
     * @return  Response对象
     */
    Response searchPaperByConf(String conf);

    /**
     * 根据论文题目搜索论文
     * @param title 论文题目
     * @return  Response对象
     */
    Response searchPaperByTitle(String title);
    /**
     * 根据发布时间搜索论文
     * @param year 发布时间
     * @return  Response对象
     */
    Response searchPaperByYear(String year);
    /**
     * 根据组合关键字搜索论文
     * @param comWords 组合关键字
     * @return  Response对象
     */
    Response searchPaperByCombination(String comWords);
    /**
     * 根据研究关键字搜索论文
     * @param keywords 发布时间
     * @return  Response对象
     */
    Response searchPaperByKeywords(String keywords);
}
