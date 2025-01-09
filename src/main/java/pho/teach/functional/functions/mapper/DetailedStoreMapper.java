package pho.teach.functional.functions.mapper;

import pho.teach.functional.commons.entities.Store;
import pho.teach.functional.commons.entities.DetailedStoreDTO;

@FunctionalInterface
public interface DetailedStoreMapper {

    DetailedStoreDTO convert(Store store);

}
