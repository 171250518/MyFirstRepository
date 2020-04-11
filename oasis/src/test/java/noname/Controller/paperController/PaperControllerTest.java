package noname.Controller.paperController;

import noname.VO.Response;
import noname.logic.paper.PaperLogic;
import noname.logicIMP.paperIMP.PaperLogicIMP;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaperControllerTest {

    private PaperController paperController;
    private PaperLogic paperLogic;

    @Before
    public void setUp() {
        paperController = new PaperController();
        paperLogic = mock(PaperLogicIMP.class);
        paperController.setPaperLogic(paperLogic);
    }
    @After
    public void tearDown() {
        paperController = null;
        paperLogic = null;
    }

    @Test
    public void getMostPaperYear() {
        when(paperLogic.showMostPaperYear()).thenReturn(Response.buildSuccess());
        Response response = paperController.getMostPaperYear();
        assertTrue(response.getSuccess());
    }

    @Test
    public void getMostPaperPublisher() {
        when(paperLogic.showMostPaperConf()).thenReturn(Response.buildSuccess());
        Response response = paperController.getMostPaperPublisher();
        assertTrue(response.getSuccess());
    }
}