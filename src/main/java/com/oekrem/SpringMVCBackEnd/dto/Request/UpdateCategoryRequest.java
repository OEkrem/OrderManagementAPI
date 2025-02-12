package com.oekrem.SpringMVCBackEnd.dto.Request;

public class UpdateCategoryRequest {

    private String name;
    private String description;

    public UpdateCategoryRequest() {
    }

    public UpdateCategoryRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CreateCategoryRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
