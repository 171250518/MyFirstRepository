package noname.dataIMP;

import noname.Application;
import noname.PO.PaperPO;
import noname.data.paper.PaperData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)*/
public class dataPaperIMPTest {

/*    @Autowired
    PaperData paperData;

    @Test
    public void searchPaperByAuthorTest(){
        PaperPO paperPO=new PaperPO();
        paperPO.setTitle("3rd FME Workshop on Formal Methods in Software Engineering (FormaliSE 2015)");
        paperPO.setAuthor("S. Gnesi; N. Plat");
        paperPO.setPublishTime("2015");
        paperPO.setAffiliation("NA; NA");
        paperPO.setLink("https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=7203136");
        String Author="S. Gnesi; N. Plat";
        List<PaperPO> res=new ArrayList<>();
        List<PaperPO> expect=new ArrayList<>();
        expect.add(paperPO);

        res=paperData.searchPaperByAuthor(Author);
        assertEquals(expect.get(0).getLink(),res.get(0).getLink());
    }

    @Test
    public void searchPaperByTitleTest(){
        PaperPO paperPO=new PaperPO();
        paperPO.setTitle("3rd FME Workshop on Formal Methods in Software Engineering (FormaliSE 2015)");
        paperPO.setAuthor("S. Gnesi; N. Plat");
        paperPO.setPublishTime("2015");
        paperPO.setAffiliation("NA; NA");
        paperPO.setLink("https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=7203136");
        String title="3rd FME Workshop on Formal Methods in Software Engineering (FormaliSE 2015)";
        List<PaperPO> res=new ArrayList<>();
        List<PaperPO> expect=new ArrayList<>();
        expect.add(paperPO);

        res=paperData.searchPaperByTitle(title);
        assertEquals(expect.get(0).getLink(),res.get(0).getLink());
    }


    @Test
    public void searchPaperByYearTest(){

        List<PaperPO> res=new ArrayList<>();
        res=paperData.searchPaperByYear("2015");
        assertEquals("S. Gnesi; N. Plat",res.get(0).getAuthor());
    }


    @Test
    public void searchPaperByCombinationTest(){
        PaperPO paperPO=new PaperPO();
        paperPO.setTitle("3rd FME Workshop on Formal Methods in Software Engineering (FormaliSE 2015)");
        paperPO.setAuthor("S. Gnesi; N. Plat");
        paperPO.setPublishTime("2015");
        paperPO.setAffiliation("NA; NA");
        paperPO.setLink("https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=7203136");
        List<PaperPO> res=new ArrayList<>();
        List<PaperPO> expect=new ArrayList<>();
        expect.add(paperPO);

        res=paperData.searchPaperByCombination("S. Gnesi; N. Plat","3rd FME Workshop on Formal Methods in Software Engineering (FormaliSE 2015)","NA; NA","2015","Formal methods;Software engineering","2015 IEEE/ACM 37th IEEE International Conference on Software Engineering");
        assertEquals(expect.get(0).getAuthor(),res.get(0).getAuthor());
    }*/



}
