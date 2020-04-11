package noname.Controller.researchDirectionController;


import noname.PO.AuthorPO;
import noname.VO.*;
import noname.logic.researchDirection.ResearchDirectionLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResearchDirectionController {
    private final ResearchDirectionLogic researchDirectionLogic;

    public ResearchDirectionController(ResearchDirectionLogic researchDirectionLogic) {
        this.researchDirectionLogic = researchDirectionLogic;
    }
    @RequestMapping(value = "/index/getReDirectionIdByName",method = RequestMethod.GET)
    public Response getReDirectionByName(@RequestParam String reName) {
        try {
            List<ResearchDirectionVO> res = researchDirectionLogic.getReDirectionByName(reName);
            return Response.buildSuccess(res);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }

    @RequestMapping(value = "/researchDirection/detail",method = RequestMethod.GET)
    public Response getResearchDirectionById(@RequestParam int reId) {
        try {
            ResearchDirectionVO researchDirection = researchDirectionLogic.getResearchDirectionById(reId);
            return Response.buildSuccess(researchDirection);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/index/getTopReDirections",method = RequestMethod.GET)
    public Response getTopReDirections() {
        try {
            List<ResearchDirectionVO> res = researchDirectionLogic.getTopReDirections();
            return Response.buildSuccess(res);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/reDirection/searchAuthors",method = RequestMethod.GET)
    public Response getAuthorOfConf(@RequestParam int reId) {
        try {
            List<AuthorVO> authorVOS = researchDirectionLogic.getReAuthor(reId);
            return Response.buildSuccess(authorVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }


    @RequestMapping(value = "/getPapersOfReDirectionByAuthor/{reId}/{auId}", method = RequestMethod.GET)
    public Response getPapersOfAuthorByCoWorker(@PathVariable int reId, @PathVariable int auId) {
        try {
            List<PaperVO> papers = researchDirectionLogic.getPapersOfReByAuthor(reId, auId);
            return Response.buildSuccess(papers);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }

    @RequestMapping(value = "/paper/searchReDirection",method = RequestMethod.GET)
    public Response getAllPapersOfRe(@RequestParam int reId){
        try {
            List<PaperVO> papers = researchDirectionLogic.getAllPapersOfRe(reId);
            return Response.buildSuccess(papers);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }

    @RequestMapping(value = "/getPapersOfReDirectionByYear/{reId}/{year}", method = RequestMethod.GET)
    public Response getPapersOfReByYear(@PathVariable int reId, @PathVariable String year) {
        try {
            List<PaperVO> papers = researchDirectionLogic.getPapersOfReByYear(reId, year);
            return Response.buildSuccess(papers);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }

    public Response getPapersOfReByAuthor(int id, int auid){
        try {
            List<PaperVO> papers = researchDirectionLogic.getPapersOfReByAuthor(id, auid);
            return Response.buildSuccess(papers);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }

    @RequestMapping(value = "/reDirection/paperNumChart",method = RequestMethod.GET)
    public Response getPaperNumOfReByYear(@RequestParam int reId){
        try {
            List<Integer> papers = researchDirectionLogic.getPaperNumOfReByYear(reId);
            return Response.buildSuccess(papers);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }

    @RequestMapping(value = "/reDirection/citationNumChart",method = RequestMethod.GET)
    public Response getCitationNumOfReByYear(@RequestParam int reId){
        try {
            List<Integer> papers = researchDirectionLogic.getCitationNumOfReByYear(reId);
            return Response.buildSuccess(papers);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    @RequestMapping(value = "/reDirection/searchTopAuthors",method = RequestMethod.GET)
    public Response getReTopAuthor(@RequestParam int reId){
        try {
            List<AuthorVO> authors = researchDirectionLogic.getReTopAuthor(reId);
            return Response.buildSuccess(authors);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }


    @RequestMapping(value = "/reDirection/searchTopAffiliations",method = RequestMethod.GET)
    public Response getReTopAffiliation(@RequestParam int reId){
        try {
            List<AffiliationVO> authors = researchDirectionLogic.getResearchDirectionTopAffiliationById(reId);
            return Response.buildSuccess(authors);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }



}
