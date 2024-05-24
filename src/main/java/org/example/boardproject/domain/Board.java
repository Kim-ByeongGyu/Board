package org.example.boardproject.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime ;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Board {
    @Id
    private Long id;
    private String title;
    private String name;
    private String password;
    private String content;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    // 포맷팅된 날짜를 반환하는 메서드
    public String getFormattedCreatedAtDate() {
        return created_at.format(DateTimeFormatter.ofPattern("YYYY/MM/dd"));
    }
    public String getFormattedCreatedAtDateTime() {
        return created_at.format(DateTimeFormatter.ofPattern("YYYY/MM/dd hh:mm"));
    }

    public String getFormattedUpdatedAt() {
        return updated_at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
