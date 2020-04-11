package noname.logic.data;

import noname.VO.Response;

import java.io.BufferedReader;
import java.util.List;

public interface Datalogic {
    List readCSV(BufferedReader reader);
    Response insertData(String filename);
    Response insertAuthor();
    Response insertAffiliation();
    Response insertAuthorAffiliation();
    Response insertPaper();
    Response insertDirection();
    Response insertConference();
    Response insertAuthorDirection();
    Response insertAuthorPaper();
    Response insertPaperAffiliation();
    Response insertPaperDirection();
}
