package com.abes.client;

import com.abes.dao.OrderDao;
import com.abes.dao.OrderDaoImpl;
import com.abes.entity.Order;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DaoClient {
    static OrderDao dao = new OrderDaoImpl(); 
    static Scanner scan = new Scanner(System.in); 

    public static void main(String[] args) { 
        String opt = null; 
        do { 
            System.out.println("1-ADD, 2--VIEW BY ORDER ID, 3--VIEW BY CUSTOMER NAME"); 
            int mtype = scan.nextInt(); 
            processMenu(mtype); 
            System.out.println("press y to continue"); 
            opt = scan.next(); 
        } while (opt.equalsIgnoreCase("y")); 
    } 

    public static void processMenu(int mtype) { 
        switch (mtype) { 
            case 1: 
                addOrder(); 
                break; 
            case 2: 
                viewOrderByOrderID(); 
                break; 
            case 3: 
                viewOrdersByCustName(); 
                break; 
            default: 
                System.out.println("Invalid option"); 
        } 
    } 

    public static void addOrder() { 
        System.out.println("Enter Order Amount:");
        Double amt = scan.nextDouble();
        
        System.out.println("Enter Customer ID for this order:");
        int custId = scan.nextInt();

        Order newOrder = new Order();
        newOrder.setOrderAmt(amt);
       
        newOrder.setOrderDate(LocalDate.now()); 

        boolean success = dao.addOrder(newOrder, custId);
        if (success) {
            System.out.println("Order added successfully!");
        } else {
            System.out.println("Failed to add order. Please check if the Customer ID exists.");
        }
    } 

    public static void viewOrderByOrderID() { 
        System.out.println("Enter Order ID:");
        int orderId = scan.nextInt();
        
        Order order = dao.getOrder(orderId);
        if (order != null) {
            
            System.out.println(order.toString());
        } else {
            System.out.println("Order not found.");
        }
    } 

    public static void viewOrdersByCustName() { 
        System.out.println("Enter Customer Name:");
        scan.nextLine(); 
        String custName = scan.nextLine();
        
        List<Order> orders = dao.getOrders(custName);
        if (orders != null && !orders.isEmpty()) {
            System.out.println("Orders for " + custName + ":");
            for (Order order : orders) {
                System.out.println(order.toString());
            }
        } else {
            System.out.println("No orders found for this customer.");
        }
    } 
}