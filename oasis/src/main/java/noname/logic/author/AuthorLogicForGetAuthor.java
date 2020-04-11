package noname.logic.author;

import noname.PO.AuthorPO;
import noname.PO.Author_Paper_PO;
import noname.VO.AuthorVO;

import java.util.List;

public interface AuthorLogicForGetAuthor {
    //为其他模块提供接口，减少耦合
    AuthorVO getAuthorById(int id);

//    List<AuthorVO> getTopAuthors(List<Author_Paper_PO> list);
}
