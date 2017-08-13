package database;

import java.sql.Connection;
import java.util.List;

public class StockRow {

    private String Symbol;
    private String Company;
    private String allUS;
    private String leastOneBuy;
    private String MacroSector;
    private String IndustrySector;
    private String DividendYield;
    private String PEG;
    private String chgVsSP;
    private String CScurr;
    private String Mcurr;
    private String WFScurr;
    private String MQcurr;
    private String QC;
    private String QG;
    private String QV;
    private String MarketPrice;
    private String Category;
    private String TechAttribScore;
    private String TrendChartColumn;
    private String PFTrend;
    private String PFSignal;
    private String RSSignal;
    private String RSColumn;
    private String PeerRSSignal;
    private String PeerRSColumn;
    private String WeeklyMomentum;
    private String WeeklyDistribution;
    private String MA200Day;
    private String VertPriceObj;
    private String RewardRisk;
    private String Yield;
    private String Optionable;
    private String UserNote;
    private String DWASector;

    public StockRow(){};


    public StockRow(String symbol, String company, String allUS, String leastOneBuy, String macroSector, String industrySector, String dividendYield, String PEG, String chgVsSP, String CScurr, String mcurr, String WFScurr, String MQcurr, String QC, String QG, String QV, String marketPrice, String category, String techAttribScore, String trendChartColumn, String PFTrend, String PFSignal, String RSSignal, String RSColumn, String peerRSSignal, String peerRSColumn, String weeklyMomentum, String weeklyDistribution, String MA200Day, String vertPriceObj, String rewardRisk, String yield, String optionable, String userNote, String DWASector) {
        this.Symbol = symbol;
        this.Company = company;
        this.allUS = allUS;
        this.leastOneBuy = leastOneBuy;
        this.MacroSector = macroSector;
        this.IndustrySector = industrySector;
        this.DividendYield = dividendYield;
        this.PEG = PEG;
        this.chgVsSP = chgVsSP;
        this.CScurr = CScurr;
        this.Mcurr = mcurr;
        this.WFScurr = WFScurr;
        this.MQcurr = MQcurr;
        this.QC = QC;
        this.QG = QG;
        this.QV = QV;
        this.MarketPrice = marketPrice;
        this.Category = category;
        this.TechAttribScore = techAttribScore;
        this.TrendChartColumn = trendChartColumn;
        this.PFTrend = PFTrend;
        this.PFSignal = PFSignal;
        this.RSSignal = RSSignal;
        this.RSColumn = RSColumn;
        this.PeerRSSignal = peerRSSignal;
        this.PeerRSColumn = peerRSColumn;
        this.WeeklyMomentum = weeklyMomentum;
        this.WeeklyDistribution = weeklyDistribution;
        this.MA200Day = MA200Day;
        this.VertPriceObj = vertPriceObj;
        this.RewardRisk = rewardRisk;
        this.Yield = yield;
        this.Optionable = optionable;
        this.UserNote = userNote;
        this.DWASector = DWASector;
    }



    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        this.Symbol = symbol;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        this.Company = company;
    }

    public String getAllUS() {
        return allUS;
    }

    public void setAllUS(String allUS) {
        this.allUS = allUS;
    }

    public String getLeastOneBuy() {
        return leastOneBuy;
    }

    public void setLeastOneBuy(String leastOneBuy) {
        this.leastOneBuy = leastOneBuy;
    }

    public String getMacroSector() {
        return MacroSector;
    }

    public void setMacroSector(String macroSector) {
        this.MacroSector = macroSector;
    }

    public String getIndustrySector() {
        return IndustrySector;
    }

    public void setIndustrySector(String industrySector) {
        this.IndustrySector = industrySector;
    }

    public String getDividendYield() {
        return DividendYield;
    }

    public void setDividendYield(String dividendYield) {
        this.DividendYield = dividendYield;
    }

    public String getPEG() {
        return PEG;
    }

    public void setPEG(String PEG) {
        this.PEG = PEG;
    }

    public String getChgVsSP() {
        return chgVsSP;
    }

    public void setChgVsSP(String chgVsSP) {
        this.chgVsSP = chgVsSP;
    }

    public String getCScurr() {
        return CScurr;
    }

    public void setCScurr(String CScurr) {
        this.CScurr = CScurr;
    }

    public String getMcurr() {
        return Mcurr;
    }

    public void setMcurr(String mcurr) {
        this.Mcurr = mcurr;
    }

    public String getWFScurr() {
        return WFScurr;
    }

    public void setWFScurr(String WFScurr) {
        this.WFScurr = WFScurr;
    }

    public String getMQcurr() {
        return MQcurr;
    }

    public void setMQcurr(String MQcurr) {
        this.MQcurr = MQcurr;
    }

    public String getQC() {
        return QC;
    }

    public void setQC(String QC) {
        this.QC = QC;
    }

    public String getQG() {
        return QG;
    }

    public void setQG(String QG) {
        this.QG = QG;
    }

    public String getQV() {
        return QV;
    }

    public void setQV(String QV) {
        this.QV = QV;
    }

    public String getMarketPrice() {
        return MarketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.MarketPrice = marketPrice;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        this.Category = category;
    }

    public String getTechAttribScore() {
        return TechAttribScore;
    }

    public void setTechAttribScore(String techAttribScore) {
        this.TechAttribScore = techAttribScore;
    }

    public String getTrendChartColumn() {
        return TrendChartColumn;
    }

    public void setTrendChartColumn(String trendChartColumn) {
        this.TrendChartColumn = trendChartColumn;
    }

    public String getPFTrend() {
        return PFTrend;
    }

    public void setPFTrend(String PFTrend) {
        this.PFTrend = PFTrend;
    }

    public String getPFSignal() {
        return PFSignal;
    }

    public void setPFSignal(String PFSignal) {
        this.PFSignal = PFSignal;
    }

    public String getRSSignal() {
        return RSSignal;
    }

    public void setRSSignal(String RSSignal) {
        this.RSSignal = RSSignal;
    }

    public String getRSColumn() {
        return RSColumn;
    }

    public void setRSColumn(String RSColumn) {
        this.RSColumn = RSColumn;
    }

    public String getPeerRSSignal() {
        return PeerRSSignal;
    }

    public void setPeerRSSignal(String peerRSSignal) {
        this.PeerRSSignal = peerRSSignal;
    }

    public String getPeerRSColumn() {
        return PeerRSColumn;
    }

    public void setPeerRSColumn(String peerRSColumn) {
        this.PeerRSColumn = peerRSColumn;
    }

    public String getWeeklyMomentum() {
        return WeeklyMomentum;
    }

    public void setWeeklyMomentum(String weeklyMomentum) {
        this.WeeklyMomentum = weeklyMomentum;
    }

    public String getWeeklyDistribution() {
        return WeeklyDistribution;
    }

    public void setWeeklyDistribution(String weeklyDistribution) {
        this.WeeklyDistribution = weeklyDistribution;
    }

    public String getMA200Day() {
        return MA200Day;
    }

    public void setMA200Day(String MA200Day) {
        this.MA200Day = MA200Day;
    }

    public String getVertPriceObj() {
        return VertPriceObj;
    }

    public void setVertPriceObj(String vertPriceObj) {
        this.VertPriceObj = vertPriceObj;
    }

    public String getRewardRisk() {
        return RewardRisk;
    }

    public void setRewardRisk(String rewardRisk) {
        this.RewardRisk = rewardRisk;
    }

    public String getYield() {
        return Yield;
    }

    public void setYield(String yield) {
        this.Yield = yield;
    }

    public String getOptionable() {
        return Optionable;
    }

    public void setOptionable(String optionable) {
        this.Optionable = optionable;
    }

    public String getUserNote() {
        return UserNote;
    }

    public void setUserNote(String userNote) {
        this.UserNote = userNote;
    }

    public String getDWASector() {
        return DWASector;
    }

    public void setDWASector(String DWASector) {
        this.DWASector = DWASector;
    }
}
