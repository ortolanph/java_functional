package pho.teach.functional.functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pho.teach.functional.commons.entities.DetailedStoreDTO;
import pho.teach.functional.commons.entities.Month;
import pho.teach.functional.commons.entities.Section;
import pho.teach.functional.commons.entities.Store;
import pho.teach.functional.functions.mapper.DetailedStoreMapper;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class DetailedStoreMapperImplTest {

    private static final Store FULL_STORE = Store
        .builder()
        .id("ID00001")
        .sections(
            List.of(
                Section
                    .builder()
                    .name("Fresh")
                    .revenues(
                        List.of(
                            Month
                                .builder()
                                .month("any")
                                .revenue(1)
                                .build()
                        )
                    )
                    .build(),
                Section
                    .builder()
                    .name("Deli")
                    .revenues(
                        List.of(
                            Month
                                .builder()
                                .month("any")
                                .revenue(2)
                                .build()
                        )
                    )
                    .build(),
                Section
                    .builder()
                    .name("Frozen")
                    .revenues(
                        List.of(
                            Month
                                .builder()
                                .month("any")
                                .revenue(3)
                                .build()
                        )
                    )
                    .build(),
                Section
                    .builder()
                    .name("Bakery")
                    .revenues(
                        List.of(
                            Month
                                .builder()
                                .month("any")
                                .revenue(4)
                                .build()
                        )
                    )
                    .build(),
                Section
                    .builder()
                    .name("Meat")
                    .revenues(
                        List.of(
                            Month
                                .builder()
                                .month("any")
                                .revenue(5)
                                .build()
                        )
                    )
                    .build(),
                Section
                    .builder()
                    .name("Seafood")
                    .revenues(
                        List.of(
                            Month
                                .builder()
                                .month("any")
                                .revenue(6)
                                .build()
                        )
                    )
                    .build(),
                Section
                    .builder()
                    .name("Dairy")
                    .revenues(
                        List.of(
                            Month
                                .builder()
                                .month("any")
                                .revenue(7)
                                .build()
                        )
                    )
                    .build(),
                Section
                    .builder()
                    .name("Cereal")
                    .revenues(
                        List.of(
                            Month
                                .builder()
                                .month("any")
                                .revenue(8)
                                .build()
                        )
                    )
                    .build(),
                Section
                    .builder()
                    .name("Snacks")
                    .revenues(
                        List.of(
                            Month
                                .builder()
                                .month("any")
                                .revenue(9)
                                .build()
                        )
                    )
                    .build(),
                Section
                    .builder()
                    .name("Pets")
                    .revenues(
                        List.of(
                            Month
                                .builder()
                                .month("any")
                                .revenue(10)
                                .build()
                        )
                    )
                    .build(),
                Section
                    .builder()
                    .name("House")
                    .revenues(
                        List.of(
                            Month
                                .builder()
                                .month("any")
                                .revenue(11)
                                .build()
                        )
                    )
                    .build(),
                Section
                    .builder()
                    .name("Cleanliness")
                    .revenues(
                        List.of(
                            Month
                                .builder()
                                .month("any")
                                .revenue(12)
                                .build()
                        )
                    )
                    .build()
            )
        )
        .build();

    private static Store PART_STORE = Store
        .builder()
        .id("ID00002")
        .sections(
            List.of(
                Section
                    .builder()
                    .name("Fresh")
                    .revenues(
                        List.of(
                            Month
                                .builder()
                                .month("any")
                                .revenue(1)
                                .build()
                        )
                    )
                    .build(),
                Section
                    .builder()
                    .name("Deli")
                    .revenues(
                        List.of(
                            Month
                                .builder()
                                .month("any")
                                .revenue(2)
                                .build()
                        )
                    )
                    .build(),
                Section
                    .builder()
                    .name("Dairy")
                    .revenues(
                        List.of(
                            Month
                                .builder()
                                .month("any")
                                .revenue(7)
                                .build()
                        )
                    )
                    .build(),
                Section
                    .builder()
                    .name("Snacks")
                    .revenues(
                        List.of(
                            Month
                                .builder()
                                .month("any")
                                .revenue(9)
                                .build()
                        )
                    )
                    .build()
            )
        )
        .build();

    private DetailedStoreMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new DetailedStoreMapperImpl();
    }

    @Test()
    @DisplayName("Full Store")
    void should_convert_test_full() {
        DetailedStoreDTO detailedStoreDTO = mapper.convert(FULL_STORE);

        assertThat(detailedStoreDTO.getId(), equalTo(FULL_STORE.getId()));
        assertThat(detailedStoreDTO.getFresh(), equalTo(1.0));
        assertThat(detailedStoreDTO.getDeli(), equalTo(2.0));
        assertThat(detailedStoreDTO.getFrozen(), equalTo(3.0));
        assertThat(detailedStoreDTO.getBakery(), equalTo(4.0));
        assertThat(detailedStoreDTO.getMeat(), equalTo(5.0));
        assertThat(detailedStoreDTO.getSeafood(), equalTo(6.0));
        assertThat(detailedStoreDTO.getDairy(), equalTo(7.0));
        assertThat(detailedStoreDTO.getCereal(), equalTo(8.0));
        assertThat(detailedStoreDTO.getSnacks(), equalTo(9.0));
        assertThat(detailedStoreDTO.getPets(), equalTo(10.0));
        assertThat(detailedStoreDTO.getHouse(), equalTo(11.0));
        assertThat(detailedStoreDTO.getCleanliness(), equalTo(12.0));
    }

    @Test()
    @DisplayName("Not All Departments Store")
    void should_convert_test_not_all_departments() {
        DetailedStoreDTO detailedStoreDTO = mapper.convert(PART_STORE);

        assertThat(detailedStoreDTO.getId(), equalTo(PART_STORE.getId()));
        assertThat(detailedStoreDTO.getFresh(), equalTo(1.0));
        assertThat(detailedStoreDTO.getDeli(), equalTo(2.0));
        assertThat(detailedStoreDTO.getFrozen(), equalTo(0.0));
        assertThat(detailedStoreDTO.getBakery(), equalTo(0.0));
        assertThat(detailedStoreDTO.getMeat(), equalTo(0.0));
        assertThat(detailedStoreDTO.getSeafood(), equalTo(0.0));
        assertThat(detailedStoreDTO.getDairy(), equalTo(7.0));
        assertThat(detailedStoreDTO.getCereal(), equalTo(0.0));
        assertThat(detailedStoreDTO.getSnacks(), equalTo(9.0));
        assertThat(detailedStoreDTO.getPets(), equalTo(0.0));
        assertThat(detailedStoreDTO.getHouse(), equalTo(0.0));
        assertThat(detailedStoreDTO.getCleanliness(), equalTo(0.0));
    }

}