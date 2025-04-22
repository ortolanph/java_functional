package pho.teach.functional.functions;

import pho.teach.functional.commons.bench.*;
import pho.teach.functional.commons.entities.functions.*;
import pho.teach.functional.commons.loader.*;
import pho.teach.functional.functions.bench.*;

import java.util.*;
import java.util.stream.*;

public class SimpleExampleMemBench {

    public static void main(String[] args) {
        List<MemoryStatistic> statistics = new ArrayList<>();
        MemoryTracker tracker = new MemoryTracker();

        RevenueLoader loader = new RevenueLoader("supermarket_revenue_medium.json");

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
        statistics.add(new MemoryStatistic("Common Streams Usage", "allCountries", tracker.getMemoryUsedMB()));
        tracker.reset();

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
        statistics.add(new MemoryStatistic("Common Streams Usage", "allStores", tracker.getMemoryUsedMB()));
        tracker.reset();

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
        statistics.add(new MemoryStatistic("Common Streams Usage", "allSections", tracker.getMemoryUsedMB()));
        tracker.reset();

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
        statistics.add(new MemoryStatistic("Common Streams Usage", "totalRevenues", tracker.getMemoryUsedMB()));
        tracker.reset();

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
        statistics.add(new MemoryStatistic("Common Streams Usage", "getCountrySummary", tracker.getMemoryUsedMB()));
        tracker.reset();

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
        statistics.add(new MemoryStatistic("Common Streams Usage", "getStoreSummary", tracker.getMemoryUsedMB()));
        tracker.reset();

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
        statistics.add(new MemoryStatistic("Common Streams Usage", "getCountriesStores", tracker.getMemoryUsedMB()));
        tracker.reset();

        System.out.printf("%nStores by Countries:%n%n");
        countryStores.forEach((key, value) -> {
            System.out.printf(" * %s: %s%n", key, value);
        });

        statistics.forEach(statistic -> System.out.println(statistic.toCSVLine()));
    }
}
