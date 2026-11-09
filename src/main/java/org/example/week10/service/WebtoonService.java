
package org.example.week10.service;

import org.example.week10.dto.WebtoonRequest;
import org.example.week10.dto.WebtoonResponse;
import org.example.week10.entity.Webtoon;
import org.example.week10.repository.WebtoonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebtoonService {

    private final WebtoonRepository repository;

    public WebtoonService(WebtoonRepository repository) {
        this.repository = repository;
    }
//Q7. save는 새로운 데이터는 추가하고 기존 데이터는 수정할 때 사용
    public WebtoonResponse create(WebtoonRequest request) {
        Webtoon webtoon = new Webtoon(
                request.title(),
                request.author(),
                request.genre(),
                request.platform(),
                request.rating()
        );

        repository.save(webtoon);
        return toResponse(webtoon);
    }

    public List<WebtoonResponse> findAll() {
        List<WebtoonResponse> result = new ArrayList<>();

        for (Webtoon webtoon : repository.findAll()) {
            result.add(toResponse(webtoon));
        }

        return result;
    }

    public WebtoonResponse findById(Long id) {
        Webtoon webtoon = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Webtoon not found"
                ));

        return toResponse(webtoon);
    }

    @Transactional
    public WebtoonResponse update(Long id, WebtoonRequest request) {
        Webtoon webtoon = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Webtoon not found"
                ));
//Q8. Transactional을 사용하면 작업 중 바뀐 내용을 DB에 저장해 준다.
        webtoon.update(
                request.title(),
                request.author(),
                request.genre(),
                request.platform(),
                request.rating()
        );

        return toResponse(webtoon);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Webtoon not found"
            );
        }

        repository.deleteById(id);
    }

    public List<WebtoonResponse> searchTitle(String title) {
        List<WebtoonResponse> result = new ArrayList<>();

        for (Webtoon w : repository.findByTitleContaining(title)) {
            result.add(toResponse(w));
        }

        return result;
    }

    public List<WebtoonResponse> searchPlatform(String platform, Double rating) {
        List<WebtoonResponse> result = new ArrayList<>();

        for (Webtoon w : repository.findByPlatformAndRatingGreaterThanEqual(platform, rating)) {
            result.add(toResponse(w));
        }

        return result;
    }

    public List<WebtoonResponse> searchGenre(String genre) {
        List<WebtoonResponse> result = new ArrayList<>();

        for (Webtoon w : repository.findByGenreOrderByRatingDesc(genre)) {
            result.add(toResponse(w));
        }

        return result;
    }

    private WebtoonResponse toResponse(Webtoon webtoon) {
        return new WebtoonResponse(
                webtoon.getId(),
                webtoon.getTitle(),
                webtoon.getAuthor(),
                webtoon.getGenre(),
                webtoon.getPlatform(),
                webtoon.getRating(),
                webtoon.getCreatedAt(),
                webtoon.getUpdatedAt()
        );
    }
}
