package steps;


import io.cucumber.java.en.Given;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@Slf4j
public class LoadStepDefs extends BaseStepDefs
{
    private File extractFile;
    private String[] reservations;
    private static final String FORMAT_DATE = "yyyy-MM-dd HH:mm:ss";
    private LocalDateTime startTimeProcess;
    private int numberReservations;
    private String testPrefix;

    @SneakyThrows
    @Given("A bunch of sabre responses")
    public void iGenerateATestPrefix()
    {
        //var pnrPrefix = String.format("%08X", (int) Instant.now().getEpochSecond());
        //this.testPrefix = String.format("%sTEST", pnrPrefix);
        System.out.print("hello world " + s3Incoming);
    }

}