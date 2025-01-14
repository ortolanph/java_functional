package pho.teach.functional.functions;

import pho.teach.functional.commons.entities.functions.DetailedStoreDTO;
import pho.teach.functional.commons.entities.functions.Month;
import pho.teach.functional.commons.entities.functions.Section;
import pho.teach.functional.commons.entities.functions.Store;
import pho.teach.functional.functions.mapper.DetailedStoreMapper;

import java.util.List;
import java.util.function.Predicate;

public class DetailedStoreMapperImpl implements DetailedStoreMapper {

    @Override
    public DetailedStoreDTO convert(Store store) {
        return DetailedStoreDTO
            .builder()
            .id(store.getId())
            .fresh(unwrapRevenue(store, "Fresh"))
            .deli(unwrapRevenue(store, "Deli"))
            .frozen(unwrapRevenue(store, "Frozen"))
            .bakery(unwrapRevenue(store, "Bakery"))
            .meat(unwrapRevenue(store, "Meat"))
            .seafood(unwrapRevenue(store, "Seafood"))
            .dairy(unwrapRevenue(store, "Dairy"))
            .cereal(unwrapRevenue(store, "Cereal"))
            .snacks(unwrapRevenue(store, "Snacks"))
            .pets(unwrapRevenue(store, "Pets"))
            .house(unwrapRevenue(store, "House"))
            .cleanliness(unwrapRevenue(store, "Cleanliness"))
            .build();
    }

    private double unwrapRevenue(Store store, String sectionName) {
        return store
            .getSections()
            .stream()
            .filter(sectionNamePredicate(sectionName))
            .findFirst()
            .orElseGet(this::zeroedSection)
            .getRevenues()
            .stream()
            .mapToDouble(Month::getRevenue)
            .sum();
    }

    Predicate<Section> sectionNamePredicate(String sectionName) {
        return section -> section.getSection().equals(sectionName);
    }

    private Section zeroedSection() {
        return Section
            .builder()
            .section("")
            .revenues(
                List.of(
                    Month
                        .builder()
                        .month("any")
                        .revenue(0.0)
                        .build()
                )
            )
            .build();
    }
}
