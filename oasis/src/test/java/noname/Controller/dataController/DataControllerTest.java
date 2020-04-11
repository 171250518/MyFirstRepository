package noname.Controller.dataController;

import noname.VO.Response;
import noname.logic.data.Datalogic;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataControllerTest {

    @Test
    public void dataCollect() {
        DataController dataController = new DataController();
        Datalogic datalogic = mock(Datalogic.class);
        dataController.setDatalogic(datalogic);
        when(datalogic.insertData("1.csv")).thenReturn(Response.buildSuccess());
        Response response = dataController.dataCollect("1.csv");
        assertTrue(response.getSuccess());
    }
}