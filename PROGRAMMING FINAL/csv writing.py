import csv

varName = [["mr men","not people"],["ama","aoooooo"]]

with open('doctorList.csv','a',newline='') as f:
    writer = csv.writer(f)
    for row in varName:
        writer.writerow(row)
    
    f.close()