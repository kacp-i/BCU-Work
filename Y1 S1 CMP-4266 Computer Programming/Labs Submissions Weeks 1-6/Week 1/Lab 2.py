# Lab1  Exercise #9 (Part 2)

import statistics #import statistics library

#sample numbers: 9, 8, 12, 33, 5

a = int(input("Type a whole number ")) #asks user to type 5 numbers
b = int(input("Type another whole number "))
c = int(input("Type another whole number "))
d = int(input("Type another whole number "))
e = int(input("Type another whole number "))

numList = [a,b,c,d,e] #list of all numbers

numSum = a+b+c+d+e #calculates the sum of all numbers
print("\nSum of all numbers is ",numSum)

numAvg = numSum / 5 #calculates the average of all numbers
print("\nAverage of all numbers is ",numAvg)

numPSD = statistics.pstdev(numList) #calculates the population standard deviation of all numbers
print("\nPopulation Standard Deviation of all numbers is ",numPSD)