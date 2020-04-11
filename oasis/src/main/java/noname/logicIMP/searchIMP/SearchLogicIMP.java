package noname.logicIMP.searchIMP;


import noname.PO.PaperPO;
import noname.VO.PaperVO;
import noname.VO.Response;
import noname.data.paper.PaperData;
import noname.logic.search.SearchLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

/**
 * 搜索逻辑接口实现
 *
 * @author ffj
 * @date 2020/02/29
 */
@Service
public class SearchLogicIMP implements SearchLogic {


    @Autowired
    private PaperData paperData;


    public void setPaperData(PaperData paperData) {
        this.paperData = paperData;
    }

    public PaperData getPaperData() {
        return paperData;
    }

    /**
     * 根据作者搜索论文
     * @param author 论文作者
     * @return Response对象
     */
    @Override
    public Response searchPaperByAuthor(String author){
        try {
            List<PaperVO> paperList = PaperPoList2PaperVOList(paperData.searchPaperByAuthor(author));
            return Response.buildSuccess(paperList);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }

    }

    /**
     * 根据发布机构搜索论文
     * @param affiliation 论文发布机构
     * @return  Response对象
     */
    @Override
    public Response searchPaperByAffiliation(String affiliation){
        try {
            List<PaperVO> paperList = PaperPoList2PaperVOList(paperData.searchPaperByAffiliation(affiliation));
            return Response.buildSuccess(paperList);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }

    /**
     * 根据发布会议搜索论文
     * @param conf 论文发布会议
     * @return  Response对象
     */
    @Override
    public Response searchPaperByConf(String conf){
        try {
            List<PaperVO> paperList = PaperPoList2PaperVOList(paperData.searchPaperByConf(conf));
            return Response.buildSuccess(paperList);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }


    /**
     * 根据论文题目搜索论文
     * @param title 论文题目
     * @return  Response对象
     */
    @Override
    public Response searchPaperByTitle(String title){
        try {
            List<PaperVO> paperList = PaperPoList2PaperVOList(paperData.searchPaperByTitle(title));
            return Response.buildSuccess(paperList);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    /**
     * 根据发布时间搜索论文
     * @param year 发布时间
     * @return  Response对象
     */
    @Override
    public Response searchPaperByYear(String year) {
        try {
            List<PaperVO> paperList = PaperPoList2PaperVOList(paperData.searchPaperByYear(year));
            return Response.buildSuccess(paperList);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }
    /**
     * 根据组合关键字搜索论文
     * @param comWords 组合关键字
     * @return  Response对象
     */
    @Override
    public Response searchPaperByCombination(String comWords) {
        try {
            String author1=null;
            String title1=null;
            String publisher1=null;
            String year1 = null;
            String keywords1=null;
            String conf1=null;
            String str[]=comWords.split("&");
            author1=str[0];
            title1=str[1];
            publisher1 = str[2];
            year1=str[3];
            keywords1=str[4];
            conf1=str[5];
            List<PaperVO> paperList = PaperPoList2PaperVOList(paperData.searchPaperByCombination(author1,title1,publisher1,year1,keywords1,conf1));
            return Response.buildSuccess(paperList);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.buildFailure("失败");
        }
    }

    @Override
    public Response searchPaperByKeywords(String keywords) {
        try {
            List<PaperVO> paperList = PaperPoList2PaperVOList(paperData.searchPaperByKeywords(keywords));
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
