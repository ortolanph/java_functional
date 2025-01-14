package pho.teach.functional.commons.entities.consumers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageHeader {
    private String createdBy;
    private String timestamp;
}
