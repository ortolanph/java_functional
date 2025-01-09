package pho.teach.functional.functions;

import pho.teach.functional.commons.entities.Market;
import pho.teach.functional.commons.loader.RevenueLoader;

public class MarketManager {

    private final Market data;

    public MarketManager() {
        RevenueLoader loader = new RevenueLoader();

        this.data = loader.getMarketData();
    }

    public String getMarketName() {
        return data.getName();
    }

    public int getReportYear() {
        return data.getYear();
    }
}
