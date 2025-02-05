package pho.teach.functional.commons.entities.functions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    private String id;
    private int employees;
    private String location;
    private boolean delivery;
    private List<Section> sections;
}
