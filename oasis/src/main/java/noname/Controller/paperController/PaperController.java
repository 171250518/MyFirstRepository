package noname.Controller.paperController;

import noname.VO.Response;
import noname.logic.paper.PaperLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaperController {
    @Autowired
    private PaperLogic paperLogic;

    public void setPaperLogic(PaperLogic paperLogic) {
        this.paperLogic = paperLogic;
    }

    @RequestMapping(value = "/paper/mostYear",method = RequestMethod.GET)
    public Response getMostPaperYear(){
        Response resp=paperLogic.showMostPaperYear();
        return resp;
    }
    @RequestMapping(value = "/paper/mostPublisher",method = RequestMethod.GET)
    public Response getMostPaperPublisher(){
        Response resp=paperLogic.showMostPaperConf();
        return resp;
    }
}
