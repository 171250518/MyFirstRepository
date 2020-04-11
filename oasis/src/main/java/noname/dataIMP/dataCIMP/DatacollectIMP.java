package noname.dataIMP.dataCIMP;

import com.alibaba.druid.util.StringUtils;
import noname.data.Datacollect.Datacollect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

@Repository
public class DatacollectIMP implements Datacollect {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void insertData(List<String> list) {
        for(int i=0;i<list.size();i++){



            String sql="INSERT INTO `ase` VALUES("+list.get(i)+");";
            jdbcTemplate.update(sql);
        }


    }

    @Override
    public void insertAuthor(List<String> list){
        for(int i=0;i<list.size();i++){
            String sql="INSERT into `author` VALUES("+i+",'"+list.get(i)+"');";
            jdbcTemplate.update(sql);
        }

    }

    @Override
    public void insertAffiliation(List<String> list) {
        for(int i=0;i<list.size();i++){
            String sql="Insert into `affiliation` VALUES("+i+",'"+list.get(i)+"');";
            jdbcTemplate.update(sql);
        }

        System.out.println("insert aff success");
    }

    @Override
    public void insertAuthor_Affiliation(List<Map<String, Object>> list) {
        for(int i=0;i<list.size()-1;i++){
            for(int j=list.size()-1;j>i;j--){
                if(list.get(i).get("author").equals(list.get(j).get("author"))){
                    if((list.get(i).get("affiliation").equals(list.get(j).get("affiliation")))&&(list.get(i).get("year").equals(list.get(j).get("year")))){
                        list.remove(j);
                    }
                }
            }
        }
        for(int i=0;i<list.size();i++){

            if(ObjectUtils.nullSafeToString(list.get(i).get("author")).equals("")||list.get(i).get("affiliation").equals("")){


                list.remove(i);
                i--;

            }
        }

        for(int i=0;i<list.size();i++){
            String sql="Insert into `author_affiliation` values ('"+list.get(i).get("author")+"','"+list.get(i).get("affiliation")+"','"+list.get(i).get("year")+"');";
            jdbcTemplate.update(sql);
        }

        System.out.println("charuchenggong");
    }

    @Override
    public void insertPaper(List<Map<String, Object>> list) {

        for(int i=0;i<list.size();i++){
            String sql="insert into `paper` values ('"
                    +list.get(i).get("paper_id")
                    +"','"
                    +list.get(i).get("conference_id")
                    +"','"
                    +list.get(i).get("startpage")
                    +"','"
                    +list.get(i).get("endpage")
                    +"','"
                    +list.get(i).get("title")
                    + "','"
                    +list.get(i).get("abstract")
                    +"','"
                    +list.get(i).get("citation")
                    +"','"
                    +list.get(i).get("year")
                    +"','"
                    +list.get(i).get("pdfLink")
                    +"');";
            jdbcTemplate.update(sql);
        }


    }

    @Override
    public void insertDirection(List<String> list) {
        for(int i=0;i<list.size()-1;i++){
            for(int j=list.size()-1;j>i;j--){
                if(list.get(i).equals(list.get(j))){
                    list.remove(j);
                }
            }
        }

        for(int i=0;i<list.size();i++){
            if(list.get(i).equals("")){
                list.remove(i);
                if(i>0) {
                    i--;
                }
            }

            String sql="insert into direction values ('"+i+"','"+list.get(i)+"');";
            jdbcTemplate.update(sql);
        }


    }

    @Override
    public void insertConference(List<String> list) {
        for(int i=0;i<list.size()-1;i++){
            for(int j=list.size()-1;j>i;j--){
                if(list.get(i).equals(list.get(j))){
                    list.remove(j);
                }
            }
        }

        for(int i=0;i<list.size();i++){
            if(list.get(i).equals("")){
                list.remove(i);
                if(i>0) {
                    i--;
                }
            }

            String sql="insert into conference values ('"+i+"','"+list.get(i)+"');";
            jdbcTemplate.update(sql);
        }
    }

    @Override
    public void insertAuthor_Direction(List<Map<String, Object>> list) {
        for(int i=0;i<list.size();i++){
            String sql="insert into author_direction values ('"+list.get(i).get("author")+"','"+list.get(i).get("direction")+"')";
            jdbcTemplate.update(sql);
        }
    }

    @Override
    public void insertAuthor_Paper(List<Map<String, Object>> list) {
        for(int i=0;i<list.size()-1;i++){
            for(int j=list.size()-1;j>i;j--){
                if(list.get(i).equals(list.get(j))){
                    list.remove(j);
                }
            }
        }
        for(int i=0;i<list.size();i++){
            String sql="insert into author_paper values ('"+list.get(i).get("author")+"','"+list.get(i).get("paper")+"');";
            jdbcTemplate.update(sql);
        }
    }

    @Override
    public void insertPaper_Affiliation(List<Map<String, Object>> list) {
        for(int i=0;i<list.size();i++){
            String sql="insert into paper_affiliation values ('"
                    +list.get(i).get("paper")
                    +"','"
                    +list.get(i).get("affiliation")
                    +"')";
            jdbcTemplate.update(sql);
        }

    }

    @Override
    public void insertPaper_Direction(List<Map<String, Object>> list) {
        for(int i=0;i<list.size();i++){
            String sql="insert into paper_direction values ('"
                    +list.get(i).get("paper")
                    +"','"
                    +list.get(i).get("direction")
                    +"')";
            jdbcTemplate.update(sql);
        }
    }


}
