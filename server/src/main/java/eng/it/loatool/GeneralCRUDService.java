package eng.it.loatool;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class GeneralCRUDService {

    @Transactional
    public Iterable getAll(CrudRepository repository) {
        return repository.findAll();
    }

    @Transactional
    public Optional getOne(
        CrudRepository repository,
        Object id
    ) {
        return repository.findById(id);
    }

    @Transactional
    public Optional create(
        CrudRepository repository,
        PrimaryKeyOwner entity
    ) {
        if (
            entity.getPrimaryKey() == null ||
            !repository.existsById(entity.getPrimaryKey())
        ) {
            return Optional.of(repository.save(entity));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional update(
        CrudRepository repository,
        Object primaryKey,
        PrimaryKeyOwner entity
    ) {
        entity.setPrimaryKey(primaryKey);
        if (primaryKey == null || !repository.existsById(primaryKey)) {
            return Optional.empty();
        }
        return Optional.of(repository.save(entity));
    }

    @Transactional
    public Optional delete(
        CrudRepository repository,
        Object id
    ) {
        Optional entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
            return entity;
        }
        return Optional.empty();
    }

}