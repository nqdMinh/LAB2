/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class Menu extends Vector<String> {
    Scanner sc = new Scanner(System.in);

    public Menu() {
    }

    public int getManager() throws InterruptedException {
        System.err.println("MANAGER OPTIONS");
        Thread.sleep(100);
        System.out.println("1-Orders Manager");
        System.out.println("2-Products Manager");
        System.out.println("3-Customer Manager");
        System.out.println("4-Quit");
        System.out.println("_____________________________");
        System.out.print("Enter your choice (1..4): ");
        return Integer.parseInt(sc.nextLine());
    }

    public int getUserChoice_Customer() throws InterruptedException {
        System.err.println("Customer Manager");
        Thread.sleep(100);
        for (int i = 0; i < this.size(); i++) {
            System.out.println((i + 1) + "-" + this.get(i));
        }
        System.out.println("_____________________________");
        System.out.print("Enter your choice(1.." + this.size() + "):");
        return Integer.parseInt(sc.nextLine());
    }

    public int getUserChoice_Products() throws InterruptedException {
        System.err.println("Products Manager");
        Thread.sleep(100);
        for (int i = 0; i < this.size(); i++) {
            System.out.println((i + 1) + "-" + this.get(i));
        }
        System.out.println("_____________________________");
        System.out.print("Enter your choice(1.." + this.size() + "):");
        return Integer.parseInt(sc.nextLine());
    }

    public int getUserChoice_Orders() throws InterruptedException {
        System.err.println("Orders Manager");
        Thread.sleep(100);
        for (int i = 0; i < this.size(); i++) {
            System.out.println((i + 1) + "-" + this.get(i));
        }
        System.out.println("_____________________________");
        System.out.print("Enter your choice(1.." + this.size() + "):");
        return Integer.parseInt(sc.nextLine());
    }
}
