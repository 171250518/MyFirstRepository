package noname.logicIMP.confIMP;

import noname.PO.*;
import noname.VO.AuthorVO;
import noname.VO.ConferenceVO;
import noname.VO.PaperVO;
import noname.data.conf.ConfData;
import noname.logic.author.AuthorLogicForGetAuthor;
import noname.logic.paper.PaperLogic;
import noname.logic.paper.PaperLogicForGetPaper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.print.Paper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConfLogicIMPTest {

    private ConfLogicIMP confLogicIMP;

    private List<AuthorPO> authorPOs;

    private List<PaperPO> paperPOS;

    private List<ResearchDirectionPO> researchDirectionPOS;

    private List<AffiliationPO> affiliationPOS;

    private List<ConferencePO> conferencePOS;

    @Before
    public void setUp(){
        confLogicIMP = new ConfLogicIMP();
        authorPOs = new ArrayList<>();
        authorPOs.add(new AuthorPO(0,"author1",55,"NJU","ML"));
        authorPOs.add(new AuthorPO(1,"author2",65,"PKU","DL"));

        paperPOS = new ArrayList<>();
        paperPOS.add(new PaperPO(0,"paper1","author1","2015","IEEE","NJU","paper","ML","link1",100,0));
        paperPOS.add(new PaperPO(1,"paper2","author1","2016","IEEE","NJU","paper","DL","link2",20,0));


        researchDirectionPOS = new ArrayList<>();
        researchDirectionPOS.add(new ResearchDirectionPO(0,"ML",1,100,"author1",55));


        affiliationPOS = new ArrayList<>();
        affiliationPOS.add(new AffiliationPO(0,"NJU",1,100,"author1",1,55));

        conferencePOS = new ArrayList<>();
        conferencePOS.add(new ConferencePO(0,"IEEE",50,"2015",15,90));
        conferencePOS.add(new ConferencePO(1,"ASE",70,"2019",20,45));
    }

    @After
    public void tearDown(){
        confLogicIMP = null;
        authorPOs = null;
        paperPOS = null;
        researchDirectionPOS = null;
        affiliationPOS = null;
        conferencePOS = null;
    }

    @Test
    public void getConfByName() {

        ConfData confData = mock(ConfData.class);
        when(confData.getConferenceByName("NJU")).thenReturn(conferencePOS);

        confLogicIMP.setConfData(confData);

        List<ConferenceVO> conferenceVOS = confLogicIMP.getConfByName("NJU");
        assertEquals(2,conferenceVOS.size());
        assertEquals("IEEE",conferenceVOS.get(0).getName());
        assertEquals("ASE",conferenceVOS.get(1).getName());
    }

    @Test
    public void getPapersOfConfByAuthor() {
        ConfData confData = mock(ConfData.class);
        when(confData.getPaperIdByConferenceIdAndAuthorId(0,0)).thenReturn(paperPOS);
        PaperLogicForGetPaper paperLogicForGetPaper = mock(PaperLogicForGetPaper.class);
        when(paperLogicForGetPaper.getPaperById(0)).thenReturn(
                new PaperVO(0,"paper1","author1","2015","IEEE","NJU","paper","ML","link1",100,0)
        );
        when(paperLogicForGetPaper.getPaperById(1)).thenReturn(
                new PaperVO(1,"paper2","author1","2016","IEEE","NJU","paper","DL","link2",20,0)
        );
        confLogicIMP.setPaperLogicForGetPaper(paperLogicForGetPaper);
        confLogicIMP.setConfData(confData);
        List<PaperVO> paperVOS = confLogicIMP.getPapersOfConfByAuthor(0,0);
        assertEquals(2,paperVOS.size());
        assertEquals("IEEE",paperVOS.get(0).getConf());
        assertEquals("2016",paperVOS.get(1).getPublishTime());
    }

    @Test
    public void getTopConfs() {
        ConfData confData = mock(ConfData.class);
        when(confData.getTopConferences()).thenReturn(conferencePOS);
        confLogicIMP.setConfData(confData);
        List<ConferenceVO> conferenceVOS = confLogicIMP.getTopConfs();
        assertEquals(0, conferenceVOS.get(0).getId());
        assertEquals("IEEE",conferenceVOS.get(0).getName());
        assertEquals(50,conferenceVOS.get(0).getRank(),1e-6);
    }

    @Test
    public void getAllPapersOfConf() {


        ConfData confData = mock(ConfData.class);
        when(confData.getPaperIdByConferenceId(0)).thenReturn(paperPOS);

        confLogicIMP.setConfData(confData);

        List<PaperVO> list = confLogicIMP.getAllPapersOfConf(0);
        assertEquals(0, list.get(0).getId());
        assertEquals("paper1",list.get(0).getTitle());
        assertEquals("2016",list.get(1).getPublishTime());
        assertEquals("link2",list.get(1).getLink());
    }

    @Test
    public void getPapersOfConfByYear() {
        List<PaperPO> papers = new ArrayList<>();
        papers.add(paperPOS.get(0));
        papers.add(paperPOS.get(1));

        List<PaperVO> paperVOS = new ArrayList<>(Arrays.asList(
                new PaperVO(0,"paper1","author1","2015","IEEE","NJU","","ML","link1",55,0),
                new PaperVO(1,"paper2","author2","2015","IEEE","PKU","","DL","link2",25,0)
        ));
        ConfData confData = mock(ConfData.class);
        PaperLogicForGetPaper paperLogicForGetPaper = mock(PaperLogicForGetPaper.class);
        when(confData.getPaperIdByConferenceIdAndYear(0,"2015")).thenReturn(papers);
        when(paperLogicForGetPaper.getPaperById(0)).thenReturn(paperVOS.get(0));
        when(paperLogicForGetPaper.getPaperById(1)).thenReturn(paperVOS.get(1));

        confLogicIMP.setConfData(confData);
        confLogicIMP.setPaperLogicForGetPaper(paperLogicForGetPaper);

        List<PaperVO> list = confLogicIMP.getPapersOfConfByYear(0,"2015");
        assertEquals("author1", list.get(0).getAuthor());
        assertEquals("NJU",list.get(0).getAffiliation());
        assertEquals("2015",list.get(1).getPublishTime());
        assertEquals("PKU",list.get(1).getAffiliation());
    }

    @Test
    public void getPaperNumOfConfByYear() {
        List<Integer> exp = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        ConfData confData = mock(ConfData.class);
        for(int i =0;i< 10;i++){
            when(confData.getConferencePaperNumByIdAndYear(0,String.valueOf(i+2011))).thenReturn(exp.get(i));
        }
        confLogicIMP.setConfData(confData);
        List<Integer> list = confLogicIMP.getPaperNumOfConfByYear(0);
        assertEquals(exp.get(0),list.get(0));
        assertEquals(exp.get(1),list.get(1));
        assertEquals(exp.get(2),list.get(2));
        assertEquals(exp.get(3),list.get(3));
        assertEquals(exp.get(4),list.get(4));
        assertEquals(exp.get(5),list.get(5));
        assertEquals(exp.get(6),list.get(6));
        assertEquals(exp.get(7),list.get(7));
        assertEquals(exp.get(8),list.get(8));
        assertEquals(exp.get(9),list.get(9));
    }

    @Test
    public void getCitationNumOfConfByYear() {
        List<Conference_Paper_PO> papers_2011 = new ArrayList<>(Arrays.asList(
                new Conference_Paper_PO(0,0,"paper1",10,2011),
                new Conference_Paper_PO(0,1,"paper2",50,2011)
        ));
        List<Conference_Paper_PO> papers_2012 = new ArrayList<>(Arrays.asList(
                new Conference_Paper_PO(0,0,"paper1",15,2012),
                new Conference_Paper_PO(0,1,"paper2",30,2012)
        ));
        List<Conference_Paper_PO> papers_2013 = new ArrayList<>(Arrays.asList(
                new Conference_Paper_PO(0,0,"paper1",50,2013),
                new Conference_Paper_PO(0,1,"paper2",30,2013)
        ));
        List<Conference_Paper_PO> papers_2014 = new ArrayList<>(Arrays.asList(
                new Conference_Paper_PO(0,0,"paper1",66,2014),
                new Conference_Paper_PO(0,1,"paper2",33,2014)
        ));
        List<Conference_Paper_PO> papers_2015 = new ArrayList<>(Arrays.asList(
                new Conference_Paper_PO(0,0,"paper1",77,2015),
                new Conference_Paper_PO(0,1,"paper2",11,2015)
        ));
        List<Conference_Paper_PO> papers_2016 = new ArrayList<>(Arrays.asList(
                new Conference_Paper_PO(0,0,"paper1",4,2016),
                new Conference_Paper_PO(0,1,"paper2",6,2016)
        ));
        List<Conference_Paper_PO> papers_2017 = new ArrayList<>(Arrays.asList(
                new Conference_Paper_PO(0,0,"paper1",9,2017),
                new Conference_Paper_PO(0,1,"paper2",10,2017)
        ));
        List<Conference_Paper_PO> papers_2018 = new ArrayList<>(Arrays.asList(
                new Conference_Paper_PO(0,0,"paper1",5,2018),
                new Conference_Paper_PO(0,1,"paper2",3,2018)
        ));
        List<Conference_Paper_PO> papers_2019 = new ArrayList<>(Arrays.asList(
                new Conference_Paper_PO(0,0,"paper1",99,2019),
                new Conference_Paper_PO(0,1,"paper2",0,2019)
        ));
        List<Conference_Paper_PO> papers_2020 = new ArrayList<>(Arrays.asList(
                new Conference_Paper_PO(0,0,"paper1",120,2020),
                new Conference_Paper_PO(0,1,"paper2",30,2020)
        ));
        ConfData confData = mock(ConfData.class);
        when(confData.getConferencePaperByIdAndYear(0,"2011")).thenReturn(papers_2011);
        when(confData.getConferencePaperByIdAndYear(0,"2012")).thenReturn(papers_2012);
        when(confData.getConferencePaperByIdAndYear(0,"2013")).thenReturn(papers_2013);
        when(confData.getConferencePaperByIdAndYear(0,"2014")).thenReturn(papers_2014);
        when(confData.getConferencePaperByIdAndYear(0,"2015")).thenReturn(papers_2015);
        when(confData.getConferencePaperByIdAndYear(0,"2016")).thenReturn(papers_2016);
        when(confData.getConferencePaperByIdAndYear(0,"2017")).thenReturn(papers_2017);
        when(confData.getConferencePaperByIdAndYear(0,"2018")).thenReturn(papers_2018);
        when(confData.getConferencePaperByIdAndYear(0,"2019")).thenReturn(papers_2019);
        when(confData.getConferencePaperByIdAndYear(0,"2020")).thenReturn(papers_2020);

        confLogicIMP.setConfData(confData);

        List<Integer> list = confLogicIMP.getCitationNumOfConfByYear(0);
        assertEquals(60,(int)list.get(0));
        assertEquals(45,(int)list.get(1));
        assertEquals(80,(int)list.get(2));
        assertEquals(99,(int)list.get(3));
        assertEquals(88,(int)list.get(4));
        assertEquals(10,(int)list.get(5));
        assertEquals(19,(int)list.get(6));
        assertEquals(8,(int)list.get(7));
        assertEquals(99,(int)list.get(8));
        assertEquals(150,(int)list.get(9));
    }

    @Test
    public void getAuthorOfConf() {

        ConfData confData = mock(ConfData.class);
        AuthorLogicForGetAuthor authorLogicForGetAuthor = mock(AuthorLogicForGetAuthor.class);
        when(confData.getAuthorsOfConfById(0)).thenReturn(authorPOs);
        when(authorLogicForGetAuthor.getAuthorById(0)).thenReturn(
                new AuthorVO(0,"author1","NJU","ML",1,65)
        );
        when(authorLogicForGetAuthor.getAuthorById(1)).thenReturn(
                new AuthorVO(1,"author2","PKU","DL",1,35)
        );
        confLogicIMP.setConfData(confData);
        confLogicIMP.setAuthorLogicForGetAuthor(authorLogicForGetAuthor);
        List<AuthorVO> list = confLogicIMP.getAuthorOfConf(0);
        assertEquals("author1",list.get(0).getName());
        assertEquals("author2",list.get(1).getName());
    }

    @Test
    public void getTopAuthorOfConf() {
        ConfData confData = mock(ConfData.class);
        AuthorLogicForGetAuthor authorLogicForGetAuthor = mock(AuthorLogicForGetAuthor.class);
        when(confData.getTopAuthorsOfConfById(0)).thenReturn(authorPOs);
        when(authorLogicForGetAuthor.getAuthorById(0)).thenReturn(
                new AuthorVO(0,"author1","NJU","ML",1,65)
        );
        when(authorLogicForGetAuthor.getAuthorById(1)).thenReturn(
                new AuthorVO(1,"author2","PKU","DL",1,35)
        );
        confLogicIMP.setConfData(confData);
        confLogicIMP.setAuthorLogicForGetAuthor(authorLogicForGetAuthor);
        List<AuthorVO> list = confLogicIMP.getTopAuthorOfConf(0);
        assertEquals(0,list.get(0).getId());
        assertEquals("author2",list.get(1).getName());
    }

    @Test
    public void getConferenceById() {
        List<ConferencePO> confs = new ArrayList<>();
        confs.add(conferencePOS.get(0));
        confs.add(conferencePOS.get(1));
        List<PaperVO> papers = new ArrayList<>(Arrays.asList(
                new PaperVO(0,"paper1","author1","2015","IEEE","NJU","paper","ML","link1",100,0),
                new PaperVO(1,"paper2","author1","2016","IEEE","NJU","paper","DL","link2",20,0)
        ));

        ConfData confData = mock(ConfData.class);
        PaperLogicForGetPaper paperLogicForGetPaper = mock(PaperLogicForGetPaper.class);
        when(confData.getConferenceById(0)).thenReturn(confs.get(0));
        when(confData.getPaperIdByConferenceId(0)).thenReturn(paperPOS);
        when(paperLogicForGetPaper.getPaperById(0)).thenReturn(papers.get(0));
        when(paperLogicForGetPaper.getPaperById(1)).thenReturn(papers.get(1));

        confLogicIMP.setConfData(confData);
        confLogicIMP.setPaperLogicForGetPaper(paperLogicForGetPaper);

        ConferenceVO conferenceVOS = confLogicIMP.getConferenceById(0);
        assertEquals(2,conferenceVOS.getPaper_num());
        assertEquals(120,conferenceVOS.getCitation());
    }
}