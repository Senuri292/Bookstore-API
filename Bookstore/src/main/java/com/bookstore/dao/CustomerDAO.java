/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.dao;

import com.bookstore.model.Customer;
import java.util.ArrayList;
import java.util.List;


public class CustomerDAO {
    // array list to store customers
    private static List<Customer> customers = new ArrayList<>();
    
    static {
        // add 5 customers to the array every time the program runs
        customers.add(new Customer(101, "John", "Doe", "john.doe@gmail.com", "johnPass99"));
        customers.add(new Customer(102, "Alice", "Smith", "alice.smith@gmail.com", "alicePass99"));
        customers.add(new Customer(103, "Bob", "Johnson", "bob.johnson@gmail.com", "bobPass99"));
        customers.add(new Customer(104, "Emily", "Davis", "emily.davis@gmail.com", "emilyPass99"));
        customers.add(new Customer(105, "Michael", "Brown", "michael.brown@gmail.com", "michaelPass99"));
    }
    
    // method to return all customers
    public List<Customer> getAllCustomers(){
        return customers;
    }
    
    // method to return a customer by given Id
    public Customer getCustomersById(int customerId){
        for (Customer customer : customers) {// for-each loop to iterrate through customers
            if(customer.getCustomerId() == customerId){
                return customer;
            }            
        }
        return null;
    }
    
    // method to add new customer
    public void addCustomer(Customer customer){
        int newUserId = getNextCustomerId();
        customer.setCustomerId(newUserId);
        customers.add(customer);
    }
    
    // method to update a customer by Id
    public void updateCustomer(Customer updatedCustomer){
        for (int i = 0; i < customers.size(); i++){
            Customer currentCustomer = customers.get(i);
            if (currentCustomer.equals(updatedCustomer.getCustomerId())) {
                customers.set(i, updatedCustomer);
                System.out.println("Customer Updated");
                return;
            }
        }
    }
    
    // method to deleta a customer by Id
    public void deleteCustomer(int customerId){
         customers.removeIf(customer -> customer.getCustomerId() == customerId);
    }
    
    // method to find out what is the next Id available
    public int getNextCustomerId(){
        if (customers.isEmpty()) {
            return 301; // Start with ID 101 if no customers exist
        }
        
        int maxUserId = 0;
        
        for (int i = 0; i < customers.size(); i++){
            int customerId = customers.get(i).getCustomerId();
            if (customerId > maxUserId){
                maxUserId = customerId;
            }
        }
        return maxUserId + 1;
    }
}
