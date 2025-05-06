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
from sklearn.linear_model import Lasso
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
    dsLearn = ds[['year', 'popu', 'PCDEA', 'MDI', 'cntry', 'micro', 'req', 'est intake']]
    # Our predicted feature is "" what kind of data is it?
    x = dsLearn.iloc[:, 0 : -2]
    y = dsLearn.iloc[:, -2:, ]
    xTrain, xTest, yTrain, yTest = train_test_split(x, y, random_state=42, test_size=0.2)

    return ((xTrain, yTrain), (xTest, yTest))
    
    
def get_csv(url):
    """
    """
    try:
        df = pd.read_csv(url, sep=';')
        return df
    except Exception as e:
        logger.exception(
            "Error: Unable to find the dataset, please check your files. %s", e) 
    
if __name__ == "__main__":
    warnings.filterwarnings("ignore")
    np.random.seed(40)    
    
    # Get the csv file
    eng_conn = create_engine("mysql+pymysql://student:Pa55W0rd123#@localhost:3306/Micronutrient")
    ds = pd.read_sql("Micronutrient", con=eng_conn)  
    
    ((train_x, train_y), (test_x, test_y)) = train_test_splitter(ds)
    
    # Initiating mlflow run
    with mlflow.start_run():
    	# Create model builder
        laModel = Lasso(alpha= 0.4)
        
        # Fit the regression models
        laModel.fit(train_x, train_y)
        y_pred_la = laModel.predict(test_x)
        rmse_4, mae_4, r2_4 = eval_metrics(test_y, y_pred_la)
        
        # Output the metrics to the stdout   
        # (this is somewhat equivalent to print)
        sys.stdout.write("  Lasso Model:\n")
        sys.stdout.write("  RMSE: %s\n" % rmse_4)
        sys.stdout.write("  MAE: %s\n" % mae_4)
        sys.stdout.write("  R2: %s\n\n" % r2_4)
        
        # Log the metrics to mlflow
        mlflow.log_metric("Lasso_rmse", rmse_4) # root mean square error
        mlflow.log_metric("Lasso_r2", r2_4) # r squared
        mlflow.log_metric("Lasso_mae", mae_4) # mean absolute error
        tracking_url_type_store = urlparse(mlflow.get_tracking_uri()).scheme
        
        # model logging
        # Model registry does not work with file store
        if tracking_url_type_store != "file":            # Register the model
            # There are other ways to use the Model Registry, which depends on the use case,
            # please refer to the doc for more information:
            # https://mlflow.org/docs/la/model-registry.html#api-workflow
            mlflow.sklearn.log_model(laModel, "model", registered_model_name="Lasso Micronutrient Model")
        else:
            mlflow.sklearn.log_model(laModel, "model")
