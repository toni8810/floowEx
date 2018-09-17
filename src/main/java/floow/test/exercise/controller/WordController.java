package floow.test.exercise.controller;

import floow.test.exercise.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;


@RestController
@RequestMapping("words")
@RequiredArgsConstructor
public class WordController {

    private final WordService wordService;

    @GetMapping("most-common")
    public Map<String, String> getMostCommonWord(@RequestParam(required = false, defaultValue = "0") Long fromLine) throws IOException {
        return Map.of("mostCommonWord", wordService.getMostCommonWord(fromLine));
    }

    @GetMapping("least-common")
    public Map<String, String> getLeastCommonWord(@RequestParam(required = false, defaultValue = "0") Long fromLine) throws IOException {
        return Map.of("leastCommonWord", wordService.getLeastCommonWord(fromLine));
    }

    @GetMapping("most-common-db")
    public Map<String, String> getMostCommonWordFromDB() {
        return Map.of("mostCommonWord", wordService.getMostCommonWordFromDB());
    }

    @GetMapping("least-common-db")
    public Map<String, String> getLeastCommonWordFromDB() {
        return Map.of("leastCommonWord", wordService.getLeastCommonWordFromDB());
    }
}
