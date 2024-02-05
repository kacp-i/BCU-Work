class Vehicle:
    def __init__(self, model, make):
        self.__make = make
        self.__model = model
        self.__state = "Not in use"
        self.__n = 0
        self.__stopList = ["New Street","Bullring","Moor Street","BCU"]

    def move(self):
        if self.__n > 5:
            print("I am finished for today")
        else:
            print(f"The bus was at {self.__stopList[self.__n]} and is moving to {self.__stopList[self.__n+1]}")
            self.__n += 1

    def stop(self):
        print("I am a non-stop bus")

    def getMake(self): 
        return self.__make

    def getModel(self): 
        return self.__model

    def getState(self):
        return self.__state

    def __str__ (self):
        return f"{self.getMake()} {self.getModel()} is {self.getState()}"

class Bus(Vehicle):
    def __init__(self, make, model, decks):
        Vehicle.__init__(self, make, model)
        self.__decksNum = decks

    def setDecksNum(self, decks):
        self.__decksNum = decks
    
    def getDecksNum(self):
        return self.__decksNum

    def getRoute(self):
        return "New Street - Bullring - Moor Street - BCU"

class Car(Vehicle):
    def __init__(self, make, model, doors):
        Vehicle.__init__(self, make, model)
        self.__doorNum = doors

    def setDoorNum(self, doors):
        self.__doorNum = doors

    def getDoorNum(self):
        return self.__doorNum

    def __str__(self):
        return f"{self.getMake()} {self.getModel()} with {self.getDoorNum()} doors is {self.getState()}"
