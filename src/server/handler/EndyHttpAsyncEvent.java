package server.handler;

public abstract class EndyHttpAsyncEvent<T> extends Thread implements IEndyHttpEvent<T> {
    
    private T object;
    
    public EndyHttpAsyncEvent(T object) {
        this.object = object;
    }
    
    @Override
    public void run() {
        handle(object);
    }
    
}
