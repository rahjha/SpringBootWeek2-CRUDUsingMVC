package com.codetoelevate.Wee2Tutorial.dto;
import com.codetoelevate.Wee2Tutorial.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EmployeeDto {
    Long id;
    //@NotNull(message="Required filed in Employee : name")
    //@NotEmpty(message = "name of the employee can't be empty")
    @NotBlank(message = "name of the employee can't be blank")
    @Size(message = "number of characters in the name should be in the range: [3, 10]")
    String firstName;
    String lastName;
    @Email(message = "email should be a valid email")
    String emailId;
    @Max(value=80, message = "age of Employee can't be beyond 80")
    @Min(value=18, message = "age of Employee can't be less than 18")
    private Integer age;
    //@Pattern(regexp= "^(ADMIN|USER)$")
    @EmployeeRoleValidation
    private String role;//ADMIN, USER
    @NotNull(message = "salary of employee can't be null")
    @Positive(message = "salary of employee must be positive")
    @Digits(integer = 6, fraction = 2, message = "The salary of employee can be in form XXXXXX.YY")
    @DecimalMax(value = "100000.00")
    @DecimalMin(value="100.50")
    private Double salary;
    @PastOrPresent(message = "dateOfJoining of employee can't be in future")
    private LocalDate dateOfJoining;
    @JsonProperty("isAdmin")
    boolean isAdmin;
}
