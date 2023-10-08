package com.course.rabbitmq.producer;

import com.course.rabbitmq.producer.entity.Employee;
import com.course.rabbitmq.producer.entity.Picture;
import com.course.rabbitmq.producer.producer.RetryEmployeeProducer;
import com.course.rabbitmq.producer.producer.RetryPictureProducer;
import com.course.rabbitmq.producer.producer.SpringPictureProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
//@EnableScheduling
public class Application implements CommandLineRunner {

    @Autowired
    private SpringPictureProducer springPictureProducer;

    private static final List<String> SOURCES = List.of("mobile", "web");
    private static final List<String> TYPES = List.of("jpg", "png", "svg");

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 1; i++) {
            Picture picture = new Picture();
            picture.setName("Test spring " + i + LocalTime.now());
            picture.setSize(ThreadLocalRandom.current().nextLong(9001, 10000));
            picture.setSource(SOURCES.get(i % SOURCES.size()));
            picture.setType(TYPES.get(i % TYPES.size()));

            springPictureProducer.sendMessage(picture);
        }
    }

//    Producer with retry mechanism
//    @Override
//    public void run(String... args) throws Exception {
//        for (int i = 0; i < 10; i++) {
//            Picture picture = new Picture();
//            picture.setName("Picture " + i);
//            picture.setSize(ThreadLocalRandom.current().nextLong(9500, 10000));
//            picture.setSource(sources.get(i % sources.size()));
//            picture.setType(types.get(i % types.size()));
//
//            retryPictureProducer.sendMessage(picture);
//        }
//    }


//    PictureProducer Direct exchange
//    @Override
//    public void run(String... args) throws Exception {
//        for (int i = 0; i < 10; i++) {
//            Picture picture = new Picture();
//            picture.setName("Picture " + i);
//            picture.setSize(ThreadLocalRandom.current().nextLong(1, 10000));
//            picture.setSource(sources.get(i % sources.size()));
//            picture.setType(types.get(i % types.size()));
//
//            pictureProducer2.sendMessage(picture);
//        }
//    }

//    MyPictureProducer Consumer Exception without DLX
//    @Override
//    public void run(String... args) throws Exception {
//        for(int i=0; i<1; i++){
//            var picture = new Picture();
//            picture.setName("Picture " + i);
//            picture.setSize(ThreadLocalRandom.current().nextLong(9000, 10000));
//            picture.setSource(sources.get(i % sources.size()));
//            picture.setType(types.get(i % types.size()));
//
//            myPictureProducer.sendMessage(picture);
//        }
//    }
}
