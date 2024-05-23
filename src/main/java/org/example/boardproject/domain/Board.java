package org.example.boardproject.domain;

import lombok.*;

import java.time.LocalDateTime ;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Board {
    private int id;
    private String title;
    private String name;
    private String password;
    private String content;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
