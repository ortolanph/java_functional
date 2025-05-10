package pho.teach.functional.functions;

import pho.teach.functional.commons.entities.functions.Country;
import pho.teach.functional.commons.entities.functions.Market;
import pho.teach.functional.commons.entities.functions.Month;
import pho.teach.functional.commons.entities.functions.Section;
import pho.teach.functional.commons.entities.functions.Store;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LazyEvaluation {

    private Market market;

    public LazyEvaluation(Market market) {
        this.market = market;
    }

    protected Stream<Country> countriesStream() {
        return market
                .getCountries()
                .stream();
    }

    protected Stream<Country> filteredCountriesStream(String countryName) {
        return
                market
                        .getCountries()
                        .stream()
                        .filter(country -> country.getName().equals(countryName));
    }

    protected Stream<Country> filteredCountriesStream(Predicate<Country> countryPredicate) {
        return
                market
                        .getCountries()
                        .stream()
                        .filter(countryPredicate);
    }

    protected Stream<Store> storeStream(Stream<Country> countryStream) {
        return countryStream
                .map(Country::getStores)
                .flatMap(List::stream);
    }

    protected Stream<String> mappedStore(Predicate<Country> countryPredicate, Function<Store, String> storeFunction) {
        return storeStream(filteredCountriesStream(countryPredicate))
            .map(storeFunction);
    }

    protected Stream<Store> filteredStores(String countryName) {
        return filteredCountriesStream(countryName)
                .map(Country::getStores)
                .flatMap(List::stream);
    }

    protected Stream<Store> filteredStores(Predicate<Country> countryPredicate) {
        return filteredCountriesStream(countryPredicate)
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
        return countriesStream()
                .map(Country::getName)
                .sorted()
                .toList();
    }

    public List<String> allStores() {
        return storeStream(countriesStream())
                .map(Store::getId)
                .sorted()
                .toList();
    }

    public List<String> storesByCountry(String countryName) {
        return storeStream(filteredCountriesStream(countryName))
                .map(Store::getId)
                .sorted()
                .toList();
    }

    public List<String> storesByCountry(Predicate<Country> countryPredicate) {
        // 🗺
        return storeStream(filteredCountriesStream(countryPredicate))
                .map(Store::getId)
                .sorted()
                .toList();
    }

    public List<String> storesByCountryFormatted(Predicate<Country> countryPredicate, Function<Store, String> storeFunction) {
        return mappedStore(countryPredicate, storeFunction)
            .toList();
    }

    public Set<String> allSections() {
        return sections(storeStream(countriesStream()))
                .map(Section::getSection)
                .collect(Collectors.toSet());

    }

    public Double totalRevenues() {
        return revenues(sections(storeStream(countriesStream())))
                .mapToDouble(Month::getRevenue)
                .sum();
    }

    public Double totalRevenuesBySection(String sectionName) {
        return revenues(sections(storeStream(countriesStream())).filter(section -> section.getSection().equals(sectionName)))
                .mapToDouble(Month::getRevenue)
                .sum();
    }

    public Double totalRevenuesBySection(Predicate<Section> sectionPredicate) {
        return revenues(sections(storeStream(countriesStream())).filter(sectionPredicate))
                .mapToDouble(Month::getRevenue)
                .sum();
    }

    public Double totalRevenuesByCountryAndSection(String countryName, String sectionName) {
        return revenues(sections(storeStream(filteredCountriesStream(countryName)))
                .filter(section -> section.getSection().equals(sectionName)))
                .mapToDouble(Month::getRevenue)
                .sum();
    }

    public Double totalRevenuesByCountryAndSection(Predicate<Country> countryPredicate, Predicate<Section> sectionPredicate) {
        return revenues(
                sections(
                        storeStream(
                                countriesStream()
                                        .filter(countryPredicate)
                        )
                ).filter(sectionPredicate))
                .mapToDouble(Month::getRevenue)
                .sum();
    }

    public Map<String, Double> getCountrySummary() {
        return
                countriesStream()
                        .collect(Collectors.toMap(
                                Country::getName,
                                country -> revenues(sections(country.getStores().stream()))
                                        .mapToDouble(Month::getRevenue)
                                        .sum()
                        ));
    }

    public Map<String, Double> getOneCountrySummary(String countryName) {
        return
                countriesStream()
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
                countriesStream()
                        .filter(countryPredicate)
                        .collect(Collectors.toMap(
                                Country::getName,
                                country -> revenues(sections(country.getStores().stream()))
                                        .mapToDouble(Month::getRevenue)
                                        .sum()
                        ));
    }

    public Map<String, Double> getStoreSummary() {
        return storeStream(countriesStream())
                .collect(Collectors.toMap(
                        Store::getId,
                        store -> revenues(store.getSections().stream())
                                .mapToDouble(Month::getRevenue)
                                .sum()
                ));
    }

    public Map<String, List<String>> getCountryStores() {
        return countriesStream()
                .collect(Collectors.toMap(
                        Country::getName,
                        country -> country
                                .getStores()
                                .stream()
                                .map(Store::getId)
                                .toList()));
    }

}
