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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        String fileName = "D:\\Projects\\JavaProjects\\Nekit\\messages.dat";
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
