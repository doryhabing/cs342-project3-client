import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Client extends Thread{
	Socket socketClient;
	ObjectOutputStream out;
	ObjectInputStream in;
	int port, word_length = 0, remaining_guesses, win_count, loss_count;
	boolean is_correct, win, lost, loss, won;
	String message, category1, category2, category3;
	List<Integer> indexes;
	List<String> categories;

	private Consumer<Serializable> callback;

	Client(Consumer<Serializable> call, String port_string){
		callback = call;
		port = Integer.parseInt(port_string);
		message = "";
		remaining_guesses = 0;
		win_count = 0;
		loss_count = 0;
		indexes = new ArrayList<>();
		categories = new ArrayList<>();
	}

	public void run() {

		try {
			socketClient = new Socket("127.0.0.1", port);
			out = new ObjectOutputStream(socketClient.getOutputStream());
			in = new ObjectInputStream(socketClient.getInputStream());
			socketClient.setTcpNoDelay(true);
		}
		catch (Exception e) {}

		while(true) {
			try {
				message = in.readObject().toString();
				callback.accept(message);
				System.out.println(message);

				int space_index = message.indexOf(' ');
				String parsed_message = message.substring(0, space_index);
				message = message.substring(space_index + 1);

				if (Objects.equals(parsed_message, "category1")) {
					this.category1 = message;
				} else if (Objects.equals(parsed_message, "category2")) {
					this.category2 = message;
				} else if (Objects.equals(parsed_message, "category3")) {
					this.category3 = message;
				} else if (Objects.equals(parsed_message, "length")) {
					this.word_length = Integer.parseInt(message);
				} else if (Objects.equals(parsed_message, "correct")) {
					this.is_correct = true;
					this.indexes.add(Integer.parseInt(message));
				} else if (Objects.equals(parsed_message, "incorrect")) {
					this.remaining_guesses = Integer.parseInt(message);
					this.is_correct = false;
				} else if (Objects.equals(parsed_message, "lives")) {
					this.remaining_guesses = Integer.parseInt(message);
				} else if (Objects.equals(parsed_message, "win")) {
					this.win_count = Integer.parseInt(message);
					this.win = true;
				} else if (Objects.equals(parsed_message, "disable")) {
					this.categories.add(message);
				} else if (Objects.equals(parsed_message, "loss")) {
					this.loss_count = Integer.parseInt(message);
					this.loss = true;
				} else if (Objects.equals(parsed_message, "won")) {
					this.win_count++;
					this.won = true;
				} else if (Objects.equals(parsed_message, "lost")) {
					this.loss_count++;
					this.lost = true;
				}

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
