package rabbitmq.workshop;

public class Main {
    public static void main(String[] args) throws InterruptedException  {
        Thread suscribe = new Thread(()->{
            try {
                Consumer.suscribeNewsletter();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    
        Thread publish = new Thread(()->{
            try {
                Producer.publicMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    
        //Ejecutar Hilo sobre clase "Consumer"
        suscribe.start();
        //Esperar 5 segundos 
        Thread.sleep(5000);
        //Ejecutar Hilo sobre clase "Producer"
        publish.start();

    }

}
