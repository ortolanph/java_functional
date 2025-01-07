package pho.teach.functional.functions.mapper;

import pho.teach.functional.commons.entities.Store;
import pho.teach.functional.commons.entities.StoreDTO;

@FunctionalInterface
public interface StoreMapper {

    StoreDTO convert(Store store);

}
