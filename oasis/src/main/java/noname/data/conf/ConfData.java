package noname.data.conf;

import noname.PO.*;
import noname.VO.PaperVO;

import java.util.List;

public interface ConfData {

    List<ConferencePO> getConferenceByName(String name);

    ConferencePO getConferenceById(int id);

    List<PaperPO> getPaperIdByConferenceId(int id);

    List<PaperPO> getPaperIdByConferenceIdAndYear(int id, String year);

    Integer getConferencePaperNumByIdAndYear(int id, String year);

    List<Conference_Paper_PO> getConferencePaperByIdAndYear(int id, String year);

    List<ConferencePO> getTopConferences();
//    List<Conference_Paper_PO> getTopConferences();

    List<AuthorPO> getAuthorsOfConfById(int id);

    List<AuthorPO> getTopAuthorsOfConfById(int id);
//    List<Author_Paper_PO> getTopAuthorsOfConfById(int id);

    List<PaperPO> getPaperIdByConferenceIdAndAuthorId(int id, int auid);
}
