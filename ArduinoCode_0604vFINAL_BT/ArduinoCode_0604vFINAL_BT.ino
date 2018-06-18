
#include <SoftwareSerial.h>

SoftwareSerial BT (2,3);
boolean flag=false;
boolean flag2=false;
int frec;

void setup() {
  // put your setup code here, to run once:

  BT.begin(9600);
  Serial.begin(9600);
  pinMode(10, INPUT); // Setup for leads off detection LO +
  pinMode(11, INPUT); // Setup for leads off detection LO -

}

void loop() {
  // put your main code here, to run repeatedly:
  if (Serial.available()>0){ 
    String input=Serial.readString();
    frec=input.toInt();
  
        if(frec>0){
            flag=true;
            
            
        }else if(frec==0){
            flag=false;
        }          
    }//if serial.available
    if(flag==true){
      if((digitalRead(10) == 1)||(digitalRead(11) == 1)){
          Serial.println('0');
      }else{
          // send the value of analog input 0:
          Serial.println(analogRead(A0));
      }
      delay(1000/frec);
    }

    if (BT.available()>0){ 
    String input=BT.readString();
    frec=input.toInt();
  
        if(frec>0){
            flag2=true;
            
            
        }else if(frec==0){
            flag2=false;
        }          
    }//if serial.available
    if(flag2==true){
      if((digitalRead(10) == 1)||(digitalRead(11) == 1)){
          BT.println('0');
      }else{
          // send the value of analog input 0:
          BT.println(analogRead(A0));
      }
      delay(1000/frec);
    }
  
}
