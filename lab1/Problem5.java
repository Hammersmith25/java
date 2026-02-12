import java.util.*;

enum Gender{
    Boy,
    Girl
}
class Person{
    Gender g;
    public Person(Gender g){
        this.g = g;
    }
    @Override
    public String toString() {
        return g.toString();
    }
}
class DragonLaunch{
    Vector<Person> kidnapped;
    public DragonLaunch(){
        kidnapped = new Vector<Person>();
    }
    public void kidnap(Person p){
        kidnapped.add(p);
    }
    public boolean willDragonEatOrNot(){
        int k = 0;
        for (int i = 0; i < kidnapped.size(); i++) {
            Person p = kidnapped.get(i);
            if (p.g == Gender.Girl && k > 0 && kidnapped.get(k - 1).g == Gender.Boy) {
                k--;
            } else {
                kidnapped.set(k, p);
                k++;
            }

        }
        if( k != 0){
            return true;
        }
        return false;
    }
}

public class Problem5 {
    public static void main(String args[]){
        DragonLaunch dl = new DragonLaunch();
        dl.kidnap(new Person(Gender.Boy));
        dl.kidnap(new Person(Gender.Boy));
        dl.kidnap(new Person(Gender.Girl));
        dl.kidnap(new Person(Gender.Girl));
        if(dl.willDragonEatOrNot()){
            System.out.println("The dragon will eat");
        }
        else{
            System.out.println("The dragon won't eat");
        }
    }
}