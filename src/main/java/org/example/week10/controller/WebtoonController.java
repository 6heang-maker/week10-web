
package org.example.week10.controller;
import org.example.week10.dto.WebtoonRequest;
import org.example.week10.dto.WebtoonResponse;
import org.example.week10.service.WebtoonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/webtoons")
public class WebtoonController {

    private final WebtoonService service;
    public WebtoonController(WebtoonService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WebtoonResponse> create(
            @Valid @RequestBody WebtoonRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping
    public List<WebtoonResponse> findAll() {
        return service.findAll();
    }
    @GetMapping("/{id}")
    public WebtoonResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public WebtoonResponse update(
            @PathVariable Long id,
            @Valid @RequestBody WebtoonRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/title")
    public List<WebtoonResponse> searchTitle(@RequestParam String title) {
        return service.searchTitle(title);
    }

    @GetMapping("/search/platform")
    public List<WebtoonResponse> searchPlatform(
            @RequestParam String platform, @RequestParam Double rating) {
        return service.searchPlatform(platform, rating);
    }

    @GetMapping("/search/genre")
    public List<WebtoonResponse> searchGenre(@RequestParam String genre) {
        return service.searchGenre(genre);
    }
}
