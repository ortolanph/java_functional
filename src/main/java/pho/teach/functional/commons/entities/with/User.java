package pho.teach.functional.commons.entities.with;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;

@Data
@AllArgsConstructor
@Builder
public class User {

    private String name;
    private String password;
    @With
    private boolean activated;

}
