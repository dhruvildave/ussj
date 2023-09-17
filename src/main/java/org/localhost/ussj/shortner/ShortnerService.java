package org.localhost.ussj.shortner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ShortnerService {
    @Autowired private ShortnerRepository repository;

    public Optional<String> findShortByLongURL(String longURL) {
        return Optional.ofNullable(repository.findShortByLongURL(longURL));
    }

    public Optional<String> findLongByShortURL(String shortURL) {
        return Optional.ofNullable(repository.findLongByShortURL(shortURL));
    }

    public Optional<Shortner> saveURL(String longURL) {
        try {
            var url = repository.save(new Shortner(longURL, Shortner.makeShort(longURL)));
            return Optional.of(url);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }
}
