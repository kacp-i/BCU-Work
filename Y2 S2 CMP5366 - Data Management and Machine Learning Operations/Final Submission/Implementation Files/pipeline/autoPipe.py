from datetime import datetime, timedelta
import pandas as pd
from sqlalchemy import create_engine
from textwrap import dedent
from airflow import DAG
from sklearn.preprocessing import LabelEncoder
from airflow.operators.bash import BashOperator
from airflow.operators.python_operator import PythonOperator
from sklearn.linear_model import LinearRegression, Lasso
from sklearn.tree import DecisionTreeRegressor
from sklearn.metrics import mean_squared_error, mean_absolute_error, r2_score
from sklearn.model_selection import train_test_split

#START PythonFunc
def getDataset():
    ds = pd.read_csv("~/S4.csv", encoding='cp1252')

def applyPreprocessing():
    ds = ds.drop(['Zone','ISO3','Fortification','Tagname'], axis=1)
    
    lblMake = LabelEncoder()
    lblMake.fit(ds['Country'].drop_duplicates())
    ds['countryEncoded'] = lblMake.transform(ds['Country'])
    ds = ds.drop('Country', axis=1)

    lblMake = LabelEncoder()
    lblMake.fit(ds['Micronutrient'].drop_duplicates())
    ds['micronutrientEncoded'] = lblMake.transform(ds['Micronutrient'])
    ds = ds.drop('Micronutrient', axis=1)

    lblMake = LabelEncoder()
    lblMake.fit(ds['Units'].drop_duplicates())
    ds['unitsEncoded'] = lblMake.transform(ds['Units'])
    ds = ds.drop('Units', axis=1)

    ds.rename(columns = {'Year' : 'year', 'Population' : 'popu', 'Estimated Intake' : 'est intake', 'Requirements' : 'req',
                         'Prevalence of Inadequate Intake' : 'prev inadeq intake', 'countryEncoded' : 'cntry', 'micronutrientEncoded' : 'micro',
                         'unitsEncoded' : 'units'},
              inplace = True)

def loadDataset():
    eng_conn = create_engine("mysql+pymysql://student:Pa55W0rd123#@localhost:3306/Micronutrient")
    ds.to_sql("Micronutrient", con=eng_conn, if_exists="replace")

# after implementing the methods below DAG has stopped working, is meant to function as normal, unsure of error
def trainingData():
    dsLearn = ds[['year', 'popu', 'PCDEA', 'MDI', 'cntry', 'micro', 'req', 'est intake']]
    x = dsLearn.iloc[:, 0 : -2]
    y = dsLearn.iloc[:, -2:, ]
    xTrain, xTest, yTrain, yTest = train_test_split(x, y, test_size=0.2)
    
def linearModel():
    lrModel = LinearRegression()
    lrModel.fit(xTrain, yTrain)

def linearTest():
    yPrediction = lrModel.predict(xTest)
    mse = mean_squared_error(yPrediction, yTest)
    sqrt_mse = np.sqrt(mse)
    mae = mean_absolute_error(yPrediction, yTest)
    r2 = r2_score(yTest, yPrediction)
    
def decisionModel():
    dtModel = DecisionTreeRegressor()
    dtModel.fit(xTrain, yTrain)
    
def decisionTest():
    y_pred_dt = dtModel.predict(xTest)
    mse_dt = mean_squared_error(yTest, y_pred_dt)
    rmse_dt = np.sqrt(mse_dt)
    mae = mean_absolute_error(y_pred_dt, yTest)
    r2_dt = r2_score(yTest, y_pred_dt)
    
def lassoModel():
    laModel = Lasso(alpha= 0.4)
    laModel.fit(xTrain, yTrain)
    
def lassoTest():
    yPred = laModel.predict(xTest)
    mse = round(mean_squared_error(yPred, yTest), 2 )
    rmse = np.sqrt(mse)
    mae = mean_absolute_error(yPred, yTest)
    r2 = r2_score(yPred, yTest)
#    
default_args = {
	"owner": "kacper",
	"depends_on_past": False,
	"email": ["kacper.popis@mail.bcu.ac.uk"],
	"email_on_failure": False,
	"email_on_retry": False,
	"retries": 1,
	"retry_delay": timedelta(minutes=1)
}

with DAG(
	"autoPipe",
	default_args=default_args,
	description="Automation of Ingestions and Preprocessing",
	schedule_interval=timedelta(days=1),
	start_date=datetime(2024, 10, 10),
	catchup=False,
	tags=["pipeline"],
) as dag:
	task1 = PythonOperator(
		task_id = "grab_dataset",
		python_callable = getDataset
	)
	task2 = PythonOperator(
		task_id ="pre_processing",
		depends_on_past = True,
		python_callable = applyPreprocessing
	)
	task3 = PythonOperator(
		task_id = "load_dataset",
        	depends_on_past = True,
		python_callable = loadDataset
	)
	task4 = PythonOperator(
	    	task_id = "build_linear_model",
	        depends_on_past = True,
	    	python_callable = linearModel
	)
	task4_1 = PythonOperator(
	    	task_id = "test_linear_model",
	        depends_on_past = True,
	    	python_callable = linearTest
	)
	task5 = PythonOperator(
	    	task_id = "build_decision_model",
	        depends_on_past = True,
	    	python_callable = decisionModel
	)
	task5_1 = PythonOperator(
	    	task_id = "test_decision_model",
	        depends_on_past = True,
	    	python_callable = decisionTest
	)
	task6 = PythonOperator(
	    	task_id = "build_lasso_model",
	        depends_on_past = True,
	    	python_callable = lassoModel
	)
	task6_1 = PythonOperator(
	    	task_id = "test_lasso_model",
	        depends_on_past = True,
	    	python_callable = lassoTest
	)
	
	task1 >> task2 >> task3 >> [[task4, task4_1], [task5, task5_1], [task6, task6_1]]
