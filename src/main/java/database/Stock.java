package database;


//import io.requery.*;

//@Entity
//@Table(name = "All_Stocks")
public class Stock {

    // @Key @Generated
    int id;

    private String Symbol;
    private String Company;
    private String AllUSequities;
    private String Buy_rated_by_at_least_one_fundamental_provider;
    private String GICs_Sectors;
    private String GICs_Industries;
    private String Dividend_yield_TTM;
    private double PEG;
    private double Price_change_YTD_vs_SP_500;
    private String Credit_Suisse_current_rating;
    private String Morningstar_current_rating;
    private String Wells_Fargo_Securities_current_rating;
    private String Morningstar_Quant_valuation;
    private int Golden_Capital_Quant_Score_core;
    private int Golden_Capital_Quant_Score_growth;
    private int Golden_Capital_Quant_Score_value;



    public Stock(
            String Symbol,
            String Company,
            String AllUSequities,
            String Buy_rated_by_at_least_one_fundamental_provider,
            String GICs_Sectors,
            String GICs_Industries,
            String Dividend_yield_TTM,
            double PEG,
            double Price_change_YTD_vs_SP_500,
            String Credit_Suisse_current_rating,
            String Morningstar_current_rating,
            String Wells_Fargo_Securities_current_rating,
            String Morningstar_Quant_valuation,
            int Golden_Capital_Quant_Score_core,
            int Golden_Capital_Quant_Score_growth,
            int Golden_Capital_Quant_Score_value){
        this.AllUSequities = AllUSequities;
        this.Buy_rated_by_at_least_one_fundamental_provider = Buy_rated_by_at_least_one_fundamental_provider;
        this.Company = Company;
        this.Credit_Suisse_current_rating = Credit_Suisse_current_rating;
        this.Dividend_yield_TTM = Dividend_yield_TTM;
        this.GICs_Industries = GICs_Industries;
        this.GICs_Sectors = GICs_Sectors;
        this.Golden_Capital_Quant_Score_core = Golden_Capital_Quant_Score_core;
        this.Golden_Capital_Quant_Score_growth = Golden_Capital_Quant_Score_growth;
        this.Golden_Capital_Quant_Score_value = Golden_Capital_Quant_Score_value;
        this.Morningstar_current_rating = Morningstar_current_rating;
        this.Morningstar_Quant_valuation = Morningstar_Quant_valuation;
        this.Price_change_YTD_vs_SP_500 = Price_change_YTD_vs_SP_500;
        this.PEG = PEG;
        this.Wells_Fargo_Securities_current_rating = Wells_Fargo_Securities_current_rating;
        this.Symbol = Symbol;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getAllUSequities() {
        return AllUSequities;
    }

    public void setAllUSequities(String allUSequities) {
        AllUSequities = allUSequities;
    }

    public String getBuy_rated_by_at_least_one_fundamental_provider() {
        return Buy_rated_by_at_least_one_fundamental_provider;
    }

    public void setBuy_rated_by_at_least_one_fundamental_provider(String buy_rated_by_at_least_one_fundamental_provider) {
        Buy_rated_by_at_least_one_fundamental_provider = buy_rated_by_at_least_one_fundamental_provider;
    }

    public String getGICs_Sectors() {
        return GICs_Sectors;
    }

    public String getSector(){
                return GICs_Sectors;
    }

    public void setGICs_Sectors(String GICs_Sectors) {
        this.GICs_Sectors = GICs_Sectors;
    }

    public String getIndustries(){
        return GICs_Industries;
    }

    public String getGICs_Industries() {
        return GICs_Industries;
    }

    public void setGICs_Industries(String GICs_Industries) {
        this.GICs_Industries = GICs_Industries;
    }

    public String getDividendYield(){
        return Dividend_yield_TTM;
    }

    public String getDividend_yield_TTM() {
        return Dividend_yield_TTM;
    }

    public void setDividend_yield_TTM(String dividend_yield_TTM) {
        Dividend_yield_TTM = dividend_yield_TTM;
    }

    public double getPEG() {
        return PEG;
    }

    public void setPEG(double PEG) {
        this.PEG = PEG;
    }

    public double getChngVS500(){
        return Price_change_YTD_vs_SP_500;
    }

    public double getPrice_change_YTD_vs_SP_500() {
        return Price_change_YTD_vs_SP_500;
    }

    public void setPrice_change_YTD_vs_SP_500(double price_change_YTD_vs_SP_500) {
        Price_change_YTD_vs_SP_500 = price_change_YTD_vs_SP_500;
    }

    public String getCredit_Suisse_current_rating() {
        return Credit_Suisse_current_rating;
    }

    public void setCredit_Suisse_current_rating(String credit_Suisse_current_rating) {
        Credit_Suisse_current_rating = credit_Suisse_current_rating;
    }

    public String getMorningstar_current_rating() {
        return Morningstar_current_rating;
    }

    public void setMorningstar_current_rating(String morningstar_current_rating) {
        Morningstar_current_rating = morningstar_current_rating;
    }

    public String getWells_Fargo_Securities_current_rating() {
        return Wells_Fargo_Securities_current_rating;
    }

    public void setWells_Fargo_Securities_current_rating(String wells_Fargo_Securities_current_rating) {
        Wells_Fargo_Securities_current_rating = wells_Fargo_Securities_current_rating;
    }

    public String getMorningstar_Quant_valuation() {
        return Morningstar_Quant_valuation;
    }

    public void setMorningstar_Quant_valuation(String morningstar_Quant_valuation) {
        Morningstar_Quant_valuation = morningstar_Quant_valuation;
    }

    public int getGolden_Capital_Quant_Score_core() {
        return Golden_Capital_Quant_Score_core;
    }

    public void setGolden_Capital_Quant_Score_core(int golden_Capital_Quant_Score_core) {
        Golden_Capital_Quant_Score_core = golden_Capital_Quant_Score_core;
    }

    public int getGolden_Capital_Quant_Score_growth() {
        return Golden_Capital_Quant_Score_growth;
    }

    public void setGolden_Capital_Quant_Score_growth(int golden_Capital_Quant_Score_growth) {
        Golden_Capital_Quant_Score_growth = golden_Capital_Quant_Score_growth;
    }

    public int getGolden_Capital_Quant_Score_value() {
        return Golden_Capital_Quant_Score_value;
    }

    public void setGolden_Capital_Quant_Score_value(int golden_Capital_Quant_Score_value) {
        Golden_Capital_Quant_Score_value = golden_Capital_Quant_Score_value;
    }



}
