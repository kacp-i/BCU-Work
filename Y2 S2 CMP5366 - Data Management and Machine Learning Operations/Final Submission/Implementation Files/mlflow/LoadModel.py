"""
Example code based on:
https://towardsdatascience.com/how-we-track-machine-learning-experiments-with-mlflow-948ff158a09a
"""
import os
import warnings
import sys

# Import the Data analytics and Data Processing libraries
import pandas as pd
import numpy as np
from sklearn.metrics import mean_squared_error, mean_absolute_error, r2_score
from sklearn.model_selection import train_test_split
from sklearn.linear_model import ElasticNet
from urllib.parse import urlparse
from sqlalchemy import create_engine



# Importing mlflow and its sklearn component
import mlflow
import mlflow.sklearn
import logging

# Set up logging of important data
logging.basicConfig(level=logging.WARN)
logger = logging.getLogger(__name__)
# Set the tracking server URI
mlflow.set_tracking_uri("http://localhost:5000")


def eval_metrics(actual, pred):
    rmse = np.sqrt(mean_squared_error(actual, pred))
    mae = mean_absolute_error(actual, pred)
    r2 = r2_score(actual, pred)
    return rmse, mae, r2
    
def train_test_splitter(data):
	# Split the data into training and test sets. 
	# What ratio does this function use to split into training and tests by default?
    train, test = train_test_split(data)
    train = train.drop(["index", "prev inadeq intake", "units"], axis=1)
    test = test.drop(["index", "prev inadeq intake", "units"], axis=1)
    train_x = train.drop(["req", "est intake"], axis=1)
    test_x = test.drop(["req", "est intake"], axis=1)
    train_y = train[["req", "est intake"]]
    test_y = test[["req", "est intake"]]
    return ((train, train_x, train_y), (test, test_x, test_y))
    
if __name__ == "__main__":
    warnings.filterwarnings("ignore")
    np.random.seed(40)    
    # The URL where the red wine quality csv file is
    eng_conn = create_engine("mysql+pymysql://student:Pa55W0rd123#@localhost:3306/Micronutrient")
    df = pd.read_sql("Micronutrient", con=eng_conn)  
    
    ((_, train_x, train_y), (_, test_x, test_y)) = train_test_splitter(df)
    
    
    # Accessing the model
    print("models:/Linear Micronutrient Model/4")
    model_uri = "models:/Linear Micronutrient Model/4"
    mdl = mlflow.sklearn.load_model(model_uri) 
    predicted_qualities = mdl.predict(test_x)        
    rmse, mae, r2 = eval_metrics(test_y, predicted_qualities)    
    
    # Output the metrics to the stdout   
    # (this is somewhat equivalent to print)
    sys.stdout.write("Linear model (Version 1.1):\n")
    sys.stdout.write("  RMSE: %s\n" % rmse)
    sys.stdout.write("  MAE: %s\n" % mae)
    sys.stdout.write("  R2: %s\n" % r2)
     
 
