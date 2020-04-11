package noname.logic.conf;

import noname.Application;
import noname.VO.AuthorVO;
import noname.VO.ConferenceVO;
import noname.VO.PaperVO;
import noname.logic.author.AuthorLogic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
public class ConfLogicTest {

    /*@Autowired
    private ConfLogic confLogic;

    @Test
    public void getConfByName() {
        List<ConferenceVO> confs = confLogic.getConfByName("conf3");
        assertEquals(1,confs.size());
        assertEquals("2016",confs.get(0).getTime());
        assertEquals(2,confs.get(0).getPaper_num());
        assertEquals(70,confs.get(0).getCitation());
    }

    @Test
    public void getTopConfs() {
        List<ConferenceVO> confs = confLogic.getTopConfs();
        assertEquals(3,confs.size());
        assertEquals("conf3",confs.get(0).getName());
    }

    @Test
    public void getAllPapersOfConf() {
        List<PaperVO> papers = confLogic.getAllPapersOfConf(3);
        assertEquals(2,papers.size());
        assertEquals("dir2 dir3 ",papers.get(1).getKeywords());
        assertEquals("author1 author2 author9 ",papers.get(0).getAuthor());
    }

    @Test
    public void getPapersOfConfByYear() {
        List<PaperVO> papers = confLogic.getPapersOfConfByYear(3, "2016");
        assertEquals(2,papers.size());
        assertEquals("dir2 dir3 ",papers.get(1).getKeywords());
        assertEquals("author1 author2 author9 ",papers.get(0).getAuthor());
    }

    @Test
    public void getPaperNumOfConfByYear() {
        List<Integer> list = confLogic.getPaperNumOfConfByYear(1);
        assertEquals(1,(int)list.get(0));
        assertEquals(0,(int)list.get(1));
        assertEquals(1,(int)list.get(8));
    }

    @Test
    public void getCitationNumOfConfByYear() {
        List<Integer> list = confLogic.getCitationNumOfConfByYear(3);
        assertEquals(0,(int)list.get(3));
        assertEquals(70, (int)list.get(5));
    }

    @Test
    public void getAuthorOfConf() {
        List<AuthorVO> authorVOS = confLogic.getAuthorOfConf(1);
        assertEquals(6,authorVOS.size());
        assertEquals(1,authorVOS.get(0).getId());
        assertEquals("author2",authorVOS.get(1).getName());
        assertEquals(5,authorVOS.get(2).getRank(),1e-6);

    }

    @Test
    public void getTopAuthorOfConf() {
        List<AuthorVO> authors = confLogic.getTopAuthorOfConf(1);
        assertEquals(6,authors.size());
        assertEquals(6,authors.get(0).getId());
        assertEquals(3,authors.get(1).getId());
        assertEquals(3,authors.get(2).getRank(),1e-6);
    }

    @Test
    public void getConferenceById() {
        ConferenceVO conferenceVO = confLogic.getConferenceById(2);
        assertEquals("conf2",conferenceVO.getName());
        assertEquals(2,conferenceVO.getPaper_num());
        assertEquals(60,conferenceVO.getCitation());
        assertEquals(55,conferenceVO.getRank(),1e-6);
    }

    @Test
    public void getPapersOfConfByAuthor() {
        List<PaperVO> papers = confLogic.getPapersOfConfByAuthor(1,1);
        assertEquals(2,papers.size());
        assertEquals(1,papers.get(0).getId());
        assertEquals(3,papers.get(1).getId());
    }*/
}