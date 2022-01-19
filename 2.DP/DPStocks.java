public class DPStocks {
    public static void main(String[] args) {
        
    }
    //LC #121 : Best Time to Buy And Sell Stock (Only One Transaction)
    public int maxProfit(int[] prices) {
        int dpi10 = 0, dpi11 = -(int)1e9;

        for(int price : prices){
            dpi10 = Math.max(dpi10, dpi11 + price);
            dpi11 = Math.max(dpi11, 0 - price);
        }
        return dpi10;
    }
    //LC #123 : Best Time to Buy And Sell Stock (Max Two Transaction)
    public int maxProfit2(int[] prices){
        int dpi10 = 0, dpi11 = -(int)1e9;
        int dpi20 = 0, dpi21 = -(int)1e9;

        for(int price : prices){
            dpi20 = Math.max(dpi20, dpi21 + price);
            dpi21 = Math.max(dpi21, dpi10 - price);

            dpi10 = Math.max(dpi10, dpi11 + price);
            dpi11 = Math.max(dpi11, 0 - price);
        }
        return dpi20;
    }
    //LC #122 : Best Time to Buy And Sell Stock (Infinite Transactions)
    public int maxProfitInfinite_Class(int[] prices){
        int dpik0 = 0, dpik1 = -(int)1e9;
        for(int price : prices){
            int old_dpik_10 = dpik0;
            dpik0 = Math.max(dpik0, dpik1 + price);
            dpik1 = Math.max(dpik1, old_dpik_10 - price);

        }
        return dpik0;
    }
    public int maxProfitInfinite(int[] prices) {
        int profit = 0;
        for(int i = 1;i<prices.length;i++)
            profit += Math.max(0, prices[i] - prices[i - 1]);

        return profit;
            
    }
    //LC #714 : Best Time to Buy-Sell Stock - Transaction Fee (Infinite Transaction)
    public int maxProfitTransactionFee(int[] prices, int fee) {
        int dpik0 = 0, dpik1 = -(int)1e9;
        for(int price : prices){
            int old_dpik_10 = dpik0;
            dpik0 = Math.max(dpik0, dpik1 + price);
            dpik1 = Math.max(dpik1, old_dpik_10 - price - fee);

        }
        return dpik0;
        
    }
    //LC #309 : Best Time to Buy-Sell Stock - Cooldown Period of One Day (Infinite Transaction)
    public int maxProfitInfiniteCooldown(int[] prices){
        int dpik0 = 0, dpik1 = -(int)1e9 ;
        int dpi_1k_10 = dpik0;
        int dpi_2k_10 = dpik0;
        
        for(int price : prices){
            
            dpik0 = Math.max(dpik0, dpik1 + price);
            dpik1 = Math.max(dpik1, dpi_2k_10 - price);
            
            dpi_2k_10 = dpi_1k_10;
            dpi_1k_10 = dpik0;


        }
        return dpik0;
    }
    //LC #188 : Best Time to Buy-Sell Stock - Atmost K Transactions
    public int maxProfit_K_Transactions(int k, int[] prices) {
        int[][] dp = new int[k+1][2];
        for(int i = 0; i<=k; i++){
            //dp[i][0] = 0;
            dp[i][1] = -(int)1e9; 
        }
        for(int price : prices){
            for(int i = k; i>0; i--){
                dp[i][0] = Math.max(dp[i][0], dp[i][1] + price);
                dp[i][1] = Math.max(dp[i][1], dp[i-1][0] - price);
            }
        }        
        return dp[k][0];
    }

}
