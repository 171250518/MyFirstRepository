package noname.logic.paper;

import noname.PO.AuthorPO;
import noname.PO.Author_Paper_PO;
import noname.VO.PaperVO;

import java.util.List;

public interface PaperLogicForGetPaper {
    //提供给其他模块的接口
    PaperVO getPaperById(int id);

}
