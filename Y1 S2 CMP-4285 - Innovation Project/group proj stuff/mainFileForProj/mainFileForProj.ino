#define ENA 5
#define ENB 6
#define IN1 7 //Left side wheels go forward
#define IN2 8 //Left side wheels go backwards
#define IN3 9 //Right side wheels go backwards
#define IN4 11 //Right side wheels go forwards

#define carSpeed 125

#define LineTeacking_Pin_Right  10
#define LineTeacking_Pin_Middle 4
#define LineTeacking_Pin_Left   2

#define echoPin A4
#define trigPin A5

int distance;
long duration;
bool avoid = false;
bool line = false;


void setup() {
  Serial.begin(9600);

  pinMode(IN1, OUTPUT);
  pinMode(IN2, OUTPUT);
  pinMode(IN3, OUTPUT);
  pinMode(IN4, OUTPUT);
  pinMode(ENA, OUTPUT);
  pinMode(ENB, OUTPUT);

  analogWrite(ENA, carSpeed);
  analogWrite(ENB, carSpeed);

  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
}


void forward(){
  digitalWrite(IN1,HIGH); 
  digitalWrite(IN2,LOW);
  digitalWrite(IN3,LOW);
  digitalWrite(IN4,HIGH);
}
void backward(){
  digitalWrite(IN1,LOW); 
  digitalWrite(IN2,HIGH);
  digitalWrite(IN3,HIGH);
  digitalWrite(IN4,LOW);
}
void left(){
  digitalWrite(IN1,LOW); 
  digitalWrite(IN2,HIGH);
  digitalWrite(IN3,LOW);
  digitalWrite(IN4,HIGH);
}
void right(){
  digitalWrite(IN1,HIGH); 
  digitalWrite(IN2,LOW);
  digitalWrite(IN3,HIGH);
  digitalWrite(IN4,LOW);
}
void stopMove(){
  digitalWrite(IN1,LOW); 
  digitalWrite(IN2,LOW);
  digitalWrite(IN3,LOW);
  digitalWrite(IN4,LOW);
}

void checkLineTrack(){
  if (digitalRead(4) == 1){
    forward();
    }
    else{
      if (digitalRead(2) == 1){
        left();
        }
        else{
          if (digitalRead(10) == 1){
            right();
          }
          else{
            stopMove();
          }
        }
      }
}

void obstacleAvoid(){
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2); 
  
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10); 

  digitalWrite(trigPin, LOW);

  duration = pulseIn(echoPin, HIGH);
  distance = duration * 0.0344 / 2;
  
  if (distance < 30){
    stopMove();

    left();
    delay(1000);
    stopMove();
    forward();
  }
}


void loop() {
  if (avoid == true){
    obstacleAvoid();
  }
  if (line == true){
    checkLineTrack();
  }
  
  if (Serial.available() > 0) {
    String data = Serial.readString();

    if (data == "1001"){
      avoid = false;
      line = false;
      
      forward();
    }

    else if (data == "1002"){
      avoid = false;
      line = false;
      
      backward();
    }

    else if (data == "1003"){
      avoid = false;
      line = false;
      
      left();
    }

    else if (data == "1004"){
      avoid = false;
      line = false;
      
      right();
    }

    else if (data == "1010"){
      line = false;
      
      forward();
      avoid = true;
    }

    else if (data == "1011"){
      avoid = false;
      
      line = true;
    }

    else{
      avoid = false;
      line = false;
      
      stopMove();
    }
  }
}
