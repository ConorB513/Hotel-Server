package hotelServer;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class HotelUserClient implements Protocol {

  public static void main(String[] args) throws IOException {

    Socket client = new Socket("localhost", Protocol.PORT);
    DataInputStream in = new DataInputStream(client.getInputStream());
    DataOutputStream out = new DataOutputStream(client.getOutputStream());
    Scanner scan = new Scanner(System.in);
    Boolean n = true;
    String response;

    response = in.readUTF();
    System.out.println(response);

    out.writeInt(USER);
    out.flush();
    response = in.readUTF();
    System.out.println(response);
    String newUserName = scan.next();
    scan.nextLine();
    out.writeUTF(newUserName);
    out.flush();
    System.out.println(in.readUTF());

    while (n) {

      System.out.print("\nPlease enter an option:\n" +
        "1 - Change User\n" +
        "2 - Make a reservation\n" +
        "3 - Cancel reservation\n" +
        "4 - See reservations\n" +
        "5 - Quit\n");

      int option = scan.nextInt();
      scan.nextLine();

      if (option == 1) {
        out.writeInt(USER);
        out.flush();
        response = in.readUTF();
        System.out.println(response);
        newUserName = scan.next();
        scan.nextLine();
        out.writeUTF(newUserName);
        out.flush();
        System.out.println(in.readUTF());

      } else if (option == 2) {
        out.writeInt(RESERVE);
        out.flush();
        System.out.print("Please enter the first reservation parameter: ");
        out.writeInt(scan.nextInt());
        out.flush();
        System.out.print("Please enter the second reservation parameter: ");
        out.writeInt(scan.nextInt());
        out.flush();
        response = in.readUTF();
        System.out.println(response);

      } else if (option == 3) {
        out.writeInt(CANCEL);
        out.flush();
        response = in.readUTF();
        System.out.println(response);
        
      } else if (option == 4) {
        out.writeInt(AVAIL);
        out.flush();
        response = in.readUTF();
        System.out.println(response);
        
      } else if (option == 5) {
        out.writeInt(QUIT);
        out.flush();
        response = in.readUTF();
        System.out.println(response);
        n = false;
        
      } else {
        out.writeInt(OTHER);
        response = in.readUTF();
        System.out.println(response);
        n = false;
      }
    }

    client.close();
    scan.close();
  }

}