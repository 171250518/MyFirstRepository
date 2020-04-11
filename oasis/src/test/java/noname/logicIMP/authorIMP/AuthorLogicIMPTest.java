package noname.logicIMP.authorIMP;

import noname.PO.*;
import noname.VO.AuthorVO;
import noname.VO.PaperVO;
import noname.data.author.AuthorData;
import noname.logic.author.AuthorLogic;
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

public class AuthorLogicIMPTest {

    private AuthorLogicIMP authorLogicIMP;

    private List<AuthorPO> authorPOs;

    private List<PaperPO> paperPOS;

    private List<ResearchDirectionPO> researchDirectionPOS;

    private List<AffiliationPO> affiliationPOS;

    @Before
    public void setUp(){
        authorLogicIMP = new AuthorLogicIMP();
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
        authorLogicIMP = null;
        authorPOs = null;
        paperPOS = null;
        researchDirectionPOS = null;
        affiliationPOS = null;
    }

    @Test
    public void getAuthorByName() {
        List<AuthorPO> authors = new ArrayList<>();
        authors.add(authorPOs.get(0));

        AuthorData authorData = mock(AuthorData.class);
        when(authorData.getAuthorPOByName("author1")).thenReturn(authors);

        authorLogicIMP.setAuthorData(authorData);

        AuthorVO author = authorLogicIMP.getAuthorByName("author1").get(0);
        assertEquals(0,author.getId());
        assertEquals("author1",author.getName());
    }

    @Test
    public void getTopAuthors() {
        AuthorData authorData = mock(AuthorData.class);
        when(authorData.getTopAuthors()).thenReturn(authorPOs);
        authorLogicIMP.setAuthorData(authorData);
        List<AuthorVO> authorVOS = authorLogicIMP.getTopAuthors();
        assertEquals(0, authorVOS.get(0).getId());
        assertEquals(65,authorVOS.get(1).getRank(), 1e-6);
        assertEquals("author4",authorVOS.get(3).getName());
    }

    @Test
    public void getAllPapersOfAuthor() {
        List<PaperPO> papers = new ArrayList<>();
        papers.add(paperPOS.get(0));
        papers.add(paperPOS.get(1));

        AuthorData authorData = mock(AuthorData.class);
        when(authorData.getPaperIdByAuthorId(0)).thenReturn(papers);

        authorLogicIMP.setAuthorData(authorData);

        List<PaperVO> list = authorLogicIMP.getAllPapersOfAuthor(0);
        assertEquals("link1", list.get(0).getLink());
        assertEquals("paper1",list.get(0).getTitle());
        assertEquals("2016",list.get(1).getPublishTime());
    }

    @Test
    public void getPapersOfAuthorByYear() {
        List<AuthorPO> authors = new ArrayList<>();
        authors.add(authorPOs.get(0));
        authors.add(authorPOs.get(1));
        List<PaperPO> papers = new ArrayList<>();
        papers.add(paperPOS.get(0));
        papers.add(paperPOS.get(1));
        List<PaperPO> papers1 = new ArrayList<>();
        papers1.add(paperPOS.get(1));
        List<PaperPO> papers2 = new ArrayList<>();
        papers2.add(paperPOS.get(1));
        List<ResearchDirectionPO> res = new ArrayList<>();
        res.add(researchDirectionPOS.get(0));
        List<AffiliationPO> affs = new ArrayList<>();
        affs.add(affiliationPOS.get(0));

        AuthorData authorData = mock(AuthorData.class);
        when(authorData.getPaperIdByAuthorIdAndPublishTime(0, "0")).thenReturn(papers);
        when(authorData.getPaperBasicById(0)).thenReturn(papers1);
        when(authorData.getPaperAuthorById(0)).thenReturn(authors);
        when(authorData.getPaperDirectionById(0)).thenReturn(res);
        when(authorData.getPaperAffiliationById(0)).thenReturn(affs);
        when(authorData.getPaperBasicById(1)).thenReturn(papers2);
        when(authorData.getPaperAuthorById(1)).thenReturn(authors);
        when(authorData.getPaperDirectionById(1)).thenReturn(res);
        when(authorData.getPaperAffiliationById(1)).thenReturn(affs);

        authorLogicIMP.setAuthorData(authorData);

        List<PaperVO> list = authorLogicIMP.getPapersOfAuthorByYear(0,"0");
        assertEquals("author1 author2 ", list.get(0).getAuthor());
        assertEquals("NJU ",list.get(0).getAffiliation());
        assertEquals("2016",list.get(1).getPublishTime());
    }

    @Test
    public void getPapersOfAuthorByConf() {
        List<AuthorPO> authors = new ArrayList<>();
        authors.add(authorPOs.get(0));
        authors.add(authorPOs.get(1));
        List<PaperPO> papers = new ArrayList<>();
        papers.add(paperPOS.get(0));
        papers.add(paperPOS.get(1));
        List<PaperPO> papers1 = new ArrayList<>();
        papers1.add(paperPOS.get(1));
        List<PaperPO> papers2 = new ArrayList<>();
        papers2.add(paperPOS.get(1));
        List<ResearchDirectionPO> res = new ArrayList<>();
        res.add(researchDirectionPOS.get(0));
        List<AffiliationPO> affs = new ArrayList<>();
        affs.add(affiliationPOS.get(0));

        AuthorData authorData = mock(AuthorData.class);
        when(authorData.getPaperIdByAuthorIdAndConference(0, "0")).thenReturn(papers);
        when(authorData.getPaperBasicById(0)).thenReturn(papers1);
        when(authorData.getPaperAuthorById(0)).thenReturn(authors);
        when(authorData.getPaperDirectionById(0)).thenReturn(res);
        when(authorData.getPaperAffiliationById(0)).thenReturn(affs);
        when(authorData.getPaperBasicById(1)).thenReturn(papers2);
        when(authorData.getPaperAuthorById(1)).thenReturn(authors);
        when(authorData.getPaperDirectionById(1)).thenReturn(res);
        when(authorData.getPaperAffiliationById(1)).thenReturn(affs);

        authorLogicIMP.setAuthorData(authorData);

        List<PaperVO> list = authorLogicIMP.getPapersOfAuthorByConf(0,"0");
        assertEquals("author1 author2 ", list.get(0).getAuthor());
        assertEquals("NJU ",list.get(0).getAffiliation());
        assertEquals("2016",list.get(1).getPublishTime());
    }

    @Test
    public void getPapersOfAuthorByCoWorker() {
        List<AuthorPO> authors = new ArrayList<>();
        authors.add(authorPOs.get(0));
        authors.add(authorPOs.get(1));
        List<PaperPO> papers = new ArrayList<>();
        papers.add(paperPOS.get(0));
        papers.add(paperPOS.get(1));
        List<PaperPO> papers1 = new ArrayList<>();
        papers1.add(paperPOS.get(1));
        List<PaperPO> papers2 = new ArrayList<>();
        papers2.add(paperPOS.get(1));
        List<ResearchDirectionPO> res = new ArrayList<>();
        res.add(researchDirectionPOS.get(0));
        List<AffiliationPO> affs = new ArrayList<>();
        affs.add(affiliationPOS.get(0));

        AuthorData authorData = mock(AuthorData.class);
        when(authorData.getPaperIdByAuthorIdAndCoAuthor(0, 1)).thenReturn(papers);
        when(authorData.getPaperBasicById(0)).thenReturn(papers1);
        when(authorData.getPaperAuthorById(0)).thenReturn(authors);
        when(authorData.getPaperDirectionById(0)).thenReturn(res);
        when(authorData.getPaperAffiliationById(0)).thenReturn(affs);
        when(authorData.getPaperBasicById(1)).thenReturn(papers2);
        when(authorData.getPaperAuthorById(1)).thenReturn(authors);
        when(authorData.getPaperDirectionById(1)).thenReturn(res);
        when(authorData.getPaperAffiliationById(1)).thenReturn(affs);

        authorLogicIMP.setAuthorData(authorData);

        List<PaperVO> list = authorLogicIMP.getPapersOfAuthorByCoWorker(0,1);
        assertEquals("author1 author2 ", list.get(0).getAuthor());
        assertEquals("NJU ",list.get(0).getAffiliation());
        assertEquals("2016",list.get(1).getPublishTime());
    }

    @Test
    public void getPaperNumOfAuByYear() {
        List<Integer> exp = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        AuthorData authorData = mock(AuthorData.class);
        for(int i =0;i< 10;i++){
            when(authorData.getAuthorPaperNumByIdAndYear(0,String.valueOf(i+2011))).thenReturn(exp.get(i));
        }
        authorLogicIMP.setAuthorData(authorData);
        List<Integer> list = authorLogicIMP.getPaperNumOfAuByYear(0);
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
    public void getCitationNumOfAuByYear() {
        List<Author_Paper_PO> papers_2011 = new ArrayList<>(Arrays.asList(
                new Author_Paper_PO(0,0,"paper1",10,2011),
                new Author_Paper_PO(0,1,"paper2",50,2011)
        ));
        List<Author_Paper_PO> papers_2012 = new ArrayList<>(Arrays.asList(
                new Author_Paper_PO(0,0,"paper1",15,2012),
                new Author_Paper_PO(0,1,"paper2",30,2012)
        ));
        List<Author_Paper_PO> papers_2013 = new ArrayList<>(Arrays.asList(
                new Author_Paper_PO(0,0,"paper1",50,2013),
                new Author_Paper_PO(0,1,"paper2",30,2013)
        ));
        List<Author_Paper_PO> papers_2014 = new ArrayList<>(Arrays.asList(
                new Author_Paper_PO(0,0,"paper1",66,2014),
                new Author_Paper_PO(0,1,"paper2",33,2014)
        ));
        List<Author_Paper_PO> papers_2015 = new ArrayList<>(Arrays.asList(
                new Author_Paper_PO(0,0,"paper1",77,2015),
                new Author_Paper_PO(0,1,"paper2",11,2015)
        ));
        List<Author_Paper_PO> papers_2016 = new ArrayList<>(Arrays.asList(
                new Author_Paper_PO(0,0,"paper1",4,2016),
                new Author_Paper_PO(0,1,"paper2",6,2016)
        ));
        List<Author_Paper_PO> papers_2017 = new ArrayList<>(Arrays.asList(
                new Author_Paper_PO(0,0,"paper1",9,2017),
                new Author_Paper_PO(0,1,"paper2",10,2017)
        ));
        List<Author_Paper_PO> papers_2018 = new ArrayList<>(Arrays.asList(
                new Author_Paper_PO(0,0,"paper1",5,2018),
                new Author_Paper_PO(0,1,"paper2",3,2018)
        ));
        List<Author_Paper_PO> papers_2019 = new ArrayList<>(Arrays.asList(
                new Author_Paper_PO(0,0,"paper1",99,2019),
                new Author_Paper_PO(0,1,"paper2",0,2019)
        ));
        List<Author_Paper_PO> papers_2020 = new ArrayList<>(Arrays.asList(
                new Author_Paper_PO(0,0,"paper1",120,2020),
                new Author_Paper_PO(0,1,"paper2",30,2020)
        ));
        AuthorData authorData = mock(AuthorData.class);
        when(authorData.getAuthorPaperByIdAndYear(0,"2011")).thenReturn(papers_2011);
        when(authorData.getAuthorPaperByIdAndYear(0,"2012")).thenReturn(papers_2012);
        when(authorData.getAuthorPaperByIdAndYear(0,"2013")).thenReturn(papers_2013);
        when(authorData.getAuthorPaperByIdAndYear(0,"2014")).thenReturn(papers_2014);
        when(authorData.getAuthorPaperByIdAndYear(0,"2015")).thenReturn(papers_2015);
        when(authorData.getAuthorPaperByIdAndYear(0,"2016")).thenReturn(papers_2016);
        when(authorData.getAuthorPaperByIdAndYear(0,"2017")).thenReturn(papers_2017);
        when(authorData.getAuthorPaperByIdAndYear(0,"2018")).thenReturn(papers_2018);
        when(authorData.getAuthorPaperByIdAndYear(0,"2019")).thenReturn(papers_2019);
        when(authorData.getAuthorPaperByIdAndYear(0,"2020")).thenReturn(papers_2020);

        authorLogicIMP.setAuthorData(authorData);

        List<Integer> list = authorLogicIMP.getCitationNumOfAuByYear(0);
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
    public void getAuthorById() {
        List<AuthorPO> authors = new ArrayList<>();
        authors.add(authorPOs.get(0));
        List<PaperPO> papers = new ArrayList<>();
        papers.add(paperPOS.get(0));
        List<ResearchDirectionPO> res = new ArrayList<>();
        res.add(researchDirectionPOS.get(0));
        List<AffiliationPO> affs = new ArrayList<>();
        affs.add(affiliationPOS.get(0));

        AuthorData authorData = mock(AuthorData.class);
        when(authorData.getAuthorAffiliationById(0)).thenReturn(authors);
        when(authorData.getAuthorDirectionById(0)).thenReturn(authors);
        when(authorData.getPaperIdByAuthorId(0)).thenReturn(papers);
        when(authorData.getPaperBasicById(0)).thenReturn(papers);
        when(authorData.getPaperAuthorById(0)).thenReturn(authors);
        when(authorData.getPaperDirectionById(0)).thenReturn(res);
        when(authorData.getPaperAffiliationById(0)).thenReturn(affs);

        authorLogicIMP.setAuthorData(authorData);

        AuthorVO author = authorLogicIMP.getAuthorById(0);
        assertEquals(0,author.getId());
        assertEquals("author1",author.getName());
        assertEquals("ML;",author.getResearchDirection());
        assertEquals("NJU ",author.getInstitution());
        assertEquals(55, author.getRank(),1e-6);
        assertEquals(1,author.getPaper_num());
        assertEquals(100,author.getC_count());
    }

    @Test
    public void getCoAuthorById() {
        List<AuthorVO> authorVOS = new ArrayList<>(Arrays.asList(
                new AuthorVO(0,"author1","","",0,0),
                new AuthorVO(1,"","","",0,0)
        ));
        
        AuthorData authorData = mock(AuthorData.class);
        when(authorData.getCoAuthorIdById(0)).thenReturn(authorVOS);
        
        authorLogicIMP.setAuthorData(authorData);
        
        List<AuthorVO> coAuthors = authorLogicIMP.getCoAuthorById(0);
        assertEquals(2,coAuthors.size());
        assertEquals("author1",coAuthors.get(0).getName());
    }

    @Test
    public void getPaperById() {
        List<AuthorPO> authors = new ArrayList<>();
        authors.add(authorPOs.get(0));
        List<PaperPO> papers = new ArrayList<>();
        papers.add(paperPOS.get(0));
        List<ResearchDirectionPO> res = new ArrayList<>();
        res.add(researchDirectionPOS.get(0));
        List<AffiliationPO> affs = new ArrayList<>();
        affs.add(affiliationPOS.get(0));

        AuthorData authorData = mock(AuthorData.class);
        when(authorData.getPaperBasicById(0)).thenReturn(papers);
        when(authorData.getPaperAuthorById(0)).thenReturn(authors);
        when(authorData.getPaperDirectionById(0)).thenReturn(res);
        when(authorData.getPaperAffiliationById(0)).thenReturn(affs);

        authorLogicIMP.setAuthorData(authorData);

        PaperVO paperVO = authorLogicIMP.getPaperById(0);
        assertEquals("author1 ",paperVO.getAuthor());
    }

}