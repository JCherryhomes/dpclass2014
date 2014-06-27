import java.util.*;

/**
 * Created by Jonathan on 6/27/2014.
 */
public class InstrumentedAirportScraper implements AirportScraperInterface {

    private final AirportScraperInterface scraper;
    private final Map<String, Double> distances;
    private final Map<String, Integer> destinations;

    public InstrumentedAirportScraper(AirportScraperInterface scraper) {
        this.scraper = scraper;
        this.distances = new HashMap<>();
        this.destinations = new HashMap<>();
    }

    // sorted by most commonly called destinations first,
    // ties broken alphabetically. ok to consider case, i.e.,
    // treat AUS and aus differently.  see test suite
    // for exact format
    public List<String> mostCommonDestinations() {
        List<String> temp = new ArrayList<>();

        for (Map.Entry<String, Integer> pair : destinations.entrySet()){
            temp.add(pair.getKey() + ":" + pair.getValue());
        }

        Collections.sort(temp, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int index1 = o1.indexOf(':') + 1;
                int index2 = o2.indexOf(':') + 1;

                int count1 = Integer.parseInt(o1.substring(index1, o1.length()));
                int count2 = Integer.parseInt(o2.substring(index2, o2.length()));

                if (count1 == count2) return o1.compareTo(o2);
                else return Integer.compare(count2, count1);
            }
        });

        return temp;
    }

    @Override
    public double lookupDistance(String dest) {
        if (!destinations.containsKey(dest)){
            destinations.put(dest, 1);
            distances.put(dest, scraper.lookupDistance(dest));
        }
        else {
            destinations.put(dest, destinations.get(dest) + 1);
        }

        return distances.get(dest);
    }
}