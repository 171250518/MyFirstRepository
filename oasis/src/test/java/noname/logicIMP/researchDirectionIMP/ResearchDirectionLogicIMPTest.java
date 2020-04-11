package noname.logicIMP.researchDirectionIMP;

import noname.PO.*;
import noname.VO.AffiliationVO;
import noname.VO.AuthorVO;
import noname.VO.PaperVO;
import noname.VO.ResearchDirectionVO;
import noname.data.researchDirection.ResearchDirectionData;
import noname.logic.author.AuthorLogicForGetAuthor;
import noname.logic.paper.PaperLogicForGetPaper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResearchDirectionLogicIMPTest {

    private ResearchDirectionLogicIMP researchDirectionLogicIMP;

    private List<AuthorPO> authorPOs;

    private List<PaperPO> paperPOS;

    private List<ResearchDirectionPO> researchDirectionPOS;

    private List<AffiliationPO> affiliationPOS;

    @Before
    public void setUp(){
        researchDirectionLogicIMP = new ResearchDirectionLogicIMP();
        authorPOs = new ArrayList<>();
        authorPOs.add(new AuthorPO(0,"author1",55,"NJU","ML"));
        authorPOs.add(new AuthorPO(1,"author2",65,"PKU","DL"));
        authorPOs.add(new AuthorPO(2,"author3",29.5,"ISE","Java"));
        authorPOs.add(new AuthorPO(3,"author4",45,"Riot","Python"));
        authorPOs.add(new AuthorPO(4,"author5",55.4,"Value","JavaScript"));
        authorPOs.add(new AuthorPO(5,"author6",55.5,"Baidu","Big Data"));
        authorPOs.add(new AuthorPO(6,"author7",53.2,"Tencent","Data Mining"));
        authorPOs.add(new AuthorPO(7,"author8",99,"Alibaba","NLP"));
        authorPOs.add(new AuthorPO(8,"author9",45.2,"cocacola","CV"));
        authorPOs.add(new AuthorPO(9,"author10",11,"Google","Devops"));
        authorPOs.add(new AuthorPO(10,"author11",0,"Facebook","Test"));
        authorPOs.add(new AuthorPO(11,"author12",36,"Net Easy","Game"));

        paperPOS = new ArrayList<>();
        paperPOS.add(new PaperPO(0,"paper1","author1","2015","IEEE","NJU","paper","ML","link1",100,0));
        paperPOS.add(new PaperPO(1,"paper2","author1","2016","IEEE","NJU","paper","DL","link2",20,0));


        researchDirectionPOS = new ArrayList<>();
        researchDirectionPOS.add(new ResearchDirectionPO(0,"ML",1,100,"author1",55));


        affiliationPOS = new ArrayList<>();
        affiliationPOS.add(new AffiliationPO(0,"NJU",1,100,"author1",1,55));
    }

    @After
    public void tearDown(){
        researchDirectionLogicIMP = null;
        authorPOs = null;
        paperPOS = null;
        researchDirectionPOS = null;
        affiliationPOS = null;
    }

    @Test
    public void getReDirectionByName() {

        ResearchDirectionData researchDirectionData = mock(ResearchDirectionData.class);
        when(researchDirectionData.getResearchDirectionPOByName("ML")).thenReturn(researchDirectionPOS);

        researchDirectionLogicIMP.setResearchDirectionData(researchDirectionData);

        List<ResearchDirectionVO> researchDirectionVOs = researchDirectionLogicIMP.getReDirectionByName("ML");
        assertEquals(1,researchDirectionVOs.size());
        assertEquals(0,researchDirectionVOs.get(0).getId());
        assertEquals("ML",researchDirectionVOs.get(0).getName());
    }

    @Test
    public void getTopReDirections() {
        List<ResearchDirectionPO> researchDirections = new ArrayList<>(Arrays.asList(
                new ResearchDirectionPO(0,"",0,55,"author1",2015),
                new ResearchDirectionPO(1,"",1,66,"author2",2016)
        ));
        ResearchDirectionData researchDirectionData = mock(ResearchDirectionData.class);
        when(researchDirectionData.getTopReDirections()).thenReturn(researchDirections);
        researchDirectionLogicIMP.setResearchDirectionData(researchDirectionData);
        List<ResearchDirectionVO> researchDirectionVOS = researchDirectionLogicIMP.getTopReDirections();
        assertEquals(2,researchDirectionVOS.size());
        assertEquals(0, researchDirections.get(0).getId());
        assertEquals("",researchDirections.get(0).getName());
        assertEquals(2016,researchDirections.get(1).getRank(),1e-6);
    }

    @Test
    public void getAllPapersOfRe() {
        List<PaperPO> papers = new ArrayList<>();
        papers.add(paperPOS.get(0));
        papers.add(paperPOS.get(1));

        List<PaperVO> paperVOS = new ArrayList<>(Arrays.asList(
                new PaperVO(0,"paper1","author1","2015","IEEE","NJU","","ML","link1",55,0),
                new PaperVO(1,"paper2","author2","2015","IEEE","PKU","","DL","link2",25,0)
        ));
        ResearchDirectionData researchDirectionData = mock(ResearchDirectionData.class);
        PaperLogicForGetPaper paperLogicForGetPaper = mock(PaperLogicForGetPaper.class);
        when(researchDirectionData.getPaperIdByReId(0)).thenReturn(papers);
        when(paperLogicForGetPaper.getPaperById(0)).thenReturn(paperVOS.get(0));
        when(paperLogicForGetPaper.getPaperById(1)).thenReturn(paperVOS.get(1));

        researchDirectionLogicIMP.setResearchDirectionData(researchDirectionData);
        researchDirectionLogicIMP.setPaperLogicForGetPaper(paperLogicForGetPaper);

        List<PaperVO> list = researchDirectionLogicIMP.getAllPapersOfRe(0);
        assertEquals("author1", list.get(0).getAuthor());
        assertEquals("NJU",list.get(0).getAffiliation());
        assertEquals("2015",list.get(1).getPublishTime());
        assertEquals("PKU",list.get(1).getAffiliation());
    }

    @Test
    public void getPapersOfReByYear() {
        List<PaperPO> papers = new ArrayList<>();
        papers.add(paperPOS.get(0));
        papers.add(paperPOS.get(1));

        List<PaperVO> paperVOS = new ArrayList<>(Arrays.asList(
                new PaperVO(0,"paper1","author1","2015","IEEE","NJU","","ML","link1",55,0),
                new PaperVO(1,"paper2","author2","2015","IEEE","PKU","","DL","link2",25,0)
        ));
        ResearchDirectionData researchDirectionData = mock(ResearchDirectionData.class);
        PaperLogicForGetPaper paperLogicForGetPaper = mock(PaperLogicForGetPaper.class);
        when(researchDirectionData.getPaperIdByReIdAndPublishTime(0,"2015")).thenReturn(papers);
        when(paperLogicForGetPaper.getPaperById(0)).thenReturn(paperVOS.get(0));
        when(paperLogicForGetPaper.getPaperById(1)).thenReturn(paperVOS.get(1));

        researchDirectionLogicIMP.setResearchDirectionData(researchDirectionData);
        researchDirectionLogicIMP.setPaperLogicForGetPaper(paperLogicForGetPaper);

        List<PaperVO> list = researchDirectionLogicIMP.getPapersOfReByYear(0,"2015");
        assertEquals("author1", list.get(0).getAuthor());
        assertEquals("NJU",list.get(0).getAffiliation());
        assertEquals("2015",list.get(1).getPublishTime());
        assertEquals("PKU",list.get(1).getAffiliation());
    }

    @Test
    public void getPapersOfReByAuthor() {
        List<PaperPO> papers = new ArrayList<>();
        papers.add(paperPOS.get(0));
        papers.add(paperPOS.get(1));

        List<PaperVO> paperVOS = new ArrayList<>(Arrays.asList(
                new PaperVO(0,"paper1","author1","2015","IEEE","NJU","","ML","link1",55,0),
                new PaperVO(1,"paper2","author2","2015","IEEE","PKU","","DL","link2",25,0)
        ));
        ResearchDirectionData researchDirectionData = mock(ResearchDirectionData.class);
        PaperLogicForGetPaper paperLogicForGetPaper = mock(PaperLogicForGetPaper.class);
        when(researchDirectionData.getPaperIdByReIdAndAuthorId(0,0)).thenReturn(papers);
        when(paperLogicForGetPaper.getPaperById(0)).thenReturn(paperVOS.get(0));
        when(paperLogicForGetPaper.getPaperById(1)).thenReturn(paperVOS.get(1));

        researchDirectionLogicIMP.setResearchDirectionData(researchDirectionData);
        researchDirectionLogicIMP.setPaperLogicForGetPaper(paperLogicForGetPaper);

        List<PaperVO> list = researchDirectionLogicIMP.getPapersOfReByAuthor(0,0);
        assertEquals("author1", list.get(0).getAuthor());
        assertEquals("NJU",list.get(0).getAffiliation());
        assertEquals("2015",list.get(1).getPublishTime());
        assertEquals("PKU",list.get(1).getAffiliation());
    }

    @Test
    public void getPaperNumOfReByYear() {
        List<Integer> exp = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        ResearchDirectionData researchDirectionData = mock(ResearchDirectionData.class);
        for(int i =0;i< 10;i++){
            when(researchDirectionData.getRePaperNumByIdAndYear(0,String.valueOf(i+2011))).thenReturn(exp.get(i));
        }
        researchDirectionLogicIMP.setResearchDirectionData(researchDirectionData);
        List<Integer> list = researchDirectionLogicIMP.getPaperNumOfReByYear(0);
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
    public void getCitationNumOfReByYear() {
        List<ResearchDirection_Paper_PO> papers_2011 = new ArrayList<>(Arrays.asList(
                new ResearchDirection_Paper_PO(0,0,"paper1",10,2011),
                new ResearchDirection_Paper_PO(0,1,"paper2",50,2011)
        ));
        List<ResearchDirection_Paper_PO> papers_2012 = new ArrayList<>(Arrays.asList(
                new ResearchDirection_Paper_PO(0,0,"paper1",15,2012),
                new ResearchDirection_Paper_PO(0,1,"paper2",30,2012)
        ));
        List<ResearchDirection_Paper_PO> papers_2013 = new ArrayList<>(Arrays.asList(
                new ResearchDirection_Paper_PO(0,0,"paper1",50,2013),
                new ResearchDirection_Paper_PO(0,1,"paper2",30,2013)
        ));
        List<ResearchDirection_Paper_PO> papers_2014 = new ArrayList<>(Arrays.asList(
                new ResearchDirection_Paper_PO(0,0,"paper1",66,2014),
                new ResearchDirection_Paper_PO(0,1,"paper2",33,2014)
        ));
        List<ResearchDirection_Paper_PO> papers_2015 = new ArrayList<>(Arrays.asList(
                new ResearchDirection_Paper_PO(0,0,"paper1",77,2015),
                new ResearchDirection_Paper_PO(0,1,"paper2",11,2015)
        ));
        List<ResearchDirection_Paper_PO> papers_2016 = new ArrayList<>(Arrays.asList(
                new ResearchDirection_Paper_PO(0,0,"paper1",4,2016),
                new ResearchDirection_Paper_PO(0,1,"paper2",6,2016)
        ));
        List<ResearchDirection_Paper_PO> papers_2017 = new ArrayList<>(Arrays.asList(
                new ResearchDirection_Paper_PO(0,0,"paper1",9,2017),
                new ResearchDirection_Paper_PO(0,1,"paper2",10,2017)
        ));
        List<ResearchDirection_Paper_PO> papers_2018 = new ArrayList<>(Arrays.asList(
                new ResearchDirection_Paper_PO(0,0,"paper1",5,2018),
                new ResearchDirection_Paper_PO(0,1,"paper2",3,2018)
        ));
        List<ResearchDirection_Paper_PO> papers_2019 = new ArrayList<>(Arrays.asList(
                new ResearchDirection_Paper_PO(0,0,"paper1",99,2019),
                new ResearchDirection_Paper_PO(0,1,"paper2",0,2019)
        ));
        List<ResearchDirection_Paper_PO> papers_2020 = new ArrayList<>(Arrays.asList(
                new ResearchDirection_Paper_PO(0,0,"paper1",120,2020),
                new ResearchDirection_Paper_PO(0,1,"paper2",30,2020)
        ));
        ResearchDirectionData researchDirectionData = mock(ResearchDirectionData.class);
        when(researchDirectionData.getRePaperByIdAndYear(0,"2011")).thenReturn(papers_2011);
        when(researchDirectionData.getRePaperByIdAndYear(0,"2012")).thenReturn(papers_2012);
        when(researchDirectionData.getRePaperByIdAndYear(0,"2013")).thenReturn(papers_2013);
        when(researchDirectionData.getRePaperByIdAndYear(0,"2014")).thenReturn(papers_2014);
        when(researchDirectionData.getRePaperByIdAndYear(0,"2015")).thenReturn(papers_2015);
        when(researchDirectionData.getRePaperByIdAndYear(0,"2016")).thenReturn(papers_2016);
        when(researchDirectionData.getRePaperByIdAndYear(0,"2017")).thenReturn(papers_2017);
        when(researchDirectionData.getRePaperByIdAndYear(0,"2018")).thenReturn(papers_2018);
        when(researchDirectionData.getRePaperByIdAndYear(0,"2019")).thenReturn(papers_2019);
        when(researchDirectionData.getRePaperByIdAndYear(0,"2020")).thenReturn(papers_2020);

        researchDirectionLogicIMP.setResearchDirectionData(researchDirectionData);

        List<Integer> list = researchDirectionLogicIMP.getCitationNumOfReByYear(0);
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
    public void getReTopAuthor() {
        ResearchDirectionData researchDirectionData = mock(ResearchDirectionData.class);
        AuthorLogicForGetAuthor authorLogicForGetAuthor = mock(AuthorLogicForGetAuthor.class);
        when(researchDirectionData.getTopReAuthors(0)).thenReturn(authorPOs);
        when(authorLogicForGetAuthor.getAuthorById(0)).thenReturn(
                new AuthorVO(0,"author1","NJU","ML",1,65)
        );
        when(authorLogicForGetAuthor.getAuthorById(1)).thenReturn(
                new AuthorVO(1,"author2","PKU","DL",1,35)
        );
        researchDirectionLogicIMP.setResearchDirectionData(researchDirectionData);
        researchDirectionLogicIMP.setAuthorLogicForGetAuthor(authorLogicForGetAuthor);
        List<AuthorVO> list = researchDirectionLogicIMP.getReTopAuthor(0);
        assertEquals(0,list.get(0).getId());
        assertEquals("author2",list.get(1).getName());
    }

    @Test
    public void getReAuthor() {
        List<AuthorVO> authorVOS = new ArrayList<>(Arrays.asList(
                new AuthorVO(0,"author1","NJU","ML",55,20),
                new AuthorVO(1,"author2","PKU","DL",65,30)
        ));
        ResearchDirectionData researchDirectionData = mock(ResearchDirectionData.class);
        AuthorLogicForGetAuthor authorLogicForGetAuthor = mock(AuthorLogicForGetAuthor.class);
        when(researchDirectionData.getReAuthorsById(0)).thenReturn(authorVOS);
        when(authorLogicForGetAuthor.getAuthorById(0)).thenReturn(
                new AuthorVO(0,"author1","NJU","ML",1,65)
        );
        when(authorLogicForGetAuthor.getAuthorById(1)).thenReturn(
                new AuthorVO(1,"author2","PKU","DL",1,35)
        );
        researchDirectionLogicIMP.setResearchDirectionData(researchDirectionData);
        researchDirectionLogicIMP.setAuthorLogicForGetAuthor(authorLogicForGetAuthor);
        List<AuthorVO> list = researchDirectionLogicIMP.getReAuthor(0);
        assertEquals("ML",list.get(0).getResearchDirection());
        assertEquals("PKU",list.get(1).getInstitution());
    }

    @Test
    public void getResearchDirectionById() {

        ResearchDirectionData researchDirectionData = mock(ResearchDirectionData.class);
        when(researchDirectionData.getDirectionAuthorById(0)).thenReturn(researchDirectionPOS);
        when(researchDirectionData.getResearchDirectionPaperById(0)).thenReturn(paperPOS);

        researchDirectionLogicIMP.setResearchDirectionData(researchDirectionData);

        ResearchDirectionVO researchDirectionVOs = researchDirectionLogicIMP.getResearchDirectionById(0);
        assertEquals("author1 ",researchDirectionVOs.getAuthors());
        assertEquals(2,researchDirectionVOs.getPaper_number());
        assertEquals(120,researchDirectionVOs.getCitation_count());
    }

    @Test
    public void getResearchDirectionTopAffiliationById() {
        ResearchDirectionData researchDirectionData = mock(ResearchDirectionData.class);
        when(researchDirectionData.getResearchDirectionTopAffiliation(0)).thenReturn(affiliationPOS);
        researchDirectionLogicIMP.setResearchDirectionData(researchDirectionData);
        List<AffiliationVO> affiliationVOS = researchDirectionLogicIMP.getResearchDirectionTopAffiliationById(0);
        assertEquals(1,affiliationVOS.size());
        assertEquals(1,affiliationVOS.get(0).getPaper_number());
    }
}