package com.codetoelevate.Wee2Tutorial.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EmployeeDto {
    Long id;
    String firstName;
    String lastName;
    String emailId;
    @JsonProperty("isAdmin")
    boolean isAdmin;
}
