userYear = int(input("Type a year to check if it is a leap year\n")) #asks for user number input

if userYear % 400 == 0: #case 1: checks if year is multiple of 400
    print("The year",userYear,"is a leap year")
    
elif userYear % 4 == 0 and userYear % 100 != 0: #case 2: checks if year is multiple of 4 but not multiple of 100
    print("The year",userYear,"is a leap year")

else: #if case 1 or 2 arent met then it is not a leap year
    print("The year",userYear,"isnt a leap year")