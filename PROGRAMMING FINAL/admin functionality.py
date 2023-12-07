# Admin
############################ DONE ############################
# Admin Login
# Register/view/update/delete doctor
# View patient
# Can assign doctor to a patient

############################ TO DO ############################
# Remove a patient from the system after treatment is done
# View discharged patient list
# Update own details
# Print all doctor details
# Print all patient details

import csv
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
5 - View Patient List
6 - Assign Patients to doctor\n""")
          
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
    elif userChoice == "6":
        A_assignDocToPatient()
    elif userChoice == "7":
        A_dischargePatients()
    else:
        pass

def A_registerDoctor():
    docName = input("\nType doctor name:\n")
    docID = input("\nType doctor ID:\n")
    newDoc = [docName, docID]
    
    with open('doctorList.csv','a',newline='') as f:
        writer = csv.writer(f)
        writer.writerow(newDoc)
        
        f.close()
    A_menu()

def A_viewDocList(operation=""):
    listD = []
    
    with open('doctorList.csv', newline='') as f:
        csvReader = csv.reader(f)
        
        for row in csvReader:
            listD.append(row)
            print(row)
            
        f.close()
        
    if operation == "menu":
        A_menu()
    elif operation == "list":
        return listD
    else:
        pass

def A_updateDocList():
    count = -1
    
    docList = A_viewDocList("list")
    
    searchID = input("Type the ID of the doctor:\n")
    
    for elem in docList:
        count += 1
        
        if elem[1] == searchID:
            print("has been found at row",count)
            break
    
    docList.pop(count)
    
    newDocName = input("What is the doctor's new name?\n")
    
    newDocElem = [newDocName,searchID]
    docList.append(newDocElem)
    
    with open('doctorList.csv','w',newline='') as f:
        writer = csv.writer(f)
        
        for newRow in docList:
            writer.writerow(newRow)
        
        f.close()
    A_menu()

def A_deleteDoc():
    count = -1
    
    docList = A_viewDocList("list")
    
    searchID = input("Type the ID of the doctor:\n")
    
    for elem in docList:
        count += 1
        
        if elem[1] == searchID:
            print("has been found at row",count)
            break
    
    docList.pop(count)
    
    with open('doctorList.csv','w',newline='') as f:
        writer = csv.writer(f)
        
        for newRow in docList:
            writer.writerow(newRow)
        
        f.close()  
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
    assigningPatients = True
    columnNames = ("Doctor Name","Patient Name")
    patientAssignList = []
    
    docName = input("Enter doctor name:\n")
    while(assigningPatients):
        patientName = input("\nEnter patient name:\n")
        patientAssignList.append(patientName)
        choice = input("Would you like to assign another patient?\t(Y/N)\n")
        if choice == "N":
            assigningPatients = False
        else:
            pass
        
    newLink = [docName,patientAssignList]
    with open('doctorLinks.csv','w',newline='') as f:
        writer = csv.writer(f)
        writer.writerow(columnNames)
        writer.writerow(newLink)
                
        f.close()  
    A_menu()
    
def A_dischargePatients():
    patientName = ['Patient Name']
    count = 1
    
    with open('patientList.csv', newline='') as f:
        csvReader = csv.reader(f)
        
        for row in csvReader:
            if row[3] == "Treatment Complete":
                patientName.append(row[0])
            count += 1
        f.close()
    
    with open('dischargeList.csv', 'w', newline='') as f:
        writer = csv.writer(f)
        writer.writerows(patientName)
        f.close()

A_menu()