package eng.it.loatool.api;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

public class ResponseEntityTransformer {

    public static <T> ResponseEntity<T> transform(Optional<T> optional) {
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.notFound().build();
    }

    public static <T> ResponseEntity<T> transform(Optional<T> optional, ResponseEntity notPresentResponse) {
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }
        return notPresentResponse;
    }

    public static <T> ResponseEntity<T> transform(Iterable<T> iterable) {
        if (iterable.iterator().hasNext()) {
            return (ResponseEntity<T>) ResponseEntity.ok(iterable);
        }
        return ResponseEntity.notFound().build();
    }

    public static <T> ResponseEntity<T> transformOk(T object) {
        return ResponseEntity.ok(object);
    }

}
