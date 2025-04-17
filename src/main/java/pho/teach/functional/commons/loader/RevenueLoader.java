package pho.teach.functional.commons.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import pho.teach.functional.commons.entities.functions.Market;

import java.io.IOException;

@Data
public class RevenueLoader {

    private Market marketData;

    public RevenueLoader(String fileName) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            marketData = mapper.readValue(
                this.getClass().getClassLoader().getResourceAsStream(fileName),
                Market.class
            );
        } catch (IOException exception) {
            System.out.printf(exception.getMessage());
        }
    }

}
