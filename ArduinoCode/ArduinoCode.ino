

boolean flag=false;
int frec;


void setup() {
  // initialize the serial communication:
  Serial.begin(9600);
  pinMode(10, INPUT); // Setup for leads off detection LO +
  pinMode(11, INPUT); // Setup for leads off detection LO -

}

void loop() {
  
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
 }//loop

