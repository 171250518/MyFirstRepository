package noname.logicIMP.confIMP;

import noname.PO.*;
import noname.VO.AuthorVO;
import noname.VO.ConferenceVO;
import noname.VO.PaperVO;
import noname.data.conf.ConfData;
import noname.logic.author.AuthorLogicForGetAuthor;
import noname.logic.conf.ConfLogic;
import noname.logic.paper.PaperLogicForGetPaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ConfLogicIMP implements ConfLogic {

    @Autowired
    private ConfData confData;

    @Autowired
    private PaperLogicForGetPaper paperLogicForGetPaper;

    @Autowired
    private AuthorLogicForGetAuthor authorLogicForGetAuthor;

    public void setConfData(ConfData confData) {
        this.confData = confData;
    }

    public void setPaperLogicForGetPaper(PaperLogicForGetPaper paperLogicForGetPaper) {
        this.paperLogicForGetPaper = paperLogicForGetPaper;
    }

    public void setAuthorLogicForGetAuthor(AuthorLogicForGetAuthor authorLogicForGetAuthor) {
        this.authorLogicForGetAuthor = authorLogicForGetAuthor;
    }

    //通过名字找到会议信息
    @Override
    public List<ConferenceVO> getConfByName(String name) {
        //先找id，再找信息
        List<ConferencePO> conferences = confData.getConferenceByName(name);
        List<ConferenceVO> res = new ArrayList<>();
        for (ConferencePO conferencePO : conferences) {
            res.add(new ConferenceVO(conferencePO.getId(),conferencePO.getName()));
        }
        return res;
    }


    @Override
    public List<PaperVO> getPapersOfConfByAuthor(int id, int auid) {
        List<PaperVO> paperVOS = new ArrayList<>();
        //先找论文id
        List<PaperPO> paperPOS = confData.getPaperIdByConferenceIdAndAuthorId(id, auid);
        //再找论文信息
        for (PaperPO paper : paperPOS) paperVOS.add(paperLogicForGetPaper.getPaperById(paper.getId()));
        return paperVOS;
    }

    //找到最活跃的会议
    @Override
    public List<ConferenceVO> getTopConfs() {
//        //先找到会议_论文表项
////        List<Conference_Paper_PO> confs = confData.getTopConferences();
////        //计算活跃度，排序
////        return getTopConfs(confs);
        List<ConferencePO> conferencePOS = confData.getTopConferences();
        List<ConferenceVO> conferenceVOS = new ArrayList<>();
        for(int i = 0; i < conferencePOS.size()&&i<10;++i){
            ConferenceVO conferenceVO = new ConferenceVO();
            conferenceVO.setId(conferencePOS.get(i).getId());
            conferenceVO.setName(conferencePOS.get(i).getName());
            conferenceVO.setRank(conferencePOS.get(i).getRank());
            conferenceVOS.add(conferenceVO);
        }
        return conferenceVOS;
    }

    //根据会议id找到论文信息
    @Override
    public List<PaperVO> getAllPapersOfConf(int id) {
        List<PaperVO> paperVOS = new ArrayList<>();
        //先找论文id
        List<PaperPO> paperPOS = confData.getPaperIdByConferenceId(id);
        //再找论文信息
        for (PaperPO paper : paperPOS) paperVOS.add(new PaperVO(paper.getId(),paper.getTitle(),paper.getPublishTime(),paper.getLink(),paper.getCitationCount()));
        return paperVOS;
    }

    //根据会议和年份找到论文信息
    @Override
    public List<PaperVO> getPapersOfConfByYear(int id, String year) {
        List<PaperVO> paperVOS = new ArrayList<>();
        //先找id，再找信息
        List<PaperPO> paperPOS = confData.getPaperIdByConferenceIdAndYear(id, year);
        for (PaperPO paper : paperPOS) paperVOS.add(paperLogicForGetPaper.getPaperById(paper.getId()));
        return paperVOS;
    }

    //按年份和会议id找到论文数，返回2011年到2020年的论文数信息
    @Override
    public List<Integer> getPaperNumOfConfByYear(int id) {
        List<Integer> paperNums = new ArrayList<>();
        for (int i = 2011; i <= 2020; i++) {
            paperNums.add(confData.getConferencePaperNumByIdAndYear(id, String.valueOf(i)));
        }
        return paperNums;
    }

    //按年份和会议id找到论文被引用数，返回2011年到2020年的论文被引用数信息
    @Override
    public List<Integer> getCitationNumOfConfByYear(int id) {
        List<Integer> citationNums = new ArrayList<>();
        for (int i = 2011; i <= 2020; i++) {
            //先按年找到所有论文
            List<Conference_Paper_PO> papers = confData.getConferencePaperByIdAndYear(id, String.valueOf(i));
            int sum = 0;
            //对每个论文累加被引用数
            for (Conference_Paper_PO paper : papers) sum += paper.getC_count();
            citationNums.add(sum);
        }
        return citationNums;
    }

    //获得某一次会议上有发表论文的作者
    @Override
    public List<AuthorVO> getAuthorOfConf(int id) {
        List<AuthorPO> authors = confData.getAuthorsOfConfById(id);
        List<AuthorVO> res = new ArrayList<>();
        for (AuthorPO authorPO : authors)if(res.size()==0||authorPO.getId()!=res.get(res.size()-1).getId()) res.add(new AuthorVO(authorPO.getId(),authorPO.getName()));
        return res;
    }

    //计算某次会议上的作者活跃度并排序
    @Override
    public List<AuthorVO> getTopAuthorOfConf(int id) {
        List<AuthorPO> topAuthors = confData.getTopAuthorsOfConfById(id);
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

    //按照id找到会议信息
    @Override
    public ConferenceVO getConferenceById(int id) {
        List<PaperVO> papers = getAllPapersOfConf(id);
        //会议名
        ConferencePO conferencePO = confData.getConferenceById(id);
        //论文被引用数
        int citation_num = 0;
        for (PaperVO paper : papers) citation_num += paper.getCitationCount();
        return new ConferenceVO(id, conferencePO.getName(), conferencePO.getRank(), papers.get(0).getPublishTime(), papers.size(), citation_num);
    }

    //计算会议活跃度、排序
    private List<ConferenceVO> getTopConfs(List<Conference_Paper_PO> list) {
        //这里说明下活跃度计算方法
        //rank = E papers.c_count * (2020 - year) / 10;
        ArrayList<ConferenceVO> confs = new ArrayList<>();
        int list_p = 0, confs_p = -1;
        while (list_p < list.size()) {
            ConferenceVO conferenceVO = new ConferenceVO();
            int id = list.get(list_p).getConferenceId();
            conferenceVO.setId(id);
            conferenceVO.setName(list.get(list_p).getName());
            conferenceVO.setPaper_num(0);
            conferenceVO.setRank(0);
            while (list_p < list.size() && list.get(list_p).getConferenceId() == id) {
                conferenceVO.setRank(conferenceVO.getRank() + (double) list.get(list_p).getC_count() * (list.get(list_p).getYear() - 2010) / 10);
                conferenceVO.setPaper_num(conferenceVO.getPaper_num() + 1);
                list_p++;
            }
            if (confs_p >= 9) {
                confs.sort(new Comparator<ConferenceVO>() {
                    @Override
                    public int compare(ConferenceVO o1, ConferenceVO o2) {
                        return Double.compare(o2.getRank(), o1.getRank());
                    }
                });
                if (conferenceVO.getRank() > confs.get(9).getRank()) {
                    confs.set(9, conferenceVO);
                }
            } else {
                confs.add(conferenceVO);
                confs_p++;
            }
        }
        //按活跃度排序
        confs.sort(new Comparator<ConferenceVO>() {
            @Override
            public int compare(ConferenceVO o1, ConferenceVO o2) {
                return Double.compare(o2.getRank(), o1.getRank());
            }
        });
        return confs;
    }

}
