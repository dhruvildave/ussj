package org.localhost.ussj.shortner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ShortnerAPI {
    @Autowired private ShortnerService service;

    @GetMapping("/api")
    public Optional<String> getShort(@RequestParam String longURL) {
        return service.findShortByLongURL(longURL);
    }

    @GetMapping("/{url}")
    public ResponseEntity<String> getLong(@PathVariable String url) {
        var s = service.findLongByShortURL(url);
        return s.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api")
    public Optional<String> postShort(String longURL) {
        return service.saveURL(longURL).map(Shortner::getShortURL);
    }
}
