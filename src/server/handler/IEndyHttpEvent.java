package server.handler;

public interface IEndyHttpEvent<T> {
    
    void handle(T object);
    
}
