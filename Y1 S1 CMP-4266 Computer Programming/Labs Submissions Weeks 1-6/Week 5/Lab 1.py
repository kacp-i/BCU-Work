def createList(): #creates a list and returns it
    myList = ["PlayStation","Xbox","Steam","iOS","Google Play"]
    
    return myList


def getInfo(myList): #turns part of a list into a tuple (1st element, 2nd last element and the number of elements) and returns it
    myTuple = (myList[0],myList[-2],len(myList))
    
    return myTuple


def getPartial(myList): #makes new list containing 2nd, 3rd and 4th elements
    partList = []
    
    partList.append(myList[1])
    partList.append(myList[2])
    partList.append(myList[3])
    
    return partList

    
def getLastThree(myList): #grabs last 3 elemenets in reverse order
    myLastThree = [myList[-1],myList[-2],myList[-3]]
    
    return myLastThree

    
def doubleList(myList): #concatenates two of myList
    myDoubleList = myList + myList
    
    return myDoubleList

    
def amend(myList): #changes 2nd element and adds an extra element
    myList[1] = "None"
    myList.append("Bye")
    
    return myList


if __name__ == "__main__":
    sampleList = createList()
    print(sampleList)
    print(getInfo(sampleList))
    print(getPartial(sampleList))
    print(getLastThree(sampleList))
    print(doubleList(sampleList))
    print(amend(sampleList))