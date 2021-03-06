package tester;

import entity.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Tester {

        
        
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee("xa12tt", "Kurt", "Wonnegut", new BigDecimal(335567)));
            em.persist(new Employee("hyu654", "Hanne", "Olsen", new BigDecimal(435867)));
            em.persist(new Employee("uio876", "Jan", "Olsen", new BigDecimal(411567)));
            em.persist(new Employee("klo999", "Irene", "Petersen", new BigDecimal(33567)));
            em.persist(new Employee("jik666", "Tian", "Wonnegut", new BigDecimal(56567)));
            em.getTransaction().commit();

            //Complete all these small tasks. Your will find the solution to all, but the last,
            //In this document: https://en.wikibooks.org/wiki/Java_Persistence/JPQL#JPQL_supported_functions
            
            //1) Create a query to fetch all employees with a salary > 100000 and print out all the salaries
            salaryAbove100k();
            
            //2) Create a query to fetch the employee with the id "klo999" and print out the firstname
            employeeByID("klo999");

            //3) Create a query to fetch the highest salary and print the value
            highestSalaries();
            
            //4) Create a query to fetch the firstName of all Employees and print the names
            firstNames();
            
            //5 Create a query to calculate the number of employees and print the number
            employeeCount();
            
            //6 Create a query to fetch the Employee with the higest salary and print all his details
            highestEmployeeSalary();
            
        } finally {
            em.close();
            emf.close();
        }
    }
    public static String salaryAbove100k() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select e FROM Employee e WHERE e.salary > 100000");
        return query.getResultList().toString();
    }
    public static String employeeByID(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select e FROM Employee e WHERE e.id = :id");
        return query.getResultList().toString();
    }
    public static String highestSalaries() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select MAX(e.salary) FROM Employee e");
        return query.getResultList().toString();
    }
    private static String firstNames() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select e.firstName, e.lastName FROM Employee e");
        List<Object[]> result = query.getResultList();
        return result.toString();
    }
    public static String employeeCount() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT COUNT(e) FROM Employee e");
        return query.getResultList().toString();
    }
    public static String highestEmployeeSalary() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT e.firstname, e.lastName, e.id, e.salary FROM Employee e WHERE MAX(e.salary)");
        return query.getResultList().toString();
    }

}
