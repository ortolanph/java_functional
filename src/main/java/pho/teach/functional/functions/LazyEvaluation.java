package pho.teach.functional.functions;

import pho.teach.functional.commons.entities.functions.Country;
import pho.teach.functional.commons.entities.functions.Market;
import pho.teach.functional.commons.entities.functions.Month;
import pho.teach.functional.commons.entities.functions.Section;
import pho.teach.functional.commons.entities.functions.Store;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
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

    protected Stream<Country> filteredCountries(String countryName) {
        return
                market
                        .getCountries()
                        .stream()
                        .filter(country -> country.getName().equals(countryName));
    }

    protected Stream<Country> filteredCountries(Predicate<Country> countryPredicate) {
        return
                market
                        .getCountries()
                        .stream()
                        .filter(countryPredicate);
    }

    protected Stream<Store> stores(Stream<Country> countryStream) {
        return countryStream
                .map(Country::getStores)
                .flatMap(List::stream);
    }

    protected Stream<Store> filteredStores(String countryName) {
        return filteredCountries(countryName)
                .map(Country::getStores)
                .flatMap(List::stream);
    }

    protected Stream<Store> filteredStores(Predicate<Country> countryPredicate) {
        return filteredCountries(countryPredicate)
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

    public List<String> storesByCountry(String countryName) {
        return stores(filteredCountries(countryName))
                .map(Store::getId)
                .sorted()
                .toList();
    }

    public List<String> storesByCountry(Predicate<Country> countryPredicate) {
        return stores(filteredCountries(countryPredicate))
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

    public Double totalRevenuesBySection(String sectionName) {
        return revenues(sections(stores(countries())).filter(section -> section.getSection().equals(sectionName)))
                .mapToDouble(Month::getRevenue)
                .sum();
    }

    public Double totalRevenuesBySection(Predicate<Section> sectionPredicate) {
        return revenues(sections(stores(countries())).filter(sectionPredicate))
                .mapToDouble(Month::getRevenue)
                .sum();
    }

    public Double totalRevenuesByCountryAndSection(String countryName, String sectionName) {
        return revenues(sections(stores(filteredCountries(countryName)))
                .filter(section -> section.getSection().equals(sectionName)))
                .mapToDouble(Month::getRevenue)
                .sum();
    }

    public Double totalRevenuesByCountryAndSection(Predicate<Country> countryPredicate, Predicate<Section> sectionPredicate) {
        return revenues(
                sections(
                        stores(
                                countries()
                                        .filter(countryPredicate)
                        )
                ).filter(sectionPredicate))
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

    public Map<String, Double> getOneCountrySummary(String countryName) {
        return
                countries()
                        .filter(country -> country.getName().equals(countryName))
                        .collect(Collectors.toMap(
                                Country::getName,
                                country -> revenues(sections(country.getStores().stream()))
                                        .mapToDouble(Month::getRevenue)
                                        .sum()
                        ));
    }

    public Map<String, Double> getOneCountrySummary(Predicate<Country> countryPredicate) {
        return
                countries()
                        .filter(countryPredicate)
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

}
