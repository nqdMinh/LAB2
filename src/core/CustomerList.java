/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author ADMIN
 */
public class CustomerList extends ArrayList<customers> {

    static String productPath;
    Scanner sc = new Scanner(System.in);

    public void readFromFile(String path) {
        productPath = path;
        File fName = new File(productPath);
        if (!fName.exists()) {
            System.err.println("Product file doesn't exist");
            return;
        }
        try {
            FileReader fr = new FileReader(fName);
            BufferedReader br = new BufferedReader(fr);
            String details;
            while ((details = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String customerID = stk.nextToken().toUpperCase();
                String customerName = stk.nextToken().toUpperCase();
                String customerAddress = stk.nextToken().toUpperCase();
                String customerPhone = stk.nextToken().toUpperCase();

                customers tmp = new customers(customerID, customerName, customerAddress, customerPhone);
                this.add(tmp);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void writeToFile() {
        File fName = new File(productPath);
        if (!fName.exists()) {
            System.err.println("Product file doesn't exist");
            return;
        }
        try {
            FileWriter fw = new FileWriter(fName);
            PrintWriter pw = new PrintWriter(fw);

            for (customers e : this) {
                pw.println(e);
            }
            pw.close();
            fw.close();
            System.err.println("Save Successfully");
        } catch (Exception e) {
            System.err.println("Fail to Save");
        }
    }

    public void printAllCustomers() {
        for (customers e : this) {
            System.err.println(e);
        }
    }

    public void searchByID() {
        String code;
        boolean cont = false;
        do {
            System.out.print("Enter ID (CXXX): ");
            code = sc.nextLine().toUpperCase();
            cont = (!validationSchema.isValid_Customer_ID(code)) ? true : false;
            if (cont) {
                System.err.println("\nERROR: Wrong Format. ID must match (CXXX).");
            }
        } while (cont);

        int pos = findID(code);
        if (pos >= 0) {
            System.err.println(this.get(pos));
        } else {
            System.err.println("This customer does not exist");
        }
    }

    public void addNewCustomer() {
        String customerID;
        String customerName;
        String customerAddress;
        String customerPhone;

        boolean contID = false;
        boolean contName = false;
        boolean contAddress = false;
        boolean contPhone = false;

        do {
            System.out.print("Enter ID(CXXX): ");
            customerID = sc.nextLine().toUpperCase();

            boolean cont_1 = validationSchema.isValid_Customer_ID(customerID);
            boolean cont_2 = findID(customerID) >= 0;
            contID = (!cont_1 || cont_2) ? true : false;

            if (cont_2) {
                System.err.println("\nERROR: Customer ID is Duplicated");
            }
            if (!cont_1) {
                System.err.println("\nERROR: Customer ID Format is wrong(Cxxx).");
            }
        } while (contID);

        do {
            System.out.print("Enter Name: ");
            customerName = sc.nextLine().toUpperCase();
            contName = customerName.isEmpty();
            if (contName) {
                System.err.println("\nERROR: Name is empty.");
            }
        } while (contName);

        do {
            System.out.print("Enter Address: ");
            customerAddress = sc.nextLine().toUpperCase();
            contAddress = customerAddress.isEmpty();
            if (contAddress) {
                System.err.println("\nERROR: Address is empty.");
            }
        } while (contAddress);

        do {
            System.out.print("Enter Phone Number: ");
            customerPhone = sc.nextLine();
            contPhone = validationSchema.isValid_Customer_Phone(customerPhone);
            System.out.println(contPhone);
        } while (!contPhone);
        customers e = new customers(customerID, customerName, customerAddress, customerPhone);
        this.add(e);
        System.err.println("Added Successfully\n" + e + "\n");
    }

    public void updateCustomer() {
        String ID;
        boolean cont = false;
        do {
            System.out.print("Enter Updated ID: ");
            ID = sc.nextLine().toUpperCase();

            cont = !validationSchema.isValid_Customer_ID(ID);

            if (cont) {
                System.err.println("\nERROR: Customer ID Format is wrong(Cxxx).");
            }
        } while (cont);

        int pos = findID(ID);
        if (pos >= 0) {
            String customerAddress;
            String customerPhone;
            boolean cont_Address = false;
            boolean cont_Phone = false;

            do {
                System.out.print("Enter new Address: ");
                customerAddress = sc.nextLine().toUpperCase();
                cont_Address = customerAddress.isEmpty();
            } while (cont_Address);
            this.get(pos).setCustomerAddress(customerAddress);

            do {
                System.out.print("Enter Phone Number: ");
                customerPhone = sc.nextLine();
                cont_Phone = validationSchema.isValid_Customer_Phone(customerPhone);
            } while (!cont_Phone);
            this.get(pos).setCustomerPhone(customerPhone);
            System.err.println("\nUpdated Successfully.");
        } else {
            System.err.println("\nERROR: Customer does not exist");
        }
    }

    public int findID(String code) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCustomerID().equals(code)) {
                return i;
            }
        }
        return -1;
    }
}

class validationSchema {

    public static boolean isValid_Customer_ID(String ID) {
        if (!(ID.matches("^C\\d{3}$"))) {
            return false;
        }
        return true;
    }

    public static boolean isValid_Customer_Phone(String Phone) {
        if (!(Phone.matches("^[0-9]{10,12}$"))) {
            return false;
        }
        return true;
    }

    public static boolean isValid_Order_ID(String ID) {
        if (!(ID.matches("^D\\d{3}$"))) {
            return false;
        }
        return true;
    }

    public static boolean isValid_Product_ID(String ID) {
        if (!(ID.matches("^P\\d{3}$"))) {
            return false;
        }
        return true;
    }

    public static boolean isValid_Order_Date(String Data) {
        String regex = "^((0[1-9]|1[012])/(0[1-9]|[12][0-9]|3[01])/(19|2[0-9])[0-9]{2})$";
        boolean cont = (Data.matches(regex)) ? true : false;
        return cont;
    }
}
