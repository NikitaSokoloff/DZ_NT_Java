import ru.pflb.mq.dummy.exception.DummyException;
import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.implementation.DestinationImpl;
import ru.pflb.mq.dummy.implementation.ProducerImpl;
import ru.pflb.mq.dummy.interfaces.Connection;
import ru.pflb.mq.dummy.interfaces.Destination;
import ru.pflb.mq.dummy.interfaces.Producer;
import ru.pflb.mq.dummy.interfaces.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
    public static void main(String[] args)  {
        List<String> list = new ArrayList<>();
        try (Connection connection = new ConnectionImpl();
             Session session = connection.createSession(true);
        ) {
            Destination destination = session.createDestination("myQueue");
            Producer producer = new ProducerImpl(destination);
            list.add("Раз");
            list.add("Два");
            list.add("Три");
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                producer.send(next);
                Thread.sleep(2000);
            }
        } catch (DummyException | InterruptedException e) {
            System.out.println("Whoops error:" + e);
        }
    }
}
