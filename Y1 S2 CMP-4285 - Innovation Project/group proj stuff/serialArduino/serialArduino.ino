#define LED1 9
#define LED2 10
#define BTN 5

byte lastButtonState = LOW;
byte currentButtonState = LOW;
unsigned long lastButtonDebounceTime = 0;
unsigned long buttonDebounceDelay = 20;

void powerOffAll(){
  digitalWrite(LED1, LOW);
  digitalWrite(LED2, LOW);
}

void setup() {
  Serial.begin(9600);
  
  pinMode(LED1, OUTPUT);
  pinMode(LED2, OUTPUT);
  pinMode(BTN, INPUT);

  powerOffAll();
}

void loop() {

  /* This is used to send data from arduino to pi one way
  Serial.println("Hello from Arduino!");
  delay(1000);
  */

  // This is used to grab data sent from pi 
  if (Serial.available() > 0) {
    String data = Serial.readStringUntil('\n');
    Serial.print("You sent me: ");
    Serial.println(data);
  }
  

  /*
  byte readValue = digitalRead(BTN);
  
  if (readValue != lastButtonState){
    lastButtonDebounceTime = millis();
  }

  if (millis() - lastButtonDebounceTime > buttonDebounceDelay){
    if (readValue != currentButtonState){
      currentButtonState = readValue;
      if (currentButtonState == HIGH){
        Serial.write(18);
      }
    }
  }

  lastButtonState = readValue;

  if (Serial.available() > 0){
    int ledNumber = Serial.read() - '0';

    powerOffAll();

    switch (ledNumber){
      case 1:
        digitalWrite(LED1, HIGH);
        break;
      case 2:
        digitalWrite(LED2, HIGH);
        break;
      default:
        //Do nothing
        break;
    }
  }
  */
}
