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
            em.getTransaction().begin();


            ElementoCatalogo libro1 = new Libro("978-3-16-148410-0", "Il Signore degli Anelli", 1954, 1178, "J.R.R. Tolkien", Genere.FANTASY);
            ElementoCatalogo libro2 = new Libro("978-0-7432-7356-5", "Il Codice da Vinci", 2003, 689, "Dan Brown", Genere.GIALLO);
            ElementoCatalogo libro3 = new Libro("978-0-553-21311-7", "IT", 1986, 1138, "Stephen King", Genere.HORROR);

            dao.save(libro1);
            dao.save(libro2);
            dao.save(libro3);


            ElementoCatalogo rivista1 = new Rivista("123-4-56-789012-3", "National Geographic", 2023, 120, Periodicita.MENSILE);
            ElementoCatalogo rivista2 = new Rivista("987-6-54-321098-7", "Science Today", 2024, 80, Periodicita.SETTIMANALE);

            dao.save(rivista1);
            dao.save(rivista2);


            Utente utente1 = new Utente("Mario", "Rossi", LocalDate.of(1990, 5, 15), "12345");
            Utente utente2 = new Utente("Luisa", "Bianchi", LocalDate.of(1985, 7, 20), "67890");

            dao.save(utente1);
            dao.save(utente2);


            Prestito prestito1 = new Prestito(utente1, libro1, LocalDate.now());
            Prestito prestito2 = new Prestito(utente2, libro2, LocalDate.now().minusDays(10)); // Prestito di 10 giorni fa
            Prestito prestito3 = new Prestito(utente1, rivista1, LocalDate.now().minusDays(20)); // Prestito di 20 giorni fa

            dao.save(prestito1);
            dao.save(prestito2);
            dao.save(prestito3);

            em.getTransaction().commit();
            System.out.println("Dati inseriti con successo!");

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
