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
import java.util.StringTokenizer;

/**
 *
 * @author ADMIN
 */
public class ProductsList extends ArrayList<products> {

    static String productPath;

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
                String productID = stk.nextToken().toUpperCase();
                String productName = stk.nextToken().toUpperCase();
                String unit = stk.nextToken().toUpperCase();
                String origin = stk.nextToken().toUpperCase();
                double price = Double.parseDouble(stk.nextToken());

                products tmp = new products(productID, productName, unit, origin, price);
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

            for (products e : this) {
                pw.println(e);
            }
            pw.close();
            fw.close();
            System.err.println("Save Successfully");
        } catch (Exception e) {
            System.err.println("Fail to Save");
        }
    }

    public void printAllProducts() {
        for (products e : this) {
            System.err.println(e + "\n");
        }
    }

    public int find(String ID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getProductID().equals(ID)) {
                return i;
            }
        }
        return -1;
    }
}
