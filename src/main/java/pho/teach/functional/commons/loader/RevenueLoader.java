package pho.teach.functional.commons.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import pho.teach.functional.commons.entities.Market;

import java.io.File;
import java.io.IOException;

@Data
public class RevenueLoader {

    private Market marketData;

    public RevenueLoader() {
        String data = "data/supermarket_revenue.json";

        ObjectMapper mapper = new ObjectMapper();

        try {
            marketData = mapper.readValue(
                new File(data),
                Market.class
            );
        } catch (IOException exception) {
            System.out.printf(exception.getMessage());
        }
    }

}
