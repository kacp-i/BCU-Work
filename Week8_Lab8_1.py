import L8_library as bL

def main():

    def displayBal():
        print(acc1.getAccount(), acc1.getBalance())
        print(acc2.getAccount(), acc2.getBalance())

    acc1 = bL.BankAccount(1, 100)
    acc2 = bL.BankAccount(2, 100)


    acc1.withdraw(20)
    acc2.withdraw(20)
    displayBal()

    acc1.deposit(10)
    acc2.deposit(10)
    displayBal()

    acc2.transfer(20, acc1)
    displayBal()

if __name__ == "__main__":
    main()