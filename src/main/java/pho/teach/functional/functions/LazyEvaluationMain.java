package pho.teach.functional.functions;

import pho.teach.functional.commons.entities.functions.*;
import pho.teach.functional.commons.loader.RevenueLoader;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.*;

public class LazyEvaluationMain {

    public static void main(String[] args) {
        RevenueLoader loader = new RevenueLoader("supermarket_revenue.json");
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

        List<String> storesInBrazil = evaluator.storesByCountry("Brazil");

        System.out.printf("%nAll Stores in Brazil:%n%n");
        storesInBrazil.forEach(s -> {
            System.out.printf(" * %s%n", s);
        });

        Predicate<Country> brazilPredicate = country -> country.getName().equals("Brasil");
        Predicate<Country> argentinaPredicate = country -> country.getName().equals("Argentina");
        List<String> storesInBrazilAndArgentina = evaluator.storesByCountry(brazilPredicate.or(argentinaPredicate));

        System.out.printf("%nAll Stores in Brazil and Argentina:%n%n");
        storesInBrazilAndArgentina.forEach(s -> {
            System.out.printf(" * %s%n", s);
        });

        Function<Store, String> storeFunction = store -> "%s - %s - %s".formatted(store.getId(), store.getLocation(), store.isDelivery());

        List<String> formattedStoresInBrazilAndArgentina = evaluator
            .storesByCountryFormatted(brazilPredicate.or(argentinaPredicate), storeFunction);

        System.out.printf("%nAll Stores in Brazil and Argentina:%n%n");
        formattedStoresInBrazilAndArgentina.forEach(s -> {
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
