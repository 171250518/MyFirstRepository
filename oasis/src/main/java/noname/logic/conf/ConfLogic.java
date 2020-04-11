package noname.logic.conf;

import noname.VO.AuthorVO;
import noname.VO.ConferenceVO;
import noname.VO.PaperVO;

import java.util.List;

public interface ConfLogic {
    /**
     * 根据会议名得到会议信息
     *
     * @param name 会议名
     * @return ConferenceVO列表
     */
    List<ConferenceVO> getConfByName(String name);


    /**
     * 展示活跃度
     */
    List<ConferenceVO> getTopConfs();


    /**
     * 根据会议名搜索全部论文
     *
     * @param id 会议名
     * @return PaperVO对象列表
     */
    List<PaperVO> getAllPapersOfConf(int id);


    /**
     * 根据会议id和年份搜索论文
     *
     * @param id 会议名；year 年份
     * @return PaperVO对象列表
     */
    List<PaperVO> getPapersOfConfByYear(int id, String year);


    /**
     * 根据会议id创建论文数关于年份的统计
     *
     * @param id 会议id
     * @return int列表
     */
    List<Integer> getPaperNumOfConfByYear(int id);


    /**
     * 根据会议id创建引用数关于年份的统计
     *
     * @param id 会议id
     * @return int列表
     */
    List<Integer> getCitationNumOfConfByYear(int id);

    /**
     * 根据会议id搜索学者信息
     *
     * @param id 会议id
     * @return AuthorVO对象列表
     */
    List<AuthorVO> getAuthorOfConf(int id);

    /**
     * 根据会议id搜索会议活跃学者信息
     *
     * @param id 会议id
     * @return AuthorVO对象列表
     */
    List<AuthorVO> getTopAuthorOfConf(int id);


    /**
     * 根据会议id搜索会议信息
     *
     * @param id 会议id
     * @return ConferenceVO对象
     */
    ConferenceVO getConferenceById(int id);

    List<PaperVO> getPapersOfConfByAuthor(int confId, int auId);
}
