package noname.data.researchDirection;

import noname.PO.*;
import noname.VO.AuthorVO;

import java.util.List;

public interface ResearchDirectionData {
    List<ResearchDirectionPO> getResearchDirectionPOByName(String name);

    List<ResearchDirectionPO> getDirectionAuthorById(int id);

    List<PaperPO> getResearchDirectionPaperById(int id);

    List<ResearchDirectionPO> getTopReDirections();
//    List<ResearchDirection_Paper_PO> getTopReDirections();

    List<PaperPO> getPaperIdByReId(int id);

    List<PaperPO> getPaperIdByReIdAndPublishTime(int id, String year);

    List<PaperPO> getPaperIdByReIdAndAuthorId(int id, int auid);

    int getRePaperNumByIdAndYear(int id, String year);

    List<ResearchDirection_Paper_PO> getRePaperByIdAndYear(int id, String year);

    List<AuthorPO> getTopReAuthors(int id);
//    List<Author_Paper_PO> getTopReAuthors(int id);

    List<AuthorVO> getReAuthorsById(int id);

    List<AffiliationPO> getResearchDirectionTopAffiliation(int id);
}
