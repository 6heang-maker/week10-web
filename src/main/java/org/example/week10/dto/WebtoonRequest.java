
package org.example.week10.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WebtoonRequest(
        @NotBlank String title,
        @NotBlank String author,
        @NotBlank String genre,
        @NotBlank String platform,
        @NotNull Double rating
) {
}
