import csv

with open('doctorList.csv', newline='') as f:
    csvReader = csv.reader(f)
    for row in csvReader:
        print(row)