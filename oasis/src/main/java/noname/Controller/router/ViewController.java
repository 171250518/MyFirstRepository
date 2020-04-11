package noname.Controller.router;

import noname.VO.Response;
import noname.logic.data.Datalogic;
import noname.logic.paper.PaperLogic;
import noname.logic.search.SearchLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 界面跳转控制
 *
 * @author wch
 * @date 2020/3/3
 * 第二次修改
 * @author ffj
 * @date 2020/3/5
 */
//@RestController
@Controller
public class ViewController {

    @RequestMapping("/login")
    public String login(){
        return "/Account/login";
    }

    @RequestMapping("/signUp")
    public String signUp(){
        return "Account/signUp";
    }

    @RequestMapping("/index")
    public String index(){
        return "Index/index";
    }

    @RequestMapping("/paper")
    public String paper(){
        return "Paper/paper";
    }

    @RequestMapping("/searchResult")
    public String searchResult(){
        return "SearchResult/searchResult";
    }
}

