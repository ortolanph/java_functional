package pho.teach.functional.functions;

import pho.teach.functional.commons.entities.functions.Market;
import pho.teach.functional.commons.loader.RevenueLoader;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class LazyEvaluationMain {

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
