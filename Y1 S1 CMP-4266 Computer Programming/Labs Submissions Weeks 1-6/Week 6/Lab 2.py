contactList = [("Stish","123"),("Rita","321")] #predefined list of contacts

def viewContacts(m="Menu"): #function that displays contacts
    print("\n---------- Current Contact List ----------")
    print("\nContact ID | Contact Name | Contact Number\n")
    count = 0 #used for contact ID
    for elem in contactList: #goes through contact list
        count += 1
        contactName = elem[0] #grabs value from tuple in list
        contactNum = elem[1]
        print("{:<13}{:<15}{}".format(count,contactName,contactNum)) #prints all contact info, with ID (formating to make it appear table-like)
    
    if m != "noMenu": #checks to see if function should return to menu or not (a way to keep the loop repeating)
        menu() #returns to menu select
    else:
        pass

def addContact(): #function that adds a new contact to list of contacts
    print("\n---------- Adding New Contact ----------")
    
    contactName = input("What is the contact name?\n") #asks user for name and number
    contactNum = input("What is the contact number?\n")
    
    if contactNum.isdigit() == True: #checks if number input is a number
        fullContact = (contactName,contactNum)
        contactList.append(fullContact) #adds new contact to list of contacts
        print(contactName,"-",contactNum,"has been added to the contact list") #print statement to verify it to user
    else: #runs if contactNum isnt a number
        print("Invalid Input, Returning to menu")
    menu()

def delContact(): #function that deletes a contact from list of contacts
    print("\n---------- Deleting Contact ----------")
    
    viewContacts("noMenu") #displays contact list without returning to menu
    
    delID = input("\nWhat is the ID of the contact you wish to delete?\n") #user input for user ID to be deleted - this is displayed
    try: #try except statement to check if user inputed a number for the index input
        delID = int(delID) - 1 #converts ID input to corresponding list index
        contactList.pop(delID) #deletes element in list at specified index
        print("Contact with the ID of",(delID+1),"has been deleted\n") #prints a confirmation to user
    except:
        print("\nContact not in Contact List")
    menu()

def menu(): #function that prints all the choices for user
    print("""
Select an operation:
v - View Contacts
a - Add Contact
d - Delete Contact
q - Quit""")
    
    choice = input("\nEnter Choice (v / a / d / q): ") #user choice
    
    if choice == "v": #if and elif statements to check for specific user input
        viewContacts("")
    
    elif choice == "a":
        addContact()
    
    elif choice == "d":
        delContact()
    
    elif choice == "q":
        print("\n---------- Application Closing ----------")
        pass
    
    else: #re-runs menu function if none of the conditions above have been met
        print("Invalid Input, Input is Case Sensitive\n")
        menu()

if __name__ == "__main__": #ensures this is only run when this file is executed
    menu()