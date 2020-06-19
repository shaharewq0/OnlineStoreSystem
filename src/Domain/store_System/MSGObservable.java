package Domain.store_System;

import java.util.List;
import java.util.Observer;

public interface MSGObservable {
    public boolean add_Observer(ClintObserver O);
    public List<String> getMessges();
}
