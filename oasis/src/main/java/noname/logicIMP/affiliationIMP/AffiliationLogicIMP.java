package noname.logicIMP.affiliationIMP;

import noname.PO.*;
import noname.VO.AffiliationVO;
import noname.VO.AuthorVO;
import noname.VO.PaperVO;
import noname.VO.ResearchDirectionVO;
import noname.data.affiliation.AffiliationData;
import noname.logic.affiliation.AffiliationLogic;
import noname.logic.author.AuthorLogicForGetAuthor;
import noname.logic.paper.PaperLogicForGetPaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class AffiliationLogicIMP implements AffiliationLogic {

    @Autowired
    private AffiliationData affiliationData;

    @Autowired
    private PaperLogicForGetPaper paperLogicForGetPaper;

    @Autowired
    private AuthorLogicForGetAuthor authorLogicForGetAuthor;

    public void setAffiliationData(AffiliationData affiliationData) {
        this.affiliationData = affiliationData;
    }

    public void setPaperLogicForGetPaper(PaperLogicForGetPaper paperLogicForGetPaper) {
        this.paperLogicForGetPaper = paperLogicForGetPaper;
    }

    public void setAuthorLogicForGetAuthor(AuthorLogicForGetAuthor authorLogicForGetAuthor) {
        this.authorLogicForGetAuthor = authorLogicForGetAuthor;
    }

    @Override
    public List<AffiliationVO> getAffiliationByName(String name) {
        //先根据机构名获得所有符合的机构id
        List<AffiliationPO> affiliations = affiliationData.getAffiliationByName(name);
        List<AffiliationVO> affiliationVOs = new ArrayList<>();
        for (AffiliationPO affiliationPO : affiliations) {
            //对每一个机构id，获取它的信息
            affiliationVOs.add(new AffiliationVO(affiliationPO.getId(),affiliationPO.getName()));
        }
        return affiliationVOs;
    }

    //结果是一个数组，大小为10，代表了从2011年到2020年的论文数
    @Override
    public List<Integer> getPaperNumOfAffByYear(int id) {
        List<Integer> paperNums = new ArrayList<>();
        for (int i = 2011; i <= 2020; i++) {
            //按年搜索论文数
            paperNums.add(affiliationData.getAffiliationPaperNumByIdAndYear(id, String.valueOf(i)));
        }
        return paperNums;
    }

    //结果是一个数组，大小为10，代表了从2011年到2020年的论文被引用次数
    @Override
    public List<Integer> getCitationNumOfAffByYear(int id) {
        List<Integer> citationNums = new ArrayList<>();
        for (int i = 2011; i <= 2020; i++) {
            //按年先找到所有论文
            List<Affiliation_Paper_PO> papers = affiliationData.getAffiliationPaperByIdAndYear(id, String.valueOf(i));
            int sum = 0;
            //对当年的每一篇论文累加被引用数
            for (Affiliation_Paper_PO paper : papers) sum += paper.getC_count();
            citationNums.add(sum);
        }
        return citationNums;
    }

    @Override
    public List<AffiliationVO> getTopAffiliations() {
        //先找到机构—论文的表项，包含了机构名和论文id，被引用次数，年份在内
//        List<Affiliation_Paper_PO> affs = affiliationData.getTopAffiliations();
        //在表项中区分并计算活跃度，排序
//        return getTopAffiliations(affs);
        List<AffiliationPO> affiliationPOS = affiliationData.getTopAffiliations();
        List<AffiliationVO> affiliationVOS = new ArrayList<>();
        for(int i = 0; i < affiliationPOS.size()&&i<11;++i){
            AffiliationVO affiliationVO = new AffiliationVO();
            affiliationVO.setId(affiliationPOS.get(i).getId());
            affiliationVO.setName(affiliationPOS.get(i).getName());
            affiliationVO.setRank(affiliationPOS.get(i).getRank());
            affiliationVOS.add(affiliationVO);
        }
        return affiliationVOS;
    }

    @Override
    public List<PaperVO> getPapersOfAffByAuthor(int id, int auId) {
        List<PaperVO> paperVOS = new ArrayList<>();
        List<PaperPO> paperPOS = affiliationData.getPaperIdByAffiliationIdAndAuthorId(id, auId);
        for (PaperPO paper : paperPOS) paperVOS.add(paperLogicForGetPaper.getPaperById(paper.getId()));
        return paperVOS;
    }

    @Override
    public List<PaperVO> getPapersOfAffByYear(int id, String year) {
        //先根据机构id和年份找到论文的id
        List<PaperVO> paperVOS = new ArrayList<>();
        List<PaperPO> paperPOS = affiliationData.getPaperIdByAffiliationIdAndYear(id, year);
        //按id获得论文的基本信息
        for (PaperPO paper : paperPOS) paperVOS.add(paperLogicForGetPaper.getPaperById(paper.getId()));
        return paperVOS;
    }

    @Override
    public List<PaperVO> getAllPapersOfAff(int id) {
        List<PaperVO> paperVOS = new ArrayList<>();
        //先找id，再找信息，skr
        List<PaperPO> paperPOS = affiliationData.getPaperIdByAffiliationId(id);
        for (PaperPO paper : paperPOS) paperVOS.add(paperLogicForGetPaper.getPaperById(paper.getId()));
        return paperVOS;
    }

    @Override
    public List<AuthorVO> getAuthorsOfAff(int id) {
        List<AuthorPO> authors = affiliationData.getAuthorsOfAffById(id);
        List<AuthorVO> res = new ArrayList<>();
        for (AuthorPO author : authors) res.add(new AuthorVO(author.getId(),author.getName()));
        return res;
    }


    //根据机构id来获得机构的信息，包括机构名，作者名，论文数，作者数、被引用的次数等
    //有做查重处理
    public AffiliationVO getAffiliationById(int id) {
        //根据机构id找到机构的所有作者
        List<AffiliationPO> affiliationAuthor = affiliationData.getAffiliationAuthorById(id);
        //统计作者数，累加作者名，以空格分割
        StringBuilder authors = new StringBuilder();
        int author_num = 0;
        for (AffiliationPO affiliationPO : affiliationAuthor) {
            if (!authors.toString().contains(affiliationPO.getAuthors())) {
                ++author_num;
                authors.append(affiliationPO.getAuthors()).append(" ");
            }
        }

        //根据机构id找到作者id
        List<AuthorPO> authorsOfAff = affiliationData.getAuthorsOfAffById(id);
        StringBuilder dirs = new StringBuilder();
        for (AuthorPO authorId : authorsOfAff) {
            //对每个作者的所有方向单独查重，累加，以空格分隔
            List<AuthorPO> authorDirectionOfAffiliation = affiliationData.getAuthorDirectionOfAffiliation(authorId.getId());
            for (AuthorPO author : authorDirectionOfAffiliation)
                if (!dirs.toString().contains(author.getResearchDirection()))
                    dirs.append(author.getResearchDirection()).append(";");
        }

        //根据机构id获得机构的论文，主要是论文数和论文的被引用数
        List<Affiliation_Paper_PO> affliationPaper = affiliationData.getAffiliationPaperById(id);
        ArrayList<Integer> papers = new ArrayList<>();
        int paper_num = 0;
        int c_num = 0;
        for (Affiliation_Paper_PO ap : affliationPaper) {
            if (!papers.contains(ap.getPaperId())) {
                ++paper_num;
                papers.add(ap.getPaperId());
                c_num += ap.getC_count();
            }
        }

        return new AffiliationVO(id, affiliationAuthor.get(0).getName(),
                paper_num, c_num, authors.length()>300?authors.substring(0,300):authors.toString(), affiliationAuthor.get(0).getRank(),
                dirs.length()>300?dirs.substring(0,300):dirs.toString());
    }

    @Override
    public List<ResearchDirectionVO> getAffiliationTopResearchDirectionById(int id) {
        List<ResearchDirectionPO> researchDirectionPOS = affiliationData.getAffiliationTopDirectionsById(id);
        List<ResearchDirectionVO> res = new ArrayList<>();
        for(int i = 0; i < 10&&i<researchDirectionPOS.size();i++){
            ResearchDirectionVO re = new ResearchDirectionVO();
            re.setPaper_number(researchDirectionPOS.get(i).getPaper_number());
            re.setId(researchDirectionPOS.get(i).getId());
            re.setName(researchDirectionPOS.get(i).getName());
            res.add(re);
        }
        return res;
    }


    //从记录项中总结并计算活跃度，按活跃度从高到低返回机构VO对象
//    private List<AffiliationVO> getTopAffiliations(List<Affiliation_Paper_PO> list) {
//        //这里说明下活跃度计算方法
//        //rank = E papers.c_count * (2020 - year) / 10;
//        ArrayList<AffiliationVO> affs = new ArrayList<>();
//        int list_p = 0, affs_p = -1;
//        while (list_p < list.size()) {
//            AffiliationVO affiliationVO = new AffiliationVO();
//            int id = list.get(list_p).getAffiliationId();
//            affiliationVO.setId(id);
//            affiliationVO.setName(list.get(list_p).getName());
//            affiliationVO.setPaper_number(0);
//            affiliationVO.setRank(0);
//            while (list_p < list.size() && list.get(list_p).getAffiliationId() == id) {
//                affiliationVO.setRank(affiliationVO.getRank() + (double) list.get(list_p).getC_count() * (list.get(list_p).getYear() - 2010) / 10);
//                affiliationVO.setPaper_number(affiliationVO.getPaper_number() + 1);
//                list_p++;
//            }
//            if (affs_p >= 10) {
//                affs.sort(new Comparator<AffiliationVO>() {
//                    @Override
//                    public int compare(AffiliationVO o1, AffiliationVO o2) {
//                        return Double.compare(o2.getRank(), o1.getRank());
//                    }
//                });
//                if (affiliationVO.getRank() > affs.get(10).getRank()) {
//                    affs.set(10, affiliationVO);
//                }
//            } else {
//                affs.add(affiliationVO);
//                affs_p++;
//            }
//        }
//        //按活跃度排序
//        affs.sort(new Comparator<AffiliationVO>() {
//            @Override
//            public int compare(AffiliationVO o1, AffiliationVO o2) {
//                return Double.compare(o2.getRank(), o1.getRank());
//            }
//        });
//        return affs;
//    }
}
