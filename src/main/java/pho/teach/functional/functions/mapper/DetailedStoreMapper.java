package pho.teach.functional.functions.mapper;

import pho.teach.functional.commons.entities.DetailedStoreDTO;
import pho.teach.functional.commons.entities.Store;

@FunctionalInterface
public interface DetailedStoreMapper {

    DetailedStoreDTO convert(Store store);

}
