import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Objects;
import java.util.function.Consumer;

public class Client extends Thread{
	Socket socketClient;
	ObjectOutputStream out;
	ObjectInputStream in;
	int port, word_length;
	String message, prev_message;
	String category1, category2, category3;
	
	private Consumer<Serializable> callback;
	
	Client(Consumer<Serializable> call, String port_string){
		callback = call;
		port = Integer.parseInt(port_string);
		message = "";
	}
	
	public void run() {
		
		try {
			socketClient= new Socket("127.0.0.1",port);
			out = new ObjectOutputStream(socketClient.getOutputStream());
			in = new ObjectInputStream(socketClient.getInputStream());
			socketClient.setTcpNoDelay(true);
		}
		catch (Exception e) {}

		int i = 1;
		while(true) {
			try {
				message = in.readObject().toString();
				callback.accept(message);

				if (i == 1) {
					category1 = message;
				} else if (i == 2) {
					category2 = message;
				} else if (i == 3) {
					category3 = message;
				} else if (Objects.equals(prev_message, "length")) {
					word_length = Integer.parseInt(message);
				}

				prev_message = message;
				i++;
			}
			catch (Exception e) {}
		}
	
    }
	
	public void send(String data) {
		
		try {
			out.writeObject(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
