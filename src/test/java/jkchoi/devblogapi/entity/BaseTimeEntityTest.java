package jkchoi.devblogapi.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BaseTimeEntityTest {

    @Test
    public void changeDateFormat() {
        LocalDateTime time = LocalDateTime.now();

        String format = time.format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm", Locale.ENGLISH));

        System.out.println("format = " + format);


    }

}