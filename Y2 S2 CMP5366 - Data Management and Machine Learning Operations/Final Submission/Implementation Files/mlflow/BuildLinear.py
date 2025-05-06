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
from sklearn.linear_model import LinearRegression
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
    """
    """
    rmse = np.sqrt(mean_squared_error(actual, pred))
    mae = mean_absolute_error(actual, pred)
    r2 = r2_score(actual, pred)
    return rmse, mae, r2
    
def train_test_splitter(data):
    """
    """
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
    
    # Get the csv file
    eng_conn = create_engine("mysql+pymysql://student:Pa55W0rd123#@localhost:3306/Micronutrient")
    df = pd.read_sql("Micronutrient", con=eng_conn)
    
    ((_, train_x, train_y), (_, test_x, test_y)) = train_test_splitter(df)
    
    # Initiating mlflow run
    with mlflow.start_run():
    	# Create model builder
        lrModel = LinearRegression()
        
        # Fit the regression models
        lrModel.fit(train_x, train_y)       
        yPrediction = lrModel.predict(test_x)    
        rmse, mae, r2 = eval_metrics(test_y, yPrediction)
        
        # Output the metrics to the stdout   
        # (this is somewhat equivalent to print)
        sys.stdout.write("  Linear Model:\n")
        sys.stdout.write("  RMSE: %s\n" % rmse)
        sys.stdout.write("  MAE: %s\n" % mae)
        sys.stdout.write("  R2: %s\n\n" % r2)
        
        # Log the metrics to mlflow
        mlflow.log_metric("Linear_rmse", rmse) # root mean square error
        mlflow.log_metric("Linear_r2", r2) # r squared
        mlflow.log_metric("Linear_mae", mae) # mean absolute error
        tracking_url_type_store = urlparse(mlflow.get_tracking_uri()).scheme
        
        # model logging
        # Model registry does not work with file store
        if tracking_url_type_store != "file":            # Register the model
            # There are other ways to use the Model Registry, which depends on the use case,
            # please refer to the doc for more information:
            # https://mlflow.org/docs/la/model-registry.html#api-workflow
            mlflow.sklearn.log_model(lrModel, "model", registered_model_name="Linear Micronutrient Model")
        else:
            mlflow.sklearn.log_model(lrModel, "model")
