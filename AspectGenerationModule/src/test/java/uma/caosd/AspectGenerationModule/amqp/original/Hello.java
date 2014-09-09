package uma.caosd.AspectGenerationModule.amqp.original;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;


public class Hello
{

    public Hello()
    {
    }

    public static void main(String[] args)
    {
        try
        {
            Class.forName("org.apache.qpid.amqp_1_0.jms.jndi.PropertiesFileInitialContextFactory");

            Hashtable env = new Hashtable();
            env.put("java.naming.provider.url", "hello.properties");
            env.put("java.naming.factory.initial", "org.apache.qpid.amqp_1_0.jms.jndi.PropertiesFileInitialContextFactory");

            Context context = new InitialContext(env);

            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("localhost");
            Connection connection = connectionFactory.createConnection();

            Session producersession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) context.lookup("queue");


            Session consumerSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer messageConsumer = consumerSession.createConsumer(queue, "hello='true'");

            messageConsumer.setMessageListener(new MessageListener()
            {
                public void onMessage(final Message message)
                {
                    try
                    {

                        if(message instanceof TextMessage)
                        {
                            System.out.println("Received text Message:");
                            System.out.println("======================");
                            System.out.println(((TextMessage) message).getText());
                        }
                        else if(message instanceof MapMessage)
                        {
                            System.out.println("Received Map Message:");
                            System.out.println("=====================");


                            MapMessage mapmessage = (MapMessage) message;

                            Enumeration names = mapmessage.getMapNames();

                            while(names.hasMoreElements())
                            {
                                String name = (String) names.nextElement();
                                System.out.println(name + " -> " + mapmessage.getObject(name));
                            }

                        }
                        else if(message instanceof BytesMessage)
                        {
                            System.out.println("Received Bytes Message:");
                            System.out.println("=======================");
                            System.out.println(((BytesMessage) message).readUTF());
                        }
                        else if(message instanceof StreamMessage)
                        {
                            System.out.println("Received Stream Message:");
                            System.out.println("========================");
                            StreamMessage streamMessage = (StreamMessage)message;
                            Object o = streamMessage.readObject();
                            System.out.println(o.getClass().getName() + ": " + o);
                            o = streamMessage.readObject();
                            System.out.println(o.getClass().getName() + ": " + o);
                            o = streamMessage.readObject();
                            System.out.println(o.getClass().getName() + ": " + o);

                        }
                        else if(message instanceof ObjectMessage)
                        {
                            System.out.println("Received Object Message:");
                            System.out.println("========================");
                            ObjectMessage objectMessage = (ObjectMessage)message;
                            Object o = objectMessage.getObject();
                            System.out.println(o.getClass().getName() + ": " + o);
                        }
                        else
                        {
                            System.out.println("Received Message " + message.getClass().getName());
                        }
                    }
                    catch (JMSException e)
                    {
                        System.out.println("Caught exception in onMessage(): " + e.getMessage());
                        e.printStackTrace();  //TODO
                    }

                }
            });

            connection.start();


            MessageProducer messageProducer = producersession.createProducer(queue);
            TextMessage message = producersession.createTextMessage("Hello world!");
            message.setJMSType("Hello");
            message.setStringProperty("hello","true");
            messageProducer.send(message);
  
            /*
            MapMessage mapmessage = producersession.createMapMessage();
            mapmessage.setBoolean("mybool", true);
            mapmessage.setString("mystring", "hello");
            mapmessage.setLong("mylong", -25L);


            messageProducer.send(mapmessage);

            BytesMessage bytesMessage = producersession.createBytesMessage();
            bytesMessage.writeUTF("This is a bytes message");

            messageProducer.send(bytesMessage);

            ObjectMessage objectMessage = producersession.createObjectMessage();
            objectMessage.setObject(new Double("3.14159265358979323846264338327950288"));

            messageProducer.send(objectMessage);

/*          StreamMessage streamMessage = producersession.createStreamMessage();
            streamMessage.writeBoolean(true);
            streamMessage.writeLong(18031974L);
            streamMessage.writeString("this is a stream Message");
            streamMessage.writeChar('\u00A3');  // POUND SIGN character
            messageProducer.send(streamMessage);
*/
            Thread.sleep(50000L);

            connection.close();
            context.close();
        }
        catch (Exception exp)
        {
            System.out.println("Caught exception: " + exp.getMessage());
            exp.printStackTrace();
        }
    }
}