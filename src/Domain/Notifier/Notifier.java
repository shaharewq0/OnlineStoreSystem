package Domain.Notifier;


import java.util.Objects;
import java.util.Observable;

public class Notifier extends Observable {

    private static Notifier notifier = new Notifier();

    private  Notifier() {}

    public static Notifier getInstance(){
        return notifier;
    }


    public void update(){
        notifier.notifyObservers();
    }

    public void update(Objects data){
        notifier.notifyObservers(data);
    }

}
