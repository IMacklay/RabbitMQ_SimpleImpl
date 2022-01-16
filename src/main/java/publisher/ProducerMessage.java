package publisher;

import Model.Note;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ProducerMessage {

    private final static String QUEUE_NAME="DevTest-Queue";

    public static void main(String[] args) {
        sendMessage(new Note("Max", 180));
    }

    public static void sendMessage(Note message){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (   Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()){
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}