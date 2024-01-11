from tkinter import *
import tkinter as tk
import csv
import datetime
import random

defaultFont = "Arial"
defaultFontSize = 18
defaultTitleSize = 24

class Navigation(tk.Tk):
    def __init__(self, *args, **kwargs):
        tk.Tk.__init__(self, *args, **kwargs)
        tk.Tk.wm_title(self, "Hospital Management System Application")

        container = tk.Frame(self)
        container.pack(side="top", fill="both", expand=True)
        container.grid_rowconfigure(0, weight = 1)
        container.grid_columnconfigure(0, weight = 1)

        self.frames = {}
        for f in (AdminLogin, AdminDash, AdminDash_RegisterDoctor, AdminDash_Doctor, AdminDash_Patient, AdminDash_AdminDetails):
            frame = f(container, self)
            self.frames[f] = frame
            frame.grid(row=0, column=0, sticky="nsew")

        self.show_frame(AdminLogin)

    def show_frame(self, cont):
        frame = self.frames[cont]
        frame.tkraise()


class AdminLogin(tk.Frame):
    def __init__(self, parent, controller):
        tk.Frame.__init__(self, parent)

        def loginUser():
            global currentUser

            userID = adminUserEnt.get()
            adminUserEnt.delete(0,END)
            userPass = adminPassEnt.get()
            adminPassEnt.delete(0,END)

            with open('adminList.csv', newline='') as f:
                csvReader = csv.reader(f)
                for row in csvReader:
                    if userID == row[2] and userPass == row[3]:
                        currentUser = row[0]
                        controller.show_frame(AdminDash)
                    else:
                        pass


        adminUserEnt = tk.Entry(self, font=("Arial",16))
        adminUserEnt.place(x=650,y=102)
        adminUserLbl = tk.Label(self, text="Username:", font=(defaultFont, 16))
        adminUserLbl.place(x=540,y=100)

        adminPassEnt = tk.Entry(self, font=("Arial",16), show="*")
        adminPassEnt.place(x=650,y=202)
        adminPassLbl = tk.Label(self, text="Password:", font=(defaultFont, 16))
        adminPassLbl.place(x=540,y=200)

        tk.Button(self, text="Login", bg="#00FF00", command=loginUser).place(x=600,y=400)

class AdminDash(tk.Frame):
    def __init__(self, parent, controller):
        tk.Frame.__init__(self, parent)

        def logout():
            global currentUser

            currentUser = ""

            controller.show_frame(AdminLogin)

        def newReport():
            global currentUser
            currentTime = datetime.datetime.now()
            fileName = (str(currentTime.year) + "_" + str(currentTime.month) + "_" + str(currentTime.day))
            totalDoc = 0
            totalPatient = 0
            totalDocAppointment = []
            totalSymptoms = {}

            with open('doctorList.csv', newline='') as f:
                csvReader = csv.reader(f)
                next(csvReader)
                for row in csvReader:
                    appointmentNum = random.randint(0,81)
                    docAppointments = [row[1],str(appointmentNum)]
                    totalDocAppointment.append(docAppointments)
                    totalDoc += 1
                f.close()

            with open('patientList.csv', newline='') as f:
                csvReader = csv.reader(f)
                next(csvReader)
                for row in csvReader:
                    if row[2] not in totalSymptoms:
                        totalSymptoms[row[2]] = 1
                    elif row[2] in totalSymptoms:
                        totalSymptoms[row[2]] += 1
                    
                    totalPatient += 1
                f.close()

    
            with open((fileName+'_Report.txt'), 'w') as f:
                f.write('\t\t\tMedical System Report\n')
                f.write('\tCreated On: ' + str(currentTime.day) + '/' + str(currentTime.month) + '/' + str(currentTime.year) + '\n')
                f.write('**********************************************\n')
                f.write('Total amount of doctors in the system: ' + str(totalDoc) + '\n')
                f.write('Total amount of patients in the system: ' + str(totalPatient) + '\n')
                f.write('**********************************************\n')
                for elem in totalDocAppointment:
                    f.write('Total amount of appointments for ' + elem[0] + ' this month was ' + elem[1] + '\n')
                f.write('**********************************************\n')
                for symptom, value in totalSymptoms.items():
                    f.write('Total amount of patients with the symptom ' + symptom + ' is ' + str(value) + '\n')
                f.write('**********************************************\n')

        tk.Label(self, text="Welcome", font=("Arial",40)).place(x=550,y=50)
        tk.Label(self, text="Select One of the options below", font=("Arial",25)).place(x=450,y=150)

        tk.Button(self, text="Register Doctor", command=lambda:controller.show_frame(AdminDash_RegisterDoctor)).place(x=250,y=300)
        tk.Button(self, text="Doctor", command=lambda:controller.show_frame(AdminDash_Doctor)).place(x=450,y=300)
        tk.Button(self, text="Patient", command=lambda:controller.show_frame(AdminDash_Patient)).place(x=650,y=300)
        tk.Button(self, text="Admin", command=lambda:controller.show_frame(AdminDash_AdminDetails)).place(x=850,y=300)
        tk.Button(self, text="Create Report", command=newReport).place(x=1050,y=300)

        returnBtn = tk.Button(self, text="Log Out", font=(defaultFont, 12), command=logout)
        returnBtn.place(x=0,y=0)

class AdminDash_RegisterDoctor(tk.Frame):
    def __init__(self, parent, controller):
        tk.Frame.__init__(self, parent)

        def A_registerDoctor():
            entList = [newDocNameEnt, newDocEmailEnt, newDocAddressEnt, newDocSpecialityEnt]
            newDoc = []
            nextDocID = 0
            
            with open('doctorList.csv', newline='') as f:
                csvReader = csv.reader(f)
                next(csvReader)
                for row in csvReader:
                    if int(row[0]) > nextDocID:
                        nextDocID = int(row[0])
            f.close()
            nextDocID += 1

            newDoc.append(str(nextDocID))

            for entry in entList:
                    newDoc.append(entry.get())

            with open('doctorList.csv','a',newline='') as f:
                writer = csv.writer(f)
                writer.writerow(newDoc)
                f.close()

            for entClear in entList:
                entClear.delete(0,END)

            popupBox("N")

            clearEntry

        def popupBox(fail=""):
            boxBg.place(x=400,y=80)
            boxTitle.place(x=585,y=110)
            boxClose.place(x=400,y=80)

            if fail == "N":
                boxMessage.config(text="Successfully registered a new doctor")
                boxMessage.place(x=515,y=300)

                boxTitle.config(text="Registered Doctor")
            if fail == "Y":
                boxMessage.config(text="Failed to register a new doctor\n\nEnsure all entry fields are filled")
                boxMessage.place(x=535,y=300)

                boxTitle.config(text="Failed Registration")
            
        def checkEntry():
            if newDocNameEnt.get() != "" and newDocEmailEnt.get() != "" and newDocAddressEnt.get() != "" and newDocSpecialityEnt.get() != "":
                A_registerDoctor()
            else:
                popupBox("Y")

        def clearEntry():
            newDocNameEnt.delete(0,END)
            newDocEmailEnt.delete(0,END)
            newDocAddressEnt.delete(0,END)
            newDocSpecialityEnt.delete(0,END)

        def popupClose():
            component = [boxBg, boxMessage, boxTitle, boxClose]

            for comp in component:
                comp.place(x=-1000,y=-200)

        
        returnBtn = tk.Button(self, text="Go Back", font=(defaultFont, 12), command=lambda:controller.show_frame(AdminDash))
        returnBtn.place(x=0,y=0)


        newDocNameEnt = tk.Entry(self, font=(defaultFont,defaultFontSize))
        newDocNameEnt.place(x=700,y=152)
        newDocNameLbl = tk.Label(self, text="Doctor Name:", font=(defaultFont, defaultFontSize))
        newDocNameLbl.place(x=535,y=150)

        newDocEmailEnt = tk.Entry(self, font=(defaultFont,defaultFontSize))
        newDocEmailEnt.place(x=700,y=202)
        newDocEmailLbl = tk.Label(self, text="Doctor Email:", font=(defaultFont, defaultFontSize))
        newDocEmailLbl.place(x=540,y=200)

        newDocAddressEnt = tk.Entry(self, font=(defaultFont,defaultFontSize))
        newDocAddressEnt.place(x=700,y=252)
        newDocAddressLbl = tk.Label(self, text="Doctor Address:", font=(defaultFont, defaultFontSize))
        newDocAddressLbl.place(x=510,y=250)

        newDocSpecialityEnt = tk.Entry(self, font=(defaultFont,defaultFontSize))
        newDocSpecialityEnt.place(x=700,y=302)
        newDocSpecialityLbl = tk.Label(self, text="Doctor Speciality:", font=(defaultFont, defaultFontSize))
        newDocSpecialityLbl.place(x=495,y=300)


        tk.Button(self, text="submit", command=checkEntry).place(x=670,y=500)


        boxBg = tk.Button(self, width=90, height=35, bg="white", state=DISABLED)
        boxTitle = tk.Label(self, text="", font=(defaultFont,defaultTitleSize), bg="white")
        boxMessage = tk.Label(self, text="", font=(defaultFont,defaultFontSize), bg="white")
        boxClose = tk.Button(self, text="X", font=(defaultFont,defaultTitleSize), bg="red", command=popupClose)

class AdminDash_Doctor(tk.Frame):
    def __init__(self, parent, controller):
        tk.Frame.__init__(self, parent)
        global lookingAtID

        lookingAtID = ""

        variable = tk.StringVar(self)
        variable.set("Select A Doctor")


        def enableEdit():
            editingBtn.config(text="Save Changes", command=saveEdit)
            undoBtn.place(x=1100,y=600)

            docNameEnt.config(state=NORMAL)
            docEmailEnt.config(state=NORMAL)
            docAddressEnt.config(state=NORMAL)
            docSpecialityEnt.config(state=NORMAL)

            deleteBtn.place(x=950,y=600)

        def stopEdit():
            editingBtn.config(text="Enable Editing", command=enableEdit)
            undoBtn.place(x=-200,y=-200)
            deleteBtn.config(state=NORMAL)

            docNameEnt.config(state=DISABLED)
            docEmailEnt.config(state=DISABLED)
            docAddressEnt.config(state=DISABLED)
            docSpecialityEnt.config(state=DISABLED)

            deleteBtn.place(x=-200,y=-200)

        def saveEdit():
            global lookingAtID

            newDocName = docNameEnt.get()
            newDocEmail = docEmailEnt.get()
            newDocAddress = docAddressEnt.get()
            newDocSpeciality = docSpecialityEnt.get()
            headers = ["ID","Name","Email","Address","Speciality"]

            docList = A_viewDocList("Y")

            for elem in docList:
                if elem[0] == lookingAtID:
                    elem[1] = newDocName
                    elem[2] = newDocEmail
                    elem[3] = newDocAddress
                    elem[4] = newDocSpeciality

            with open('doctorList.csv','w',newline='') as f:
                writer = csv.writer(f)
                writer.writerow(headers)
                for row in docList:
                    writer.writerow(row)
                f.close()

            stopEdit()

        def A_viewDocList(ifReturn=""):
            docList = []
            docNameList = []
            
            with open('doctorList.csv', newline='') as f:
                csvReader = csv.reader(f)
                next(csvReader)
                for row in csvReader:
                    docList.append(row)
                    docNameList.append(row[1])
                f.close()

            combo = OptionMenu(self, variable, *docNameList, command=OptionMenuSelect)
            combo.place(x=100,y=200)
            #loadBtn.place(x=-100,y=-100)

            if ifReturn == "Y":
                return docList

        def A_deleteDoc():
            global lookingAtID
            count = 0
            headers = ["ID","Name","Email","Address","Speciality"]
            entList = [docIDEnt, docNameEnt, docEmailEnt, docAddressEnt, docSpecialityEnt]

            docList = A_viewDocList("Y")

            for elem in docList:
                if elem[0] == lookingAtID:
                    docList.pop(count)
                count += 1

            with open('doctorList.csv','w',newline='') as f:
                writer = csv.writer(f)
                writer.writerow(headers)
                for newRow in docList:
                    writer.writerow(newRow)
                f.close()

                variable.set("Select A Doctor")

                for ent in entList:
                    ent.config(state=NORMAL)
                    ent.delete(0,END)
                    ent.config(state=DISABLED)
                
                A_viewDocList()

        def OptionMenuSelect(event):
            global lookingAtID
            currentDoctor = variable.get()
            entList = [docIDEnt, docNameEnt, docEmailEnt, docAddressEnt, docSpecialityEnt]
            
            docList = A_viewDocList("Y")
            for elem in docList:
                count = 0
                if currentDoctor == elem[1]:
                    for ent in entList:
                        ent.config(state=NORMAL)
                        ent.delete(0,END)
                        ent.insert(0,elem[count])
                        ent.config(state=DISABLED)
                        
                        count += 1
            
            lookingAtID = docIDEnt.get()
            stopEdit()
            editingBtn.place(x=1250,y=600)
            

        returnBtn = tk.Button(self, text="Go Back", font=(defaultFont, 12), command=lambda:controller.show_frame(AdminDash))
        returnBtn.place(x=0,y=0)
        refreshBtn = tk.Button(self, text="Refresh", font=(defaultFont, 12), command=A_viewDocList())
        refreshBtn.place(x=634,y=0)


        tk.Button(self, state=DISABLED, width=100, height=60, bg="white", relief=GROOVE).place(x=700,y=-1)
        tk.Label(self, text="Doctor Details", font=(defaultFont,defaultTitleSize), bg="white").place(x=955,y=50)

        editingBtn = tk.Button(self, text="Enable Editing", command=enableEdit, width=16, height=4)
        undoBtn = tk.Button(self, text="Undo Changes", command=stopEdit, width=16, height=4)
        deleteBtn = tk.Button(self, text="Delete", command=A_deleteDoc, width=16, height=4)


        docID = tk.Label(self, text="ID:", font=(defaultFont, defaultFontSize), bg="white")
        docID.place(x=720,y=150)
        docIDEnt = tk.Entry(self, font=(defaultFont, defaultFontSize), bg="white", state=DISABLED, width=41)
        docIDEnt.place(x=840,y=150)

        docName = tk.Label(self, text="Name:", font=(defaultFont,defaultFontSize), bg="white")
        docName.place(x=720,y=200)
        docNameEnt = tk.Entry(self, font=(defaultFont, defaultFontSize), bg="white", state=DISABLED, width=41)
        docNameEnt.place(x=840,y=200)

        docEmail = tk.Label(self, text="Email:", font=(defaultFont,defaultFontSize), bg="white")
        docEmail.place(x=720,y=250)
        docEmailEnt = tk.Entry(self, font=(defaultFont, defaultFontSize), bg="white", state=DISABLED, width=41)
        docEmailEnt.place(x=840,y=250)

        docAddress = tk.Label(self, text="Address:", font=(defaultFont,defaultFontSize), bg="white")
        docAddress.place(x=720,y=300)
        docAddressEnt = tk.Entry(self, font=(defaultFont, defaultFontSize), bg="white", state=DISABLED, width=41)
        docAddressEnt.place(x=840,y=300)
        
        docSpeciality = tk.Label(self, text="Speciality:", font=(defaultFont,defaultFontSize), bg="white")
        docSpeciality.place(x=720,y=300)
        docSpecialityEnt = tk.Entry(self, font=(defaultFont, defaultFontSize), bg="white", state=DISABLED, width=41)
        docSpecialityEnt.place(x=840,y=300)


        #loadBtn = tk.Button(self, text="Load Doctor", command=lambda:A_viewDocList(), width=20, height=5)
        #loadBtn.place(x=200,y=300)
        A_viewDocList()

class AdminDash_Patient(tk.Frame):
    def __init__(self, parent, controller):
        tk.Frame.__init__(self, parent)
        global viewType

        selected = tk.StringVar(self)
        selected.set("Select A Patient")

        docSelected = tk.StringVar(self)
        docSelected.set("Select A Doctor")

        viewType = "Patient"


        def A_viewPatientList(ifReturn=""):
            patientList = []
            patientNameList = []

            with open('patientList.csv', newline='') as f:
                csvReader = csv.reader(f)
                next(csvReader)
                for row in csvReader:
                    patientList.append(row)
                    patientNameList.append(row[1])
                f.close()

                combo = OptionMenu(self, selected, *patientNameList, command=OptionMenuSelect)
                combo.place(x=100,y=200)
            
            if ifReturn == "Y":
                return patientList

        def A_viewDischargeList(ifReturn=""):
            dischargeList = []
            dischargeNameList = []

            with open('dischargeList.csv', newline='') as f:
                csvReader = csv.reader(f)
                next(csvReader)
                for row in csvReader:
                    dischargeList.append(row)
                    dischargeNameList.append(row[1])
                f.close()

                combo = OptionMenu(self, selected, *dischargeNameList, command=OptionMenuSelect)
                combo.place(x=100,y=200)
            
            if ifReturn == "Y":
                return dischargeList

        def editDoc():
            global comboDoc

            doctorNameList = []
            docEditable = True
            
            with open('doctorList.csv', newline='') as f:
                csvReader = csv.reader(f)
                next(csvReader)
                for row in csvReader:
                    doctorNameList.append(row[1])
                f.close()

            assignBtn.config(text="Save Changes", command=saveChanges)
            undoBtn.place(x=350,y=600)

            comboDoc = OptionMenu(self, docSelected, *doctorNameList, command=DoctorMenuSelect)
            comboDoc.place(x=100,y=500)

        def hideDocCombo():
            try:
                comboDoc.place(x=-200,y=-200)
                docSelected.set("Select A Doctor")
            except:
                pass

        def stopEdit():
            assignBtn.config(text="Assign / Change\nDoctor", command=editDoc)
            undoBtn.place(x=-200,y=-200)

        def resetValues(clear=""):
            entList = [patientIdEnt, patientFullNameEnt, patientSymptomEnt, patientTelEnt, patientEmailEnt, patientEmergencyEnt, patientDobEnt, patientAddressEnt, patientAssignedDocEnt]
            
            if clear == "Y":
                for entry in entList:
                        entry.config(state=NORMAL)
                        entry.delete(0,END)
                        entry.config(state=DISABLED)
                        assignBtn.place(x=-200,y=-200)
                        dischargeBtn.place(x=-200,y=-200)
            else:
                currentPatient = selected.get()
                
                patientList = A_viewPatientList("Y")
                
                for elem in patientList:
                    if currentPatient == elem[1]:
                        count = 0

                        for entry in entList:
                            entry.config(state=NORMAL)
                            entry.delete(0,END)
                            entry.insert(0,elem[count])
                            entry.config(state=DISABLED)

                            count += 1
            hideDocCombo()
            stopEdit()

        def saveChanges():
            entList = [patientIdEnt, patientFullNameEnt, patientSymptomEnt, patientTelEnt, patientEmailEnt, patientEmergencyEnt, patientDobEnt, patientAddressEnt, patientAssignedDocEnt]
            patientValues = []
            patientList = A_viewPatientList("Y")
            count = patientIdEnt.get()
            headers = ["ID","Full Name","Symptoms","Tel Number","Email","Emergency Contact","Date of birth","Address","Assigned Doctor"]
            
            for elem in entList:
                patientValues.append(elem.get())

            for value in patientList:
                if value[0] == count:
                    patientList[int(count)-1] = patientValues

            sortedList = sortPatient(patientList)
            
            with open('patientList.csv','w',newline='') as f:
                writer = csv.writer(f)
                writer.writerow(headers)
                for row in sortedList:
                    writer.writerow(row)
                f.close()

            stopEdit()
            hideDocCombo()

        def switchViewType():
            global viewType
            lblList = [patientId, patientFullName, patientSymptom, patientTel, patientEmail, patientEmergency, patientDob, patientAddress, patientAssignedDoc]
            entList = [patientIdEnt, patientFullNameEnt, patientSymptomEnt, patientTelEnt, patientEmailEnt, patientEmergencyEnt, patientDobEnt, patientAddressEnt, patientAssignedDocEnt]
            xPosEnt = 950
            xPosLbl = 720
            yPos = 150


            if viewType == "Patient":
                viewType = "Discharge"

                switchViewBtn.config(text="View Current\nPaitents")
                detailsLbl.config(text="Discharged Patient Details")
                detailsLbl.place(x=880,y=50)
                assignBtn.place(x=-200,y=-200)
                dischargeBtn.place(x=-200,y=-200)

                for lbl in lblList:
                    if lbl == patientSymptom:
                        lbl.place(x=-1000,y=-200)
                    else:
                        lbl.place(x=xPosLbl,y=yPos)
                        yPos += 50
                
                yPos = 150

                for ent in entList:
                    if ent == patientSymptomEnt:
                        ent.place(x=-1000,y=-200)
                    else:
                        ent.place(x=xPosEnt,y=yPos)
                        yPos += 50
                
                A_viewDischargeList()
            
            elif viewType == "Discharge":
                viewType = "Patient"

                switchViewBtn.config(text="View Discharged\nPaitents")
                detailsLbl.config(text="Patient Details")
                detailsLbl.place(x=955,y=50)

                for lbl in lblList:
                    lbl.place(x=xPosLbl,y=yPos)
                    yPos += 50
                
                yPos = 150

                for ent in entList:
                    ent.place(x=xPosEnt,y=yPos)
                    yPos += 50

                A_viewPatientList()
            
            selected.set("Select A Patient")
            for entry in entList:
                entry.config(state=NORMAL)
                entry.delete(0,END)
                entry.config(state=DISABLED)

            hideDocCombo()
            stopEdit()

        def DoctorMenuSelect(event):
            currentDoctor = docSelected.get()

            patientAssignedDocEnt.config(state=NORMAL)
            patientAssignedDocEnt.delete(0,END)
            patientAssignedDocEnt.insert(0,currentDoctor)
            patientAssignedDocEnt.config(state=DISABLED)

        def OptionMenuSelect(event):
            global viewType
            entList = [patientIdEnt, patientFullNameEnt, patientSymptomEnt, patientTelEnt, patientEmailEnt, patientEmergencyEnt, patientDobEnt, patientAddressEnt, patientAssignedDocEnt]

            currentPatient = selected.get()
            
            if viewType == "Patient":
                patientList = A_viewPatientList("Y")
                assignBtn.place(x=530,y=600)
                dischargeBtn.place(x=15,y=600)
            
            elif viewType == "Discharge":
                patientList = A_viewDischargeList("Y")
                assignBtn.place(x=-200,y=-200)
            
            for elem in patientList:
                if currentPatient == elem[1]:
                    count = 0

                    for entry in entList:
                        if entry == patientSymptomEnt and viewType == "Discharge":
                            pass
                        else:
                            entry.config(state=NORMAL)
                            entry.delete(0,END)
                            entry.insert(0,elem[count])
                            entry.config(state=DISABLED)

                            count += 1
            stopEdit()
            hideDocCombo()
            lookingAtID = patientIdEnt.get()
        
        def dischargePatient():
            entList = [patientIdEnt, patientFullNameEnt, patientSymptomEnt, patientTelEnt, patientEmailEnt, patientEmergencyEnt, patientDobEnt, patientAddressEnt, patientAssignedDocEnt]
            currentPatient = []
            headers = ["ID","Full Name","Symptoms","Tel Number","Email","Emergency Contact","Date of birth","Address","Assigned Doctor"]
            patientList = A_viewPatientList("Y")
            dischargeList = A_viewDischargeList("Y")
            count = 0
            dischargeNextId = 0

            for entry in entList:
                currentPatient.append(entry.get())

            for value in dischargeList:
                if int(value[0]) > dischargeNextId:
                    dischargeNextId = int(value[0])
            dischargeNextId += 1

            for elem in patientList:
                if elem[0] == currentPatient[0]:
                    patientList.pop(count)
                    currentPatient[0] = dischargeNextId
                count += 1

            sortedList = sortPatient(patientList)

            with open('patientList.csv','w',newline='') as f:
                writer = csv.writer(f)
                writer.writerow(headers)
                for newRow in sortedList:
                    writer.writerow(newRow)
                f.close()

            with open('dischargeList.csv','a',newline='') as f:
                writer = csv.writer(f)
                writer.writerow(currentPatient)
                f.close()

            resetValues("Y")
            stopEdit()
            switchViewType()

        def sortPatient(patientList):
            patientLastName = []
            patientSortedNameList = []
            patientSortedList = []
            nextID = 1

            for elem in patientList:
                name = elem[1]
                fullName = name.split()
                lastName = fullName[1]

                patientLastName.append(fullName[1])
                patientSortedNameList.append(fullName[1])
            
            patientSortedNameList.sort()

            for rerun in patientSortedNameList:
                index = patientLastName.index(rerun)
                patientSortedList.append(patientList[index])
                patientLastName.pop(index)
                patientList.pop(index)

            for index in patientSortedList:
                index[0] = str(nextID)
                nextID += 1

            return patientSortedList

        


        tk.Button(self, state=DISABLED, width=100, height=60, bg="white", relief=GROOVE).place(x=700,y=-1)
        detailsLbl = tk.Label(self, text="Patient Details", font=(defaultFont,defaultTitleSize), bg="white")
        detailsLbl.place(x=955,y=50)

        returnBtn = tk.Button(self, text="Go Back", font=(defaultFont, 12), command=lambda:controller.show_frame(AdminDash))
        returnBtn.place(x=0,y=0)

        assignBtn = tk.Button(self, text="Assign / Change\nDoctor", command=editDoc, font=(defaultFont, 12), width=16, height=4)
        undoBtn = tk.Button(self, text="Undo Changes", command=resetValues, font=(defaultFont, 12), width=16, height=4)
        dischargeBtn = tk.Button(self, text="Discharge Patient", command=dischargePatient, font=(defaultFont, 12), width=16, height=4)
        switchViewBtn = tk.Button(self, text="View Discharged\nPatients", command=switchViewType, font=(defaultFont, 12), width=16, height=4)
        switchViewBtn.place(x=530,y=15)

        patientId = tk.Label(self, text="ID:", font=(defaultFont, defaultFontSize), bg="white")
        patientId.place(x=720,y=150)
        patientIdEnt = tk.Entry(self, font=(defaultFont, defaultFontSize), bg="white", state=DISABLED, width=33)
        patientIdEnt.place(x=950,y=150)

        patientFullName = tk.Label(self, text="Full Name:", font=(defaultFont,defaultFontSize), bg="white")
        patientFullName.place(x=720,y=200)
        patientFullNameEnt = tk.Entry(self, font=(defaultFont, defaultFontSize), bg="white", state=DISABLED, width=33)
        patientFullNameEnt.place(x=950,y=200)

        patientSymptom = tk.Label(self, text="Symptoms:", font=(defaultFont,defaultFontSize), bg="white")
        patientSymptom.place(x=720,y=250)
        patientSymptomEnt = tk.Entry(self, font=(defaultFont, defaultFontSize), bg="white", state=DISABLED, width=33)
        patientSymptomEnt.place(x=950,y=250)

        patientTel = tk.Label(self, text="Tel. Number:", font=(defaultFont,defaultFontSize), bg="white")
        patientTel.place(x=720,y=300)
        patientTelEnt = tk.Entry(self, font=(defaultFont, defaultFontSize), bg="white", state=DISABLED, width=33)
        patientTelEnt.place(x=950,y=300)

        patientEmail = tk.Label(self, text="Email:", font=(defaultFont,defaultFontSize), bg="white")
        patientEmail.place(x=720,y=350)
        patientEmailEnt = tk.Entry(self, font=(defaultFont, defaultFontSize), bg="white", state=DISABLED, width=33)
        patientEmailEnt.place(x=950,y=350)

        patientEmergency = tk.Label(self, text="Emergency Contact:", font=(defaultFont,defaultFontSize), bg="white")
        patientEmergency.place(x=720,y=400)
        patientEmergencyEnt = tk.Entry(self, font=(defaultFont, defaultFontSize), bg="white", state=DISABLED, width=33)
        patientEmergencyEnt.place(x=950,y=400)

        patientDob = tk.Label(self, text="Date Of Birth:", font=(defaultFont,defaultFontSize), bg="white")
        patientDob.place(x=720,y=450)
        patientDobEnt = tk.Entry(self, font=(defaultFont, defaultFontSize), bg="white", state=DISABLED, width=33)
        patientDobEnt.place(x=950,y=450)

        patientAddress = tk.Label(self, text="Address:", font=(defaultFont,defaultFontSize), bg="white")
        patientAddress.place(x=720,y=500)
        patientAddressEnt = tk.Entry(self, font=(defaultFont, defaultFontSize), bg="white", state=DISABLED, width=33)
        patientAddressEnt.place(x=950,y=500)

        patientAssignedDoc = tk.Label(self, text="Assigned Doctor:", font=(defaultFont,defaultFontSize), bg="white")
        patientAssignedDoc.place(x=720,y=550)
        patientAssignedDocEnt = tk.Entry(self, font=(defaultFont, defaultFontSize), bg="white", state=DISABLED, width=33)
        patientAssignedDocEnt.place(x=950,y=550)


        A_viewPatientList()

class AdminDash_AdminDetails(tk.Frame):
    def __init__(self, parent, controller):
        tk.Frame.__init__(self, parent)


        def edit():
            entList = [adminFullNameEnt, adminUsernameEnt, adminPasswordEnt, adminAddressEnt]

            for entry in entList:
                entry.config(state=NORMAL)

            updateBtn.config(text="Save Changes", command=saveChanges)
            undoBtn.place(x=350,y=600)

        def stopEdit():
            entList = [adminFullNameEnt, adminUsernameEnt, adminPasswordEnt, adminAddressEnt]

            for entry in entList:
                entry.config(state=DISABLED)

            updateBtn.config(text="Update Details", command=edit)
            undoBtn.place(x=-200,y=-200)

        def saveChanges():
            entList = [adminIdEnt, adminFullNameEnt, adminUsernameEnt, adminPasswordEnt, adminAddressEnt]
            adminValues  = []
            adminList = A_viewAdminList("Y","")
            
            for entry in entList:
                adminValues.append(entry.get())
            count = adminValues[0]


            for value in adminList:
                if value[0] == count:
                    adminList[int(count)] = adminValues
            
            with open('adminList.csv','w',newline='') as f:
                writer = csv.writer(f)
                for row in adminList:
                    writer.writerow(row)
                f.close()

            stopEdit()

        def undoChanges():
            global currentUser
            entList = [adminIdEnt, adminFullNameEnt, adminUsernameEnt, adminPasswordEnt, adminAddressEnt]
            adminList = A_viewAdminList("Y")

            for elem in adminList:
                count = 0
                if elem[0] == currentUser:
                    for ent in entList:
                        ent.config(state=NORMAL)
                        ent.delete(0,END)
                        ent.insert(0,elem[count])
                        ent.config(state=DISABLED)

                        count += 1

            updateBtn.config(text="Update Details", command=edit)
            undoBtn.place(x=-200,y=-200)


        def A_viewAdminList(ifReturn="",ifPlace="Y"):
            global currentUser
            currentAdmin = []
            adminList = []
            entList = [adminIdEnt, adminFullNameEnt, adminUsernameEnt, adminPasswordEnt, adminAddressEnt]

            with open('adminList.csv', newline="") as f:
                csvReader = csv.reader(f)
                for row in csvReader:
                    adminList.append(row)
                    if row[0] == currentUser:
                        currentAdmin.append(row)

                        if ifPlace == "Y":
                            count = 0

                            for entry in entList:
                                entry.config(state=NORMAL)
                                entry.delete(0,END)
                                entry.insert(0,row[count])
                                entry.config(state=DISABLED)

                                count += 1

            updateBtn.place(x=530,y=600)

            if ifReturn == "Y":
                return adminList


        tk.Button(self, state=DISABLED, width=100, height=60, bg="white", relief=GROOVE).place(x=700,y=-1)
        detailsLbl = tk.Label(self, text="Admin Details", font=(defaultFont,defaultTitleSize), bg="white")
        detailsLbl.place(x=955,y=50)


        returnBtn = tk.Button(self, text="Go Back", font=(defaultFont, 12), command=lambda:controller.show_frame(AdminDash))
        returnBtn.place(x=0,y=0)


        updateBtn = tk.Button(self, text="Update Details", command=edit, font=(defaultFont, 12), width=16, height=4)
        undoBtn = tk.Button(self, text="Undo Changes", command=undoChanges, font=(defaultFont, 12), width=16, height=4)


        adminId = tk.Label(self, text="ID:", font=(defaultFont, defaultFontSize), bg="white")
        adminId.place(x=720,y=150)
        adminIdEnt = tk.Entry(self, font=(defaultFont, defaultFontSize), bg="white", state=DISABLED, width=41)
        adminIdEnt.place(x=850,y=150)

        adminFullName = tk.Label(self, text="Full Name:", font=(defaultFont,defaultFontSize), bg="white")
        adminFullName.place(x=720,y=200)
        adminFullNameEnt = tk.Entry(self, font=(defaultFont, defaultFontSize), bg="white", state=DISABLED, width=41)
        adminFullNameEnt.place(x=850,y=200)

        adminUsername = tk.Label(self, text="Username:", font=(defaultFont,defaultFontSize), bg="white")
        adminUsername.place(x=720,y=250)
        adminUsernameEnt = tk.Entry(self, font=(defaultFont, defaultFontSize), bg="white", state=DISABLED, width=41)
        adminUsernameEnt.place(x=850,y=250)

        adminPassword = tk.Label(self, text="Password:", font=(defaultFont, defaultFontSize), bg="white")
        adminPassword.place(x=720,y=300)
        adminPasswordEnt = tk.Entry(self, font=(defaultFont, defaultFontSize), bg="white", state=DISABLED, width=41)
        adminPasswordEnt.place(x=850,y=300)

        adminAddress = tk.Label(self, text="Address:", font=(defaultFont,defaultFontSize), bg="white")
        adminAddress.place(x=720,y=350)
        adminAddressEnt = tk.Entry(self, font=(defaultFont, defaultFontSize), bg="white", state=DISABLED, width=41)
        adminAddressEnt.place(x=850,y=350)

        tk.Button(self, text="Load User Information", font=(defaultFont,defaultFontSize), command=A_viewAdminList).place(x=200,y=300)

root = Navigation()
root.geometry("1400x700")
root.resizable(False,False)
root.mainloop()
