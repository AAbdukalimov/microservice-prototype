package kz.idf.solva.utility;

public class SqlUtility {

    private SqlUtility() {}

    public static final String MyURL = "jdbc:mysql://localhost:3306/solva";
    public static final String USER_NAME = "akanay";
    public static final String PASSWORD = "Frfyfq19071981";
    public static final String JDBCDriver = "com.mysql.cj.jdbc.Driver";
    public static final String CREATE_MONTH_LIMIT = "INSERT INTO solva.month_limit (starting_date, ending_date, expense_category, currency, sum_limit) VALUES(?,?,?,?,?);";
    public static final String CREATE_TRANSACTION = "INSERT INTO solva.transaction" +
            " (date_time, account_from, account_to, currency_shortname, sum, expense_category, transaction_sum_in_usd, limit_exceeded) VALUES(?,?,?,?,?,?,?,?);";
    public static final String CREATE_CURRENCY_RATE = "INSERT INTO solva.currency_rate (symbol, datetime, close, previous_close, is_market_open) VALUES(?,?,?,?,?);";
    public static final String FIND_ALL_TRANSACTIONS = "SELECT * FROM solva.transaction";
    public static final String FIND_ALL_MONTH_LIMITS = "SELECT * FROM solva.month_limit";
    public static final String FIND_ALL_CURRENCY_RATES = "SELECT * FROM solva.currency_rate";
    public static final String FIND_ACTUAL_RATE = "SELECT * FROM solva.currency_rate WHERE datetime = ?";

    public static final String CURRENCY_RATE_UPDATE_RESOURCE_URL = "https://api.twelvedata.com/quote?symbol=USD/KZT&interval=1day&outputsize=12&apikey=43f2bd345e4b44ec81b3b258850baf1e";
}
