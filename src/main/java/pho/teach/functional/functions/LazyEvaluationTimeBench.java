package pho.teach.functional.functions;

import pho.teach.functional.commons.entities.functions.Country;
import pho.teach.functional.commons.entities.functions.Market;
import pho.teach.functional.commons.entities.functions.Month;
import pho.teach.functional.commons.entities.functions.Section;
import pho.teach.functional.commons.entities.functions.Store;
import pho.teach.functional.commons.loader.RevenueLoader;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LazyEvaluationTimeBench {

    private Market market;

    public LazyEvaluationTimeBench(Market market) {
        this.market = market;
    }

    protected Stream<Country> countries() {
        return market
                .getCountries()
                .stream();
    }

    protected Stream<Store> stores(Stream<Country> countryStream) {
        return countryStream
                .map(Country::getStores)
                .flatMap(List::stream);
    }

    protected Stream<Section> sections(Stream<Store> storeStream) {
        return storeStream
                .map(Store::getSections)
                .flatMap(List::stream);
    }

    protected Stream<Month> revenues(Stream<Section> sectionStream) {
        return sectionStream
                .map(Section::getRevenues)
                .flatMap(List::stream);
    }

    public List<String> allCountries() {
        return countries()
                .map(Country::getName)
                .sorted()
                .toList();
    }

    public List<String> allStores() {
        return stores(countries())
                .map(Store::getId)
                .sorted()
                .toList();
    }

    public Set<String> allSections() {
        return sections(stores(countries()))
                .map(Section::getSection)
                .collect(Collectors.toSet());

    }

    public Double totalRevenues() {
        return revenues(sections(stores(countries())))
                .mapToDouble(Month::getRevenue)
                .sum();
    }

    public Map<String, Double> getCountrySummary() {
        return
                countries()
                        .collect(Collectors.toMap(
                                Country::getName,
                                country -> revenues(sections(country.getStores().stream()))
                                        .mapToDouble(Month::getRevenue)
                                        .sum()
                        ));
    }

    public Map<String, Double> getStoreSummary() {
        return stores(countries())
                .collect(Collectors.toMap(
                        Store::getId,
                        store -> revenues(store.getSections().stream())
                                .mapToDouble(Month::getRevenue)
                                .sum()
                ));
    }

    public Map<String, List<String>> getCountryStores() {
        return countries()
                .collect(Collectors.toMap(
                        Country::getName,
                        country -> country
                                .getStores()
                                .stream()
                                .map(Store::getId)
                                .toList()));
    }

    public static void main(String[] args) {
        RevenueLoader loader = new RevenueLoader("supermarket_revenue_detailed_prod.json");
        LazyEvaluationTimeBench evaluator = new LazyEvaluationTimeBench(loader.getMarketData());

        Market data = loader.getMarketData();

        System.out.printf("Supermarket: %s%n", data.getName());
        System.out.printf("Year: %d%n", data.getYear());

        List<String> countries = evaluator.allCountries();
        System.out.printf("%nAll countries:%n%n");
        countries.forEach(s -> {
            System.out.printf(" * %s%n", s);
        });

        List<String> stores = evaluator.allStores();

        System.out.printf("%nAll Stores:%n%n");
        stores.forEach(s -> {
            System.out.printf(" * %s%n", s);
        });

        Set<String> sections = evaluator.allSections();

        System.out.printf("%nAll Sections:%n%n");
        sections.forEach(s -> {
            System.out.printf(" * %s%n", s);
        });

        Double totalRevenue = evaluator.totalRevenues();
        System.out.printf("%nTotal Revenue: %,.2f%n", totalRevenue);

        Map<String, Double> countrySummary = evaluator.getCountrySummary();
        System.out.printf("%nRevenue by Country:%n%n");
        countrySummary.forEach((key, value) -> {
            System.out.printf(" * %s: %,.2f%n", key, value);
        });

        Map<String, Double> storeSummary = evaluator.getStoreSummary();
        System.out.printf("%nRevenue by Store:%n%n");
        storeSummary.forEach((key, value) -> {
            System.out.printf(" * %s: %,.2f%n", key, value);
        });

        Map<String, List<String>> countryStores = evaluator.getCountryStores();
        System.out.printf("%nStores by Countries:%n%n");
        countryStores.forEach((key, value) -> {
            System.out.printf(" * %s: %s%n", key, value);
        });
    }
}
