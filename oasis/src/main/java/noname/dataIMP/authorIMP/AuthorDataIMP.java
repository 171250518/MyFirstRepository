package noname.dataIMP.authorIMP;


import noname.PO.*;
import noname.VO.AuthorVO;
import noname.VO.PaperVO;
import noname.data.author.AuthorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorDataIMP implements AuthorData {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<AuthorPO> getAuthorPOByName(String name) {
        String sql = "select * from author where Author_name like ? order by Author_id";
        return jdbcTemplate.query(sql, new Object[]{"%"+name+"%"}, new RowMapper<AuthorPO>() {
            @Override
            public AuthorPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                AuthorPO author=new AuthorPO();
                author.setId(rs.getInt("Author_id"));
                author.setName(rs.getString("Author_name"));
                return author;
            }
        });
    }

    @Override
    public List<AuthorPO> getAuthorAffiliationById(int author_id) {
        String sql = "select * from affiliation " +
                "left join author_affiliation using (Affiliation_id) " +
                "left join author using (Author_id) " +
                "where Author_id = ? order by Affiliation_id";
        return jdbcTemplate.query(sql, new Object[]{author_id}, new RowMapper<AuthorPO>() {
            @Override
            public AuthorPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                AuthorPO author=new AuthorPO();
                author.setId(rs.getInt("Author_id"));
                author.setName(rs.getString("Author_name"));
                author.setInstitution(rs.getString("Affiliation_name"));
                author.setRank(rs.getDouble("Author_rank"));
                return author;
            }
        });
    }

    @Override
    public List<AuthorPO> getAuthorDirectionById(int author_id) {
        String sql = "select * from direction " +
                "left join author_direction using (Direction_id) " +
                "left join author using (Author_id) " +
                "where Author_id = ? order by Direction_id";
        return jdbcTemplate.query(sql, new Object[]{author_id}, new RowMapper<AuthorPO>() {
            @Override
            public AuthorPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                AuthorPO author=new AuthorPO();
                author.setId(rs.getInt("Author_id"));
                author.setName(rs.getString("Author_name"));
                author.setResearchDirection(rs.getString("Direction_name"));
                return author;
            }
        });
    }

    @Override
    public List<AuthorPO> getTopAuthors() {
        String sql = "select * from author order by Author_rank DESC ";
        return jdbcTemplate.query(sql, new Object[]{}, new RowMapper<AuthorPO>() {
            @Override
            public AuthorPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                AuthorPO author = new AuthorPO();
                author.setName(rs.getString("Author_name"));
                author.setId(rs.getInt("Author_id"));
                author.setRank(rs.getDouble("Author_rank"));
                return author;
            }
        });
    }
//
//    @Override
//    public List<Author_Paper_PO> getTopAuthors() {
//        String sql = "select * from author left join author_paper using(Author_id) left join paper using(Paper_id) order by Author_id;";
//        return jdbcTemplate.query(sql, new Object[]{}, new RowMapper<Author_Paper_PO>() {
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
    public List<PaperPO> getPaperIdByAuthorId(int author_id) {
        String sql = "select distinct Paper_id,Title,PDF_linking,Publication_year,Citation_count from author_paper  " +
                "left join paper using (Paper_id) " +
                "where Author_id = ? order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{author_id}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paper = new PaperPO();
                paper.setId(rs.getInt("Paper_id"));
                paper.setTitle(rs.getString("Title"));
                paper.setLink(rs.getString("PDF_linking"));
                paper.setCitationCount(rs.getInt("Citation_count"));
                paper.setPublishTime(rs.getString("Publication_year"));
                return paper;
            }
        });
    }

    @Override
    public List<PaperPO> getPaperIdByAuthorIdAndPublishTime(int author_id, String year) {
        String sql = "select * from author_paper  " +
                "left join paper using (Paper_id) " +
                "where Author_id = ? and Publication_year like ? order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{author_id, "%"+year+"%"}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paper = new PaperPO();
                paper.setId(rs.getInt("Paper_id"));
                return paper;
            }
        });
    }

    @Override
    public List<PaperPO> getPaperIdByAuthorIdAndConference(int author_id, String conf) {
        String sql = "select * from author_paper  " +
                "left join paper using (Paper_id) " +
                "left join conference using (Conference_id)" +
                "where Author_id = ? and Conference_name like ? order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{author_id, "%"+conf+"%"}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paper = new PaperPO();
                paper.setId(rs.getInt("Paper_id"));
                return paper;
            }
        });
    }

    @Override
    public List<AuthorPO> getPaperAuthorById(int id) {
        String sql = "select * from author " +
                "left join author_paper using (Author_id)" +
                "where Paper_id = ? order by Author_id";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<AuthorPO>() {
            @Override
            public AuthorPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                AuthorPO paper = new AuthorPO();
                paper.setId(rs.getInt("Author_id"));
                paper.setName(rs.getString("Author_name"));
                return paper;
            }
        });
    }

    @Override
    public List<ResearchDirectionPO> getPaperDirectionById(int id) {
        String sql = "select * from direction " +
                "left join paper_direction using (Direction_id) " +
                "left join paper using (Paper_id) " +
                "where Paper_id = ? order by Direction_id";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<ResearchDirectionPO>() {
            @Override
            public ResearchDirectionPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                ResearchDirectionPO re = new ResearchDirectionPO();
                re.setId(rs.getInt("Direction_id"));
                re.setName(rs.getString("Direction_name"));
                return re;
            }
        });
    }

    @Override
    public List<AffiliationPO> getPaperAffiliationById(int id) {
        String sql = "select * from affiliation " +
                "left join paper_affiliation using (Affiliation_id) " +
                "where Paper_id = ? order by Affiliation_id";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<AffiliationPO>() {
            @Override
            public AffiliationPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                AffiliationPO aff = new AffiliationPO();
                aff.setId(rs.getInt("Affiliation_id"));
                aff.setName(rs.getString("Affiliation_name"));
                return aff;
            }
        });
    }

    @Override
    public List<PaperPO> getPaperIdByAuthorIdAndCoAuthor(int id, int coid) {
        String sql = "select * from author_paper ap1 " +
                "where Author_id = ? and ? in (select Author_id from author_paper ap2 where ap2.Paper_id = ap1.Paper_id) order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{id, coid}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paper = new PaperPO();
                paper.setId(rs.getInt("Paper_id"));
                return paper;
            }
        });
    }

    @Override
    public List<AuthorVO> getCoAuthorIdById(int id) {
        String sql = "select distinct Author_id,Affiliation_name,Author_name from author " +
                "    left join author_affiliation using (Author_id) " +
                "    left join affiliation using (Affiliation_id) " +
                "where Author_id in " +
                "      (select Author_id from author_paper ap1 where ap1.Paper_id in " +
                "        (select Paper_id from author_paper ap2 where ap2.Author_id=?)) " +
                "order by Author_id";
//        String sql = "select * from author_paper ap1 left join author using (Author_id) left join author_affiliation using (Author_id) left join affiliation using (Affiliation_id)" +
//                "where ap1.Paper_id in (select ap2.Paper_id from author_paper ap2 where ap2.Author_id = ?) order by ap1.Author_id";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<AuthorVO>() {
            @Override
            public AuthorVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                AuthorVO author = new AuthorVO();
                author.setId(rs.getInt("Author_id"));
                author.setInstitution(rs.getString("Affiliation_name"));
                author.setName(rs.getString("Author_name"));
                return author;
            }
        });
    }

    @Override
    public int getAuthorPaperNumByIdAndYear(int id, String year) {
        String sql = "select * from author left join author_paper using (Author_id) left join paper using (Paper_id) where Author_id = ? and Publication_year = ? order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{id, year}, new RowMapper<Author_Paper_PO>() {
            @Override
            public Author_Paper_PO mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Author_Paper_PO();
            }
        }).size();
    }

    @Override
    public List<Author_Paper_PO> getAuthorPaperByIdAndYear(int id, String year) {
        String sql = "select * from author left join author_paper using (Author_id) left join paper using (Paper_id) where Author_id = ? and Publication_year = ? order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{id, year}, new RowMapper<Author_Paper_PO>() {
            @Override
            public Author_Paper_PO mapRow(ResultSet rs, int rowNum) throws SQLException {
                Author_Paper_PO author=new Author_Paper_PO();
                author.setAuthorId(rs.getInt("Author_id"));
                author.setName(rs.getString("Author_name"));
                author.setC_count(rs.getInt("Citation_count"));
                author.setPaperId(rs.getInt("Paper_id"));
                author.setYear(Integer.parseInt(rs.getString("Publication_year")));
                return author;
            }
        });
    }


    @Override
    public List<PaperPO> getPaperBasicById(int id) {
        String sql = "select * from paper " +
                "left join conference using (Conference_id)" +
                "where Paper_id = ? order by Paper_id";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paper = new PaperPO();
                paper.setId(rs.getInt("Paper_id"));
                paper.setTitle(rs.getString("Title"));
                paper.setCitationCount(rs.getInt("Citation_count"));
                paper.setConf(rs.getString("Conference_name"));
                paper.setLink(rs.getString("PDF_linking"));
                paper.setPublishTime(rs.getString("Publication_year"));
                return paper;
            }
        });
    }

    @Override
    public List<PaperPO> getPaperIdByAuthorIdOrderByCitation(int id) {
        String sql = "select distinct Paper_id,Title,PDF_linking,Publication_year,Citation_count from author_paper  " +
                "left join paper using (Paper_id) " +
                "where Author_id = ? order by Citation_count DESC ";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<PaperPO>() {
            @Override
            public PaperPO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PaperPO paper = new PaperPO();
                paper.setId(rs.getInt("Paper_id"));
                paper.setTitle(rs.getString("Title"));
                paper.setLink(rs.getString("PDF_linking"));
                paper.setPublishTime(rs.getString("Publication_year"));
                return paper;
            }
        });
    }


}
