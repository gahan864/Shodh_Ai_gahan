package com.shodhacode.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Problem problem;

    private String language;

    @Lob
    private String code;

    private String status; // Pending, Running, Accepted, Wrong Answer
    @Lob
    private String output;
    private LocalDateTime createdAt;
}
