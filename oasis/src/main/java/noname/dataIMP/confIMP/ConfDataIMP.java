package noname.dataIMP.confIMP;

import noname.PO.*;
import noname.VO.PaperVO;
import noname.data.conf.ConfData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ConfDataIMP implements ConfData {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ConferencePO> getConferenceByName(String name) {
        String sql = "select * from conference where Conference_name like ? order by Conference_id";
        return jdbcTemplate.query(sql, new Object[]{"%"+name+"%"}, new RowMapper<ConferencePO>() {
            @Override
            public ConferencePO mapRow(ResultSet rs, int rowNum) throws SQLException {
                ConferencePO conf=new ConferencePO();
                conf.setId(rs.getInt("Conference_id"));
                conf.setName(rs.getString("Conference_name"));
                return conf;
            }
        });
    }

    @Override
    public ConferencePO getConferenceById(int id) {
        String sql = "select * from conference where Conference_id = ? order by Conference_id";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<ConferencePO>() {
            @Override
            public ConferencePO mapRow(ResultSet rs, int rowNum) throws SQLException {
                ConferencePO conf=new ConferencePO();
                conf.setId(rs.getInt("Conference_id"));
                conf.setName(rs.getString("Conference_name"));
                conf.setRank(rs.getDouble("Conference_rank"));
                return conf;
            }
        }).get(0);
    }


    @Override
    public List<PaperPO> getPaperIdByConferenceIdAndAuthorId(int id, int auid) {
        String sql = "select * from paper " +
                "left join author_paper using (Paper_id) " +
                "where Conference_id = ? and Author_id = ? order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{id, auid}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paper = new PaperPO();
                paper.setId(rs.getInt("Paper_id"));
                return paper;
            }
        });
    }

    @Override
    public List<PaperPO> getPaperIdByConferenceId(int id) {
        String sql = "select * from paper  " +
                "where Conference_id = ? order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paper = new PaperPO();
                paper.setId(rs.getInt("Paper_id"));
                paper.setTitle(rs.getString("Title"));
                paper.setPublishTime(rs.getString("Publication_year"));
                paper.setLink(rs.getString("PDF_linking"));
                paper.setCitationCount(rs.getInt("Citation_count"));
                return paper;
            }
        });
    }

    @Override
    public List<PaperPO> getPaperIdByConferenceIdAndYear(int id, String year) {
        String sql = "select * from paper  " +
                "where Conference_id = ? and Publication_year = ? order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{id, year}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paper = new PaperPO();
                paper.setId(rs.getInt("Paper_id"));
                return paper;
            }
        });
    }

    @Override
    public Integer getConferencePaperNumByIdAndYear(int id, String year) {
        String sql = "select * from paper  " +
                "where Conference_id = ? and Publication_year = ? order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{id, year}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paper = new PaperPO();
                return paper;
            }
        }).size();
    }

    @Override
    public List<Conference_Paper_PO> getConferencePaperByIdAndYear(int id, String year) {
        String sql = "select * from paper " +
                "left join conference using (Conference_id) " +
                "where Conference_id = ? and Publication_year = ? order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{id, year}, new RowMapper<Conference_Paper_PO>() {
            @Override
            public Conference_Paper_PO mapRow(ResultSet rs, int rowNum) throws SQLException {
                Conference_Paper_PO paper = new Conference_Paper_PO();
                paper.setConferenceId(id);
                paper.setC_count(rs.getInt("Citation_count"));
                paper.setName(rs.getString("Conference_name"));
                paper.setPaperId(rs.getInt("Paper_id"));
                paper.setYear(Integer.parseInt(rs.getString("Publication_year")));
                return paper;
            }
        });
    }

    @Override
    public List<ConferencePO> getTopConferences() {
        String sql = "select * from conference order by Conference_rank DESC ";
        return jdbcTemplate.query(sql, new Object[]{}, new RowMapper<ConferencePO>() {
            @Override
            public ConferencePO mapRow(ResultSet rs, int rowNum) throws SQLException {
                ConferencePO conf = new ConferencePO();
                conf.setName(rs.getString("Conference_name"));
                conf.setId(rs.getInt("Conference_id"));
                conf.setRank(rs.getDouble("Conference_rank"));
                return conf;
            }
        });
    }
//    @Override
//    public List<Conference_Paper_PO> getTopConferences() {
//        String sql = "select * from conference left join paper using(Conference_id) order by Conference_id;";
//        return jdbcTemplate.query(sql, new Object[]{}, new RowMapper<Conference_Paper_PO>() {
//            @Override
//            public Conference_Paper_PO mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Conference_Paper_PO conf = new Conference_Paper_PO();
//                conf.setName(rs.getString("Conference_name"));
//                conf.setConferenceId(rs.getInt("Conference_id"));
//                conf.setPaperId(rs.getInt("Paper_id"));
//                conf.setC_count(rs.getInt("Citation_count"));
//                conf.setYear(Integer.parseInt(rs.getString("Publication_year").equals("null")?"0":rs.getString("Publication_year")));
//                return conf;
//            }
//        });
//    }

    @Override
    public List<AuthorPO> getAuthorsOfConfById(int id) {
        String sql = "select * from author_paper  " +
                "left join paper using (Paper_id) " +
                "left join author using (Author_id)" +
                "where Conference_id = ? order by Author_id";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<AuthorPO>() {
            @Override
            public AuthorPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                AuthorPO authorPO = new AuthorPO();
                authorPO.setId(rs.getInt("Author_id"));
                authorPO.setName(rs.getString("Author_name"));
                return authorPO;
            }
        });
    }

    @Override
    public List<AuthorPO> getTopAuthorsOfConfById(int id) {
        String sql = "select * from author " +
                "where ? in (select Conference_id from paper where paper.Paper_id in " +
                "(select author_paper.Paper_id from author_paper where author_paper.Author_id = author.Author_id))" +
                " order by Author_rank DESC ";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<AuthorPO>() {
            @Override
            public AuthorPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                AuthorPO authorPO = new AuthorPO();
                authorPO.setId(rs.getInt("Author_id"));
                authorPO.setName(rs.getString("Author_name"));
                authorPO.setRank(rs.getDouble("Author_rank"));
                return authorPO;
            }
        });
    }
//    @Override
//    public List<Author_Paper_PO> getTopAuthorsOfConfById(int id) {
//        String sql = "select * from author_paper  " +
//                "left join paper using (Paper_id) " +
//                "left join author using (Author_id)" +
//                "where Conference_id = ? order by Author_id";
//        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<Author_Paper_PO>() {
//            @Override
//            public Author_Paper_PO mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Author_Paper_PO authorPO = new Author_Paper_PO();
//                authorPO.setAuthorId(rs.getInt("Author_id"));
//                authorPO.setName(rs.getString("Author_name"));
//                authorPO.setPaperId(rs.getInt("Paper_id"));
//                authorPO.setYear(Integer.parseInt(rs.getString("Publication_year")));
//                authorPO.setC_count(rs.getInt("Citation_count"));
//                return authorPO;
//            }
//        });
//    }


}
