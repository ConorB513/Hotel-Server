# Hotel Server

Simulates a basic client-server hotel reservation system 

## Description

The hotel server program contains the basics for client-server communication based on the Totel class (provided by Professor Chinmaya Mahapatra). The hotel class simulates room reservations in a 31-day period. Users can request and cancel reservations aswell inquire about the reservations for the whole month. 

The hotelServer class monitors a specific port for client connections, and if so, handles the client using the hotelService class. The hotelService class allows the client to make and cancel reservations utilizing the functionality of the hotel class. The hotelService class also implements Runnable enabling multiple clients to simultaneously interact with the hotel server through multithreading. The HotelUserClient class connects a client to the hotel server and communicates with it via the protocol interface. The protocol interface contains the server's port and numeric values corresponding to actions including reserve, cancel and quit. 

## Topics 
Client-Server Architecture, Multithreading, Sockets


## Version History
* 0.1
    * Initial Release
 
## Acknowledgments

*This project was given as an assignment for my Object-Oriented Programming class (CPSC 1181 - Langara)
