package noname.Controller.searchController;

import noname.VO.Response;
import noname.logic.search.SearchLogic;
import noname.logicIMP.searchIMP.SearchLogicIMP;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class SearchControllerTest {
    private SearchController searchController;
    private SearchLogic searchLogic;

    @Before
    public void setUp() {
        searchController = new SearchController();
        searchLogic = mock(SearchLogicIMP.class);
        searchController.setSearchLogic(searchLogic);
    }

    @After
    public void tearDown() {
        searchController = null;
        searchLogic = null;
    }

    @Test
    public void getPaperByAuthor() throws Exception {
        when(searchLogic.searchPaperByAuthor("author")).thenReturn(Response.buildSuccess());
        Response response = searchController.getPaperByAuthor("author");
        assertTrue(response.getSuccess());
    }

    @Test
    public void getPaperByTitle() {
        when(searchLogic.searchPaperByTitle("title")).thenReturn(Response.buildSuccess());
        Response response = searchController.getPaperByTitle("title");
        assertTrue(response.getSuccess());
    }

    @Test
    public void getPaperByPublisher() {
        when(searchLogic.searchPaperByAffiliation("publisher")).thenReturn(Response.buildSuccess());
        Response response = searchController.getPaperByPublisher("publisher");
        assertTrue(response.getSuccess());
    }

    @Test
    public void getPaperByYear() {
        when(searchLogic.searchPaperByYear("2015")).thenReturn(Response.buildSuccess());
        Response response = searchController.getPaperByYear("2015");
        assertTrue(response.getSuccess());
    }

    @Test
    public void getPaperByKeywords() {
        when(searchLogic.searchPaperByKeywords("ML")).thenReturn(Response.buildSuccess());
        Response response = searchController.getPaperByKeywords("ML");
        assertTrue(response.getSuccess());
    }

    @Test
    public void getPaperByConf() {
        when(searchLogic.searchPaperByConf("IEEE")).thenReturn(Response.buildSuccess());
        Response response = searchController.getPaperByConf("IEEE");
        assertTrue(response.getSuccess());
    }

    @Test
    public void getPaperByCombination() {
        when(searchLogic.searchPaperByCombination("#")).thenReturn(Response.buildSuccess());
        Response response = searchController.getPaperByCombination("#");
        assertTrue(response.getSuccess());
    }
}