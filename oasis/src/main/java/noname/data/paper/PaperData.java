package noname.data.paper;
import noname.PO.PaperPO;
import java.util.List;
/**
 * 论文数据的增删改查接口
 *
 * @author wch
 * @date 2020/3/6
 */
public interface PaperData {
    /**
     * 根据作者搜索论文
     * @param author 论文作者
     * @return PaperPO列表
     */
     List<PaperPO> searchPaperByAuthor(String author);

    /**
     * 根据机构搜索论文
     * @param affiliation 论文发布机构
     * @return PaperPO列表
     */
     List<PaperPO> searchPaperByAffiliation(String affiliation);

    /**
     * 根据发布会议搜索论文
     * @param conf 论文发布会议
     * @return  PaperPO列表
     */
     List<PaperPO> searchPaperByConf(String conf);

    /**
     * 根据论文题目搜索论文
     * @param title 论文题目
     * @return  Response对象
     */
     List<PaperPO> searchPaperByTitle(String title);
    /**
     * 根据发布时间搜索论文
     * @param year 发布时间
     * @return  Response对象
     */
    List<PaperPO> searchPaperByYear(String year);
    /**
     * 根据组合关键字搜索论文
     * @param author  组合关键字
     * @return  Response对象
     */
    List<PaperPO> searchPaperByCombination(String author, String title, String publisher, String year, String keywords,String conf);

    /**
     * 得到发表论文最多的年份
     * @return Response对象
     */
    List<PaperPO> showMostPaperYear();
    /**
     * 得到发表论文最多的会议
     * @return Response对象
     */
    List<PaperPO> showMostPaperConf();
    /**
     * 根据研究关键字搜索论文
     * @param keywords 发布时间
     * @return  Response对象
     */
    List<PaperPO> searchPaperByKeywords(String keywords);

}
