/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author ADMIN
 */
public class OrderList extends ArrayList<orders> {

    static String ordersPath;
    static Scanner sc = new Scanner(System.in);
    static CustomerList cList = new CustomerList();

    public void readFromFile(String path, String customerPath) {
        ordersPath = path;
        File fName = new File(ordersPath);
        if (!fName.exists()) {
            System.err.println("Orders file doesn't exist");
            return;
        }
        try {
            FileReader fr = new FileReader(fName);
            BufferedReader br = new BufferedReader(fr);
            String details;
            while ((details = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");

                String orderID = stk.nextToken().toUpperCase();
                String customerID = stk.nextToken().toUpperCase();
                String productID = stk.nextToken().toUpperCase();
                String orderQuantity = stk.nextToken().toUpperCase();
                String orderDate = stk.nextToken().toUpperCase();
                boolean Status = Boolean.parseBoolean(stk.nextToken());

                orders tmp = new orders(orderID, customerID, productID, orderQuantity, orderDate, Status);
                this.add(tmp);
            }
            br.close();
            fr.close();
            cList.readFromFile(customerPath);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void writeToFile() {
        File fName = new File(ordersPath);
        if (!fName.exists()) {
            System.err.println("Product file doesn't exist");
            return;
        }
        try {
            FileWriter fw = new FileWriter(fName);
            PrintWriter pw = new PrintWriter(fw);

            for (orders e : this) {
                pw.println(e);
            }
            pw.close();
            fw.close();
            System.err.println("Save Successfully");
        } catch (Exception e) {
            System.err.println("Fail to Save");
        }
    }

    public void printAllOrders() {
        Collections.sort(this,
                (orders obj1, orders obj2) -> obj1.getCustomerName(cList).compareTo(obj2.getCustomerName(cList)));

        for (orders e : this) {
            System.err.println(e);
        }
    }

    public void printAllPendingOrders() {
        for (orders e : this) {
            if (!e.isStatus()) {
                System.err.println(e);
            }
        }
    }

    public int findOrderByID(String ID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getOrderID().equalsIgnoreCase(ID)) {
                return i;
            }
        }
        return -1;
    }

    public String get_Day(String data) {
        String[] date = data.split("/");
        String result = date[1];
        return result;
    }

    public String get_Month(String data) {
        String[] date = data.split("/");
        String result = date[0];
        return result;
    }

    public String get_Year(String data) {
        String[] date = data.split("/");
        String result = date[2];
        return result;
    }

    public boolean is_Valid_Date(String data) {
        int dd = Integer.parseInt(get_Day(data));
        int mm = Integer.parseInt(get_Month(data));
        int yyyy = Integer.parseInt(get_Year(data));

        if (dd < 1 || dd > 31 || mm < 1 || mm > 12) {
            return false;
        }

        switch (mm) {
            case 4:
            case 6:
            case 9:
            case 11:
                if (dd > 30) {
                    return false;
                }
                break;
            case 2:
                if (yyyy % 400 == 0 || (yyyy % 4 == 0 && yyyy % 100 != 0)) {
                    if (dd > 29) {
                        return false;
                    }
                } else {
                    if (dd > 28) {
                        return false;
                    }
                }
            default:
                return true;
        }

        return true;
    }

    public void addNewOrder(CustomerList cList, ProductsList pList) {
        String orderID;
        String customerID;
        String productID;
        String orderQuantity;
        String orderDate;
        boolean Status;

        boolean cont_orderID = false;
        boolean cont_customerID = false;
        boolean cont_productID = false;
        boolean cont_orderQuantity = false;
        boolean cont_orderDate = false;

        do {
            System.out.print("Enter ID(Order): ");
            orderID = sc.nextLine().toUpperCase();
            boolean cont_1 = findOrderByID(orderID) >= 0;
            boolean cont_2 = validationSchema.isValid_Order_ID(orderID);

            cont_orderID = (cont_1 || !cont_2) ? true : false;
            if (cont_1) {
                System.err.println("\n\t\t\tERROR: Order ID is Duplicated");
            }
            if (!cont_2) {
                System.err.println("\n\t\t\tERROR: Format of Order ID is not match (Dxxx)");
            }
        } while (cont_orderID);

        do {
            cList.forEach(e -> System.err.println(e));
            System.out.print("\nEnter ID(Customer): ");
            customerID = sc.nextLine().toUpperCase();

            boolean cont_1 = cList.findID(customerID) < 0;
            boolean cont_2 = validationSchema.isValid_Customer_ID(customerID);

            cont_customerID = (cont_1 || !cont_2) ? true : false;
            if (cont_1) {
                System.err.println("\n\t\t\tCustomer ID Doesn't Exist.");
            }
            if (!cont_2) {
                System.err.println("\n\t\t\tFormat of Customer ID Doesn't match(Cxxx).");
            }
        } while (cont_customerID);

        do {
            pList.forEach(e -> System.err.println(e));
            System.out.print("\nEnter ID(Product): ");
            productID = sc.nextLine().toUpperCase();

            boolean cont_1 = pList.find(productID) < 0;
            boolean cont_2 = validationSchema.isValid_Product_ID(productID);
            cont_productID = (cont_1 || !cont_2) ? true : false;

            if (cont_1) {
                System.err.println("\n\t\t\tERROR:Product ID doesn't exist.");
            }
            if (!cont_2) {
                System.err.println("\n\t\t\tERROR:Format of Product ID doesn't mathc(Pxxx).");
            }
        } while (cont_productID);

        do {
            System.out.print("Enter Quantity: ");
            orderQuantity = sc.nextLine();
            cont_orderQuantity = orderQuantity.isEmpty();
        } while (cont_orderQuantity);

        do {
            System.out.print("Enter Date: ");
            orderDate = sc.nextLine();

            boolean cont_1 = !validationSchema.isValid_Order_Date(orderDate);
            boolean cont_2 = !is_Valid_Date(orderDate);

            cont_orderDate = (cont_1 || cont_2) ? true : false;

            if (cont_1) {
                System.err.println("\nERROR: Date Format is Wrong.");
            }
            if (cont_2 && !cont_1) {
                System.err.println("\nERROR: Date doesn't exist.");
            }
        } while (cont_orderDate);

        Status = false;

        orders tmp = new orders(orderID, customerID, productID, orderQuantity, orderDate, Status);
        this.add(tmp);
    }

    public void updateOrder(CustomerList cList, ProductsList pList) {
        String ID;
        String productID;
        String orderQuantity;
        String orderDate;
        boolean orderStatus;
        boolean cont_productID = false;
        boolean cont_orderQuantity = false;
        boolean cont_orderDate = false;

        boolean cont_orderID = false;
        do {
            this.forEach(e -> System.err.println(e));
            System.out.print("Enter Update Order ID: ");
            ID = sc.nextLine().toUpperCase();

            cont_orderID = validationSchema.isValid_Order_ID(ID);
            if (!cont_orderID) {
                System.err.println("\n\t\t\tERROR:Format of OrderID doesn't match.");
            }
        } while (!cont_orderID);

        int pos = this.findOrderByID(ID);
        if (pos < 0) {
            System.err.println("\n\t\t\tERROR:OrderID doesn't exist.");
            return;
        }

        do {
            System.out.print("Choose Another Product ID: ");
            productID = sc.nextLine().toUpperCase();

            boolean cont_1 = !validationSchema.isValid_Product_ID(productID);
            boolean cont_2 = pList.find(productID) < 0;

            cont_productID = (cont_1 || cont_2) ? true : false;
            if (cont_1) {
                System.err.println("\n\t\t\tERROR: Format of Product ID doesn't match.");
            }
            if (cont_2) {
                System.err.println("\n\t\t\tERROR: Product ID you entered doesn't exist.");
            }
        } while (cont_productID);

        do {
            System.out.print("Enter New Quantity: ");
            orderQuantity = sc.nextLine().toUpperCase();
            cont_orderQuantity = orderQuantity.isEmpty();
        } while (cont_orderQuantity);

        do {
            System.out.print("Enter Date: ");
            orderDate = sc.nextLine();

            boolean cont_1 = !validationSchema.isValid_Order_Date(orderDate);
            boolean cont_2 = !is_Valid_Date(orderDate);

            cont_orderDate = (cont_1 || cont_2) ? true : false;
            if (cont_1) {
                System.err.println("\nERROR: Date Format is Wrong.");
            }
            if (cont_2 && !cont_1) {
                System.err.println("\nERROR: Date Doesn't Exist");
            }
        } while (cont_orderDate);

        System.out.print("Is Delivered(Y/N): ");
        orderStatus = (sc.nextLine().toUpperCase().equalsIgnoreCase("Y")) ? true : false;

        this.get(pos).setProductID(productID);
        this.get(pos).setOrderQuantity(orderQuantity);
        this.get(pos).setOrderDate(orderDate);
        this.get(pos).setStatus(orderStatus);
    }

    public void deleteOrder() {

        String ID;
        boolean cont_orderID = false;

        do {
            System.out.print("Enter Order ID: ");
            ID = sc.nextLine().toUpperCase();

            cont_orderID = !validationSchema.isValid_Order_ID(ID);
            if (cont_orderID) {
                System.err.println("\n\t\t\tERROR:Format of Order ID doesn't match.");
            }
        } while (cont_orderID);

        int pos = this.findOrderByID(ID);
        if (pos < 0) {
            System.err.println("\n\t\t\tERROR: Input ID doesn't exist.");
            return;
        }
        this.remove(pos);
        System.err.println("Remove Successfully!");
    }
}
