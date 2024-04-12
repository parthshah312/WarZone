package model;

import java.util.ArrayList;

/**
 * Observable class
 */
public class Observable {

    private ArrayList<Observer> d_Observers  = new ArrayList<>();

    /**
     * adds observer to the list
     * @param p_observer observer
     */
    public void attach(Observer p_observer){
        this.d_Observers.add(p_observer);
    }

    /**
     * removes observer from list
     * @param p_observer observer
     */
    public void detach(Observer p_observer){
        if(!d_Observers.isEmpty()){
            d_Observers.remove(p_observer);
        }
    }

    /**
     * updates the observer
     * @param p_observers observable
     */
    public void notifyObservers(Observable p_observers){
        for(Observer l_observer : d_Observers){
            l_observer.update(p_observers);
        }
    }
}