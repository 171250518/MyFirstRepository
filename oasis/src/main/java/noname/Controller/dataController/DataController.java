package noname.Controller.dataController;

import noname.VO.Response;
import noname.logic.data.Datalogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
    @Autowired
    private Datalogic datalogic;

    public void setDatalogic(Datalogic datalogic) {
        this.datalogic = datalogic;
    }

    @RequestMapping(value="/data/collect",method = RequestMethod.GET)
    public Response dataCollect(@RequestParam String urls){
        return datalogic.insertData(urls);
    }
}
