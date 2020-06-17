package Domain.Notifier;

import java.util.Observable;

public class Notifier extends Observable {

    private static Notifier notifier = new Notifier();

    private Notifier() {}

    public static Notifier getInstance(){
        return notifier;
    }


    /*public void update(String msg){
        notifier.notifyObservers(msg);
    }

    public void update(int gustID, String msg){
        notifier.notifyObservers(msg);
    } */

    public void update(String userName, String msg){
        notifier.setChanged();
        notifier.notifyObservers(userName + "!@!" + msg);
    }

}
