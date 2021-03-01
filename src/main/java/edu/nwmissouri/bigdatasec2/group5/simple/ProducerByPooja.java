package edu.nwmissouri.bigdatasec2.group5.simple;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;

public class ProducerByPooja {
  private static Scanner in;

  public static void main(String[] argv) throws Exception {
    if (argv.length != 1) {
      System.err.println("Please specify 1 parameter (the name of the topic)");
      System.exit(-1);
    }
    String topicName = argv[0];
    in = new Scanner(System.in);
    System.out.println("Thank you for providing the topic " + topicName + "\n");
    System.out.println("Enter message (type exit to quit).\n");

    // Configure the Producer
    Properties configProperties = new Properties();
    configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.ByteArraySerializer");
    configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringSerializer");
    System.out.println("The configuration properties are: " + configProperties.toString());
    System.out.println("\nWill use this configuration to create a producer.\n");

    org.apache.kafka.clients.producer.Producer producer = new KafkaProducer(configProperties);

    // Make our own messages - create your custom logic here

    for (int i = 1; i <= 5; i++) {
      int ran = ran.nextInt(5);
      String msg = Sentences(ran);
      ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, msg);
      producer.send(rec);
    }

    // still allow input from keyboard

    String line = in.nextLine();
    while (!line.equals("exit")) {
      ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, line);
      producer.send(rec);
      line = in.nextLine();
    }

    in.close();
    producer.close();
  }

  private static String Sentences(int i){
    if (i==1){ 
     System.out.println("The Dark Talent is the fifth action-packed fantasy adventure in the Alcatraz vs. the Evil Librarians series for young readers by the #1 New York Times bestselling author Brandon Sanderson.");
    }
    else if (i==2){
      return "The afterlife (also referred to as life after death, the world to come or reincarnation) is an existence in which the essential part of an individual's identity or their stream of consciousness continues to live after the death of their physical body.";

    }
    else if (i==3){
      return "To Kill a Mockingbird is a novel by the American author Harper Lee. It was published in 1960";
  }
  else (i==4) {
    return "After staging his own suicide, a crazed scientist uses his power to become invisible to stalk and terrorize his ex-girlfriend";
  }
  }
}
