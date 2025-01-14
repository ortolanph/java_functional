package pho.teach.functional.functions.mapper;

import pho.teach.functional.commons.entities.functions.DetailedStoreDTO;
import pho.teach.functional.commons.entities.functions.Store;

@FunctionalInterface
public interface DetailedStoreMapper {

    DetailedStoreDTO convert(Store store);

}
