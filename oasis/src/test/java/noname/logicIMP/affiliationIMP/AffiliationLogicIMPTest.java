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
import noname.logicIMP.authorIMP.AuthorLogicIMP;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AffiliationLogicIMPTest {

    private AffiliationLogicIMP affiliationLogicIMP;

    private List<AuthorPO> authorPOs;

    private List<PaperPO> paperPOS;

    private List<ResearchDirectionPO> researchDirectionPOS;

    private List<AffiliationPO> affiliationPOS;

    @Before
    public void setUp(){
        affiliationLogicIMP = new AffiliationLogicIMP();
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
        affiliationLogicIMP = null;
        authorPOs = null;
        paperPOS = null;
        researchDirectionPOS = null;
        affiliationPOS = null;
    }

    @Test
    public void getAffiliationByName() {
        List<AffiliationPO> affs = new ArrayList<>();
        affs.add(affiliationPOS.get(0));

        AffiliationData affiliationData = mock(AffiliationData.class);
        when(affiliationData.getAffiliationByName("NJU")).thenReturn(affs);

        affiliationLogicIMP.setAffiliationData(affiliationData);

        AffiliationVO affiliationVO = affiliationLogicIMP.getAffiliationByName("NJU").get(0);
        assertEquals(0,affiliationVO.getId());
        assertEquals("NJU",affiliationVO.getName());
    }

    @Test
    public void getPaperNumOfAffByYear() {
        List<Integer> exp = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        AffiliationData affiliationData = mock(AffiliationData.class);
        for(int i =0;i< 10;i++){
            when(affiliationData.getAffiliationPaperNumByIdAndYear(0,String.valueOf(i+2011))).thenReturn(exp.get(i));
        }
        affiliationLogicIMP.setAffiliationData(affiliationData);
        List<Integer> list = affiliationLogicIMP.getPaperNumOfAffByYear(0);
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
    public void getCitationNumOfAffByYear() {
        List<Affiliation_Paper_PO> papers_2011 = new ArrayList<>(Arrays.asList(
                new Affiliation_Paper_PO(0,0,"paper1",10,2011),
                new Affiliation_Paper_PO(0,1,"paper2",50,2011)
        ));
        List<Affiliation_Paper_PO> papers_2012 = new ArrayList<>(Arrays.asList(
                new Affiliation_Paper_PO(0,0,"paper1",15,2012),
                new Affiliation_Paper_PO(0,1,"paper2",30,2012)
        ));
        List<Affiliation_Paper_PO> papers_2013 = new ArrayList<>(Arrays.asList(
                new Affiliation_Paper_PO(0,0,"paper1",50,2013),
                new Affiliation_Paper_PO(0,1,"paper2",30,2013)
        ));
        List<Affiliation_Paper_PO> papers_2014 = new ArrayList<>(Arrays.asList(
                new Affiliation_Paper_PO(0,0,"paper1",66,2014),
                new Affiliation_Paper_PO(0,1,"paper2",33,2014)
        ));
        List<Affiliation_Paper_PO> papers_2015 = new ArrayList<>(Arrays.asList(
                new Affiliation_Paper_PO(0,0,"paper1",77,2015),
                new Affiliation_Paper_PO(0,1,"paper2",11,2015)
        ));
        List<Affiliation_Paper_PO> papers_2016 = new ArrayList<>(Arrays.asList(
                new Affiliation_Paper_PO(0,0,"paper1",4,2016),
                new Affiliation_Paper_PO(0,1,"paper2",6,2016)
        ));
        List<Affiliation_Paper_PO> papers_2017 = new ArrayList<>(Arrays.asList(
                new Affiliation_Paper_PO(0,0,"paper1",9,2017),
                new Affiliation_Paper_PO(0,1,"paper2",10,2017)
        ));
        List<Affiliation_Paper_PO> papers_2018 = new ArrayList<>(Arrays.asList(
                new Affiliation_Paper_PO(0,0,"paper1",5,2018),
                new Affiliation_Paper_PO(0,1,"paper2",3,2018)
        ));
        List<Affiliation_Paper_PO> papers_2019 = new ArrayList<>(Arrays.asList(
                new Affiliation_Paper_PO(0,0,"paper1",99,2019),
                new Affiliation_Paper_PO(0,1,"paper2",0,2019)
        ));
        List<Affiliation_Paper_PO> papers_2020 = new ArrayList<>(Arrays.asList(
                new Affiliation_Paper_PO(0,0,"paper1",120,2020),
                new Affiliation_Paper_PO(0,1,"paper2",30,2020)
        ));
        AffiliationData affiliationData = mock(AffiliationData.class);
        when(affiliationData.getAffiliationPaperByIdAndYear(0,"2011")).thenReturn(papers_2011);
        when(affiliationData.getAffiliationPaperByIdAndYear(0,"2012")).thenReturn(papers_2012);
        when(affiliationData.getAffiliationPaperByIdAndYear(0,"2013")).thenReturn(papers_2013);
        when(affiliationData.getAffiliationPaperByIdAndYear(0,"2014")).thenReturn(papers_2014);
        when(affiliationData.getAffiliationPaperByIdAndYear(0,"2015")).thenReturn(papers_2015);
        when(affiliationData.getAffiliationPaperByIdAndYear(0,"2016")).thenReturn(papers_2016);
        when(affiliationData.getAffiliationPaperByIdAndYear(0,"2017")).thenReturn(papers_2017);
        when(affiliationData.getAffiliationPaperByIdAndYear(0,"2018")).thenReturn(papers_2018);
        when(affiliationData.getAffiliationPaperByIdAndYear(0,"2019")).thenReturn(papers_2019);
        when(affiliationData.getAffiliationPaperByIdAndYear(0,"2020")).thenReturn(papers_2020);

        affiliationLogicIMP.setAffiliationData(affiliationData);

        List<Integer> list = affiliationLogicIMP.getCitationNumOfAffByYear(0);
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
    public void getTopAffiliations() {
        AffiliationData affiliationData = mock(AffiliationData.class);
        when(affiliationData.getTopAffiliations()).thenReturn(affiliationPOS);
        affiliationLogicIMP.setAffiliationData(affiliationData);
        List<AffiliationVO> affiliationVOS = affiliationLogicIMP.getTopAffiliations();
        assertEquals(0, affiliationVOS.get(0).getId());
        assertEquals("NJU",affiliationVOS.get(0).getName());
        assertEquals(55,affiliationVOS.get(0).getRank(),1e-6);
    }

    @Test
    public void getPapersOfAffByAuthor() {
        List<PaperPO> papers = new ArrayList<>();
        papers.add(paperPOS.get(0));
        papers.add(paperPOS.get(1));

        List<PaperVO> paperVOS = new ArrayList<>(Arrays.asList(
                new PaperVO(0,"paper1","author1","2015","IEEE","NJU","","ML","link1",55,0),
                new PaperVO(1,"paper2","author2","2015","IEEE","PKU","","DL","link2",25,0)
        ));
        AffiliationData affiliationData = mock(AffiliationData.class);
        PaperLogicForGetPaper paperLogicForGetPaper = mock(PaperLogicForGetPaper.class);
        when(affiliationData.getPaperIdByAffiliationIdAndAuthorId(0,0)).thenReturn(papers);
        when(paperLogicForGetPaper.getPaperById(0)).thenReturn(paperVOS.get(0));
        when(paperLogicForGetPaper.getPaperById(1)).thenReturn(paperVOS.get(1));

        affiliationLogicIMP.setAffiliationData(affiliationData);
        affiliationLogicIMP.setPaperLogicForGetPaper(paperLogicForGetPaper);

        List<PaperVO> list = affiliationLogicIMP.getPapersOfAffByAuthor(0,0);
        assertEquals("author1", list.get(0).getAuthor());
        assertEquals("NJU",list.get(0).getAffiliation());
        assertEquals("2015",list.get(1).getPublishTime());
        assertEquals("PKU",list.get(1).getAffiliation());
    }

    @Test
    public void getPapersOfAffByYear() {
        List<PaperPO> papers = new ArrayList<>();
        papers.add(paperPOS.get(0));
        papers.add(paperPOS.get(1));

        List<PaperVO> paperVOS = new ArrayList<>(Arrays.asList(
                new PaperVO(0,"paper1","author1","2015","IEEE","NJU","","ML","link1",55,0),
                new PaperVO(1,"paper2","author2","2015","IEEE","PKU","","DL","link2",25,0)
        ));
        AffiliationData affiliationData = mock(AffiliationData.class);
        PaperLogicForGetPaper paperLogicForGetPaper = mock(PaperLogicForGetPaper.class);
        when(affiliationData.getPaperIdByAffiliationIdAndYear(0,"2015")).thenReturn(papers);
        when(paperLogicForGetPaper.getPaperById(0)).thenReturn(paperVOS.get(0));
        when(paperLogicForGetPaper.getPaperById(1)).thenReturn(paperVOS.get(1));

        affiliationLogicIMP.setAffiliationData(affiliationData);
        affiliationLogicIMP.setPaperLogicForGetPaper(paperLogicForGetPaper);

        List<PaperVO> list = affiliationLogicIMP.getPapersOfAffByYear(0,"2015");
        assertEquals("author1", list.get(0).getAuthor());
        assertEquals("NJU",list.get(0).getAffiliation());
        assertEquals("2015",list.get(1).getPublishTime());
        assertEquals("PKU",list.get(1).getAffiliation());
    }

    @Test
    public void getAllPapersOfAff() {
        List<PaperPO> papers = new ArrayList<>();
        papers.add(paperPOS.get(0));
        papers.add(paperPOS.get(1));

        List<PaperVO> paperVOS = new ArrayList<>(Arrays.asList(
                new PaperVO(0,"paper1","author1","2015","IEEE","NJU","","ML","link1",55,0),
                new PaperVO(1,"paper2","author2","2015","IEEE","PKU","","DL","link2",25,0)
        ));
        AffiliationData affiliationData = mock(AffiliationData.class);
        PaperLogicForGetPaper paperLogicForGetPaper = mock(PaperLogicForGetPaper.class);
        when(affiliationData.getPaperIdByAffiliationId(0)).thenReturn(papers);
        when(paperLogicForGetPaper.getPaperById(0)).thenReturn(paperVOS.get(0));
        when(paperLogicForGetPaper.getPaperById(1)).thenReturn(paperVOS.get(1));

        affiliationLogicIMP.setAffiliationData(affiliationData);
        affiliationLogicIMP.setPaperLogicForGetPaper(paperLogicForGetPaper);

        List<PaperVO> list = affiliationLogicIMP.getAllPapersOfAff(0);
        assertEquals("author1", list.get(0).getAuthor());
        assertEquals("NJU",list.get(0).getAffiliation());
        assertEquals("2015",list.get(1).getPublishTime());
        assertEquals("PKU",list.get(1).getAffiliation());
    }

    @Test
    public void getAuthorsOfAff() {

        AffiliationData affiliationData = mock(AffiliationData.class);
        when(affiliationData.getAuthorsOfAffById(0)).thenReturn(authorPOs);
        affiliationLogicIMP.setAffiliationData(affiliationData);
        List<AuthorVO> list = affiliationLogicIMP.getAuthorsOfAff(0);
        assertEquals("author1",list.get(0).getName());
        assertEquals("author2",list.get(1).getName());
    }

    @Test
    public void getAffiliationById() {
        List<AuthorPO> authors = new ArrayList<>();
        authors.add(authorPOs.get(0));
        List<AffiliationPO> affs = new ArrayList<>();
        affs.add(affiliationPOS.get(0));
        List<Affiliation_Paper_PO> affiliationPaperPos = new ArrayList<>(Arrays.asList(
                new Affiliation_Paper_PO(0,0,"",55,2015),
                new Affiliation_Paper_PO(0,1,"",66,2016)
        ));

        AffiliationData affiliationData = mock(AffiliationData.class);
        when(affiliationData.getAffiliationAuthorById(0)).thenReturn(affs);
        when(affiliationData.getAuthorsOfAffById(0)).thenReturn(authorPOs);
        when(affiliationData.getAuthorDirectionOfAffiliation(0)).thenReturn(authors);
        when(affiliationData.getAuthorDirectionOfAffiliation(1)).thenReturn(authors);
        when(affiliationData.getAffiliationPaperById(0)).thenReturn(affiliationPaperPos);

        affiliationLogicIMP.setAffiliationData(affiliationData);

        AffiliationVO affiliationVO = affiliationLogicIMP.getAffiliationById(0);
        assertEquals(0,affiliationVO.getId());
        assertEquals("NJU",affiliationVO.getName());
        assertEquals("ML;",affiliationVO.getReDirection());
        assertEquals(55, affiliationVO.getRank(),1e-6);
        assertEquals(2,affiliationVO.getPaper_number());
        assertEquals(121,affiliationVO.getCitation_count());
        assertEquals("author1 ",affiliationVO.getAuthors());
    }

    @Test
    public void getAffiliationTopResearchDirectionById() {
        AffiliationData affiliationData = mock(AffiliationData.class);
        when(affiliationData.getAffiliationTopDirectionsById(0)).thenReturn(researchDirectionPOS);
        affiliationLogicIMP.setAffiliationData(affiliationData);
        List<ResearchDirectionVO> researchDirectionVOS = affiliationLogicIMP.getAffiliationTopResearchDirectionById(0);
        assertEquals(1,researchDirectionVOS.size());
        assertEquals("ML",researchDirectionVOS.get(0).getName());
    }
}