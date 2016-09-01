package be.vdab.spring.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by aryastark on 01/09/16.
 */

@Repository
@Transactional
public class FilmRepository {
    @PersistenceContext
    private EntityManager em;

    public Film findById(int id) {
        return em.find(Film.class, id);
    }

    public void save(Film film) {
        em.persist(film);
    }

    public List<Film> findAll() {
      return   em.createQuery("select f from Film f", Film.class).getResultList();
    }






}
