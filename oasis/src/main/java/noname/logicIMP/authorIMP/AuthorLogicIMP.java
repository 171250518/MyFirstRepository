package noname.logicIMP.authorIMP;

import noname.PO.*;
import noname.VO.AuthorVO;
import noname.VO.PaperVO;
import noname.data.author.AuthorData;
import noname.logic.author.AuthorLogic;
import noname.logic.author.AuthorLogicForGetAuthor;
import noname.logic.paper.PaperLogicForGetPaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class AuthorLogicIMP implements AuthorLogic, PaperLogicForGetPaper, AuthorLogicForGetAuthor {

    @Autowired
    private AuthorData authorData;

    public void setAuthorData(AuthorData authorData) {
        this.authorData = authorData;
    }


    //通过名字找到作者
    @Override
    public List<AuthorVO> getAuthorByName(String name) {
        //先找id
        List<AuthorPO> authorsPO = authorData.getAuthorPOByName(name);
        List<AuthorVO> author = new ArrayList<>();
        for (AuthorPO authorPO : authorsPO) {
            //对每个id找到具体信息
            author.add(new AuthorVO(authorPO.getId(),authorPO.getName()));
        }
        return author;
    }

    //找到活跃学者
    @Override
    public List<AuthorVO> getTopAuthors() {
        //先找到作者_论文表项，包括年份、论文数、论文被引用数
//        List<Author_Paper_PO> authors = authorData.getTopAuthors();
        //调用方法来计算活跃度、排序
//        return getTopAuthors(authors);
        // 重构代码
        List<AuthorPO> topAuthors = authorData.getTopAuthors();
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

    //作者的论文
    @Override
    public List<PaperVO> getAllPapersOfAuthor(int id) {
        //先根据作者id找论文id
        List<PaperPO> paperId = authorData.getPaperIdByAuthorId(id);
        List<PaperVO> res = new ArrayList<>();
        //对每个论文id找到论文的信息
        for (PaperPO paper : paperId) res.add(new PaperVO(paper.getId(),paper.getTitle(),paper.getPublishTime(),paper.getLink(),paper.getCitationCount()));
        return res;
    }

    @Override
    public List<PaperVO> getAllPapersOfAuthorOrderByCitation(int id) {
        //先根据作者id找论文id
        List<PaperPO> paperId = authorData.getPaperIdByAuthorIdOrderByCitation(id);
        List<PaperVO> res = new ArrayList<>();
        //对每个论文id找到论文的信息
        for (PaperPO paper : paperId) res.add(getPaperById(paper.getId()));
        return res;
    }

    //作者某一年的论文
    @Override
    public List<PaperVO> getPapersOfAuthorByYear(int id, String year) {
        //先找id，再找论文
        List<PaperPO> paperId = authorData.getPaperIdByAuthorIdAndPublishTime(id, year);
        List<PaperVO> res = new ArrayList<>();
        for (PaperPO paper : paperId) res.add(getPaperById(paper.getId()));
        return res;
    }

    //根据作者和会议找论文
    @Override
    public List<PaperVO> getPapersOfAuthorByConf(int id, String conf) {
        //先找id，再找论文
        List<PaperPO> paperId = authorData.getPaperIdByAuthorIdAndConference(id, conf);
        List<PaperVO> res = new ArrayList<>();
        for (PaperPO paper : paperId) res.add(getPaperById(paper.getId()));
        return res;
    }

    @Override
    public List<Integer> getPaperNumOfAuByYear(int id) {
        List<Integer> paperNums = new ArrayList<>();
        for (int i = 2011; i <= 2020; i++) {
            //按年搜索论文数
            paperNums.add(authorData.getAuthorPaperNumByIdAndYear(id, String.valueOf(i)));
        }
        return paperNums;
    }

    @Override
    public List<Integer> getCitationNumOfAuByYear(int id) {
        List<Integer> citationNums = new ArrayList<>();
        for (int i = 2011; i <= 2020; i++) {
            //按年先找到所有论文
            List<Author_Paper_PO> papers = authorData.getAuthorPaperByIdAndYear(id, String.valueOf(i));
            int sum = 0;
            //对当年的每一篇论文累加被引用数
            for (Author_Paper_PO paper : papers) sum += paper.getC_count();
            citationNums.add(sum);
        }
        return citationNums;
    }


    //根据作者和合作作者找论文
    @Override
    public List<PaperVO> getPapersOfAuthorByCoWorker(int id, int coId) {
        //先找id，再找论文
        List<PaperPO> paperId = authorData.getPaperIdByAuthorIdAndCoAuthor(id, coId);
        List<PaperVO> res = new ArrayList<>();
        for (PaperPO paper : paperId) res.add(getPaperById(paper.getId()));
        return res;
    }

    //根据id找到作者的详情
    @Override
    public AuthorVO getAuthorById(int id) {
        //机构表
        List<AuthorPO> affiliations = authorData.getAuthorAffiliationById(id);
        //研究方向表
        List<AuthorPO> directions = authorData.getAuthorDirectionById(id);
        //论文表
        List<PaperVO> papers = getAllPapersOfAuthor(id);
        //将表整理成字符串
        StringBuilder affs = new StringBuilder();
        StringBuilder dirs = new StringBuilder();
        int c_count = 0;
        for (AuthorPO author : affiliations){
            affs.append(author.getInstitution()).append(" ");
            if(affs.length()>100){
                affs.append("......");
                break;
            }
        }
        for (AuthorPO author : directions){
            dirs.append(author.getResearchDirection()).append(";");
            if(dirs.length()>300){
                dirs.append("......");
                break;
            }
        }
        for (PaperVO paper : papers) {
            //要不要做查重？不用
            c_count += paper.getCitationCount();
        }
        return new AuthorVO(id, affiliations.get(0).getName(), affs.toString(), dirs.toString(), papers.size(), c_count,affiliations.get(0).getRank());
    }

    //找到作者的合作作者
    @Override
    public List<AuthorVO> getCoAuthorById(int id) {
        //先找id，再找信息
        List<AuthorVO> authorVOS = authorData.getCoAuthorIdById(id);
        List<AuthorVO> authors = new ArrayList<>();
        if(authorVOS.size()==0)return authors;
        int coid = authorVOS.get(0).getId();
        int i = 0;
        while(i<authorVOS.size()){
            AuthorVO authorVO = new AuthorVO();
            authorVO.setName(authorVOS.get(i).getName());
            authorVO.setId(authorVOS.get(i).getId());
            authorVO.setInstitution(authorVOS.get(i).getInstitution());
            authors.add(authorVO);
            while (i<authorVOS.size()&&authorVOS.get(i).getId()==coid){
                i++;
            }
            if(i<authorVOS.size())coid=authorVOS.get(i).getId();
        }
        return authors;
    }


    //通过id找到论文详情
    @Override
    public PaperVO getPaperById(int id) {
        //基本信息
        PaperPO paper = authorData.getPaperBasicById(id).get(0);
        //作者
        List<AuthorPO> authors = authorData.getPaperAuthorById(id);
        //研究方向
        List<ResearchDirectionPO> dirs = authorData.getPaperDirectionById(id);
        //机构
        List<AffiliationPO> affs = authorData.getPaperAffiliationById(id);
        //将表转化为字符串
        StringBuilder sb_author = new StringBuilder();
        StringBuilder sb_dir = new StringBuilder();
        StringBuilder sb_affs = new StringBuilder();
        for (AuthorPO author : authors) {
            sb_author.append(author.getName()).append(" ");
            for (AffiliationPO aff : affs)
                if (!sb_affs.toString().contains(aff.getName())) sb_affs.append(aff.getName()).append(" ");
        }
        for (ResearchDirectionPO dir : dirs) sb_dir.append(dir.getName()).append(" ");
        return new PaperVO(id, paper.getTitle(), sb_author.toString(), paper.getPublishTime(),
                paper.getConf(), sb_affs.toString(), null, sb_dir.toString(), paper.getLink(),
                paper.getCitationCount(), 0);

    }


    //计算活跃度、排序
//    @Override
//    public List<AuthorVO> getTopAuthors(List<Author_Paper_PO> list) {
//        //这里说明下活跃度计算方法
//        //rank = E papers.c_count * (2020 - year) / 10;
//        ArrayList<AuthorVO> authors = new ArrayList<>();
//        int list_p = 0, authors_p = -1;
//        while (list_p < list.size()) {
//            AuthorVO authorVO = new AuthorVO();
//            int id = list.get(list_p).getAuthorId();
//            authorVO.setId(id);
//            authorVO.setName(list.get(list_p).getName());
//            authorVO.setPaper_num(0);
//            authorVO.setRank(0);
//            while (list_p < list.size() && list.get(list_p).getAuthorId() == id) {
//                authorVO.setRank(authorVO.getRank() + (double) list.get(list_p).getC_count() * (list.get(list_p).getYear() - 2010) / 10);
//                authorVO.setPaper_num(authorVO.getPaper_num() + 1);
//                list_p++;
//            }
//            if (authors_p >= 9) {
////                authors.sort(new Comparator<AuthorVO>() {
////                    @Override
////                    public int compare(AuthorVO o1, AuthorVO o2) {
////                        return Double.compare(o2.getRank(), o1.getRank());
////                    }
////                });
////                if (authorVO.getRank() > authors.get(9).getRank()) {
////                    authors.set(9, authorVO);
////                }
//            } else {
//                authors.add(authorVO);
//                authors_p++;
//            }
//        }
//        //按活跃度排序
//        authors.sort(new Comparator<AuthorVO>() {
//            @Override
//            public int compare(AuthorVO o1, AuthorVO o2) {
//                return Double.compare(o2.getRank(), o1.getRank());
//            }
//        });
//        return authors;
//    }
}
