package pho.teach.functional.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {
    private String id;
    private double fresh;
    private double deli;
    private double frozen;
    private double bakery;
    private double meat;
    private double seafood;
    private double dairy;
    private double cereal;
    private double snacks;
    private double pets;
    private double house;
    private double cleanliness;
}
