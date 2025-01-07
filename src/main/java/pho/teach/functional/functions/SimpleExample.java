package pho.teach.functional.functions;

import pho.teach.functional.commons.entities.Country;
import pho.teach.functional.commons.entities.Market;
import pho.teach.functional.commons.entities.Section;
import pho.teach.functional.commons.entities.Store;
import pho.teach.functional.commons.loader.RevenueLoader;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SimpleExample {

    public static void main(String[] args) {
        RevenueLoader loader = new RevenueLoader();

        Market data = loader.getMarketData();

        System.out.printf("Supermarket: %s%n", data.getName());
        System.out.printf("Year: %d%n", data.getYear());

        List<String> countries = data
            .getCountries()
            .stream()
            .map(Country::getName)
            .sorted()
            .toList();

        System.out.printf("%nAll countries:%n%n");
        countries.forEach(s -> {
            System.out.printf(" * %s%n", s);
        });

        List<String> stores = data
            .getCountries()
            .stream()
            .map(Country::getStores)
            .flatMap(List::stream)
            .map(Store::getId)
            .sorted()
            .toList();

        System.out.printf("%nAll Stores:%n%n");
        stores.forEach(s -> {
            System.out.printf(" * %s%n", s);
        });

        Set<String> sections = data
            .getCountries()
            .stream()
            .map(Country::getStores)
            .flatMap(List::stream)
            .map(Store::getSections)
            .flatMap(List::stream)
            .map(Section::getName)
            .collect(Collectors.toSet());

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
            .mapToDouble(Section::getRevenue)
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
                    .mapToDouble(Section::getRevenue)
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
                    .mapToDouble(Section::getRevenue)
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
