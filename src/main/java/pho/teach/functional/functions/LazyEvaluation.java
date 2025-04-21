package pho.teach.functional.functions;

import pho.teach.functional.commons.entities.functions.Country;
import pho.teach.functional.commons.entities.functions.Market;
import pho.teach.functional.commons.entities.functions.Month;
import pho.teach.functional.commons.entities.functions.Section;
import pho.teach.functional.commons.entities.functions.Store;

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

}
