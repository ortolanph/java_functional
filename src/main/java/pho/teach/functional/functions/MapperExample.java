package pho.teach.functional.functions;

import pho.teach.functional.commons.entities.Section;
import pho.teach.functional.commons.entities.Store;
import pho.teach.functional.commons.entities.StoreDTO;
import pho.teach.functional.functions.mapper.StoreMapper;

import java.util.List;
import java.util.function.Predicate;

public class MapperExample implements StoreMapper {

    public static void main(String[] args) {
        MapperExample mapperExample = new MapperExample();

        List<Store> stores = List.of(
            Store
                .builder()
                .id("ID00001")
                .sections(
                    List.of(
                        Section
                            .builder()
                            .name("Fresh")
                            .revenue(1)
                            .build(),
                        Section
                            .builder()
                            .name("Deli")
                            .revenue(2)
                            .build(),
                        Section
                            .builder()
                            .name("Frozen")
                            .revenue(3)
                            .build(),
                        Section
                            .builder()
                            .name("Bakery")
                            .revenue(4)
                            .build(),
                        Section
                            .builder()
                            .name("Meat")
                            .revenue(5)
                            .build(),
                        Section
                            .builder()
                            .name("Seafood")
                            .revenue(6)
                            .build(),
                        Section
                            .builder()
                            .name("Dairy")
                            .revenue(7)
                            .build(),
                        Section
                            .builder()
                            .name("Cereal")
                            .revenue(8)
                            .build(),
                        Section
                            .builder()
                            .name("Snacks")
                            .revenue(9)
                            .build(),
                        Section
                            .builder()
                            .name("Pets")
                            .revenue(10)
                            .build(),
                        Section
                            .builder()
                            .name("House")
                            .revenue(11)
                            .build(),
                        Section
                            .builder()
                            .name("Cleanliness")
                            .revenue(11)
                            .build()
                    )
                )
                .build()
        );

        List<StoreDTO> mappedStores = stores
            .stream()
            .map(mapperExample::convert)
            .toList();

        System.out.println(stores.get(0));
        System.out.println(mappedStores.get(0));
    }


    @Override
    public StoreDTO convert(Store store) {
        return StoreDTO
            .builder()
            .id(store.getId())
            .fresh(store.getSections().stream().filter(sectionNamePredicate("Fresh")).toList().get(0).getRevenue())
            .deli(store.getSections().stream().filter(sectionNamePredicate("Deli")).toList().get(0).getRevenue())
            .frozen(store.getSections().stream().filter(sectionNamePredicate("Frozen")).toList().get(0).getRevenue())
            .bakery(store.getSections().stream().filter(sectionNamePredicate("Bakery")).toList().get(0).getRevenue())
            .meat(store.getSections().stream().filter(sectionNamePredicate("Meat")).toList().get(0).getRevenue())
            .seafood(store.getSections().stream().filter(sectionNamePredicate("Seafood")).toList().get(0).getRevenue())
            .dairy(store.getSections().stream().filter(sectionNamePredicate("Dairy")).toList().get(0).getRevenue())
            .cereal(store.getSections().stream().filter(sectionNamePredicate("Cereal")).toList().get(0).getRevenue())
            .snacks(store.getSections().stream().filter(sectionNamePredicate("Snacks")).toList().get(0).getRevenue())
            .pets(store.getSections().stream().filter(sectionNamePredicate("Pets")).toList().get(0).getRevenue())
            .house(store.getSections().stream().filter(sectionNamePredicate("House")).toList().get(0).getRevenue())
            .cleanliness(store.getSections().stream().filter(sectionNamePredicate("Cleanliness")).toList().get(0).getRevenue())
            .build();
    }

    Predicate<Section> sectionNamePredicate(String sectionName) {
        return section -> section.getName().equals(sectionName);
    }
}
