''' Program make a simple calculator that can add, subtract, multiply and divide using functions '''

# This function adds two numbers 
def add(x, y): #syntax - no colon
   return x + y

# This function subtracts two numbers 
def subtract(x, y): #syntax - no comma
   return x - y

# This function multiplies two numbers
def multiply(x, y): #syntax - no bracket
   return x * y

# This function divides two numbers
def divide(x, y):
   return x / y


print("Select operation.")
print("1.Add")
print("2.Subtract")
print("3.Multiply")
print("4.Divide")

# Take input from the user 
choice = input("Enter choice(1/2/3/4):")

num1 = int(input("Enter first number: ")) #logic - variable is string, cant do arithmetic with string
num2 = int(input("Enter second number: ")) #logic - variable is string, cant do arithmetic with string

if choice == '1': #syntax - no apostrophe
   print(num1,"+",num2,"=", add(num1,num2))

elif choice == '2':
   print(num1,"-",num2,"=", subtract(num1,num2)) #syntax - no var called num

elif choice == '3':
   print(num1,"*",num2,"=", multiply(num1,num2)) #syntax - no funct called Multiply

elif choice == '4': #syntax - no ==
   print(num1,"/",num2,"=", divide(num1,num2)) #syntax - no comma after "=",
else:
   print("Invalid input")
