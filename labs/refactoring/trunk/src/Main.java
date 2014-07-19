import java.util.Date;
import Helpers.*;

public class Main {
    public static void main (String[] args) {
        Customer C1;
        Customer C2;
        Customer C3;

        C1 = new Customer("John");
        C2 = new Customer("Mary");
        C3 = new Customer("Manny");

        Movie M1 = new Movie("Oz The Great and Powerful", Movie.NEW_RELEASE);
        Movie M2 = new Movie("The Dark Knight", Movie.REGULAR);
        Movie M3 = new Movie("Wreck-it Ralph", Movie.CHILDRENS);


        Date start = new Date(2013, 7, 1);
        Date end1 = new Date(2013, 7, 6);
        Date end2 = new Date(2013, 7, 4);
        Date end3 = new Date(2013, 7, 5);

        DateRange d1 = new DateRange(start, end1);
        DateRange d2 = new DateRange(start, end2);
        DateRange d3 = new DateRange(start, end3);

        C1.addRental(new Rental(M1, d1));
        C1.addRental(new Rental(M2, d2));
        C1.addRental(new Rental(M3, d3));

        System.out.println(C1.statement()); 
        System.out.println();

        start = new Date(2013, 7, 11);
        end1 = new Date(2013, 7, 12);
        end2 = new Date(2013, 7, 19);
        C2.addRental(new Rental(M1, d1));
        C2.addRental(new Rental(M3, d2));
        System.out.println(C2.statement()); 
        System.out.println();

        start = new Date(2013, 6, 1);
        end1 = new Date(2013, 6, 3);
        end2 = new Date(2013, 6, 2);

        C3.addRental(new Rental(M2, d1));
        C3.addRental(new Rental(M3, d2));

        System.out.println(C3.statement()); 
        System.out.println();
    }
}
