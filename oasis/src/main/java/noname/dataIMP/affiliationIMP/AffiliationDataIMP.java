package noname.dataIMP.affiliationIMP;

import noname.PO.*;
import noname.VO.AuthorVO;
import noname.data.affiliation.AffiliationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class AffiliationDataIMP implements AffiliationData {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AffiliationPO> getAffiliationByName(String name) {
        String sql = "select * from affiliation where Affiliation_name like ? order by Affiliation_id";
        return jdbcTemplate.query(sql, new Object[]{"%"+name+"%"}, new RowMapper<AffiliationPO>() {
            @Override
            public AffiliationPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                AffiliationPO affiliation=new AffiliationPO();
                affiliation.setId(rs.getInt("Affiliation_id"));
                affiliation.setName(rs.getString("Affiliation_name"));
                return affiliation;
            }
        });
    }

    @Override
    public List<AffiliationPO> getAffiliationAuthorById(int id) {
        String sql = "select * from affiliation left join author_affiliation using (Affiliation_id) left join author using (Author_id) where Affiliation_id = ? order by Author_id";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<AffiliationPO>() {
            @Override
            public AffiliationPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                AffiliationPO affiliation=new AffiliationPO();
                affiliation.setId(rs.getInt("Affiliation_id"));
                affiliation.setName(rs.getString("Affiliation_name"));
                affiliation.setAuthors(rs.getString("Author_name"));
                affiliation.setRank(rs.getDouble("Affiliation_rank"));
                return affiliation;
            }
        });
    }

    @Override
    public List<Affiliation_Paper_PO> getAffiliationPaperById(int id) {
        String sql = "select * from affiliation left join paper_affiliation using (Affiliation_id) left join paper using (Paper_id) where Affiliation_id = ? order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<Affiliation_Paper_PO>() {
            @Override
            public Affiliation_Paper_PO mapRow(ResultSet rs, int rowNum) throws SQLException {
                Affiliation_Paper_PO affiliation=new Affiliation_Paper_PO();
                affiliation.setAffiliationId(rs.getInt("Affiliation_id"));
                affiliation.setName(rs.getString("Affiliation_name"));
                affiliation.setC_count(rs.getInt("Citation_count"));
                affiliation.setPaperId(rs.getInt("Paper_id"));
                affiliation.setYear((rs.getInt("Publication_year")));
                return affiliation;
            }
        });
    }

    @Override
    public List<Affiliation_Paper_PO> getAffiliationPaperByIdAndYear(int id, String year) {
        String sql = "select * from affiliation left join paper_affiliation using (Affiliation_id) left join paper using (Paper_id) where Affiliation_id = ? and Publication_year = ? order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{id, year}, new RowMapper<Affiliation_Paper_PO>() {
            @Override
            public Affiliation_Paper_PO mapRow(ResultSet rs, int rowNum) throws SQLException {
                Affiliation_Paper_PO affiliation=new Affiliation_Paper_PO();
                affiliation.setAffiliationId(rs.getInt("Affiliation_id"));
                affiliation.setName(rs.getString("Affiliation_name"));
                affiliation.setC_count(rs.getInt("Citation_count"));
                affiliation.setPaperId(rs.getInt("Paper_id"));
                affiliation.setYear((rs.getInt("Publication_year")));
                return affiliation;
            }
        });
    }

    @Override
    public int getAffiliationPaperNumByIdAndYear(int id, String year) {
        String sql = "select * from affiliation left join paper_affiliation using (Affiliation_id) left join paper using (Paper_id) where Affiliation_id = ? and Publication_year = ? order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{id, year}, new RowMapper<Affiliation_Paper_PO>() {
            @Override
            public Affiliation_Paper_PO mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Affiliation_Paper_PO();
            }
        }).size();
    }

    @Override
    public List<AffiliationPO> getTopAffiliations() {
        String sql = "select * from affiliation order by Affiliation_rank DESC ";
        return jdbcTemplate.query(sql, new Object[]{}, new RowMapper<AffiliationPO>() {
            @Override
            public AffiliationPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                AffiliationPO aff = new AffiliationPO();
                aff.setName(rs.getString("Affiliation_name"));
                aff.setId(rs.getInt("Affiliation_id"));
                aff.setRank(rs.getDouble("Affiliation_rank"));
                return aff;
            }
        });
    }
//    @Override
//    public List<Affiliation_Paper_PO> getTopAffiliations() {
//        String sql = "select * from affiliation left join paper_affiliation using(Affiliation_id) left join paper using(Paper_id) order by Affiliation_id;";
//        return jdbcTemplate.query(sql, new Object[]{}, new RowMapper<Affiliation_Paper_PO>() {
//            @Override
//            public Affiliation_Paper_PO mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Affiliation_Paper_PO aff = new Affiliation_Paper_PO();
//                aff.setName(rs.getString("Affiliation_name"));
//                aff.setAffiliationId(rs.getInt("Affiliation_id"));
//                aff.setPaperId(rs.getInt("Paper_id"));
//                aff.setC_count(rs.getInt("Citation_count"));
//                aff.setYear(Integer.parseInt(rs.getString("Publication_year").equals("null")?"0":rs.getString("Publication_year")));
//                return aff;
//            }
//        });
//    }

    @Override
    public List<PaperPO> getPaperIdByAffiliationId(int id) {
        String sql = "select * from paper_affiliation  " +
                "left join paper using (Paper_id) " +
                "where Affiliation_id = ? order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paper = new PaperPO();
                paper.setId(rs.getInt("Paper_id"));
                return paper;
            }
        });
    }

    @Override
    public List<PaperPO> getPaperIdByAffiliationIdAndAuthorId(int id, int auId) {
        String sql = "select * from paper_affiliation  " +
                "left join paper using (Paper_id) " +
                "where Affiliation_id = ? and ? in (select Author_id from author_paper where author_paper.Paper_id = paper.Paper_id) order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{id, auId}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paper = new PaperPO();
                paper.setId(rs.getInt("Paper_id"));
                return paper;
            }
        });
    }

    @Override
    public List<PaperPO> getPaperIdByAffiliationIdAndYear(int id, String year) {
        String sql = "select * from paper_affiliation  " +
                "left join paper using (Paper_id) " +
                "where Affiliation_id = ? and Publication_year = ? order by Paper_id";
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
    public List<AuthorPO> getAuthorsOfAffById(int id) {
        String sql = "select distinct Author_id,Author_name from author_affiliation  " +
                "left join author using (Author_id) " +
                "where Affiliation_id = ? order by Author_id";
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
    public List<AuthorPO> getAuthorDirectionOfAffiliation(int id) {
        String sql = "select * from author_direction  " +
                "left join direction using (Direction_id) " +
                "where Author_id = ? order by Author_id";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<AuthorPO>() {
            @Override
            public AuthorPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                AuthorPO authorPO = new AuthorPO();
                authorPO.setId(rs.getInt("Author_id"));
                authorPO.setResearchDirection(rs.getString("Direction_name"));
                return authorPO;
            }
        });
    }

    @Override
    public List<ResearchDirectionPO> getAffiliationTopDirectionsById(int id) {
        String sql = "select Direction_id, Direction_name, count(Paper_id) from paper_direction left join direction using (Direction_id) where Paper_id in (select Paper_id from paper_affiliation where Affiliation_id = ?) group by Direction_id, Direction_name ORDER BY count(Paper_id) DESC";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<ResearchDirectionPO>() {
            @Override
            public ResearchDirectionPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                ResearchDirectionPO res = new ResearchDirectionPO();
                res.setId(rs.getInt("Direction_id"));
                res.setName(rs.getString("Direction_name"));
                res.setPaper_number(rs.getInt("count(Paper_id)"));
                return res;
            }
        });
    }


}
