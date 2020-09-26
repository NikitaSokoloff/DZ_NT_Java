import ru.pflb.mq.dummy.exception.DummyException;
import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.implementation.ProducerImpl;
import ru.pflb.mq.dummy.interfaces.Connection;
import ru.pflb.mq.dummy.interfaces.Destination;
import ru.pflb.mq.dummy.interfaces.Producer;
import ru.pflb.mq.dummy.interfaces.Session;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test3 {
    public static void main(String[] args) {
        while (true) {
            String fileName = args[0];
            try (BufferedReader br = new BufferedReader(new FileReader(fileName));
                 Connection connection = new ConnectionImpl();
                 Session session = connection.createSession(true);
            ) {
                Destination destination = session.createDestination("myQueue");
                Producer producer = new ProducerImpl(destination);
                String line;
                while ((line = br.readLine()) != null) {
                    producer.send(line);
                    Thread.sleep(2000);
                }
            } catch (DummyException | InterruptedException | IOException e) {
                System.out.println("Whoops error:" + e);
            }
        }
    }
}
