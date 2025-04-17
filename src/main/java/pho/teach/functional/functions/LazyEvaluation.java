package pho.teach.functional.functions;

import pho.teach.functional.commons.entities.functions.Country;
import pho.teach.functional.commons.entities.functions.Market;
import pho.teach.functional.commons.entities.functions.Month;
import pho.teach.functional.commons.entities.functions.Section;
import pho.teach.functional.commons.entities.functions.Store;
import pho.teach.functional.commons.loader.RevenueLoader;

import java.sql.CallableStatement;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LazyEvaluation {

    private Market market;

    public LazyEvaluation(Market market) {
        this.market = market;
    }

    protected Stream<Country> countries() {
        return market
                .getCountries()
                .stream();
    }

    protected Stream<Store> stores() {
        return countries()
                .map(Country::getStores)
                .flatMap(List::stream);
    }

    public List<String> allCountries() {
        return countries()
                .map(Country::getName)
                .sorted()
                .toList();
    }

    public List<String> allStores() {
        return stores()
                .map(Store::getId)
                .sorted()
                .toList();
    }

    Set<String> allSections()  {
        return stores()
                .map(Store::getSections)
                .flatMap(List::stream)
                .map(Section::getSection)
                .collect(Collectors.toSet());

    }

    public static void main(String[] args) {
        RevenueLoader loader = new RevenueLoader("supermarket_revenue_detailed_prod.json");
        LazyEvaluation evaluator = new LazyEvaluation(loader.getMarketData());

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
;
        Set<String> sections = evaluator.allSections();

        System.out.printf("%nAll Sections:%n%n");
        sections.forEach(s -> {
            System.out.printf(" * %s%n", s);
        });

        Double totalRevenue = data
            .getCountries()
            .stream()
            .map(Country::getStores)
            .flatMap(List::stream)
            .map(Store::getSections)
            .flatMap(List::stream)
            .map(Section::getRevenues)
            .flatMap(List::stream)
            .mapToDouble(Month::getRevenue)
            .sum();

        System.out.printf("%nTotal Revenue: %,.2f%n", totalRevenue);

        Map<String, Double> countrySummary = data
            .getCountries()
            .stream()
            .collect(Collectors.toMap(
                Country::getName,
                country -> country
                    .getStores()
                    .stream()
                    .map(Store::getSections)
                    .flatMap(List::stream)
                    .map(Section::getRevenues)
                    .flatMap(List::stream)
                    .mapToDouble(Month::getRevenue)
                    .sum()
            ));

        System.out.printf("%nRevenue by Country:%n%n");
        countrySummary.forEach((key, value) -> {
            System.out.printf(" * %s: %,.2f%n", key, value);
        });

        Map<String, Double> storeSummary = data
            .getCountries()
            .stream()
            .map(Country::getStores)
            .flatMap(List::stream)
            .collect(Collectors.toMap(
                Store::getId,
                store -> store
                    .getSections()
                    .stream()
                    .map(Section::getRevenues)
                    .flatMap(List::stream)
                    .mapToDouble(Month::getRevenue)
                    .sum()
            ));

        System.out.printf("%nRevenue by Store:%n%n");
        storeSummary.forEach((key, value) -> {
            System.out.printf(" * %s: %,.2f%n", key, value);
        });

        Map<String, List<String>> countryStores = data
            .getCountries()
            .stream()
            .collect(Collectors.toMap(
                Country::getName,
                country -> country
                    .getStores()
                    .stream()
                    .map(Store::getId)
                    .toList()));

        System.out.printf("%nStores by Countries:%n%n");
        countryStores.forEach((key, value) -> {
            System.out.printf(" * %s: %s%n", key, value);
        });
    }
}
