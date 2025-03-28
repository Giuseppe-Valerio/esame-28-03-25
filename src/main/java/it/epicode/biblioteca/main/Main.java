package it.epicode.biblioteca.main;

import it.epicode.biblioteca.dao.BibliotecaDAO;
import it.epicode.biblioteca.entities.*;
import it.epicode.biblioteca.utils.Genere;
import it.epicode.biblioteca.utils.Periodicita;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();
        BibliotecaDAO dao = new BibliotecaDAO(em);

        try {

            ElementoCatalogo libro1 = new Libro("978-3-16-148410-0", "Il Signore degli Anelli", 1954, 1178, "J.R.R. Tolkien", Genere.FANTASY);
            dao.save(libro1);


            ElementoCatalogo rivista1 = new Rivista("123-4-56-789012-3", "National Geographic", 2023, 120, Periodicita.MENSILE);
            dao.save(rivista1);


            Utente utente1 = new Utente("Mario", "Rossi", LocalDate.of(1990, 5, 15), "12345");
            dao.save(utente1);


            Prestito prestito1 = new Prestito(utente1, libro1, LocalDate.now());
            dao.save(prestito1);

            System.out.println("Dati inseriti con successo!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
