empWage = 22 #declaring variables
empOTWage = empWage * 1.5
normWeekPay = 0 #placeholder variables
overWeekPay = 0

def displayInfo(): #function that prints all data
    print("\nHere is a breakdown of your week",empName) #prints all the information
    print("\nYou have worked for a total of ",totHour," hours")
    print("Regular Pay: {:.2f}".format(normWeekPay)) #formating to 2dp
    print("Overtime Pay: {:.2f}".format(overWeekPay))
    print("Total Gross Pay: {:.2f}".format(totGrossPay))

empName = input("What is your name?\n") #asks user for name 
totHour = float(input("How many hours have you worked this week?\n")) #asks user for hours worked (using float to allow for half hours)

if totHour <= 40 and totHour > 0: #if the user has 40 or less hours it will calculate their hours without overtime
    normWeekPay = totHour * empWage #works out weekly pay
    
    totGrossPay = normWeekPay + overWeekPay #works out total gross pay (sum of normal and overtime pay)
    
    displayInfo()
    
elif totHour > 40: #if the user has more hours it will calculate their hours with overtime
    normWeekPay = 40 * empWage #works out weekly pay
    overHour = totHour - 40 #works out overtime hours

    overWeekPay = overHour * empOTWage #works out overtime pay
    
    totGrossPay = normWeekPay + overWeekPay #works out total gross pay (sum of normal and overtime pay)
    
    displayInfo()
    
else:
    print("INVALID HOURS")
