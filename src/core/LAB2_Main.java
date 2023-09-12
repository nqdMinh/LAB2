/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class LAB2_Main {

    static Scanner sc = new Scanner(System.in);

    static CustomerList cList = new CustomerList();
    static OrderList oList = new OrderList();
    static ProductsList pList = new ProductsList();

    static String cName = "C:\\Users\\ADMIN\\OneDrive\\Documents\\NetBeansProjects\\LAB2\\src\\data\\customers.txt";
    static String oName = "C:\\Users\\ADMIN\\OneDrive\\Documents\\NetBeansProjects\\LAB2\\src\\data\\orders.txt";
    static String pName = "C:\\Users\\ADMIN\\OneDrive\\Documents\\NetBeansProjects\\LAB2\\src\\data\\products.txt";

    static Menu menu_Customers = new Menu();
    static Menu menu_Products = new Menu();
    static Menu menu_Orders = new Menu();
    static Menu menu_Manager = new Menu();

    public static void main(String args[]) throws InterruptedException {
        cList.readFromFile(cName);
        oList.readFromFile(oName,cName);
        pList.readFromFile(pName);

        menu_Customers.add("Add Customer");
        menu_Customers.add("List All Customer");
        menu_Customers.add("Update Customer");
        menu_Customers.add("Search Customer");
        menu_Customers.add("Save Customer");

        menu_Products.add("List All Products");
        menu_Products.add("Quit");

        menu_Orders.add("Add New Orders");
        menu_Orders.add("List All Orders");
        menu_Orders.add("List All Pending Orders");
        menu_Orders.add("Update Orders");
        menu_Orders.add("Delete Orders");
        menu_Orders.add("Save Orders");

        int menu_Manger_choice;
        do {
            menu_Manger_choice = menu_Manager.getManager();
            switch (menu_Manger_choice) {
                case 1:
                    int menu_Orders_choice;
                    do {
                        Thread.sleep(300);
                        menu_Orders_choice = menu_Orders.getUserChoice_Orders();
                        switch (menu_Orders_choice) {
                            case 1:
                                oList.addNewOrder(cList, pList);
                                break;
                            case 2:
                                oList.printAllOrders();
                                break;
                            case 3:
                                oList.printAllPendingOrders();
                                break;
                            case 4:
                                oList.updateOrder(cList, pList);
                                break;
                            case 5:
                                oList.deleteOrder();
                                break;
                            default:
                                oList.writeToFile();
                                break;
                        }
                    } while (menu_Orders_choice > 0 && menu_Orders_choice < 6);
                    break;
                case 2:
                    int menu_Products_choice;
                    do {
                        Thread.sleep(300);
                        menu_Products_choice = menu_Products.getUserChoice_Products();
                        switch (menu_Products_choice) {
                            case 1:
                                pList.printAllProducts();
                                break;
                            default:
                                break;
                        }
                    } while (menu_Products_choice == 1);
                    break;
                case 3:
                    int menu_Customers_choice;
                    do {
                        Thread.sleep(300);
                        menu_Customers_choice = menu_Customers.getUserChoice_Customer();
                        switch (menu_Customers_choice) {
                            case 1:
                                cList.addNewCustomer();
                                break;
                            case 2:
                                cList.printAllCustomers();
                                break;
                            case 3:
                                cList.updateCustomer();
                                break;
                            case 4:
                                cList.searchByID();
                                break;
                            default:
                                cList.writeToFile();
                                break;
                        }
                    } while (menu_Customers_choice > 0 && menu_Customers_choice < 5);
                    break;
                default:
                    System.exit(0);
            }
        } while (menu_Manger_choice > 0 && menu_Manger_choice < 4);

    }
}
