package com.newsAggregator.newsAggregator.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@AllArgsConstructor
public class NewsReaderUserDTO {

    @NotNull(message = "Please provide the valid user name")
    private String userName;

    @NotNull(message = "Please provide the valid email-Id ")
    @Email
    private String emailId;

    @NotNull(message = "Password can not be blank ")
    private String password;

    @NotNull(message = "Please provide the valid news preference ")
    private List<String> newsPreferences;


    @NotNull
    private String language;
}
