package server.http;

public class HttpStatusCode {
    
    public static final int OK = 200; //OK
    public static final int CREATED = 201; //Created
    public static final int Accepted = 202; //Accepted
    public static final int NO_CONTENT = 204; //No content
    
    public static final int MOVED_PERMERNENTLY = 301; //Moved permernently
    public static final int FOUND = 302; //Found
    public static final int NOT_MODIFIED = 304; //Not modified
    
    public static final int BAD_REQUEST = 400; //Bad request
    public static final int UNAUTHORIZED = 401; //Unautorized
    public static final int FORBIDDEN = 403; //Forbidden
    public static final int NOT_FOUND = 404; //Not found
    
    public static final int INTERNAL_SERVER_ERROR = 500; //Internal server error
    public static final int NOT_IMPLEMENTED = 501; //Not implemented
    public static final int BAD_GATEWAY = 502; //Bad gateway
    public static final int SERVICE_UNAVAILABLE = 503; //Service Unavailable
    
}
