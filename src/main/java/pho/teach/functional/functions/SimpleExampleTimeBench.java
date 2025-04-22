package pho.teach.functional.functions;

import pho.teach.functional.commons.bench.TimeTracker;
import pho.teach.functional.commons.entities.functions.Country;
import pho.teach.functional.commons.entities.functions.Market;
import pho.teach.functional.commons.entities.functions.Month;
import pho.teach.functional.commons.entities.functions.Section;
import pho.teach.functional.commons.entities.functions.Store;
import pho.teach.functional.commons.loader.RevenueLoader;
import pho.teach.functional.functions.bench.TimeStatistic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SimpleExampleTimeBench {

    public static void main(String[] args) {
        List<TimeStatistic> statistics = new ArrayList<>();
        TimeTracker tracker = new TimeTracker();

        RevenueLoader loader = new RevenueLoader("supermarket_revenue_big.json");

        Market data = loader.getMarketData();

        System.out.printf("Supermarket: %s%n", data.getName());
        System.out.printf("Year: %d%n", data.getYear());

        // ------------------------------------------------------------------------------------------------

        tracker.start();
        List<String> countries = data
            .getCountries()
            .stream()
            .map(Country::getName)
            .sorted()
            .toList();
        tracker.stop();
        statistics.add(new TimeStatistic("Common Streams Usage", "allCountries", tracker.getElapsedMillis()));

        System.out.printf("%nAll countries:%n%n");
        countries.forEach(s -> {
            System.out.printf(" * %s%n", s);
        });

        // ------------------------------------------------------------------------------------------------

        tracker.start();
        List<String> stores = data
            .getCountries()
            .stream()
            .map(Country::getStores)
            .flatMap(List::stream)
            .map(Store::getId)
            .sorted()
            .toList();
        tracker.stop();
        statistics.add(new TimeStatistic("Common Streams Usage", "allStores", tracker.getElapsedMillis()));

        System.out.printf("%nAll Stores:%n%n");
        stores.forEach(s -> {
            System.out.printf(" * %s%n", s);
        });

        // ------------------------------------------------------------------------------------------------

        tracker.start();
        Set<String> sections = data
            .getCountries()
            .stream()
            .map(Country::getStores)
            .flatMap(List::stream)
            .map(Store::getSections)
            .flatMap(List::stream)
            .map(Section::getSection)
            .collect(Collectors.toSet());
        tracker.stop();
        statistics.add(new TimeStatistic("Common Streams Usage", "allSections", tracker.getElapsedMillis()));

        System.out.printf("%nAll Sections:%n%n");
        sections.forEach(s -> {
            System.out.printf(" * %s%n", s);
        });

        // ------------------------------------------------------------------------------------------------

        tracker.start();
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
        tracker.stop();
        statistics.add(new TimeStatistic("Common Streams Usage", "totalRevenues", tracker.getElapsedMillis()));

        System.out.printf("%nTotal Revenue: %,.2f%n", totalRevenue);

        // ------------------------------------------------------------------------------------------------

        tracker.start();
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
        tracker.stop();
        statistics.add(new TimeStatistic("Common Streams Usage", "getCountrySummary", tracker.getElapsedMillis()));

        System.out.printf("%nRevenue by Country:%n%n");
        countrySummary.forEach((key, value) -> {
            System.out.printf(" * %s: %,.2f%n", key, value);
        });

        // ------------------------------------------------------------------------------------------------

        tracker.start();
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
        tracker.stop();
        statistics.add(new TimeStatistic("Common Streams Usage", "getStoreSummary", tracker.getElapsedMillis()));

        System.out.printf("%nRevenue by Store:%n%n");
        storeSummary.forEach((key, value) -> {
            System.out.printf(" * %s: %,.2f%n", key, value);
        });

        // ------------------------------------------------------------------------------------------------

        tracker.start();
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
        tracker.stop();
        statistics.add(new TimeStatistic("Common Streams Usage", "getCountriesStores", tracker.getElapsedMillis()));

        System.out.printf("%nStores by Countries:%n%n");
        countryStores.forEach((key, value) -> {
            System.out.printf(" * %s: %s%n", key, value);
        });

        statistics.forEach(statistic -> System.out.println(statistic.toCSVLine()));
    }
}
