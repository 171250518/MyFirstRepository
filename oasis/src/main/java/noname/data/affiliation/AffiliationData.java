package noname.data.affiliation;

import noname.PO.*;
import noname.VO.AuthorVO;

import java.util.List;


public interface AffiliationData {
    /**
     * 根据机构名获得机构信息
     * @param name 机构名
     * @return List<AffiliationPO>机构列表
     */
    List<AffiliationPO> getAffiliationByName(String name);

    /**
     * 根据机构id获得机构的作者信息
     * @param id 机构id
     * @return AffiliationPO机构对象列表
     */
    List<AffiliationPO> getAffiliationAuthorById(int id);

    /**
     * 根据机构id获得机构的论文信息
     * @param id 机构id
     * @return AffiliationPO机构对象列表
     */
    List<Affiliation_Paper_PO> getAffiliationPaperById(int id);

    /**
     * 根据机构id和年份获得机构的论文信息
     * @param id 机构id
     * @param year 年份
     * @return AffiliationPO机构对象列表
     */
    List<Affiliation_Paper_PO> getAffiliationPaperByIdAndYear(int id, String year);

    /**
     * 根据机构id和年份获得机构的论文数
     * @param id 机构id
     * @param year 年份
     * @return int 论文数
     */
    int getAffiliationPaperNumByIdAndYear(int id, String year);

    /**
     * 获得计算活跃度相关的信息
     * @return Affiliation_Paper_PO对象列表
     */
    List<AffiliationPO> getTopAffiliations();
//    /**
//     * 获得计算活跃度相关的信息
//     * @return Affiliation_Paper_PO对象列表
//     */
//    List<Affiliation_Paper_PO> getTopAffiliations();

    /**
     * 根据机构id获得论文id
     * @param id 机构id
     * @return PaperPO对象列表
     */
    List<PaperPO> getPaperIdByAffiliationId(int id);

    /**
     * 根据机构id和作者id获得论文id
     * @param id 机构id
     * @param auId 作者id
     * @return PaperPO对象列表
     */
    List<PaperPO> getPaperIdByAffiliationIdAndAuthorId(int id, int auId);

    /**
     * 根据机构id和发表年份获得论文id
     * @param id 机构id
     * @param year 作者id
     * @return PaperPO对象列表
     */
    List<PaperPO> getPaperIdByAffiliationIdAndYear(int id, String year);

    List<AuthorPO> getAuthorsOfAffById(int id);

    List<AuthorPO> getAuthorDirectionOfAffiliation(int id);

    List<ResearchDirectionPO> getAffiliationTopDirectionsById(int id);
}
