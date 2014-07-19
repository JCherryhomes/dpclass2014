import java.util.Vector;

class Customer {
    private String _name;
    private Vector<Rental> _rentals = new Vector<Rental>();

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }

    public String htmlStatement(){
        StringBuilder result = new StringBuilder("<h1>Rentals for <em>").append(getName());
        result.append("</em></h1><p>\n");

        for (Rental  each : _rentals){
            result.append(each.getMovie().getTitle()).append(": ");
            result.append(String.valueOf(each.getCharge())).append("<br>\n");
        }

        result.append("<p>You owe <em>" + String.valueOf(getTotalCharges()));
        result.append("</em></p>/n");
        result.append("On  this rental you earned <em>");
        result.append(String.valueOf(getTotalFrequentRenterPoints()));
        result.append("</em> frequent renter points</p>");

        return result.toString();
    }

    public String statement() {
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        for (Rental each : _rentals) {

            //show figures for this rental
            result.append("\t").append(each.getTitle());
            result.append("\t").append(String.valueOf(each.getCharge()));
            result.append("\n");
        }

        //add footer lines
        result.append("Amount owed is ").append(String.valueOf(getTotalCharges()));
        result.append("\n");
        result.append("You earned ").append(String.valueOf(getTotalFrequentRenterPoints()));
        result.append(" frequent renter points");
        return result.toString();
    }

    public int getTotalFrequentRenterPoints() {
        int frequentRenterPoints = 0;

        for (Rental each : _rentals) {
            frequentRenterPoints += each.getFrequentRenterPoints();
        }

        return frequentRenterPoints;
    }

    public double getTotalCharges() {
        double result = 0;

        for (Rental each : _rentals) {
            result += each.getCharge();
        }

        return result;
    }
}
