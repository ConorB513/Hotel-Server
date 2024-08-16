package hotelServer;

//A basic protocol interface for the hotelServer program 

public interface Protocol {
	
	int PORT = 8098;
	int USER = 1;
	int RESERVE = 2;
	int CANCEL = 3;
	int AVAIL = 4;
	int QUIT = 5;
	int OTHER = 6;
	
}
