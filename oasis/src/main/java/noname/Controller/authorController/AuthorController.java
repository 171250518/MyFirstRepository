package noname.Controller.authorController;


import noname.PO.AuthorPO;
import noname.VO.AuthorVO;
import noname.VO.PaperVO;
import noname.VO.Response;
import noname.logic.author.AuthorLogic;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    private final AuthorLogic authorLogic;

    public AuthorController(AuthorLogic authorLogic) {
        this.authorLogic = authorLogic;
    }
    @RequestMapping(value = "/index/getAuthorIdByName",method = RequestMethod.GET)
    public Response getAuthorByName(@RequestParam String auName) {
        try {
            List<AuthorVO> authors = authorLogic.getAuthorByName(auName);
            return Response.buildSuccess(authors);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/author/getCoAuthorById",method = RequestMethod.GET)
    public Response getCoWorkerById(@RequestParam int auId) {
        try {
            List<AuthorVO> authors = authorLogic.getCoAuthorById(auId);
            return Response.buildSuccess(authors);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/author/detail",method = RequestMethod.GET)
    public Response getAuthorById(@RequestParam int auId) {
        try {
           AuthorVO author = authorLogic.getAuthorById(auId);
            return Response.buildSuccess(author);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/index/getTopAuthors",method = RequestMethod.GET)
    public Response getTopAuthors() {
        try {
            List<AuthorVO> authors = authorLogic.getTopAuthors();
            return Response.buildSuccess(authors);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/author/paperNumChart",method = RequestMethod.GET)
    public Response getPaperNumOfAffByYear(@RequestParam int auId) {
        try {
            List<Integer> papers = authorLogic.getPaperNumOfAuByYear(auId);
            return Response.buildSuccess(papers);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/author/citationNumChart",method = RequestMethod.GET)
    public Response getCitationNumOfAffByYear(@RequestParam int auId) {
        try {
            List<Integer> papers = authorLogic.getCitationNumOfAuByYear(auId);
            return Response.buildSuccess(papers);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/Author/searchPapersById",method = RequestMethod.GET)
    public Response getAllPapersOfAuthor(@RequestParam int auId) {
        try {
            List<PaperVO> papers = authorLogic.getAllPapersOfAuthor(auId);
            return Response.buildSuccess(papers);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/Author/searchPapersByCitation",method = RequestMethod.GET)
    public Response getAllPapersOfAuthorOrderByCitation(@RequestParam int auId) {
        try {
            List<PaperVO> papers = authorLogic.getAllPapersOfAuthorOrderByCitation(auId);
            return Response.buildSuccess(papers);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/getPapersOfAuthorByYear/{auId}/{year}", method = RequestMethod.GET)
    public Response getPapersOfAuthorByYear(@PathVariable int auId, @PathVariable String year) {
        try {
            List<PaperVO> papers = authorLogic.getPapersOfAuthorByYear(auId, year);
            return Response.buildSuccess(papers);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/getPapersOfAuthorByConf/{auId}/{conf}", method = RequestMethod.GET)
    public Response getPapersOfAuthorByConf(@PathVariable int auId, @PathVariable String conf) {
        try {
            conf=conf.replace('_',' ');
            List<PaperVO> papers = authorLogic.getPapersOfAuthorByConf(auId, conf);
            return Response.buildSuccess(papers);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/getPapersOfAuthorByCoWorker/{auId}/{coId}", method = RequestMethod.GET)
    public Response getPapersOfAuthorByCoWorker(@PathVariable int auId, @PathVariable int coId) {
        try {
            List<PaperVO> papers = authorLogic.getPapersOfAuthorByCoWorker(auId, coId);
            return Response.buildSuccess(papers);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }


}
