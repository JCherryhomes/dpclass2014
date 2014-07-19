/**
 * Created by Jonathan on 7/19/2014.
 */
public class NewReleasePrice extends Price {
    @Override
    public double getCharge(int daysRented) {
        return daysRented * 3.0;
    }

    @Override
    public int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented){
        int amount= 1;

        if (daysRented > 1) amount++;

        return amount;
    }
}
