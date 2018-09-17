package floow.test.exercise.repository;

import floow.test.exercise.document.Text;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TextRepository extends MongoRepository<Text, String> {
}
