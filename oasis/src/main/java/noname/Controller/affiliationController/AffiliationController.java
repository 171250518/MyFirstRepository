package noname.Controller.affiliationController;


import noname.VO.*;
import noname.logic.affiliation.AffiliationLogic;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AffiliationController {
    private final AffiliationLogic affiliationLogic;

    public AffiliationController(AffiliationLogic affiliationLogic) {
        this.affiliationLogic = affiliationLogic;
    }
    @RequestMapping(value = "/index/getAffiliationIdByName",method = RequestMethod.GET)
    public Response getAffiliationByName(@RequestParam String affName) {
        try {
            List<AffiliationVO> affs = affiliationLogic.getAffiliationByName(affName);
            return Response.buildSuccess(affs);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }

    @RequestMapping(value = "/affiliation/detail",method = RequestMethod.GET)
    public Response getAffiliationById(@RequestParam int affId) {
        try {
            AffiliationVO author = affiliationLogic.getAffiliationById(affId);
            return Response.buildSuccess(author);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }

    @RequestMapping(value = "/affiliation/searchTopRes",method = RequestMethod.GET)
    public Response getReTopAuthor(@RequestParam int affId){
        try {
            List<ResearchDirectionVO> authors = affiliationLogic.getAffiliationTopResearchDirectionById(affId);
            return Response.buildSuccess(authors);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/affiliation/paperNumChart",method = RequestMethod.GET)
    public Response getPaperNumOfAffByYear(@RequestParam int affId) {
        try {
            List<Integer> papers = affiliationLogic.getPaperNumOfAffByYear(affId);
            return Response.buildSuccess(papers);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/affiliation/citationNumChart",method = RequestMethod.GET)
    public Response getCitationNumOfAffByYear(@RequestParam int affId) {
        try {
            List<Integer> papers = affiliationLogic.getCitationNumOfAffByYear(affId);
            return Response.buildSuccess(papers);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/index/getTopAffiliations",method = RequestMethod.GET)
    public Response getTopAffiliations() {
        try {
            List<AffiliationVO> affs = affiliationLogic.getTopAffiliations();
            return Response.buildSuccess(affs);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/getPapersOfAffiliationByAuthor/{affId}/{auId}", method = RequestMethod.GET)
    public Response getPapersOfAffByAuthor(@PathVariable int affId, @PathVariable  int auId) {
        try {
            List<PaperVO> papers = affiliationLogic.getPapersOfAffByAuthor(affId, auId);
            return Response.buildSuccess(papers);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/getPapersOfAffiliationByYear/{affId}/{year}", method = RequestMethod.GET)
    public Response getPapersOfAffByYear(@PathVariable int affId, @PathVariable String year) {
        try {
            List<PaperVO> papers = affiliationLogic.getPapersOfAffByYear(affId, year);
            return Response.buildSuccess(papers);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/paper/searchAffiliationArticle",method = RequestMethod.GET)
    public Response getAllPapersOfAff(@RequestParam int affId) {
        try {
            List<PaperVO> papers = affiliationLogic.getAllPapersOfAff(affId);
            return Response.buildSuccess(papers);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/affiliation/getAuthorById",method = RequestMethod.GET)
    public Response getAuthorsById(@RequestParam int affId) {
        try {
            List<AuthorVO> authors = affiliationLogic.getAuthorsOfAff(affId);
            return Response.buildSuccess(authors);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }

}
