class BMI:
    def __init__(self, Weight=0, Height=0):
        self.__weight = Weight #in kilos
        self.__height = Height #in meters
    
    def setWeight(self, newWeight):
        weight = newWeight
    
    def setHeight(self, newHeight):
        height = newHeight

    def __calcBMI(self):
        bmi = (self.__weight / (self.__height**2))
        return bmi

    def displayBMI(self):
        bmi = self.__calcBMI()
        print("BMI is {:.2f}".format(bmi))
    
