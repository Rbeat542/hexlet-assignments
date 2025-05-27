package exercise.dto;

// BEGIN
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Setter
@Getter
public class GuestCreateDTO {

    @NotEmpty
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "[+][0-9]{11,13}")
    private String phoneNumber;

    @Pattern(regexp = "[0-9]{4}")
    private String clubCard;

    @Future
    private LocalDate cardValidUntil;

}

// END
