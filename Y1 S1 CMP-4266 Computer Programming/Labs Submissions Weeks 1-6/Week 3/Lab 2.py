corPass = "test100" #correct predefined password
count = 0 #counts how many times a password attempt has been made

while count < 5: #keeps looping until 5 attempts have been made
    userPass = input("\nPlease enter your password\n") #asks user for password input

    if userPass == corPass: #checks if user typed password is the same as correct password
        print("\nYou are logged in")
        break #exits the while loop
    
    else: #if user typed password isnt correct this will run
        print("\nIncorrect login details\n")

    count += 1 #adds 1 to count to show the amount of attempts
    #print(count) #checks the number of count
    
    if count == 5: #runs once count is 5
        print("You’ve reached the maximum login attempts")
