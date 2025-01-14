package pho.teach.functional.commons.entities.consumers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageBody {
    private String id;
    private String name;
    private Phone phone;
    private String birthDate;
}
