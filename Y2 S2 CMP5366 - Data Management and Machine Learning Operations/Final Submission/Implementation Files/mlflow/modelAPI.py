from typing import Optional, List

from fastapi import FastAPI
from pydantic import BaseModel
import pickle
import pandas as pd
import numpy as np
import inspect

app = FastAPI()

# Loading our model from a file
# The model's pickled file might be at a different location on your filesystem
lrPath = "./artifacts/0/a39ccd18abd04287bd129f71a6b80868/artifacts/model/model.pkl"
laPath = "./artifacts/0/8073e5504f384647a4f3aa43587c0d12/artifacts/model/model.pkl"
dtPath = "./artifacts/0/7cb24f996e9a4c5c890272611fd480f9/artifacts/model/model.pkl"
lr_pickle_input = open(lrPath, "rb")
la_pickle_input = open(laPath, "rb")
dt_pickle_input = open(dtPath, "rb")
lrModel = pickle.load(lr_pickle_input)
laModel = pickle.load(la_pickle_input)
dtModel = pickle.load(dt_pickle_input)

columns = "year","population","PCDEA","MDI","country","micronutrient"

class NutInput(BaseModel):
    year : int
    population : int
    PCDEA : int
    MDI : int
    cntry : int
    micro : int
    
class NutPrediction(BaseModel):
    year : int
    population : int
    PCDEA : int
    MDI : int
    cntry : int
    micro : int
    req : float
    est_intake : float
   
# Leaving this in from the example
@app.get("/")
def read_root():
    return {"Hello": "World"}


@app.get("/items/{item_id}")
def read_item(item_id: int, q: Optional[str] = None):
    return {"item_id": item_id, "q": q}

# Our model serving endpoint    
@app.post("/nut/predict/linear")
def predict_nut_linear(data: NutInput):
    data_dict = data.dict()
    
    year = data_dict["year"]
    population = data_dict["population"]
    PCDEA = data_dict["PCDEA"]
    MDI = data_dict["MDI"]
    cntry = data_dict["cntry"]
    micro = data_dict["micro"]
    
    lst = [year, population, PCDEA, MDI, cntry, micro]
    arr = np.asarray(lst).reshape(1, -1)
    print(lst, "\n", arr)
    prediction = lrModel.predict(arr)
    prediction = prediction.tolist()
    print("Predicted required is ", prediction[0][0], "\n Predicted estimated intake is ", prediction [0][1]) #fix this
    return { "req": prediction[0][0], "est_intake": prediction[0][1], "parameters": data_dict }
    
@app.post("/nut/predict/lasso")
def predict_nut_lasso(data: NutInput):
    data_dict = data.dict()
    
    year = data_dict["year"]
    population = data_dict["population"]
    PCDEA = data_dict["PCDEA"]
    MDI = data_dict["MDI"]
    cntry = data_dict["cntry"]
    micro = data_dict["micro"]
    
    lst = [year, population, PCDEA, MDI, cntry, micro]
    arr = np.asarray(lst).reshape(1, -1)
    print(lst, "\n", arr)
    prediction = laModel.predict(arr)
    prediction = prediction.tolist()
    print("Predicted required is ", prediction[0][0], "\n Predicted estimated intake is ", prediction [0][1]) #fix this
    return { "req": prediction[0][0], "est_intake": prediction[0][1], "parameters": data_dict }

@app.post("/nut/predict/decision")
def predict_nut_decision(data: NutInput):
    data_dict = data.dict()
    
    year = data_dict["year"]
    population = data_dict["population"]
    PCDEA = data_dict["PCDEA"]
    MDI = data_dict["MDI"]
    cntry = data_dict["cntry"]
    micro = data_dict["micro"]
    
    lst = [year, population, PCDEA, MDI, cntry, micro]
    arr = np.asarray(lst).reshape(1, -1)
    print(lst, "\n", arr)
    prediction = dtModel.predict(arr)
    prediction = prediction.tolist()
    print("Predicted required is ", prediction[0][0], "\n Predicted estimated intake is ", prediction [0][1]) #fix this
    return { "req": prediction[0][0], "est_intake": prediction[0][1], "parameters": data_dict }
