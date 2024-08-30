package rabbitmq.workshop;

import com.rabbitmq.client.*;

public class Consumer {
    private final static String EXCHANGE_NAME = "newsletter_topic";

    public static void suscribeNewsletter() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        //Se ejecuta en local
        factory.setHost("localhost");

        try{
            Connection connection = factory.newConnection(); 
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        
             //Se crea una queue exclusiva para el usuario
            String queueName = channel.queueDeclare().getQueue();
            //Crear una relación entre el Exchange y el queue (binding) y pasar el patrón de coincidencia (Topic)
            String[] bindingKeys = {"tech.#","#.electronic"};

            for (String bindingKey : bindingKeys) {
                channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
            }
            //Se procesan los mensajes recibidos y se imprime el mensaje y el topic            
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                String routingKey = delivery.getEnvelope().getRoutingKey();
                System.out.println("[RECEIVED] User received the following newsletter successfully - " + routingKey + "\nMessage: " + message+"\n");
            };

            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
} 