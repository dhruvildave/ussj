package org.localhost.ussj.shortner;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

@Entity
public class Shortner {
    @Id
    @Column(name = "long", columnDefinition = "text")
    private String longURL;

    @Column(name = "short", columnDefinition = "text", nullable = false, unique = true)
    private String shortURL;

    public Shortner() {}

    public Shortner(String longURL, String shortURL) {
        this.shortURL = shortURL;
        this.longURL = longURL;
    }

    public static String makeShort(String longURL) throws NoSuchAlgorithmException {
        var md = MessageDigest.getInstance("SHA-1");
        var b = md.digest(longURL.getBytes(StandardCharsets.UTF_8));

        // each index contains two hex characters
        return HexFormat.of().formatHex(b, 0, 4);
    }

    public String getLongURL() {
        return longURL;
    }

    public String getShortURL() {
        return shortURL;
    }
}
