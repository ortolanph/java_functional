package pho.teach.functional.functions;

import pho.teach.functional.commons.entities.Market;
import pho.teach.functional.commons.loader.RevenueLoader;

import java.util.List;

public class MarketManager {

    private final Market data;

    public MarketManager(String profile) {
        RevenueLoader loader = new RevenueLoader();

        this.data = loader.getMarketData();
    }

    public String getMarketName() {
        return data.getName();
    }

    public int getReportYear() {
        return data.getYear();
    }

    public List<String> getAllCountries() {
        return null;
    }
}
