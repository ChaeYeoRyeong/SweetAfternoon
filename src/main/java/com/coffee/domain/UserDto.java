package com.coffee.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
public class UserDto {
    private String user_id;
    private String user_pwd;
    private String user_name;
    private String user_email;
    private Date user_birth;
    private Date user_reg_date;

    public UserDto(){}
    public UserDto(String user_id, String user_pwd, String user_name, String user_email, Date user_birth, Date user_reg_date) {
        this.user_id = user_id;
        this.user_pwd = user_pwd;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_birth = user_birth;
        this.user_reg_date = user_reg_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto user = (UserDto) o;
        return user_id.equals(user.user_id) &&Objects.equals(user_pwd, user.user_pwd) && Objects.equals(user_name, user.user_name) && Objects.equals(user_birth, user.user_birth) && Objects.equals(user_email, user.user_email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, user_pwd, user_name, user_birth, user_email);
    }
}
