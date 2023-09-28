package com.course.rabbitmq.producer;

import com.course.rabbitmq.producer.entity.Furniture;
import com.course.rabbitmq.producer.entity.Picture;
import com.course.rabbitmq.producer.producer.FurnitureProducer;
import com.course.rabbitmq.producer.producer.PictureProducer;
import com.course.rabbitmq.producer.producer.PictureProducer2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
//@EnableScheduling
public class Application implements CommandLineRunner {

    @Autowired
    private FurnitureProducer furnitureProducer;

    private List<String> colors = List.of("white", "red", "green");
    private List<String> materials  = List.of("wood", "plastic", "steel");

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Furniture furniture = new Furniture();
            furniture.setName("Furniture" + i);
            furniture.setColor(colors.get(i % colors.size()));
            furniture.setMaterial(materials.get(i % materials.size()));
            furniture.setPrice(i);

            furnitureProducer.sendMessage(furniture);
        }
    }
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
}
