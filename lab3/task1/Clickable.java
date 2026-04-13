//We use Interfaces when we want to say that some classes must to be able to do something(must have these methods)
public interface Clickable{
    void onClick();
}
interface Saveable{
    void save();
}
// when multiple unrelated classes need the same ability
class Button implements Clickable{
    public void onClick(){
        System.out.println("Button clicked");
    }
}
//You need a class to follow multiple contracts at once.(Image class promises that it will have methods from these 2 interfaces )
class Image implements Clickable, Saveable{
    public void onClick() {
        System.out.println("Image clicked");
    }
    public void save() {
        System.out.println("Image saved");
    }
}