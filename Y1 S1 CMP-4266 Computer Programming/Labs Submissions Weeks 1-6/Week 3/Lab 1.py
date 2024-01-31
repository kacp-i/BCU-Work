userBalance = 1000 #declaring variables
inUse = True


def menu(): #function that displays the 4 choices and asks for an input, then returns the input
    print("\nChoose 1 for Deposit\nChoose 2 for Withdraw\nChoose 3 for Check Balance\nChoose Q to Exit")
    choice = input("\nPlease choose an option:\n") #user input
    return choice #returns user input


print("*******WELCOME TO OUR BANK*******")
userName = input("Please enter your name\n") #asks user for name input

while inUse: #as long as variable inUse is True the loop will run
    
    userChoice = menu() #calls menu function and sets the input from the function as a variable
    
    if userChoice == "1": #user chooses 'deposit'
        tempSum = float(input("\nType the deposit amount\n")) #asks user for amount
        if tempSum < 0: #checks if number is less than 0 so a user cant add negative numbers
            print("\nIT IS NOT POSSIBLE TO DEPOSIT THIS BALANCE")
        else:
            userBalance += tempSum #adds deposit amount to balance
        
    elif userChoice == "2": #user chooses 'withdraw'
       tempSum = float(input("\nType the withdraw amount\n")) #asks user for amount
       
       if tempSum > userBalance or tempSum < 0: #checks if withdraw amount is bigger than sum or less than 0
           print("\nIT IS NOT POSSIBLE TO WITHDRAW BEYOND THE ACCOUNT BALANCE")
           
       else:
           userBalance -= tempSum #subtracts withdraw amount from balance
        
    elif userChoice=="3": #user chooses 'balance check'
        print("\n~~~~~~~~~~",userName,"~~~~~~~~~~\nYour Balance: £{:.2f}".format(userBalance)) #formats the balance to 2dp
      
    elif userChoice.upper() == "Q": #user chooses 'quit'
        
        print("""
    -------------------------------------
   | Thanks for choosing us as your bank |
   | Visit us again!                     |
    -------------------------------------
        """)
        
        inUse = False #prevents the while loop from looping again
    
    else: #if none conditions are met, this will execute and loop from the start again
        print("Invalid Input, Try again.")

   