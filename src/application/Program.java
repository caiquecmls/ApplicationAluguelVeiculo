/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import mode.services.BrazilTaxService;
import mode.services.RentalService;
import model.entities.CarRental;
import model.entities.Vehicle;

/**
 *
 * @author caiqu
 */
public class Program {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");

        System.out.println("Enter rental data");

        System.out.print("Car model: ");
        String carModel = sc.nextLine();
        System.out.print("Pickup (dd/MM/yyyy hh:ss): ");
        Date start = sdf.parse(sc.nextLine());
        System.out.print("Return (dd/MM/yyyy hh:ss): ");
        Date finish = sdf.parse(sc.nextLine());
        
        CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
        
        System.out.print("Enter price per hour: ");
        double pricePerHour = sc.nextDouble();
        System.out.println("Enter price per day: ");
        double pricePerDay = sc.nextDouble();
        
        RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());
        
        rentalService.processInvoice(cr);
        
        System.out.println("INVOICE:");
        System.out.println("Basic payment: "+ String.format("%.2f", cr.getInvoice().getBasicPayment()));
        System.out.println("Tax: "+ String.format("%.2f", cr.getInvoice().getTax())); 
        System.out.println("Total payment: "+ String.format("%.2f", cr.getInvoice().getTotalPayment()));
    }
}
