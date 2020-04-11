package noname.logicIMP.dataIMP;

import noname.VO.Response;
import noname.data.Datacollect.Datacollect;
import noname.logic.data.Datalogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Repository
public class DataLogicIMP implements Datalogic {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Datacollect datacollect;



    /**
     * @Description: 把数据从csv文件中读取到list
     * @Param: [reader] 字符缓冲流
     * @return: java.util.List 读取到的数据（一次读取1000行）
     */
    @Override
    public List readCSV(BufferedReader reader){
        try {
            ArrayList<String> list = new ArrayList<String>();
            String line = null;
            for(int i=0; i < 1000; i++) {
                line = reader.readLine();

                if(line == null){
                    break;
                }
                line=line.replaceAll("'","`");
                line = "'"+line.replaceAll("︴","','");

                line=line.replaceAll("\"","'");
                line=line.substring(1);
                list.add(line);
            }
            return list;
        } catch (Exception e) {

            try {
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @Description: 批量插入到数据库
     * @Param: [file, type] file：文件，type：数据库对应表
     * @return: void
     */
    @Override
    public Response insertData(String filename) {

        File file=new File(filename);
        String head = null;
        List<String> list = null;
        BufferedReader reader = null;
        //读取第一行的标题信息
        try {
            reader = new BufferedReader(new FileReader(file));
            head = reader.readLine();

            if(head == null){


            } else {
                head = head.replaceAll("︴",",");
            }

            while ((list = readCSV(reader)).size() != 0){

                datacollect.insertData(list);
                return Response.buildSuccess("数据库插入成功");

            }
        } catch (IOException e) {
            return Response.buildFailure("数据库插入异常失败");

        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Response.buildSuccess("数据库插入成功啦～～");

        }
    }

    @Override
    public Response insertAuthor() {
        String sql="select Authors from `ase`;";
        List<Map<String, Object>> res_list=new ArrayList<>();
        res_list=jdbcTemplate.queryForList(sql);
        System.out.println("duqu");
        List<String>authors=new ArrayList<>();
       //将所有作者读入authors的列表中，有重复
       for(int i=0;i<res_list.size();i++){
           String author_s=String.valueOf(res_list.get(i).get("Authors"));
           List<String> a= Arrays.asList(author_s.split(";"));
           authors.addAll(a);
       }
       //qu 重复
       for(int i=0;i<authors.size()-1;i++){
           for(int j=authors.size()-1;j>i;j--){
               if(authors.get(i).startsWith(" ")){
                   authors.set(i,authors.get(i).substring(1));
               }
               if(authors.get(j).startsWith(" ")){
                   System.out.println(j);
                   authors.set(j,authors.get(j).substring(1));
               }
               if(authors.get(i).equals(authors.get(j))){
                   authors.remove(j);
               }
           }
       }
       for(int i=0;i<authors.size();i++){
           if(authors.get(i).equals("")){
               authors.remove(i);
           }
       }

       System.out.println("quchong");
       datacollect.insertAuthor(authors);



       return Response.buildSuccess();
    }

    @Override
    public Response insertAffiliation() {
        String sql="select Author_Affiliations from `ase`;";
        List<Map<String, Object>> res_list=new ArrayList<>();
        res_list=jdbcTemplate.queryForList(sql);
        System.out.println("ksdfj");
        List<String> affiliation=new ArrayList<>();
        for(int i=0;i<res_list.size();i++){
            String[] aff=String.valueOf(res_list.get(i).get("Author_Affiliations")).split(";");
            for(int j=0;j<aff.length;j++){
                affiliation.add(aff[j]);
            }
        }



        //
        for(int i=0;i<affiliation.size()-1;i++){
            for(int j=affiliation.size()-1;j>i;j--){
                if(affiliation.get(i).startsWith(" ")){
                    String a=affiliation.get(i).substring(1);
                    affiliation.set(i,a);
                }
                if(affiliation.get(j).startsWith(" ")){
                    String a=affiliation.get(j).substring(1);
                    affiliation.set(j,a);
                }
                if(affiliation.get(i).equals(affiliation.get(j))){
                    affiliation.remove(j);
                }
            }
        }

        datacollect.insertAffiliation(affiliation);

        System.out.println("quchong1");

        return Response.buildSuccess();



    }

    @Override
    public Response insertAuthorAffiliation() {

        String sql="select Authors,Author_Affiliations,Publication_Year from `ase`;";
        List<Map<String, Object>> res_list=new ArrayList<>();
        res_list=jdbcTemplate.queryForList(sql);

        String sql1="select Author_id,Author_name from `author`;";
        List<Map<String,Object>> res_list1=new ArrayList<>();
        res_list1=jdbcTemplate.queryForList(sql1);

        String sql2="select Affiliation_id,Affiliation_name from `affiliation`;";
        List<Map<String,Object>> res_list2=new ArrayList<>();
        res_list2=jdbcTemplate.queryForList(sql2);

        List<Map<String,Object>> author_aff=new ArrayList<>();


        for(int i=0;i<res_list.size();i++){
            String[] authors= String.valueOf(res_list.get(i).get("Authors")).split(";");
            String[] affiliations=String.valueOf(res_list.get(i).get("Author_Affiliations")).split(";");
            String year= String.valueOf(res_list.get(i).get("Publication_Year"));
            for(int j=0;j<authors.length;j++){
                String author="";
                String affiliation="";
                if(authors[j].startsWith(" ")){
                    authors[j]=authors[j].substring(1);
                }
                if(affiliations[j].startsWith(" ")){
                    affiliations[j]=affiliations[j].substring(1);
                }
                for(int k=0;k<res_list1.size();k++){
                    if(authors[j].equals(String.valueOf(res_list1.get(k).get("Author_name")))){
                        author= String.valueOf(res_list1.get(k).get("Author_id"));
                    }
                }
                for(int k=0;k<res_list2.size();k++){
                    if(affiliations[j].equals(String.valueOf(res_list2.get(k).get("Affiliation_name")))){
                        affiliation=String.valueOf(res_list2.get(k).get("Affiliation_id"));
                    }
                }
                Map<String,Object> author_affiliation=new HashMap<>();
                author_affiliation.put("author",author);
                author_affiliation.put("affiliation",affiliation);
                author_affiliation.put("year",year);
                author_aff.add(author_affiliation);
            }
        }

        datacollect.insertAuthor_Affiliation(author_aff);




        return Response.buildSuccess();
    }

    @Override
    public Response insertPaper() {
        String sql="select * from ase";
        List<Map<String, Object>> res_list=new ArrayList<>();
        res_list=jdbcTemplate.queryForList(sql);
        List<Map<String,Object>> paperlist=new ArrayList<>();

        String sqlforconfer="select * from conference";
        List<Map<String, Object>> res_list1=new ArrayList<>();
        res_list1=jdbcTemplate.queryForList(sqlforconfer);

        for(int i=0;i<res_list.size();i++){
            Map<String,Object> pa=new HashMap<>();
            String title= String.valueOf(res_list.get(i).get("Document_Title"));
            title=title.replaceAll("\'","\"");
            String startpage=String.valueOf(res_list.get(i).get("Start_Page"));
            String endpage=String.valueOf(res_list.get(i).get("End_Page"));
            String abst=String.valueOf(res_list.get(i).get("Abstract"));
            abst=abst.replaceAll("\'","\"");
            String citation=String.valueOf(res_list.get(i).get("Article_Citation_Count"));
            if(citation.length()==0){
                citation="0";
            }
            String year=String.valueOf(res_list.get(i).get("Publication_Year"));
            String PDFlink=String.valueOf(res_list.get(i).get("PDF_Link"));




            String conference_id="";
            for(int j=0;j<res_list1.size();j++){
                if(String.valueOf(res_list.get(i).get("Publication_Title")).equals(res_list1.get(j).get("Conference_name"))){
                    conference_id= String.valueOf(res_list1.get(j).get("Conference_id"));
                }
            }

            pa.put("title",title);
            pa.put("startpage",startpage);
            pa.put("endpage",endpage);
            pa.put("abstract",abst);
            pa.put("citation",citation);
            pa.put("year",year);
            pa.put("pdfLink",PDFlink);
            pa.put("paper_id",i);
            pa.put("conference_id",conference_id);

            paperlist.add(pa);

        }

        datacollect.insertPaper(paperlist);
        return Response.buildSuccess();
    }

    @Override
    public Response insertDirection() {
        String sql="select Author_Keywords from ase";
        List<Map<String, Object>> res_list=new ArrayList<>();
        res_list=jdbcTemplate.queryForList(sql);
        List<String> directions=new ArrayList<>();
        for(int i=0;i<res_list.size();i++){
            String[] dir=String.valueOf(res_list.get(i).get("Author_Keywords")).split(";");
            for(int j=0;j<dir.length;j++){
                if(dir[j].startsWith(" ")){
                    dir[j]=dir[j].substring(1);
                }
                directions.add(dir[j]);
            }
        }

        datacollect.insertDirection(directions);

        return Response.buildSuccess();
    }

    @Override
    public Response insertConference() {
        String sql="select Publication_Title from ase";
        List<Map<String, Object>> res_list=new ArrayList<>();
        res_list=jdbcTemplate.queryForList(sql);
        List<String> conferences=new ArrayList<>();
        for(int i=0;i<res_list.size();i++){
            String confer=String.valueOf(res_list.get(i).get("Publication_Title"));
            conferences.add(confer);
        }

        datacollect.insertConference(conferences);

        return Response.buildSuccess();
    }

    @Override
    public Response insertAuthorDirection() {
        String sql="select Authors,Author_Keywords from ase";
        List<Map<String, Object>> res_list=new ArrayList<>();
        res_list=jdbcTemplate.queryForList(sql);


        String sqlForAuthor="select Author_id,Author_name from author";
        List<Map<String, Object>> res_list_author=new ArrayList<>();
        res_list_author=jdbcTemplate.queryForList(sqlForAuthor);

        String sqlForDirection="select Direction_id,Direction_name from direction";
        List<Map<String, Object>> res_list_Direction=new ArrayList<>();
        res_list_Direction=jdbcTemplate.queryForList(sqlForDirection);


        List<Map<String,Object>> author_direction=new ArrayList<>();


        //wei qu chongfu
        for(int i=0;i<res_list.size();i++){
            if((!String.valueOf(res_list.get(i).get("Authors")).equals(""))&&(!String.valueOf(res_list.get(i).get("Author_Keywords")).equals(""))){
                String authors[]=String.valueOf(res_list.get(i).get("Authors")).split(";");
                String author_keywords[]=String.valueOf(res_list.get(i).get("Author_Keywords")).split(";");
                for (int j = 0; j < authors.length; j++) {

                    String author="";
                    for (int k = 0; k < res_list_author.size(); k++) {
                        if (authors[j].startsWith(" ")) {
                            authors[j] = authors[j].substring(1);
                        }
                        if (authors[j].equals(String.valueOf(res_list_author.get(k).get("Author_name")))) {
                            author=String.valueOf(res_list_author.get(k).get("Author_id"));
                        }
                    }

                    for(int k=0;k<author_keywords.length;k++){
                        if(author_keywords[k].startsWith(" ")){
                            author_keywords[k]=author_keywords[k].substring(1);
                        }
                        for(int z=0;z<res_list_Direction.size();z++) {
                            if (author_keywords[k].equals(String.valueOf(res_list_Direction.get(z).get("Direction_name")))) {
                                Map<String, Object> author_d = new HashMap<>();
                                String dir = String.valueOf(res_list_Direction.get(z).get("Direction_id"));
                                author_d.put("author", author);
                                author_d.put("direction", dir);
                                author_direction.add(author_d);
                            }
                        }
                    }


                }
            }
        }

        //qu chong
        for(int i=0;i<author_direction.size()-1;i++){
            for(int j=author_direction.size()-1;j>i;j--){
                if(author_direction.get(i).get("author").equals(author_direction.get(j).get("author"))&&author_direction.get(i).get("direction").equals(author_direction.get(j).get("direction"))){
                    author_direction.remove(j);
                }
            }
        }

        datacollect.insertAuthor_Direction(author_direction);


        return Response.buildSuccess();
    }

    @Override
    public Response insertAuthorPaper() {
        String sql="select Authors,PDF_Link from ase";
        List<Map<String,Object>> list;
        List<Map<String,Object>> list1;
        List<Map<String,Object>> list2;
        String sql1="select Paper_id,PDF_linking from paper";
        String sql2="SELECT Author_id,Author_name from author";
        list1=jdbcTemplate.queryForList(sql1);
        list2=jdbcTemplate.queryForList(sql2);
        list=jdbcTemplate.queryForList(sql);

        List<Map<String,Object>> authorPaper=new ArrayList<>();

        for(int i=0;i<list.size();i++){
            String[] author=String.valueOf(list.get(i).get("Authors")).split(";");
            String t=String.valueOf(list.get(i).get("PDF_Link"));
            for(int j=0;j<author.length;j++){
                if(author[j].startsWith(" ")){
                    author[j]=author[j].substring(1);
                }
                String paper="";
                for(int k=0;k<list1.size();k++){
                    if(t.equals(String.valueOf(list1.get(k).get("PDF_linking")))){
                        paper= String.valueOf(list1.get(k).get("Paper_id"));
                    }
                }
                String author_id;
                for(int k=0;k<list2.size();k++){
                    if(author[j].equals(list2.get(k).get("Author_name"))) {
                        author_id = String.valueOf(list2.get(k).get("Author_id"));
                        Map<String,Object> pa=new HashMap<>();
                        pa.put("author",author_id);
                        pa.put("paper",paper);
                        authorPaper.add(pa);
                    }
                }
            }
        }
        datacollect.insertAuthor_Paper(authorPaper);
        return Response.buildSuccess();
    }

    @Override
    public Response insertPaperAffiliation() {

        String sql1="SELECT Affiliation_id,Affiliation_name from affiliation";
        List<Map<String,Object>> reslist1=new ArrayList<>();
        reslist1=jdbcTemplate.queryForList(sql1);


        String sql2="Select Author_Affiliations,PDF_Link from ase";
        List<Map<String,Object>> reslist2=new ArrayList<>();
        reslist2=jdbcTemplate.queryForList(sql2);

        List<Map<String,Object>> paperAff=new ArrayList<>();

        for(int i=0;i<reslist2.size();i++){
            String[] aff=String.valueOf(reslist2.get(i).get("Author_Affiliations")).split(";");

            for(int j=0;j<aff.length;j++){
                if(aff[j].startsWith(" ")){
                    aff[j]=aff[j].substring(1);
                }
                for(int k=0;k<reslist1.size();k++) {
                    if (aff[j].equals(reslist1.get(k).get("Affiliation_name"))) {
                        Map<String, Object> pa = new HashMap<>();
                        pa.put("paper", String.valueOf(i));
                        pa.put("affiliation", String.valueOf(reslist1.get(k).get("Affiliation_id")));
                        paperAff.add(pa);
                    }
                }
            }
        }



        for(int i=0;i<paperAff.size()-1;i++){
            for(int j=paperAff.size()-1;j>i;j--){
                if(paperAff.get(i).equals(paperAff.get(j))){
                    paperAff.remove(j);
                }
            }
        }
        datacollect.insertPaper_Affiliation(paperAff);

        return Response.buildSuccess();
    }

    @Override
    public Response insertPaperDirection() {
        String sql="select Author_Keywords from ase";
        List<Map<String,Object>> reslist=new ArrayList<>();
        reslist=jdbcTemplate.queryForList(sql);

        String sql1="select Direction_name,Direction_id from direction";
        List<Map<String,Object>> reslist1=new ArrayList<>();
        reslist1=jdbcTemplate.queryForList(sql1);

        List<Map<String,Object>> paperdir=new ArrayList<>();

        for(int i=0;i<reslist.size();i++){
            String[]dir=String.valueOf(reslist.get(i).get("Author_Keywords")).split(";");
            for(int j=0;j<dir.length;j++){
                if(dir[j].startsWith(" ")){
                    dir[j]=dir[j].substring(1);
                }
                for(int k=0;k<reslist1.size();k++){
                    if(dir[j].equals(reslist1.get(k).get("Direction_name"))){
                        String direction=String.valueOf(reslist1.get(k).get("Direction_id"));
                        Map<String,Object> pd=new HashMap<>();
                        pd.put("paper",String.valueOf(i));
                        pd.put("direction",direction);
                        paperdir.add(pd);
                    }
                }
            }
        }
        for(int i=0;i<paperdir.size()-1;i++){
            for(int j=paperdir.size()-1;j>i;j--){
                if(paperdir.get(i).equals(paperdir.get(j))){
                    paperdir.remove(j);
                }
            }
        }

        datacollect.insertPaper_Direction(paperdir);

        return Response.buildSuccess();
    }


}
