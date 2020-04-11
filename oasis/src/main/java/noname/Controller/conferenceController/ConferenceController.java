package noname.Controller.conferenceController;

import noname.VO.ConferenceVO;
import noname.VO.AuthorVO;
import noname.VO.PaperVO;
import noname.VO.Response;
import noname.logic.conf.ConfLogic;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConferenceController {
    private final ConfLogic confLogic;


    public ConferenceController(ConfLogic confLogic) {
        this.confLogic = confLogic;
    }

    @RequestMapping(value = "/index/getConfIdByName",method = RequestMethod.GET)
    public Response getConfByName(@RequestParam String confName) {
        try {
            List<ConferenceVO> affs = confLogic.getConfByName(confName);
            return Response.buildSuccess(affs);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }

    @RequestMapping(value = "/index/getTopConfs",method = RequestMethod.GET)
    public Response getTopConfs() {
        try {
            List<ConferenceVO> affs = confLogic.getTopConfs();
            return Response.buildSuccess(affs);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }


    @RequestMapping(value = "/conference/detail",method = RequestMethod.GET)
    public Response getConferenceById(@RequestParam int confId){
        try {
            ConferenceVO conferenceVO = confLogic.getConferenceById(confId);
            return Response.buildSuccess(conferenceVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }

    @RequestMapping(value = "/paper/searchConference",method = RequestMethod.GET)
    public Response getAllPapersOfConf(@RequestParam int confId) {
        try {
            List<PaperVO> paperVOS = confLogic.getAllPapersOfConf(confId);
            return Response.buildSuccess(paperVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }

    @RequestMapping(value = "/conference/searchTopAuthors",method = RequestMethod.GET)
    public Response getTopAuthorOfConf(@RequestParam int confId) {
        try {
            List<AuthorVO> authorVOS = confLogic.getTopAuthorOfConf(confId);
            return Response.buildSuccess(authorVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }


    @RequestMapping(value = "/getPapersOfConfByAuthor/{confId}/{auId}", method = RequestMethod.GET)
    public Response getPapersOfAuthorByCoWorker(@PathVariable int confId, @PathVariable int auId) {
        try {
            List<PaperVO> papers = confLogic.getPapersOfConfByAuthor(confId, auId);
            return Response.buildSuccess(papers);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/conference/searchAuthors",method = RequestMethod.GET)
    public Response getAuthorOfConf(@RequestParam int confId) {
        try {
            List<AuthorVO> authorVOS = confLogic.getAuthorOfConf(confId);
            return Response.buildSuccess(authorVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }


    public Response getPapersOfConfByYear(int id, String year) {
        try {
            List<PaperVO> paperVOS = confLogic.getPapersOfConfByYear(id, year);
            return Response.buildSuccess(paperVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }

    public Response getPaperNumOfConfByYear(int id) {
        try {
            List<Integer> paperVOS = confLogic.getPaperNumOfConfByYear(id);
            return Response.buildSuccess(paperVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }

    public Response getCitationNumOfConfByYear(int id) {
        try {
            List<Integer> paperVOS = confLogic.getCitationNumOfConfByYear(id);
            return Response.buildSuccess(paperVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
}
