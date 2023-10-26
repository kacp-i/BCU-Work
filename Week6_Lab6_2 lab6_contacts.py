contactList = [("Stish","123"),("Rita","321")]

def viewContacts(m="Menu"):
    print("\n---------- Current Contact List ----------")
    count = 0
    for elem in contactList:
        count += 1
        contactName = elem[0]
        contactNum = elem[1]
        print(count,contactName,contactNum)
    if m != "noMenu":
        menu()
    else:
        pass

def addContact():
    contactName = input("What is contact Name?\n")
    contactNum = input("What is contact Number?\n")
    if contactNum.isdigit() == True:
        fullContact = (contactName,contactNum)
        contactList.append(fullContact)
    else:
        print("Invalid Input, Returning to menu")
    menu()

def delContact():
    viewContacts("noMenu")
    delID = input("\nWhat is the ID of the contact you wish to delete?\n")
    try:
        delID = int(delID) - 1
        contactList.pop(delID)
        print("Contact with the ID of",(delID+1),"has been deleted\n")
    except:
        print("\nContact not in Contact List")
    menu()

def menu():
    print("""
Select an operation:
v - View Contacts
a - Add Contact
d - Delete Contact
q - Quit""")
    
    choice = input("\nEnter Choice (v / a / d / q): ")
    
    if choice == "v":
        viewContacts("")
    
    elif choice == "a":
        addContact()
    
    elif choice == "d":
        delContact()
    
    elif choice == "q":
        print("Application Closing")
        pass
    
    else:
        print("Invalid Input, Input is Case Sensitive\n")
        menu()

if __name__ == "__main__":
    menu()