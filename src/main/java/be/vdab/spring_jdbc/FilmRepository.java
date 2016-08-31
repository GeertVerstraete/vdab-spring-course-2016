package be.vdab.spring_jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by aryastark on 31/08/16.
 */
@Repository
public class FilmRepository {
    @Autowired
    private JdbcTemplate jt;


    public List<Film> findAllFilms() {
        return jt.query("SELECT * FROM film", (rs, rowNum) -> {
            return new Film(
                    rs.getString("title"),
                    rs.getInt("release_year"),
                    rs.getString("description")
            );
        });
    }
}
