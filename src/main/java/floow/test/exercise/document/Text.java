package floow.test.exercise.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document
public class Text {

    @Id
    private String id;

    private String mostCommonWord;
    private String leastCommonWord;

    private Map<String, Long> wordOccurences;
}
