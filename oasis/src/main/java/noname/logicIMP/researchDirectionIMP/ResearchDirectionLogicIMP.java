package noname.logicIMP.researchDirectionIMP;


import noname.PO.*;
import noname.VO.AffiliationVO;
import noname.VO.AuthorVO;
import noname.VO.PaperVO;
import noname.VO.ResearchDirectionVO;
import noname.data.researchDirection.ResearchDirectionData;
import noname.logic.author.AuthorLogicForGetAuthor;
import noname.logic.paper.PaperLogicForGetPaper;
import noname.logic.researchDirection.ResearchDirectionLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ResearchDirectionLogicIMP implements ResearchDirectionLogic {

    @Autowired
    private ResearchDirectionData researchDirectionData;

    @Autowired
    private PaperLogicForGetPaper paperLogicForGetPaper;

    @Autowired
    private AuthorLogicForGetAuthor authorLogicForGetAuthor;

    public void setResearchDirectionData(ResearchDirectionData researchDirectionData) {
        this.researchDirectionData = researchDirectionData;
    }

    public void setPaperLogicForGetPaper(PaperLogicForGetPaper paperLogicForGetPaper) {
        this.paperLogicForGetPaper = paperLogicForGetPaper;
    }

    public void setAuthorLogicForGetAuthor(AuthorLogicForGetAuthor authorLogicForGetAuthor) {
        this.authorLogicForGetAuthor = authorLogicForGetAuthor;
    }

    //根据名字找到研究方向信息
    @Override
    public List<ResearchDirectionVO> getReDirectionByName(String name) {
        //先找id，再按id找到信息
        List<ResearchDirectionPO> researchDirectionPOS = researchDirectionData.getResearchDirectionPOByName(name);
        List<ResearchDirectionVO> researchDirectionVOS = new ArrayList<>();
        for (ResearchDirectionPO researchDirectionPO : researchDirectionPOS) {
            researchDirectionVOS.add(new ResearchDirectionVO(researchDirectionPO.getId(),researchDirectionPO.getName()));
        }
        return researchDirectionVOS;
    }

    //获得活跃研究方向
    @Override
    public List<ResearchDirectionVO> getTopReDirections() {
//        //先找id
//        List<ResearchDirection_Paper_PO> directions = researchDirectionData.getTopReDirections();
//        //再计算活跃度、排序
//        return getTopReDirections(directions);
        List<ResearchDirectionPO> researchDirectionPOS = researchDirectionData.getTopReDirections();
        List<ResearchDirectionVO> researchDirectionVOS = new ArrayList<>();
        for(int i = 0; i < researchDirectionPOS.size()&&i<10;++i){
            ResearchDirectionVO researchDirectionVO = new ResearchDirectionVO();
            researchDirectionVO.setId(researchDirectionPOS.get(i).getId());
            researchDirectionVO.setName(researchDirectionPOS.get(i).getName());
            researchDirectionVO.setRank(researchDirectionPOS.get(i).getRank());
            researchDirectionVOS.add(researchDirectionVO);
        }
        return researchDirectionVOS;
    }

    //获得研究方向上的所有论文
    @Override
    public List<PaperVO> getAllPapersOfRe(int id) {
        //先找id，再找信息
        List<PaperPO> paperId = researchDirectionData.getPaperIdByReId(id);
        List<PaperVO> res = new ArrayList<>();
        for (PaperPO paper : paperId) res.add(paperLogicForGetPaper.getPaperById(paper.getId()));
        return res;
    }

    //找到研究方向某年的论文信息
    @Override
    public List<PaperVO> getPapersOfReByYear(int id, String year) {
        List<PaperPO> paperId = researchDirectionData.getPaperIdByReIdAndPublishTime(id, year);
        List<PaperVO> res = new ArrayList<>();
        for (PaperPO paper : paperId) res.add(paperLogicForGetPaper.getPaperById(paper.getId()));
        return res;
    }

    //找到研究方向上某个作者的所有论文信息
    @Override
    public List<PaperVO> getPapersOfReByAuthor(int id, int auid) {
        List<PaperVO> paperVOS = new ArrayList<>();
        List<PaperPO> paperPOS = researchDirectionData.getPaperIdByReIdAndAuthorId(id, auid);
        for (PaperPO paper : paperPOS) paperVOS.add(paperLogicForGetPaper.getPaperById(paper.getId()));
        return paperVOS;
    }

    //按年找到研究方向的论文数，返回从2011年到2020年的论文数
    @Override
    public List<Integer> getPaperNumOfReByYear(int id) {
        List<Integer> paperNums = new ArrayList<>();
        for (int i = 2011; i <= 2020; i++) {
            paperNums.add(researchDirectionData.getRePaperNumByIdAndYear(id, String.valueOf(i)));
        }
        return paperNums;
    }

    //按年找到研究方向的论文被引用数，返回从2011年到2020年的论文被引用数
    @Override
    public List<Integer> getCitationNumOfReByYear(int id) {
        List<Integer> citationNums = new ArrayList<>();
        for (int i = 2011; i <= 2020; i++) {
            List<ResearchDirection_Paper_PO> papers = researchDirectionData.getRePaperByIdAndYear(id, String.valueOf(i));
            int sum = 0;
            for (ResearchDirection_Paper_PO paper : papers) sum += paper.getC_count();
            citationNums.add(sum);
        }
        return citationNums;
    }

    //找到某个方向上的最活跃作者
    @Override
    public List<AuthorVO> getReTopAuthor(int id) {
        List<AuthorPO> topAuthors = researchDirectionData.getTopReAuthors(id);
        List<AuthorVO> authors = new ArrayList<>();
        for(int i = 0; i < topAuthors.size()&&i<10;++i){
            AuthorVO authorVO = new AuthorVO();
            authorVO.setId(topAuthors.get(i).getId());
            authorVO.setName(topAuthors.get(i).getName());
            authorVO.setRank(topAuthors.get(i).getRank());
            authors.add(authorVO);
        }
        return authors;
    }
//    @Override
//    public List<AuthorVO> getReTopAuthor(int id) {
//        List<Author_Paper_PO> authors = researchDirectionData.getTopReAuthors(id);
//        return authorLogicForGetAuthor.getTopAuthors(authors);
//    }

    //找到某个方向上的所有作者
    @Override
    public List<AuthorVO> getReAuthor(int id) {
        return researchDirectionData.getReAuthorsById(id);
    }

    //根据id找到研究方向的信息
    public ResearchDirectionVO getResearchDirectionById(int id) {
        List<ResearchDirectionPO> dir_authors = researchDirectionData.getDirectionAuthorById(id);
        List<PaperPO> dir_papers = researchDirectionData.getResearchDirectionPaperById(id);
        StringBuilder authors = new StringBuilder();
        int c_count = 0;
        for (ResearchDirectionPO dir_author : dir_authors){
            authors.append(dir_author.getAuthors()).append(" ");
            if(authors.length()>300){
                authors.append("......");
                break;
            }
        }
        for (PaperPO paper : dir_papers){
            c_count += paper.getCitationCount();
        }
        return new ResearchDirectionVO(id, dir_authors.get(0).getName(), dir_papers.size(), c_count, authors.toString(), dir_authors.get(0).getRank());
    }

    @Override
    public List<AffiliationVO> getResearchDirectionTopAffiliationById(int id) {
        List<AffiliationPO> affiliationPOS = researchDirectionData.getResearchDirectionTopAffiliation(id);
        List<AffiliationVO> affiliationVOS = new ArrayList<>();
        AffiliationVO affiliationVO;
        for(int i =0;i < affiliationPOS.size()&&i<10;++i){
            if(affiliationPOS.get(i).getName().equals("NA")){
                continue;
            }
            affiliationVO = new AffiliationVO();
            affiliationVO.setId(affiliationPOS.get(i).getId());
            affiliationVO.setPaper_number(affiliationPOS.get(i).getPaper_number());
            affiliationVO.setName(affiliationPOS.get(i).getName());

            affiliationVOS.add(affiliationVO);
        }
        return affiliationVOS;
    }

    //计算研究方向活跃度、排序
    private List<ResearchDirectionVO> getTopReDirections(List<ResearchDirection_Paper_PO> list) {
        //这里说明下活跃度计算方法
        //rank = E papers.c_count * (2020 - year) / 10;
        ArrayList<ResearchDirectionVO> res = new ArrayList<>();
        int list_p = 0, res_p = -1;
        while (list_p < list.size()) {
            ResearchDirectionVO researchDirectionVO = new ResearchDirectionVO();
            int id = list.get(list_p).getResearchDirectionId();
            researchDirectionVO.setId(id);
            researchDirectionVO.setName(list.get(list_p).getName());
            researchDirectionVO.setPaper_number(0);
            researchDirectionVO.setRank(0);
            while (list_p < list.size() && list.get(list_p).getResearchDirectionId() == id) {
                researchDirectionVO.setRank(researchDirectionVO.getRank() + (double) list.get(list_p).getC_count() * (list.get(list_p).getYear() - 2010) / 10);
                researchDirectionVO.setPaper_number(researchDirectionVO.getPaper_number() + 1);
                list_p++;
            }
            if (res_p >= 9) {
                res.sort(new Comparator<ResearchDirectionVO>() {
                    @Override
                    public int compare(ResearchDirectionVO o1, ResearchDirectionVO o2) {
                        return Double.compare(o2.getRank(), o1.getRank());
                    }
                });
                if (researchDirectionVO.getRank() > res.get(9).getRank()) {
                    res.set(9, researchDirectionVO);
                }
            } else {
                res.add(researchDirectionVO);
                res_p++;
            }
        }
        //按活跃度排序
        res.sort(new Comparator<ResearchDirectionVO>() {
            @Override
            public int compare(ResearchDirectionVO o1, ResearchDirectionVO o2) {
                return Double.compare(o2.getRank(), o1.getRank());
            }
        });
        return res;
    }

}
