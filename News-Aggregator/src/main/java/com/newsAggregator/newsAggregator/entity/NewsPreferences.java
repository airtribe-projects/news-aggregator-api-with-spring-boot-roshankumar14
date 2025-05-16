package com.newsAggregator.newsAggregator.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prefId;

    @NotNull(message = "Please provide the valid news preference ")
    private String ReaderPref;

    @ManyToOne
    @JsonBackReference
    private NewsReaderUser newsReaderUser;


}
