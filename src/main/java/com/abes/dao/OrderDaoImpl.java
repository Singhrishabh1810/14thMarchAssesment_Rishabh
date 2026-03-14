package com.abes.dao;

import com.abes.entity.Order;
import com.abes.entity.Customer;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class OrderDaoImpl implements OrderDao {
    
   
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("abes");

    @Override
    public boolean addOrder(Order order, int custId) {
     
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
           
            Customer customer = em.find(Customer.class, Integer.valueOf(custId));
            if (customer != null) {
                order.setCustomer(customer); 
                em.persist(order);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public Order getOrder(int orderId) {
        
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Order.class, Integer.valueOf(orderId));
        } finally {
            em.close();
        }
    }

    @Override
    public List<Order> getOrders(String custName) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT o FROM Order o WHERE o.customer.customerName = :name";
            TypedQuery<Order> query = em.createQuery(jpql, Order.class);
            query.setParameter("name", custName);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}