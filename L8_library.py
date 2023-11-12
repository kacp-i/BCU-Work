class BankAccount:
    def __init__(self, accNum, money=0):
        self.__accountNumber = accNum
        self.__balance = money
    
    def getAccount(self):
        return self.__accountNumber
    
    def getBalance(self):
        return self.__balance

    def deposit(self, amount):
        self.__balance += amount

    def withdraw(self, amount):
        self.__balance -= amount

    def transfer(self, amount, acc):
        self.__balance -= amount
        acc.deposit(amount)
    
