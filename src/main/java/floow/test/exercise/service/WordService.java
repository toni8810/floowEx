package floow.test.exercise.service;

import floow.test.exercise.document.Text;
import floow.test.exercise.repository.TextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WordService {

    private final File xml = new File(System.getProperty("user.dir") + "/src/main/resources/enwiki-latest-pages-articles1.xml");
    private final TextRepository textRepository;

    public String getMostCommonWord(long fromLine) throws IOException {
        var mostOccurence = 0L;
        var commonWord = "";
        var wordOccurances = getWordOccurances(fromLine);
        var text = textRepository.findAll().get(0);

        if (text == null) text = new Text();

        Iterator<Map.Entry<String, Long>> map = wordOccurances.entrySet().iterator();
        while (map.hasNext()) {
            var entry = map.next();
            if (entry.getValue() > mostOccurence) {
                mostOccurence = entry.getValue();
                commonWord = entry.getKey();
            }
        }
        text.setMostCommonWord(commonWord);
        text.setWordOccurences(wordOccurances);
        textRepository.save(text);

        return commonWord;
    }

    public String getMostCommonWordFromDB() {
        return textRepository.findAll().get(0).getMostCommonWord();
    }

    public String getLeastCommonWordFromDB() {
        return textRepository.findAll().get(0).getLeastCommonWord();
    }

    public String getLeastCommonWord(long fromLine) throws IOException {
        var leastOccurence = Long.MAX_VALUE;
        var uncommonWord = "";
        var wordOccurances = getWordOccurances(fromLine);
        var text = textRepository.findAll().get(0);

        Iterator<Map.Entry<String, Long>> map = wordOccurances.entrySet().iterator();
        while (map.hasNext()) {
            var entry = map.next();
            if (entry.getValue() < leastOccurence) {
                leastOccurence = entry.getValue();
                uncommonWord = entry.getKey();
            }
        }

        text.setLeastCommonWord(uncommonWord);
        text.setWordOccurences(wordOccurances);
        textRepository.save(text);

        return uncommonWord;
    }

    private Map<String, Long> getWordOccurances(long fromLine) throws IOException {
        var wordOccurances = new HashMap<String, Long>();
        String currentLine;
        var currentLineNum = 0L;
        var words = new ArrayList<String>();
        var bufferedReader = new BufferedReader(new FileReader(xml));
        //reading every line in the file
        while ((currentLine = bufferedReader.readLine()) != null) {
            ++currentLineNum;
            //we are reading 100000 lines from the line number sent with the request
            if (currentLineNum > fromLine && currentLineNum < fromLine + 100000L) {
                //looping through each word in the line
                Arrays.stream(currentLine.split(" ")).forEach(word -> {
                    //if the word is empty just ignore it
                    if (!word.isEmpty()) {
                        //if the word contains a . change it to D as . is a keyword in Mongo
                        if (word.contains(".")) word = word.replace(".", "D");
                        if (word.contains("$")) word = word.replace("$", "DO");
                        words.add(word);
                    }
                });
            }
            //If we have read the first 100000 break out of the loop
            if (currentLineNum > fromLine + 100000L) break;
        }
        //get how many times a word occur
        words.forEach(word -> {
            if (wordOccurances.containsKey(word)) {
                wordOccurances.put(word, wordOccurances.get(word) + 1);
            }
            else {
                wordOccurances.put(word, 1L);
            }
        });

        return wordOccurances;
    }
}
