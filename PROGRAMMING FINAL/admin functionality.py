# Admin
#✓ Admin Login
#✓ Register/view/update/delete doctor
#✓ View patient
# Can assign doctor to a patient
docList = [["Dr. Ami",9102],["Dr. Emi",2115]]
patientList = [["Hamood Hamood"],["Jamie Jame"]]


def A_login():
    corrUser = "123"
    corrPass = "321"
    
    userTyped = input("\nType username:\n")
    passTyped = input("\nType password:\n")
    
    if userTyped == corrUser and passTyped == corrPass:
        print("\nWelcome",userTyped)
        A_menu()
    else:
        pass
    
def A_menu():
    print("""
Select one of the X options below\n
1 - Register Doctor
2 - View Doctor List
3 - Update Doctor
4 - Delete Doctor
5 - View Patient List\n""")
          
    userChoice = input()
    print()
    
    if userChoice == "1":
        A_registerDoctor()
    elif userChoice == "2":
        A_viewDocList("menu")
    elif userChoice == "3":
        A_updateDocList()
    elif userChoice == "4":
        A_deleteDoc()
    elif userChoice == "5":
        A_viewPatientList()
    else:
        pass

def A_registerDoctor():
    docName = input("\nType doctor name:\n")
    docID = input("\nType doctor ID:\n")
    
    docList.append([docName, docID])
    print("\n"+docName, "-", docID, "has been added to list of doctors")
    print("Returning to menu")
    A_menu()

def A_viewDocList(goMenu=""):
    count = 1
    print("Displaying current Doctors in the system\n")
    
    for elem in docList:
        #print(len(elem[0])) use for format string
        print("{:<3} {:<15} {}".format(count, elem[0], elem[1]))
        count += 1
        
    if goMenu == "menu":
        A_menu()
    else:
        pass

def A_updateDocList():
    print("""
Select one of the 3 options below
1 - Change Doctor Name
2 - Change Doctor ID
3 - Quit""")

    userChoice = input()
    
    if userChoice == "1":
        A_viewDocList()
        
        docIndex = int(input("\nType the index of the doctor you would like to change:\n"))
        newDocName = input("\nType the new name of the doctor:\n")
        
        print("You are changing",docList[docIndex-1][0],"to",newDocName,"\nAre you sure? (Y/N)\n")
        userChoice = input()
        
        if userChoice.upper() == "Y":
            docList[docIndex-1][0] = newDocName
            print("Update confirmed")
            A_menu()
        else:
            print("Doctor info update cancelled, returning to menu")
            A_menu()
            
    elif userChoice == "2":
        A_viewDocList()
    elif userChoice == "3":
        A_menu()
    else:
        pass

def A_deleteDoc():
    A_viewDocList()
    
    docIndex = int(input("\nType the index of the doctor you would like to remove:\n"))
    
    print("You are about to remove",docList[docIndex-1],"from the system, are you sure? (Y/N)")
    userChoice = input()
    
    if userChoice.upper() == "Y":
        print("You have sucessfully deleted",docList[docIndex-1][0])
        docList.pop(docIndex-1)
        A_menu()
    else:
        print("Doctor deletion cancelled, returning to menu")
        A_menu()

def A_viewPatientList():
    count = 1
    print("Displaying current Patients in the system\n")
    
    for elem in patientList:
        #print(len(elem[0])) use for format string
        print("{:<3} {}".format(count, elem[0]))
        count += 1
    
    A_menu()
    
def A_assignDocToPatient():
    #do some CSV stuff
    pass

A_menu()