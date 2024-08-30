package rabbitmq.workshop;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    private final static String EXCHANGE_NAME = "newsletter_topic";
    private static String[] topics = {"tech.laptops","tech.cellphones","music.electronic","tech.cellphones.iphone","music.pop"};

    public static void publicMessage() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        //Se ejecuta en local
        factory.setHost("localhost");

        //Abrir una conexión para crear un channel 
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()){
            //Declarar un exchange de tipo "Topic" 
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            System.out.println("-------------------------PRODUCER OUTPUT---------------------------");
            for(int i=0; i<topics.length; i++){
                String message = "This is a newsletter sent about - " + topics[i];
                //Envía mensajes de acuerdo al tema
                channel.basicPublish(EXCHANGE_NAME, topics[i], null, message.getBytes());
                System.out.println("[SENT] Some content was sent about - " + topics[i] + "\nMessage: "+ message+"\n");
            }
            System.out.println("---------------------END PRODUCER OUTPUT---------------------------");

        }

    }
}