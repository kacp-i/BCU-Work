houseList = [] #lists to hold multiple values
houseListOccupy = []


def getData(): #function that asks user for data input
    for i in range (8):
        if i == 7:
            occupants = int(input("\nProvide the number of houses with 6+ occupants: "))
        else:
            occupants = int(input(f"\nProvide the number of houses with {i} occupants: "))
        houseList.append(occupants) #adds user provided number into a list
        
def calPercent(): #function that calculates percentage of all houses
    totalHouseCount = 0 #adds all numbers to make a total to calculate percentages
    for num in houseList:
        totalHouseCount += num
        
    for num in houseList: #calculates the percentage of houses for each amount of occupants
        housePercent = (num / totalHouseCount) * 100
        housePercent = round(housePercent,1)
        houseListOccupy.append(housePercent)
        

def displayResult():
    print("{:>12}{:>7}{:>7}{:>7}{:>7}{:>7}{:>7}{:>7}{:>7}".format("Occupants:","0","1","2","3","4","5","6","6+")) #displays values with formating to make it look like a table in console
    print("{:>12}{:>7}{:>7}{:>7}{:>7}{:>7}{:>7}{:>7}{:>7}".format("No. Houses:",houseList[0],houseList[1],houseList[2],houseList[3],houseList[4],houseList[5],houseList[6],houseList[7]))
    print("{:>12}{:>6}%{:>6}%{:>6}%{:>6}%{:>6}%{:>6}%{:>6}%{:>6}%".format("Percentage:",houseListOccupy[0],houseListOccupy[1],houseListOccupy[2],houseListOccupy[3],houseListOccupy[4],houseListOccupy[5],houseListOccupy[6],houseListOccupy[7]))

getData() #calls all functions one by one
calPercent()
displayResult()