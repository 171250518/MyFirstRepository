package noname.dataIMP.researchDirectionIMP;


import noname.PO.*;
import noname.VO.AuthorVO;
import noname.data.researchDirection.ResearchDirectionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ResearchDirectionDataIMP implements ResearchDirectionData {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ResearchDirectionPO> getResearchDirectionPOByName(String name) {
        String sql = "select * from direction where Direction_name like ? order by Direction_id";
        return jdbcTemplate.query(sql, new Object[]{"%"+name+"%"}, new RowMapper<ResearchDirectionPO>() {
            @Override
            public ResearchDirectionPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                ResearchDirectionPO author=new ResearchDirectionPO();
                author.setId(rs.getInt("Direction_id"));
                author.setName(rs.getString("Direction_name"));
                return author;
            }
        });
    }

    @Override
    public List<ResearchDirectionPO> getDirectionAuthorById(int id) {
        String sql = "select * from direction " +
                "left join author_direction using (Direction_id) " +
                "left join author using (Author_id) " +
                "where Direction_id = ? order by Author_id";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<ResearchDirectionPO>() {
            @Override
            public ResearchDirectionPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                ResearchDirectionPO dir_author=new ResearchDirectionPO();
                dir_author.setAuthors(rs.getString("Author_name"));
                dir_author.setName(rs.getString("Direction_name"));
                dir_author.setRank(rs.getDouble("Direction_rank"));
                return dir_author;
            }
        });
    }

    @Override
    public List<PaperPO> getResearchDirectionPaperById(int id) {
        String sql = "select * from direction " +
                "left join paper_direction using (Direction_id) " +
                "left join paper using (Paper_id) " +
                "where direction.Direction_id = ? order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paper=new PaperPO();
                paper.setId(rs.getInt("Paper_id"));
                paper.setCitationCount(rs.getInt("Citation_count"));
                return paper;
            }
        });
    }

    @Override
    public List<ResearchDirectionPO> getTopReDirections() {
        String sql = "select * from direction order by Direction_rank DESC ";
        return jdbcTemplate.query(sql, new Object[]{}, new RowMapper<ResearchDirectionPO>() {
            @Override
            public ResearchDirectionPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                ResearchDirectionPO direction = new ResearchDirectionPO();
                direction.setName(rs.getString("Direction_name"));
                direction.setId(rs.getInt("Direction_id"));
                direction.setRank(rs.getDouble("Direction_rank"));
                return direction;
            }
        });
    }
//    @Override
//    public List<ResearchDirection_Paper_PO> getTopReDirections() {
//        String sql = "select * from direction left join paper_direction using(Direction_id) left join paper using(Paper_id) order by direction.Direction_id;";
//        return jdbcTemplate.query(sql, new Object[]{}, new RowMapper<ResearchDirection_Paper_PO>() {
//            @Override
//            public ResearchDirection_Paper_PO mapRow(ResultSet rs, int rowNum) throws SQLException {
//                ResearchDirection_Paper_PO direction = new ResearchDirection_Paper_PO();
//                direction.setName(rs.getString("Direction_name"));
//                direction.setResearchDirectionId(rs.getInt("Direction_id"));
//                direction.setPaperId(rs.getInt("Paper_id"));
//                direction.setC_count(rs.getInt("Citation_count"));
//                direction.setYear(Integer.parseInt(rs.getString("Publication_year").equals("null")?"0":rs.getString("Publication_year")));
//                return direction;
//            }
//        });
//    }

    @Override
    public List<PaperPO> getPaperIdByReId(int id) {
        String sql = "select * from paper_direction  " +
                "left join paper using (Paper_id) " +
                "where paper_direction.Direction_id = ? order by Paper_id";
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
    public List<PaperPO> getPaperIdByReIdAndPublishTime(int id, String year) {
        String sql = "select * from paper_direction  " +
                "left join paper using (Paper_id) " +
                "where Direction_id = ? and Publication_year like ? order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{id, "%"+year+"%"}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paper = new PaperPO();
                paper.setId(rs.getInt("Paper_id"));
                return paper;
            }
        });
    }

    @Override
    public List<PaperPO> getPaperIdByReIdAndAuthorId(int id, int auid) {
        String sql = "select * from paper_direction  " +
                "left join paper using (Paper_id) " +
                "where Direction_id = ? and ? in (select Author_id from author_paper where author_paper.Paper_id = paper.Paper_id) order by Paper_id";
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
    public int getRePaperNumByIdAndYear(int id, String year) {
        String sql = "select * from direction left join paper_direction using (Direction_id) left join paper using (Paper_id) where direction.Direction_id = ? and Publication_year = ? order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{id, year}, new RowMapper<ResearchDirection_Paper_PO>() {
            @Override
            public ResearchDirection_Paper_PO mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ResearchDirection_Paper_PO();
            }
        }).size();
    }

    @Override
    public List<ResearchDirection_Paper_PO> getRePaperByIdAndYear(int id, String year) {
        String sql = "select * from direction left join paper_direction using (Direction_id) left join paper using (Paper_id) where direction.Direction_id = ? and Publication_year = ? order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{id, year}, new RowMapper<ResearchDirection_Paper_PO>() {
            @Override
            public ResearchDirection_Paper_PO mapRow(ResultSet rs, int rowNum) throws SQLException {
                ResearchDirection_Paper_PO paper=new ResearchDirection_Paper_PO();
                paper.setResearchDirectionId(rs.getInt("Direction_id"));
                paper.setName(rs.getString("Direction_name"));
                paper.setC_count(rs.getInt("Citation_count"));
                paper.setPaperId(rs.getInt("Paper_id"));
                paper.setYear(Integer.parseInt(rs.getString("Publication_year")));
                return paper;
            }
        });
    }

    @Override
    public List<AuthorPO> getTopReAuthors(int id) {
        String sql = "select * from author where ? in (select Direction_id from author_direction where author.Author_id=author_direction.Author_id) order by Author_rank DESC ";
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
//    public List<Author_Paper_PO> getTopReAuthors(int id) {
//        String sql = "select * from author left join author_paper using(Author_id) left join paper using(Paper_id) where ? in (select Direction_id from author_direction where author_direction.Author_id = author_paper.Author_id) order by Author_id;";
//        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<Author_Paper_PO>() {
//            @Override
//            public Author_Paper_PO mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Author_Paper_PO author = new Author_Paper_PO();
//                author.setName(rs.getString("Author_name"));
//                author.setAuthorId(rs.getInt("Author_id"));
//                author.setPaperId(rs.getInt("Paper_id"));
//                author.setC_count(rs.getInt("Citation_count"));
//                author.setYear(Integer.parseInt(rs.getString("Publication_year").equals("null")?"0":rs.getString("Publication_year")));
//                return author;
//            }
//        });
//    }

    @Override
    public List<AuthorVO> getReAuthorsById(int id) {
        String sql = "select distinct Author_id,Author_name from author_direction  " +
                "left join author using (Author_id) " +
                "where Direction_id = ? order by Author_id";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<AuthorVO>() {
            @Override
            public AuthorVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                AuthorVO authorVO = new AuthorVO();
                authorVO.setId(rs.getInt("Author_id"));
                authorVO.setName(rs.getString("Author_name"));
                return authorVO;
            }
        });
    }

    @Override
    public List<AffiliationPO> getResearchDirectionTopAffiliation(int id) {
        String sql = "select Affiliation_id,Affiliation_name,count(Paper_id) from paper_affiliation left join affiliation using (Affiliation_id) where Paper_id in (select Paper_id from paper_direction where Direction_id = ?) group by Affiliation_id, Affiliation_name ORDER BY count(Paper_id) DESC";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<AffiliationPO>() {
            @Override
            public AffiliationPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                AffiliationPO aff = new AffiliationPO();
                aff.setId(rs.getInt("Affiliation_id"));
                aff.setName(rs.getString("Affiliation_name"));
                aff.setPaper_number(rs.getInt("count(Paper_id)"));
                return aff;
            }
        });
    }


}
