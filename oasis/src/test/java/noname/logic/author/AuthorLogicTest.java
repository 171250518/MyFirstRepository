package noname.logic.author;

import noname.Application;
import noname.PO.AuthorPO;
import noname.VO.AuthorVO;
import noname.VO.PaperVO;
import noname.VO.Response;
import noname.data.author.AuthorData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
/*
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)*/
public class AuthorLogicTest {
/*

    @Autowired
    private AuthorLogic authorLogic;

    @Test
    public void getAuthorByName() {
        List<AuthorVO> authors = authorLogic.getAuthorByName("author2");
        assertEquals("author2", authors.get(0).getName());
        assertEquals("dir2 dir3 ",authors.get(0).getResearchDirection());
        assertEquals("aff2 aff3 ", authors.get(0).getInstitution());
    }

    @Test
    public void getTopAuthors() {
        List<AuthorVO> authors = authorLogic.getTopAuthors();
        assertEquals(10,authors.size());
        assertEquals(15,authors.get(0).getRank(),1e-6);
        assertEquals("author7", authors.get(1).getName());
    }

    @Test
    public void getAllPapersOfAuthor() {
        List<PaperVO> papers = authorLogic.getAllPapersOfAuthor(2);
        assertEquals(4, papers.size());
    }

    @Test
    public void getPapersOfAuthorByYear() {
        List<PaperVO> papers = authorLogic.getPapersOfAuthorByYear(2,"2019");
        assertEquals(2, papers.size());
        assertEquals("aff1 aff3 ",papers.get(0).getAffiliation());
        assertEquals("aff2 aff3 ",papers.get(1).getAffiliation());
        assertEquals("dir1 dir2 dir3 ",papers.get(0).getKeywords());
    }

    @Test
    public void getPapersOfAuthorByConf() {
        List<PaperVO> papers = authorLogic.getPapersOfAuthorByConf(2,"2");
        assertEquals(1, papers.size());
        assertEquals("aff2 aff3 ",papers.get(0).getAffiliation());
        assertEquals("dir1 dir2 ",papers.get(0).getKeywords());
    }

    @Test
    public void getPapersOfAuthorByCoWorker() {
        List<PaperVO> papers = authorLogic.getPapersOfAuthorByCoWorker(2,1);
        assertEquals(2, papers.size());
        assertEquals(1,papers.get(0).getId());
        assertEquals(6,papers.get(1).getId());
    }

    @Test
    public void getAuthorById() {
        AuthorVO author = authorLogic.getAuthorById(2);
        assertEquals("author2",author.getName());
        assertEquals("dir2 dir3 ",author.getResearchDirection());
        assertEquals("aff2 aff3 ",author.getInstitution());
        assertEquals(4,author.getPaper_num());
        assertEquals(180,author.getC_count());
        assertEquals(2,author.getRank(),1e-6);
    }

    @Test
    public void getCoAuthorById() {
        List<AuthorVO> coAuthors = authorLogic.getCoAuthorById(12);
        assertEquals(2,coAuthors.size());
        assertEquals("author1",coAuthors.get(0).getName());
        assertEquals("author5",coAuthors.get(1).getName());
    }

    @Test
    public void getPaperNumOfAuByYear() {
        List<Integer> list = authorLogic.getPaperNumOfAuByYear(2);
        assertEquals(1,(int)list.get(0));
        assertEquals(1,(int)list.get(5));
        assertEquals(2,(int)list.get(8));
    }

    @Test
    public void getCitationNumOfAuByYear() {
        List<Integer> list = authorLogic.getCitationNumOfAuByYear(2);
        assertEquals(55,(int)list.get(0));
        assertEquals(60,(int)list.get(5));
        assertEquals(65,(int)list.get(8));
    }
*/

}