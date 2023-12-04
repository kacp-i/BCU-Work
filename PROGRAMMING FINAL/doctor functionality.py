# Doctor
############################ DONE ############################

############################ TO DO ############################
# View their patient details assigned to them by admin

import csv

def D_viewPatientDetails():
    count = 0
    docName = "Dr Emi"
    
    with open('doctorLinks.csv', newline='') as f:
        csvReader = csv.reader(f)
        
        for row in csvReader:
            if docName == row[0]:
                f.close()
                break
            else:
                count += 1
    print("row:",count)
        
D_viewPatientDetails()