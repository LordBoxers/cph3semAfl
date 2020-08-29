/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Mibsen
 */
public class MakeTestData {
    public static void main(String[] args) {
        BankCustomer bc1 = new BankCustomer("John", "Doe", "01" , 133.7, 5, "jeps");
        BankCustomer bc2 = new BankCustomer("John", "Doe", "01" , 133.7, 5, "jeps");
        BankCustomer bc3 = new BankCustomer("John", "Doe", "01" , 133.7, 5, "jeps");
        BankCustomer bc4 = new BankCustomer("John", "Doe", "01" , 133.7, 5, "jeps");
        BankCustomer bc5 = new BankCustomer("John", "Doe", "01" , 133.7, 5, "jeps");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(bc1);
            em.persist(bc2);
            em.persist(bc3);
            em.persist(bc4);
            em.persist(bc5);
            em.getTransaction().commit();
        } finally {
            em.close();
            emf.close();
    }
        
    }
}
