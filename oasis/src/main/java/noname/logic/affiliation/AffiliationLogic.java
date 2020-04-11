package noname.logic.affiliation;

import noname.VO.AffiliationVO;
import noname.VO.AuthorVO;
import noname.VO.PaperVO;
import noname.VO.ResearchDirectionVO;

import java.util.List;

public interface AffiliationLogic {
    /**
     * 根据机构名获得机构信息
     *
     * @param name 机构名
     * @return List<AffiliationVO>机构列表
     */
    List<AffiliationVO> getAffiliationByName(String name);


    /**
     * 创建机构论文数关于年份的统计
     *
     * @param id 机构id
     * @return List<Integer>论文数统计表
     */
    List<Integer> getPaperNumOfAffByYear(int id);


    /**
     * 创建机构引用数关于年份的统计
     *
     * @param id 机构id
     * @return List<Integer>被引用数统计表
     */
    List<Integer> getCitationNumOfAffByYear(int id);


    /**
     * 展示机构活跃度
     */
    List<AffiliationVO> getTopAffiliations();


    /**
     * 根据机构id和学者名搜索论文
     *
     * @param id   机构id
     * @param auId 学者id
     * @return List<PaperVO>论文列表
     */
    List<PaperVO> getPapersOfAffByAuthor(int id, int auId);


    /**
     * 根据机构id和年份搜索论文
     *
     * @param id   机构id
     * @param year 年份
     * @return List<PaperVO>论文列表
     */
    List<PaperVO> getPapersOfAffByYear(int id, String year);


    /**
     * 根据机构id搜索全部论文
     *
     * @param id 机构id
     * @return List<PaperVO>论文列表
     */
    List<PaperVO> getAllPapersOfAff(int id);


    /**
     * 根据机构id搜索全部论文
     *
     * @param id 机构id
     * @return List<PaperVO>论文列表
     */
    List<AuthorVO> getAuthorsOfAff(int id);
    /**
     * 根据机构id获得机构信息
     *
     * @param id 机构id
     * @return List<AffiliationVO>机构列表
     */
    AffiliationVO getAffiliationById(int id);
    /**
     * 根据机构id获得机构研究热点
     *
     * @param id 机构id
     * @return List<AffiliationVO>机构列表
     */
    List<ResearchDirectionVO> getAffiliationTopResearchDirectionById(int id);
}


