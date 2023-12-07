# Doctor
############################ DONE ############################
# View their patient details assigned to them by admin

############################ TO DO ############################

import csv

def D_viewPatientList():
    count = 0
    docName = "Dr Emi" #decided when logging in
    patientList = []
    
    with open('doctorLinks.csv', newline='') as f:
        csvReader = csv.reader(f)
        
        for row in csvReader:
            if docName == row[0]:
                patientList.append(row[1])
                f.close()
                break
            else:
                count += 1
    print("found at row:",count)
    print("Your patients are:\n",patientList)
    
    D_viewPatientDetails()
        
def D_viewPatientDetails():
    search = input("Type patient name to view their details:\n")
    with open('patientList.csv', newline='') as f:
        csvReader = csv.reader(f)
        
        for row in csvReader:
            if row[0] == search:
                print(row)
                break
            else:
                pass

D_viewPatientList()