/**
 * Created by Jonathan on 7/19/2014.
 */
public abstract class Price {
    public abstract double getCharge(int daysRented);
    public abstract int getPriceCode();
    public int getFrequentRenterPoints(int daysRented){
        return 1;
    }
}
