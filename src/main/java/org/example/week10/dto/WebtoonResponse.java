
package org.example.week10.dto;
import java.time.LocalDateTime;

public record WebtoonResponse(
        Long id,
        String title,
        String author,
        String genre,
        String platform,
        Double rating,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
