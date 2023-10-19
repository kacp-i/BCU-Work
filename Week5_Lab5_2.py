guestList = [] #list to hold names


def guestAdd(): #function that adds a guest to guestList
    guestName = input("\nEnter the name of the guest to add: ")
    
    guestList.append(guestName) #appends guestList with guestName
    print(guestName, "has been added to the guest list\n")
    
    menu() #calls menu function to loop back

def guestRemove(): #function that removes a guest from guestList
    guestName = input("\nEnter the name of the guest you want to remove: ")
    
    try: #checks if guestName is in guestList
        guestList.remove(guestName)
        print(guestName, "has been removed from the guest list\n")
        
    except: #displays to user that guestName isnt in guestList
        print(guestName, "is not present in the guest list")
    
    menu()

def guestShow():
    sortedList = sorted(guestList) #sorts list into alphabetical order
    for name in sortedList: #goes through each element of list one by one
        print(name) #prints currently looked at element
    
    print("\nThe current list of guests (sorted alphabetically):")
    
    menu()

def menu(): #function that prints options and allows a user to choose function to call
    print("""
Options:\n
A. Add a guest
R. Remove a guest
P. Display guest list
Q. Quit\n""")

    choice = input("Enter your choice: ")
    
    if choice.upper() == "A": #varName.upper() converts user input into capitals
        guestAdd()
    elif choice.upper() == "R":
        guestRemove()
    elif choice.upper() == "P":
        guestShow()
    elif choice.upper() == "Q":
        guestCount = len(guestList) #grabs length of list
        print("Total number of guests:", guestCount, "\nGoodbye! See you in the party!")
        
        pass #exits loop
    else:
        print("Invalid choice. Please choose a valid option(A, R, P or Q)")
        
        menu()
        
menu() #starts loop