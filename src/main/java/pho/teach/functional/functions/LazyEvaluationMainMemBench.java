package pho.teach.functional.functions;

import pho.teach.functional.commons.bench.*;
import pho.teach.functional.commons.entities.functions.*;
import pho.teach.functional.commons.loader.*;
import pho.teach.functional.functions.bench.*;

import java.util.*;

public class LazyEvaluationMainMemBench {

    public static void main(String[] args) {
        List<MemoryStatistic> statistics = new ArrayList<>();
        MemoryTracker tracker = new MemoryTracker();

        RevenueLoader loader = new RevenueLoader("supermarket_revenue_medium.json");
        LazyEvaluation evaluator = new LazyEvaluation(loader.getMarketData());

        Market data = loader.getMarketData();

        System.out.printf("Supermarket: %s%n", data.getName());
        System.out.printf("Year: %d%n", data.getYear());

        // ------------------------------------------------------------------------------------------------

        tracker.start();
        List<String> countries = evaluator.allCountries();
        tracker.stop();
        statistics.add(new MemoryStatistic("Lazy Evaluation", "allCountries", tracker.getMemoryUsedMB()));
        tracker.reset();

        System.out.printf("%nAll countries:%n%n");
        countries.forEach(s -> {
            System.out.printf(" * %s%n", s);
        });

        // ------------------------------------------------------------------------------------------------

        tracker.start();
        List<String> stores = evaluator.allStores();
        tracker.stop();
        statistics.add(new MemoryStatistic("Lazy Evaluation", "allStores", tracker.getMemoryUsedMB()));
        tracker.reset();

        System.out.printf("%nAll Stores:%n%n");
        stores.forEach(s -> {
            System.out.printf(" * %s%n", s);
        });


        // ------------------------------------------------------------------------------------------------

        tracker.start();
        Set<String> sections = evaluator.allSections();
        tracker.stop();
        statistics.add(new MemoryStatistic("Lazy Evaluation", "allSections", tracker.getMemoryUsedMB()));
        tracker.reset();

        System.out.printf("%nAll Sections:%n%n");
        sections.forEach(s -> {
            System.out.printf(" * %s%n", s);
        });

        // ------------------------------------------------------------------------------------------------

        tracker.start();
        Double totalRevenue = evaluator.totalRevenues();
        tracker.stop();
        statistics.add(new MemoryStatistic("Lazy Evaluation", "totalRevenues", tracker.getMemoryUsedMB()));
        tracker.reset();

        System.out.printf("%nTotal Revenue: %,.2f%n", totalRevenue);

        // ------------------------------------------------------------------------------------------------

        tracker.start();
        Map<String, Double> countrySummary = evaluator.getCountrySummary();
        tracker.stop();
        statistics.add(new MemoryStatistic("Lazy Evaluation", "getCountrySummary", tracker.getMemoryUsedMB()));
        tracker.reset();

        System.out.printf("%nRevenue by Country:%n%n");
        countrySummary.forEach((key, value) -> {
            System.out.printf(" * %s: %,.2f%n", key, value);
        });

        // ------------------------------------------------------------------------------------------------

        tracker.start();
        Map<String, Double> storeSummary = evaluator.getStoreSummary();
        tracker.stop();
        statistics.add(new MemoryStatistic("Lazy Evaluation", "getStoreSummary", tracker.getMemoryUsedMB()));

        System.out.printf("%nRevenue by Store:%n%n");
        storeSummary.forEach((key, value) -> {
            System.out.printf(" * %s: %,.2f%n", key, value);
        });

        // ------------------------------------------------------------------------------------------------

        tracker.start();
        Map<String, List<String>> countryStores = evaluator.getCountryStores();
        tracker.stop();
        statistics.add(new MemoryStatistic("Lazy Evaluation", "getCountriesStores", tracker.getMemoryUsedMB()));
        tracker.reset();


        System.out.printf("%nStores by Countries:%n%n");
        countryStores.forEach((key, value) -> {
            System.out.printf(" * %s: %s%n", key, value);
        });

        statistics.forEach(statistic -> System.out.println(statistic.toCSVLine()));
    }

}
