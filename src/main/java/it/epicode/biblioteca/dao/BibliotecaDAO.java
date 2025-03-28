package it.epicode.biblioteca.dao;

import jakarta.persistence.*;
import java.util.List;

public class BibliotecaDAO {

    private final EntityManager em;


    public BibliotecaDAO(EntityManager em) {
        this.em = em;
    }


    public void save(Object obj) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(obj);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }


    public <T> T findById(Class<T> clazz, Long id) {
        return em.find(clazz, id);
    }


    public <T> T findByIsbn(Class<T> clazz, String isbn) {
        try {
            TypedQuery<T> query = em.createQuery(
                    "SELECT e FROM " + clazz.getSimpleName() + " e WHERE e.isbn = :isbn", clazz);
            query.setParameter("isbn", isbn);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public <T> List<T> findAll(Class<T> clazz) {
        TypedQuery<T> query = em.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e", clazz);
        return query.getResultList();
    }


    public <T> List<T> findByAnno(Class<T> clazz, int anno) {
        TypedQuery<T> query = em.createQuery(
                "SELECT e FROM " + clazz.getSimpleName() + " e WHERE e.annoPubblicazione = :anno", clazz);
        query.setParameter("anno", anno);
        return query.getResultList();
    }


    public List<Object> findByAutore(String autore) {
        TypedQuery<Object> query = em.createQuery(
                "SELECT l FROM Libro l WHERE l.autore = :autore", Object.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }


    public <T> List<T> findByTitolo(Class<T> clazz, String titolo) {
        TypedQuery<T> query = em.createQuery(
                "SELECT e FROM " + clazz.getSimpleName() + " e WHERE LOWER(e.titolo) LIKE LOWER(:titolo)", clazz);
        query.setParameter("titolo", "%" + titolo + "%");
        return query.getResultList();
    }


    public List<Object> findPrestitiAttivi(String numeroTessera) {
        TypedQuery<Object> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :tessera AND p.dataRestituzioneEffettiva IS NULL", Object.class);
        query.setParameter("tessera", numeroTessera);
        return query.getResultList();
    }


    public List<Object> findPrestitiScadutiNonRestituiti() {
        TypedQuery<Object> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < CURRENT_DATE AND p.dataRestituzioneEffettiva IS NULL", Object.class);
        return query.getResultList();
    }


    public void deleteByIsbn(Class<?> clazz, String isbn) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Query query = em.createQuery("DELETE FROM " + clazz.getSimpleName() + " e WHERE e.isbn = :isbn");
            query.setParameter("isbn", isbn);
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }
}
