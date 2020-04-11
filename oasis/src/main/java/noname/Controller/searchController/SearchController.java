package noname.Controller.searchController;

import noname.VO.Response;
import noname.logic.search.SearchLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
    @Autowired
    private SearchLogic searchLogic;

    public void setSearchLogic(SearchLogic searchLogic) {
        this.searchLogic = searchLogic;
    }

    @RequestMapping(value = "/paper/searchAuthor",method = RequestMethod.GET)
    public Response getPaperByAuthor(@RequestParam String author){
        Response resp=searchLogic.searchPaperByAuthor(author);
        return resp;
    }

    @RequestMapping(value = "/paper/searchTitle",method = RequestMethod.GET)
    public Response getPaperByTitle(@RequestParam String title){
        Response resp=searchLogic.searchPaperByTitle(title);
        return resp;
    }

    @RequestMapping(value = "/paper/searchAffiliation",method = RequestMethod.GET)
    public Response getPaperByPublisher(@RequestParam String affiliation){
        Response resp=searchLogic.searchPaperByAffiliation(affiliation);
        return resp;
    }
    @RequestMapping(value = "/paper/searchYear",method = RequestMethod.GET)
    public Response getPaperByYear(@RequestParam String year){
        Response resp=searchLogic.searchPaperByYear(year);
        return resp;
    }
    @RequestMapping(value = "/paper/searchKeywords",method = RequestMethod.GET)
    public Response getPaperByKeywords(@RequestParam String keywords){
        Response resp=searchLogic.searchPaperByKeywords(keywords);
        return resp;
    }  @RequestMapping(value = "/paper/searchConf",method = RequestMethod.GET)
    public Response getPaperByConf(@RequestParam String conf){
        Response resp=searchLogic.searchPaperByConf(conf);
        return resp;
    }
    //根据组合关键字搜索
    @RequestMapping(value = "/paper/searchCombination",method = RequestMethod.GET)
    public Response getPaperByCombination(@RequestParam String comWords){
        Response resp=searchLogic.searchPaperByCombination(comWords);
        return resp;
    }



}
