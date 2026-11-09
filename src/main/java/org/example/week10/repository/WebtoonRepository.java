
package org.example.week10.repository;

import org.example.week10.entity.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {

    List<Webtoon> findByTitleContaining(String title);
    List<Webtoon> findByPlatformAndRatingGreaterThanEqual(String platform, Double rating);
    List<Webtoon> findByGenreOrderByRatingDesc(String genre);
}
//Q3. 저장할 데이터의 종류이다. id의 자료형이다.
//Q4. save는 저장, findAll은 전체 조회, findById는 번호로 검색, deleteById는 삭제, existsById는 데이터가 있는지 확인
//Q5. 찾는 데이터가 없을 수도 있어서
//Q6.  기본 기능은 JpaRepository가 제공하고 검색 기능은 직접 이름을 정해서 만든다