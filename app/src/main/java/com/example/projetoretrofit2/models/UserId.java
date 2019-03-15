package com.example.projetoretrofit2.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserId extends DefaultModel{

    private Integer userId;

    public UserId(Integer id, String title, Integer userId) {
        super(id, title);
        this.userId = userId;
    }
}
