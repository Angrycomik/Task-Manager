package taskmanagement.dto;

public record TaskResponseDto(
        String id,
        String title,
        String desription,
        String status,
        String author
){}
