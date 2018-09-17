package floow.test.exercise;

import floow.test.exercise.document.Text;
import floow.test.exercise.repository.TextRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class ExerciseApplication implements CommandLineRunner {

    private final TextRepository textRepository;

    public static void main(String[] args) {
        SpringApplication.run(ExerciseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var textList = textRepository.findAll();
        var lineNum = 0L;
        var text = textList.size() > 0 ? textList.get(0) : new Text();
        var bufferedReader = new BufferedReader(new FileReader(new File(System.getProperty("user.dir") + "/src/main/resources/enwiki-latest-pages-articles1.xml")));
        log.info("Start counting lines in the file");
        while (bufferedReader.readLine() != null) {
            ++lineNum;
        }

        for (int i = 0; i < lineNum / 100000; i++) {
            //Calling another microservice /words/most-common?fromLine=" + lineNum * 100000 +
            //" which will get the most common word from line to line and after we should get the most common word " +
            //"from the whole text using the individual common words"
        }

        //do similarly with least common words

        //finally persist the results to mongodb
        text.setLeastCommonWord("least common");
        text.setMostCommonWord("most common");

        textRepository.save(text);
    }
}
