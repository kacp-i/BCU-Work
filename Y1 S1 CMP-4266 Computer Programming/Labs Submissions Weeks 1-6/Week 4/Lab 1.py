def add(num1, num2): #function that adds 2 numbers together
    numSum = num1 + num2
    return numSum 

def subtract(num1, num2): #function that subtracts 2 numbers together
    numSum = num1 - num2 
    return numSum 

def multiply(num1, num2): #function that multiplies 2 numbers together
    numSum = num1 * num2 
    return numSum 

def divide(num1, num2): #function that divides 2 numbers together
    numSum = num1 / num2 
    return numSum 


def calculator(num1,num2,operator): #function that decides what function is called based on operator input
    if operator == "+": #function adds 2 numbers
        total = add(num1,num2) 
        print(f"\nAdding {num2} to {num1} = {total}")
    
    elif operator == "-": #function subtracts 2 numbers
        total = subtract(num1,num2)
        print(f"\nSubtracting {num2} from {num1} = {total}")
    
    elif operator == "/": #function divides 2 numbers
        total = divide(num1,num2)
        print(f"\nDividing {num1} by {num2} = {total}")
    
    elif operator == "*": #function multiplies 2 numbers
        total = multiply(num1,num2)
        print(f"\nMultiplying {num1} by {num2} = {total}")
        
    else:
        print("Invalid Input")

num1 = int(input("\nType number 1:\n")) #number input from user
num2 = int(input("\nType number 2:\n"))
operator = input("\nSelect an operator; * for multiplication, - for subtraction, + for addition, / for division:\n") #operator input from user

    
calculator(num1, num2, operator) #calls calculator function