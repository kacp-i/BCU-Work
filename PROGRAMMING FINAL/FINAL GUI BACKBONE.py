import tkinter as tk
from tkinter import *

class Navigation(tk.Tk):
    def __init__(self, *args, **kwargs):
        tk.Tk.__init__(self, *args, **kwargs)
        tk.Tk.wm_title(self, "nice one")

        container = tk.Frame(self)
        container.pack(side="top", fill="both", expand=True)
        container.grid_rowconfigure(0, weight = 1)
        container.grid_columnconfigure(0, weight = 1)

        self.frames = {}
        for f in (StartPage, Page1):
            frame = f(container, self)
            self.frames[f] = frame
            frame.grid(row=0, column=0, sticky="nsew")

        self.show_frame(StartPage)

    def show_frame(self, cont):
        frame = self.frames[cont]
        frame.tkraise()

class StartPage(tk.Frame):
    def __init__(self, parent, controller):
        tk.Frame.__init__(self, parent)

        tk.Label(self, text="nice one").place(x=20,y=300)
        tk.Button(self, text="go next", command=lambda:controller.show_frame(Page1)).place(x=0,y=400)
        
class Page1(tk.Frame):
    def __init__(self, parent, controller):
        tk.Frame.__init__(self, parent)

        tk.Label(self, text="you switched").place(x=0,y=200)
        tk.Button(self, text="go back?", command=lambda:controller.show_frame(StartPage)).place(x=40,y=170)
root = Navigation()
root.geometry("1400x700")
root.mainloop()
