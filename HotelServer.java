package hotelServer;

//Hotel Server program 

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HotelServer {

  public static void main(String[] args) throws IOException {

    final int HOTEL_PORT = 8098;
    Hotel hotel = new Hotel();
    ServerSocket s = new ServerSocket(HOTEL_PORT);

    while (true) {
      System.out.println("Waiting for clients to connect...");
      Socket client = s.accept();
      System.out.println("Client Connected");
      HotelService hotelService = new HotelService(client, hotel);
      Thread t = new Thread(hotelService);
      t.start();

    }
  }
}

class HotelService implements Runnable, Protocol {

  Socket client;
  Hotel hotel;
  private DataInputStream in ;
  private DataOutputStream out;

  public HotelService(Socket client, Hotel hotel) {
    this.client = client;
    this.hotel = hotel;
  }

  public void run() {
    try {
      in = new DataInputStream(client.getInputStream());
      out = new DataOutputStream(client.getOutputStream());
      doService();
    } catch (IOException exception) {
    	exception.printStackTrace();
    } finally {
      try {
        client.close();
      } catch (IOException exception) {}
    }

  }

  
 
  private void doService() throws IOException {
    int option = 0;
    int count = 0;
    String n = "";
    int first = 0;
    int last = 0;
    boolean reserve = true;
    boolean cancel = true;

    out.writeUTF("Welcome to the Hotel Reservation Program");

    while (option != QUIT) {

      option = in.readInt();

      if (option != (USER) && count == 0) {
        out.writeUTF("Incorrect Input");
        out.flush();
        option = QUIT;
        break;
      }

      switch (option) {

      case Protocol.USER:

        out.writeUTF("\nPlease enter the name of the user: ");
        n = in.readUTF();
        out.writeUTF("Hello " + n);
        out.flush();
        break;

      case Protocol.RESERVE:

        first = in.readInt();
        last = in.readInt();
        reserve = hotel.requestReservation(n, first, last);

        if (reserve == true) {
          out.writeUTF("Reservation made: " + n + " from the " + first +
            " through the " + last+" of December");
        } else {
          out.writeUTF("Reservation Unsuccesful: " + n + " from the " + first +
            " through the " + last+" of December");
        }

        out.flush();
        break;

      case Protocol.CANCEL:

        cancel = hotel.cancelReservation(n);

        if (cancel == true) {
          out.writeUTF("Reservation succesfully canceled for " + n);
        } else {
          out.writeUTF("Reservation not canceled for " + n + " no current reservation");
        }
        out.flush();
        break;

      case Protocol.AVAIL:
        out.writeUTF(hotel.reservationInformation());
        out.flush();
        break;

      case Protocol.QUIT:
        out.writeUTF("Closing Connection");
        out.flush();
        break;

      default:
        out.writeUTF(" invalid option, Closing Connection");
        option = QUIT;
        out.flush();
        break;

      }

      count++;
    }

  }

}
	
	

