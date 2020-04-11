package noname.data.author;

import noname.PO.*;
import noname.VO.AuthorVO;
import noname.VO.PaperVO;

import java.util.List;

public interface AuthorData {

    List<AuthorPO> getAuthorPOByName(String name);

    List<AuthorPO> getAuthorAffiliationById(int author_id);

    List<AuthorPO> getAuthorDirectionById(int author_id);

//    List<Author_Paper_PO> getTopAuthors();
    List<AuthorPO> getTopAuthors();

    List<PaperPO> getPaperIdByAuthorId(int author_id);

    List<PaperPO> getPaperIdByAuthorIdAndPublishTime(int author_id, String year);

    List<PaperPO> getPaperIdByAuthorIdAndConference(int author_id, String conf);

    List<AuthorPO> getPaperAuthorById(int id);

    List<ResearchDirectionPO> getPaperDirectionById(int id);

    List<AffiliationPO> getPaperAffiliationById(int id);

    List<PaperPO> getPaperIdByAuthorIdAndCoAuthor(int id, int coid);

    List<AuthorVO> getCoAuthorIdById(int id);

    int getAuthorPaperNumByIdAndYear(int id, String year);

    List<Author_Paper_PO> getAuthorPaperByIdAndYear(int id, String year);

    List<PaperPO> getPaperBasicById(int id);

    List<PaperPO> getPaperIdByAuthorIdOrderByCitation(int id);
}
