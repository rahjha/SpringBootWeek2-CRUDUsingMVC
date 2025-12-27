package com.codetoelevate.Wee2Tutorial.dto;
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
    boolean isAdmin;
}
