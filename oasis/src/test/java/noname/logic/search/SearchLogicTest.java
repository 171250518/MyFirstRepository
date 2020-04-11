package noname.logic.search;

import noname.Application;
import noname.PO.PaperPO;
import noname.VO.PaperVO;
import noname.VO.Response;
import noname.data.paper.PaperData;
import noname.dataIMP.paperIMP.PaperDataIMP;
import noname.logicIMP.searchIMP.SearchLogicIMP;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)*/
public class SearchLogicTest {
/*
    @Autowired
    private SearchLogic searchLogic;
    private String valid_author = "S. Gnesi; N. Plat";
    private String invalid_author = "invalid author";
    private String valid_conf = "2015 IEEE/ACM 37th IEEE International Conference on Software Engineering";
    private String invalid_conf = "invalid conf";
    private String valid_kw = "Formal methods;Software engineering";
    private String invalid_kw = "invalid kw";
    private String valid_title="3rd FME Workshop on Formal Methods in Software Engineering (FormaliSE 2015)";
    private String invalid_title="ssss";
    private String valid_publisher="NA; NA";
    private String invalid_publisher="sss";
    private String valid_link="https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=7203136";
    private String invalid_link="";
    private List<PaperVO> list = new ArrayList<>();

    @Before
    public void setUp(){

    }

    @After
    public void tearDown(){

    }

    @Test
    public void searchPaperByAuthorSuccess() {
        Response response = searchLogic.searchPaperByAuthor(valid_author);
        assertTrue(response.getSuccess());
        assertEquals(valid_author,((List<PaperVO>)response.getContent()).get(0).getAuthor());
    }

    @Test
    public void searchPaperByAuthorFail() {
        Response response = searchLogic.searchPaperByAuthor(invalid_author);
        assertTrue(response.getSuccess());
        assertEquals(list,response.getContent());
    }

    @Test
    public void searchPaperByInsSuccess() {
        Response response = searchLogic.searchPaperByAffiliation(valid_publisher);
        assertTrue(response.getSuccess());
        assertEquals(valid_author,((List<PaperVO>)response.getContent()).get(0).getAuthor());
    }

    @Test
    public void searchPaperByInsFail() {
        Response response = searchLogic.searchPaperByAffiliation(invalid_publisher);
        assertTrue(response.getSuccess());
        assertEquals(list,response.getContent());
    }

    @Test
    public void searchPaperByConfSuccess() {
        Response response = searchLogic.searchPaperByConf(valid_conf);
        assertTrue(response.getSuccess());
        assertEquals(valid_author,((List<PaperVO>)response.getContent()).get(0).getAuthor());
    }

    @Test
    public void searchPaperByConfFail() {
        Response response = searchLogic.searchPaperByConf(invalid_conf);
        assertTrue(response.getSuccess());
        assertEquals(list,response.getContent());
    }

    @Test
    public void searchPaperByKeyWordSuccess() {
        Response response = searchLogic.searchPaperByKeywords(valid_kw);
        assertTrue(response.getSuccess());
        assertEquals(valid_author,((List<PaperVO>)response.getContent()).get(0).getAuthor());
    }

    @Test
    public void searchPaperByKeyWordFail() {
        Response response = searchLogic.searchPaperByKeywords(invalid_kw);
        assertTrue(response.getSuccess());
        assertEquals(list,response.getContent());
    }

    @Test
    public void searchPaperByTitleSuccess() {

        Response response = searchLogic.searchPaperByTitle(valid_title);
        assertTrue(response.getSuccess());
        assertEquals(valid_author,((List<PaperVO>)response.getContent()).get(0).getAuthor());
    }

    @Test
    public void searchPaperByTitleFail() {
        Response response = searchLogic.searchPaperByTitle(invalid_title);
        assertTrue(response.getSuccess());
        assertEquals(list,response.getContent());
    }

    @Test
    public void searchPaperByYearSuccess() {
        Response response = searchLogic.searchPaperByYear("2015");
        assertTrue(response.getSuccess());
        assertEquals(valid_author,((List<PaperVO>)response.getContent()).get(0).getAuthor());
    }

    @Test
    public void searchPaperByYearFail() {
        Response response = searchLogic.searchPaperByYear("2022");
        assertTrue(response.getSuccess());
        assertEquals(list,response.getContent());
    }

    @Test
    public void searchPaperByCombination() {
        Response response = searchLogic.searchPaperByCombination(valid_author+"&"+valid_title+"&"+valid_publisher+"&2015&"+valid_kw+"&"+valid_conf);
        assertTrue(response.getSuccess());
        assertEquals(valid_author,((List<PaperVO>)response.getContent()).get(0).getAuthor());
    }*/

}
