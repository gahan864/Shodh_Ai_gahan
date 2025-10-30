package com.shodhacode.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    @ManyToOne
    private Contest contest;

    @Column(length = 2000)
    private String inputSample;

    @Column(length = 2000)
    private String expectedOutput;
}
