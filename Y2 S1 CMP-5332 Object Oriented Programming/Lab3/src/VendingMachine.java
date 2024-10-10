class VendingMachine {
    static final int CANDY_PRICE = 3;
    static final int CANDY_CAPACITY = 20;
    
    int candyBars;
    int balance;
    int revenue;
    
    VendingMachine() {
        candyBars = CANDY_CAPACITY;
        balance = 0;
        revenue = 0;
    }
    
    int getBalance() {
        return balance;
    }
    
    int getRevenue() {
        return revenue;
    }
    
    void insertCoin() {
        balance++;
    }
    
    int refund() {
        int amount = balance;
        balance = 0;
        return amount;
    }
    
    boolean vendCandyBar() {
        if(candyBars >= 1 && balance >= CANDY_PRICE) {
            candyBars--;
            balance -= CANDY_PRICE;
            revenue += CANDY_PRICE;
            return true;
        } else {
            return false;
        }
    }
    
    void restock() {
        candyBars = CANDY_CAPACITY;
    }
}    