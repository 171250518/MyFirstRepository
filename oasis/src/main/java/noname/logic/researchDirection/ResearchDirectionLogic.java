package noname.logic.researchDirection;

import noname.VO.AffiliationVO;
import noname.VO.AuthorVO;
import noname.VO.PaperVO;
import noname.VO.ResearchDirectionVO;

import java.util.List;

public interface ResearchDirectionLogic {
    /**
     * 根据研究方向名得到研究方向信息（有多少论文，学者，被引用）
     * @param name 研究方向名
     * @return ResearchDirectionVO对象列表
     */
    List<ResearchDirectionVO> getReDirectionByName(String name);


    /**
     * 展示活跃度
     */
    List<ResearchDirectionVO> getTopReDirections();



    /**
     * 根据研究方向id搜索全部论文
     * @param id 研究方向id
     * @return PaperVO对象列表
     */
    List<PaperVO> getAllPapersOfRe(int id);



    /**
     * 根据研究方向id和年份搜索论文
     * @param id 研究方向id
     * @param year 年份
     * @return PaperVO对象列表
     */
    List<PaperVO> getPapersOfReByYear(int id,String year);


    /**
     * 根据研究方向id和学者搜索论文
     * @param id 研究方向id
     * @param auid 学者id
     * @return PaperVO对象列表
     */
    List<PaperVO> getPapersOfReByAuthor(int id, int auid);

    /**
     * 根据研究方向id创建论文数关于年份的统计
     * @param id 研究方向id
     * @return int 列表
     */
    List<Integer> getPaperNumOfReByYear(int id);


    /**
     * 根据研究方向id创建引用数关于年份的统计
     * @param id 研究方向id
     * @return int 列表
     */
    List<Integer> getCitationNumOfReByYear(int id);

    /**
     * 根据研究方向id获得最活跃的作者
     * @param id 研究方向id
     * @return AuthorVO对象列表
     */
    List<AuthorVO> getReTopAuthor(int id);

    /**
     * 根据研究方向id获得所有作者
     * @param id 研究方向id
     * @return AuthorVO对象列表
     */
    List<AuthorVO> getReAuthor(int id);

    /**
     * 根据研究方向id获得研究方向
     * @param id 研究方向id
     * @return ResearchDirectionVO对象
     */
    ResearchDirectionVO getResearchDirectionById(int id);

    /**
     * 根据研究方向id获得活跃机构
     * @param id 研究方向id
     * @return AffiliationVO对象列表
     */
    List<AffiliationVO> getResearchDirectionTopAffiliationById(int id);

}
