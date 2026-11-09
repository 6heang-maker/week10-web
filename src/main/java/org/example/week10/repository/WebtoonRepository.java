
package org.example.week10.repository;

import org.example.week10.entity.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {

    List<Webtoon> findByTitleContaining(String title);
    List<Webtoon> findByPlatformAndRatingGreaterThanEqual(String platform, Double rating);
    List<Webtoon> findByGenreOrderByRatingDesc(String genre);
}
