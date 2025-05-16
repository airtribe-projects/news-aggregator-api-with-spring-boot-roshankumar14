package com.newsAggregator.newsAggregator.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsReaderUser {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @NotNull(message = "Please provide the valid user name")
    private String userName;

    @NotNull(message = "Please provide the valid email-Id ")
    @Email
    private String emailId;

    @NotNull(message = "Password can not be blank ")
    private String password;

    private boolean enable;

    @NotNull
    private String language;


    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "newsReaderUser")
    @JsonManagedReference
    private List<NewsPreferences> newsPreferences;
}

