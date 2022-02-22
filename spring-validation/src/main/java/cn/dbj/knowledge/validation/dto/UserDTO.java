package cn.dbj.knowledge.validation.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
public class UserDTO {
    @NotNull
    @Max(18L)
    @Min(0L)
    private Integer age;
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @AssertTrue
    private Boolean isPerson;

    @Valid
    @NonNull
    private GradeDTO grade;

    @Data
    static class GradeDTO {
        @NotNull
        @PositiveOrZero
        private Integer english;
        @NotNull
        @PositiveOrZero
        private Integer math;
        @NotNull
        @PositiveOrZero
        private Integer chinese;
    }
}
