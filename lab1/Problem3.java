class Temperature{
    private double value;
    private char c;

    public Temperature(){
        this.value = 0;
        this.c = 'C';
    }
    public Temperature(double v){
        this.value = v;
        this.c = 'C';
    }
    public Temperature(char c){
        if(c == 'C' || c == 'F'){
            this.c = c;
            this.value = 0;
        }
        else{
            System.out.println("Incorrect Scale!");
        }
        
    }
    public Temperature(double v, char c){
        if(c == 'C' || c == 'F'){
            this.c = c;
            this.value = v;
        }
        else{
            System.out.println("Incorrect Scale!");
        }
    }


    public double getInCelsius(){
        if(this.c == 'C'){
            return value;
        }
        return 5*((value-32)/9);
    }
    public double getInFahrenheit(){
        if(this.c == 'F'){
            return value;
        }
        return (9*(value/5))+32;
    }
    

    public void setValue(double v){
        this.value = v;
    }
    public void setScale(char c){
        if(c != 'C' || c != 'F'){
            System.out.println("Incorrect scale!");
            return;
        }
        this.c = c;
    }
    public void setAll(double v, char c){
        if(c != 'C' || c != 'F'){
            System.out.println("Incorrect scale!");
            return;
        }
        this.value = v;
        this.c = c;
    }
    public char getScale(){
        return this.c;
    }
}


public class Problem3 {
    public static void main(String args[]){
        Temperature t = new Temperature();
        System.out.println(t.getInFahrenheit());
        System.out.println(t.getScale());
        Temperature t2 = new Temperature(20);
        System.out.println(t2.getInCelsius());
    }
}