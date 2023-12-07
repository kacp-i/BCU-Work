# Patient
############################ DONE ############################
# View the assigned Doctor

############################ TO DO ############################


import csv

def P_viewAssignedDoctor():
    patientName = input("Type your name:\n")
    
    with open('doctorLinks.csv', newline='') as f:
        csvReader = csv.reader(f)
        
        for row in csvReader:
            if patientName == row[1]:
                print("Your doctor is\n" + row[0])
                f.close()
                break
            else:
                pass
            
P_viewAssignedDoctor()