public class Animal{
    protected String name;
    public Animal(){
        this.name = "Unknown";
    }
    public Animal(String name){
        this.name = name;
    }
    public void setName(String name){
        this.name = name;
    }
    protected void sound(){
        System.out.println("Sound!");
    }

    @Override 
    public String toString(){
        return "My name is " + this.name;
    }
}