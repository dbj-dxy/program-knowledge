package cn.dbj.knowledge.validation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

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
    public static class GradeDTO implements Serializable {
        private static final long serialVersionUID = 1L;

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
