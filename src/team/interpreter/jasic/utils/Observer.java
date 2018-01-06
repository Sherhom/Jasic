package team.interpreter.jasic.utils;

import team.interpreter.jasic.domain.Store;

public interface Observer {

    public Store store = new Store().getStore();
    
    public void execute(Exception e);
    
}
