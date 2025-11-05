package taskmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ChangeStatusRequestDto {

    @NotBlank
    @Pattern(regexp = "CREATED|IN_PROGRESS|COMPLETED")
    String status;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
//enum Status {
//    CREATED, IN_PROGRESS, COMPLETED
//}
