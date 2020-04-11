package noname.data.Datacollect;

import java.util.List;
import java.util.Map;

public interface Datacollect {
     void insertData(List<String> list);
     void insertAuthor(List<String> list);
     void insertAffiliation(List<String> list);
     void insertAuthor_Affiliation(List<Map<String,Object>> list);
     void insertPaper(List<Map<String,Object>> list);
     void insertDirection(List<String> list);
     void insertConference(List<String> list);
     void insertAuthor_Direction(List<Map<String,Object>> list);
     void insertAuthor_Paper(List<Map<String,Object>>list);
     void insertPaper_Affiliation(List<Map<String,Object>> list);
     void insertPaper_Direction(List<Map<String,Object>> list);

}
