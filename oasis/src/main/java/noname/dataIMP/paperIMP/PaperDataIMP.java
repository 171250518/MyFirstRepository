package noname.dataIMP.paperIMP;

import noname.PO.PaperPO;
import noname.data.paper.PaperData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class PaperDataIMP implements PaperData {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<PaperPO> searchPaperByAuthor(final String author) {
        String sql="select * from ase as m where m.Authors like ?;";
        return jdbcTemplate.query(sql, new Object[]{"%"+author+"%"}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paperPO=new PaperPO();
              /*  paperPO.setId(rs.getInt("Paper_id"));
                paperPO.setAuthor(rs.getString("Authors"));*/
                paperPO.setAffiliation(rs.getString("Author_Affiliations"));
                paperPO.setTitle(rs.getString("Document_Title"));
                paperPO.setPublishTime(rs.getString("Publication_Year"));
                paperPO.setLink(rs.getString("PDF_Link"));
                return paperPO;
            }
        });
    }

    @Override
    public List<PaperPO> searchPaperByTitle(final String title) {
        String sql="select * from ase as m where m.Document_Title like ?;";
        return jdbcTemplate.query(sql, new Object[]{"%"+title+"%"}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paperPO=new PaperPO();
              /*  paperPO.setId(rs.getInt("Paper_id"));
                paperPO.setAuthor(rs.getString("Authors"));*/
                paperPO.setAffiliation(rs.getString("Author_Affiliations"));
                paperPO.setTitle(rs.getString("Document_Title"));
                paperPO.setPublishTime(rs.getString("Publication_Year"));
                paperPO.setLink(rs.getString("PDF_Link"));
                return paperPO;
            }
        });
    }

    @Override
    public List<PaperPO> searchPaperByYear(final String year) {
        String sql="select * from ase as m where m.Publication_Year=?;";
        return jdbcTemplate.query(sql, new Object[]{year}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paperPO=new PaperPO();
           /*     paperPO.setId(rs.getInt("Paper_id"));
                paperPO.setAuthor(rs.getString("Authors"));*/
                paperPO.setAffiliation(rs.getString("Author_Affiliations"));
                paperPO.setTitle(rs.getString("Document_Title"));
                paperPO.setPublishTime(rs.getString("Publication_Year"));
                paperPO.setLink(rs.getString("PDF_Link"));
                return paperPO;
            }
        });
    }

    @Override
    public List<PaperPO> searchPaperByCombination(final String author,final String title,final String publisher,final String year,final String keywords,final String conf) {
        List<PaperPO>paperPOList=new ArrayList<>();
        List<Object>objects=new ArrayList<>();
        int flag=0;
        String sql = "select * from ase";
        if(!author.equals("#")){
            objects.add("%"+author+"%");
            if(flag==0){
                sql+=" where ase.Authors like ?";
                flag=1;
            }else if(flag==1){
                sql+=" and ase.Authors like ?";
            }
        }
        if(!title.equals("#")){
            objects.add("%"+title+"%");
            if(flag==0){
                sql+=" where ase.Document_Title like ?";
                flag=1;
            }else if(flag==1){
                sql+=" and ase.Document_Title like ?";
            }
        }
        if(!publisher.equals("#")){
            objects.add("%"+publisher+"%");
            if(flag==0){
                sql+=" where ase.Author_Affiliations like ?";
                flag=1;
            }else if(flag==1){
                sql+=" and ase.Author_Affiliations like ?";
            }
        }
        if(!year.equals("#")){
            objects.add(year);
            if(flag==0){
                sql+=" where ase.Publication_Year= ?";
                flag=1;
            }else if(flag==1){
                sql+=" and ase.Publication_Year=?";
            }
        }
        if(!keywords.equals("#")){
            objects.add("%"+keywords+"%");
            if(flag==0){
                sql+=" where ase.Author_Keywords like ?";
                flag=1;
            }else if(flag==1){
                sql+=" and ase.Author_Keywords like ?";
            }
        }
        if(!conf.equals("#")){
            objects.add("%"+conf+"%");
            if(flag==0){
                sql+=" where ase.Publication_TiTle like ?";
                flag=1;
            }else if(flag==1){
                sql+=" and ase.Publication_Title like ?";
            }
        }
        sql+=";";
        Object[] objectArray  = objects.toArray();
           paperPOList= jdbcTemplate.query(sql, objectArray, new RowMapper<PaperPO>() {
                @Override
                public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                    PaperPO paperPO = new PaperPO();
                    /*paperPO.setAuthor(rs.getString("Authors"));*/
                    paperPO.setAffiliation(rs.getString("Author_Affiliations"));
                    paperPO.setTitle(rs.getString("Document_Title"));
                    paperPO.setPublishTime(rs.getString("Publication_Year"));
                    paperPO.setLink(rs.getString("PDF_Link"));
                    return paperPO;
                }
            });

        return paperPOList;
    }
    @Override
    public List<PaperPO> showMostPaperYear() {
        List<PaperPO>paperPOList1=new ArrayList<>();
        //select * from (select count(1) t_counts,t.Publication_Year from ase13_15_16_17_19 t group by t.Publication_Year order by count(1) desc)t_1 limit 0,10;可视化可以用
        String sql="select * from (select * from ase t_3 left join(select count(1) t_counts,t.Publication_Year n_Publication_Year from ase t group by t.Publication_Year)t_2 on t_3.Publication_Year = t_2.n_Publication_Year order by case when t_2.t_counts is null then 0 else t_2.t_counts end desc)t_4 limit 0,10;";
        paperPOList1= jdbcTemplate.query(sql, new Object[]{}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paperPO=new PaperPO();
                paperPO.setPublishTime(rs.getString("Publication_Year"));
                return paperPO;
            }
        });
        return paperPOList1;
    }
    @Override
    public List<PaperPO> showMostPaperConf() {
        List<PaperPO>paperPOList1=new ArrayList<>();
        //select * from (select count(1) t_counts,t.Publication_Year from ase13_15_16_17_19 t group by t.Publication_Year order by count(1) desc)t_1 limit 0,10;可视化可以用
        String sql="select * from (select * from ase t_3 left join(select count(1) t_counts,t.Publication_Title n_Publication_Title from ase t group by t.Publication_Title)t_2 on t_3.Publication_Title = t_2.n_Publication_Title order by case when t_2.t_counts is null then 0 else t_2.t_counts end desc)t_4 limit 0,10;";
        paperPOList1= jdbcTemplate.query(sql, new Object[]{}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paperPO=new PaperPO();
                paperPO.setConf(rs.getString("Publication_Title"));
                return paperPO;
            }
        });
        return paperPOList1;
    }

    @Override
    public List<PaperPO> searchPaperByKeywords(String keywords) {
        String sql="select * from ase as m where m.Author_Keywords like ?;";
        return jdbcTemplate.query(sql, new Object[]{"%"+keywords+"%"}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paperPO=new PaperPO();
             /*   paperPO.setAuthor(rs.getString("Authors"));*/
                paperPO.setAffiliation(rs.getString("Author_Affiliations"));
                paperPO.setTitle(rs.getString("Document_Title"));
                paperPO.setPublishTime(rs.getString("Publication_Year"));
                paperPO.setLink(rs.getString("PDF_Link"));
                return paperPO;
            }
        });
    }

    @Override
    public List<PaperPO> searchPaperByAffiliation(final String affiliation) {
        String sql="select * from ase as m where m.Author_Affiliations like ?;";
        return jdbcTemplate.query(sql, new Object[]{"%"+affiliation+"%"}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paperPO=new PaperPO();
             /*   paperPO.setAuthor(rs.getString("Authors"));*/
                paperPO.setAffiliation(rs.getString("Author_Affiliations"));
                paperPO.setTitle(rs.getString("Document_Title"));
                paperPO.setPublishTime(rs.getString("Publication_Year"));
                paperPO.setLink(rs.getString("PDF_Link"));
                return paperPO;
            }
        });
    }

    @Override
    public List<PaperPO> searchPaperByConf(String conf) {
        String sql="select * from ase as m where m.Publication_Title like ?;";
        return jdbcTemplate.query(sql, new Object[]{"%"+conf+"%"}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paperPO=new PaperPO();
            /*    paperPO.setAuthor(rs.getString("Authors"));*/
                paperPO.setAffiliation(rs.getString("Author_Affiliations"));
                paperPO.setTitle(rs.getString("Document_Title"));
                paperPO.setPublishTime(rs.getString("Publication_Year"));
                paperPO.setLink(rs.getString("PDF_Link"));
                return paperPO;
            }
        });
    }


}
