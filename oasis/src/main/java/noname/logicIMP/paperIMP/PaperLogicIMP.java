package noname.logicIMP.paperIMP;

import noname.PO.PaperPO;
import noname.VO.PaperVO;
import noname.VO.Response;
import noname.data.paper.PaperData;
import noname.logic.paper.PaperLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 论文逻辑接口实现
 *
 * @author wch
 * @date 2020/3/6
 */
@Service
public class PaperLogicIMP implements PaperLogic {
    @Autowired
    private PaperData paperData;

    public void setPaperData(PaperData paperData) {
        this.paperData = paperData;
    }

    public PaperData getPaperData() {
        return paperData;
    }
    @Override
    public Response showMostPaperYear() {
        try {
            List<PaperVO> paperList = PaperPoList2PaperVOList(paperData.showMostPaperYear());
            return Response.buildSuccess(paperList);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }

    @Override
    public Response showMostPaperConf() {
        try {
            List<PaperVO> paperList = PaperPoList2PaperVOList(paperData.showMostPaperConf());
            return Response.buildSuccess(paperList);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }

    private List<PaperVO> PaperPoList2PaperVOList(List<PaperPO> paperPoList){
        List<PaperVO> paperVOList = new ArrayList<>();
        for(PaperPO paperPO : paperPoList){
            paperVOList.add(new PaperVO(paperPO));
        }
        return paperVOList;
    }
}
