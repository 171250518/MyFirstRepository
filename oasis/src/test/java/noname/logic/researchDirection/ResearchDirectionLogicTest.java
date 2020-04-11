package noname.logic.researchDirection;

import noname.Application;
import noname.PO.AuthorPO;
import noname.VO.AffiliationVO;
import noname.VO.AuthorVO;
import noname.VO.PaperVO;
import noname.VO.ResearchDirectionVO;
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
public class ResearchDirectionLogicTest {
/*
    @Autowired
    private ResearchDirectionLogic researchDirectionLogic;

    @Test
    public void getReDirectionByName() {
        List<ResearchDirectionVO> researchDirectionVOS = researchDirectionLogic.getReDirectionByName("dir");
        assertEquals(3,researchDirectionVOS.size());
        assertEquals(4,researchDirectionVOS.get(0).getPaper_number());
        assertEquals("author1 author2 ",researchDirectionVOS.get(1).getAuthors());
        assertEquals(100,researchDirectionVOS.get(2).getCitation_count());
    }

    @Test
    public void getTopReDirections() {
        List<ResearchDirectionVO> researchDirectionVOS = researchDirectionLogic.getTopReDirections();
        assertEquals(550,researchDirectionVOS.get(0).getRank(),1e-6);
        assertEquals("dir2",researchDirectionVOS.get(0).getName());
        assertEquals(240,researchDirectionVOS.get(1).getRank(),1e-6);
        assertEquals("dir1",researchDirectionVOS.get(1).getName());
        assertEquals(60,researchDirectionVOS.get(2).getRank(),1e-6);
        assertEquals("dir3",researchDirectionVOS.get(2).getName());
    }

    @Test
    public void getAllPapersOfRe() {
        List<PaperVO> papers = researchDirectionLogic.getAllPapersOfRe(3);
        assertEquals(4,papers.size());
        assertEquals("author1 author2 author4 ",papers.get(0).getAuthor());
        assertEquals("aff1 aff2 ",papers.get(1).getAffiliation());
        assertEquals("conf1",papers.get(2).getConf());
        assertEquals("dir2 dir3 ",papers.get(3).getKeywords());
    }

    @Test
    public void getPapersOfReByYear() {
        List<PaperVO> papers = researchDirectionLogic.getPapersOfReByYear(2,"2016");
        assertEquals(2,papers.size());
        assertEquals("dir1 dir2 ",papers.get(0).getKeywords());
        assertEquals("author3 author10 ",papers.get(1).getAuthor());
    }

    @Test
    public void getPapersOfReByAuthor() {
        List<PaperVO> papers = researchDirectionLogic.getPapersOfReByAuthor(1,1);
        assertEquals(2,papers.size());
        assertEquals("aff1 aff3 ",papers.get(0).getAffiliation());
        assertEquals("author1 author2 author9 ",papers.get(1).getAuthor());
    }

    @Test
    public void getPaperNumOfReByYear() {
        List<Integer> list = researchDirectionLogic.getPaperNumOfReByYear(2);
        assertEquals(0,(int)list.get(0));
        assertEquals(2,(int)list.get(5));
        assertEquals(2,(int)list.get(8));
        assertEquals(1,(int)list.get(9));
    }

    @Test
    public void getCitationNumOfReByYear() {
        List<Integer> list = researchDirectionLogic.getCitationNumOfReByYear(1);
        assertEquals(55,(int)list.get(0));
        assertEquals(0,(int)list.get(1));
        assertEquals(60,(int)list.get(5));
        assertEquals(65,(int)list.get(8));
    }

    @Test
    public void getReTopAuthor() {
        List<AuthorVO> authors = researchDirectionLogic.getReTopAuthor(2);
        assertEquals(2,authors.size());
        assertEquals(2,authors.get(0).getRank(),1e-6);
        assertEquals(1,authors.get(1).getRank(),1e-6);
    }

    @Test
    public void getReAuthor() {
        List<AuthorVO> authors = researchDirectionLogic.getReAuthor(1);
        assertEquals(1,authors.size());
        assertEquals("aff1 ",authors.get(0).getInstitution());
    }

    @Test
    public void getResearchDirectionById() {
        ResearchDirectionVO researchDirectionVO = researchDirectionLogic.getResearchDirectionById(2);
        assertEquals("dir2",researchDirectionVO.getName());
        assertEquals(5,researchDirectionVO.getPaper_number());
        assertEquals(145,researchDirectionVO.getCitation_count());
        assertEquals(550,researchDirectionVO.getRank(),1e-6);
        assertEquals("author1 author2 ",researchDirectionVO.getAuthors());
    }

    @Test
    public void getResearchDirectionTopAffiliationById() {
        List<AffiliationVO> affiliationVOS = researchDirectionLogic.getResearchDirectionTopAffiliationById(1);
        assertEquals(3,affiliationVOS.get(0).getPaper_number());
        assertEquals(1,affiliationVOS.get(1).getPaper_number());
        assertEquals(1,affiliationVOS.get(2).getPaper_number());
    }

 */
}