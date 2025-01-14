package pho.teach.functional.commons.entities.consumers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String id;
    private MessageAction action;
    private MessageHeader header;
    private MessageBody body;
}
