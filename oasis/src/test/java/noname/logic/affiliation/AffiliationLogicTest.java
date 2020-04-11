package noname.logic.affiliation;

import noname.Application;
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

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)*/
public class AffiliationLogicTest {

 /*   @Autowired
    private AffiliationLogic affiliationLogic;

    @Test
    public void getAffiliationByName() {
        List<AffiliationVO> affiliationVOS = affiliationLogic.getAffiliationByName("aff");
        assertEquals(4,affiliationVOS.size());
        assertEquals("aff1",affiliationVOS.get(0).getName());
        assertEquals("author2 author3 ",affiliationVOS.get(2).getAuthors());
        assertEquals(80,affiliationVOS.get(1).getCitation_count());
        assertEquals(6,affiliationVOS.get(0).getRank(),1e-6);
    }

    @Test
    public void getPaperNumOfAffByYear() {
        List<Integer> list = affiliationLogic.getPaperNumOfAffByYear(3);
        assertEquals(10,list.size());
        assertEquals(1,(int)list.get(0));
        assertEquals(2,(int)list.get(2019-2011));
    }

    @Test
    public void getCitationNumOfAffByYear() {
        List<Integer> list = affiliationLogic.getCitationNumOfAffByYear(3);
        assertEquals(10,list.size());
        assertEquals(55,(int)list.get(0));
        assertEquals(65,(int)list.get(2019-2011));
    }

    @Test
    public void getTopAffiliations() {
        List<AffiliationVO> list = affiliationLogic.getTopAffiliations();
        assertEquals(5,list.size());
        assertEquals(2,list.get(0).getId());
        assertEquals(3,list.get(2).getId());
        assertEquals(6,list.get(3).getRank(),1e-6);
    }

    @Test
    public void getPapersOfAffByAuthor() {
        List<PaperVO> papers = affiliationLogic.getPapersOfAffByAuthor(1, 3);
        assertEquals(0,papers.size());
        assertEquals("author1 author3 author6 ",papers.get(0).getAuthor());
    }

    @Test
    public void getPapersOfAffByYear() {
        List<PaperVO> papers = affiliationLogic.getPapersOfAffByYear(2,"2019");
        assertEquals(1,papers.size());
        assertEquals("author2 author3 author7 author11 ",papers.get(0).getAuthor());
    }

    @Test
    public void getAllPapersOfAff() {
        List<PaperVO> papers = affiliationLogic.getAllPapersOfAff(1);
        assertEquals(1,papers.size());
        assertEquals("dir1 dir2 dir3 ",papers.get(0).getKeywords());
        assertEquals("aff1 aff2 ",papers.get(1).getAffiliation());
        assertEquals("author1 author3 author6 ",papers.get(2).getAuthor());
    }

    @Test
    public void getAuthorsOfAff() {
        List<AuthorVO> authors = affiliationLogic.getAuthorsOfAff(3);
        assertEquals(1,authors.size());
        assertEquals("dir2 dir3 ",authors.get(0).getResearchDirection());
        assertEquals("aff3 ",authors.get(1).getInstitution());
    }

    @Test
    public void getAffiliationById() {
        AffiliationVO affiliationVO = affiliationLogic.getAffiliationById(2);
        assertEquals("author2 author5 ",affiliationVO.getAuthors());
        assertEquals("dir2 dir3 ",affiliationVO.getReDirection());
        assertEquals(45,affiliationVO.getRank(),1e-6);
    }

    @Test
    public void getAffiliationTopResearchDirectionById() {
        List<ResearchDirectionVO> researchDirectionVOS = affiliationLogic.getAffiliationTopResearchDirectionById(1);
        assertEquals(1,researchDirectionVOS.size());
        assertEquals(2,researchDirectionVOS.get(0).getPaper_number());
        assertEquals(2,researchDirectionVOS.get(1).getPaper_number());
        assertEquals(1,researchDirectionVOS.get(2).getPaper_number());
    }*/
}