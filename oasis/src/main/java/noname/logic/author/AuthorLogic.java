package noname.logic.author;

import noname.VO.AuthorVO;
import noname.VO.PaperVO;

import java.util.List;

public interface AuthorLogic {
    /**
     * 根据姓名获得学者信息
     *
     * @param name 学者姓名
     * @return AuthorVO对象列表
     */
    List<AuthorVO> getAuthorByName(String name);

    /**
     * 根据id获得学者信息
     * @param id 学者id
     * @return Response对象
     */
    AuthorVO getAuthorById(int id);
    /**
     * 展示活跃度
     * @return
     */
    List<AuthorVO> getTopAuthors();



    /**
     *
     * 根据学者id搜索全部论文
     * @param id 学者id
     * @return PaperVO对象列表
     */
    List<PaperVO> getAllPapersOfAuthor(int id);

    /**
     *
     * 根据学者id搜索全部论文，按被引用次数排序
     * @param id 学者id
     * @return PaperVO对象列表
     */
    List<PaperVO> getAllPapersOfAuthorOrderByCitation(int id);



    /**
     * 根据学者姓名和年份搜索论文
     *
     * @param id   学者id
     * @param year 论文发表年份
     * @return PaperVO对象列表
     */
    List<PaperVO> getPapersOfAuthorByYear(int id, String year);



    /**
     * 根据学者id和会议搜索论文
     *
     * @param id   学者id
     * @param conf 会议
     * @return PaperVO对象列表
     */
    List<PaperVO> getPapersOfAuthorByConf(int id, String conf);


    List<Integer> getPaperNumOfAuByYear(int id);

    List<Integer> getCitationNumOfAuByYear(int id);

    /**
     * 根据学者姓名和共同作者搜索论文
     *
     * @param id   学者id
     * @param coId 共同作者的姓名
     * @return PaperVO对象列表
     */
    List<PaperVO> getPapersOfAuthorByCoWorker(int id, int coId);




    /**
     * 根据学者id找到合作学者信息
     *
     * @param id 学者id
     * @return AuthorVO对象列表
     */
    List<AuthorVO> getCoAuthorById(int id);

}
