# Wire protocol

This is a generic wire protocol to allow a client to access an object at a server that implements a given interface I via a wire (such as a socket or a pipe).

1. While the client wants to make more calls:
    1. The client sends the method index, as a single byte (0 = first method declared by the interface, 1 = second method, etc.).
    2. The client sends the arguments for the call.
    3. If the method is not a void method, the server sends the return value.
2. The client closes the connection.
