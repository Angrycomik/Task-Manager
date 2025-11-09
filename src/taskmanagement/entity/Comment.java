package taskmanagement.entity;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Integer id;

    private String taskId;

    private String text;

    private String author;

    public Comment(int taskId, String text, String author) {
        this.taskId = String.valueOf(taskId);
        this.text = text;
        this.author = author;
    }

    public Comment() {}

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
