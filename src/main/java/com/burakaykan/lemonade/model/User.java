package com.burakaykan.lemonade.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.naturalprogrammer.spring.lemon.commons.util.UserUtils;
import com.naturalprogrammer.spring.lemon.domain.AbstractUser;
import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class User extends AbstractUser<Long> implements Serializable {
    private static final long serialVersionUID = 2716710947175132319L;
    private static final int NAME_MIN = 1;
    private static final int NAME_MAX = 50;

    public static class Tag implements Serializable {

        private static final long serialVersionUID = -2129078111926834670L;

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @JsonView(UserUtils.SignupInput.class)
    @NotBlank(message = "{blank.name}", groups = {UserUtils.SignUpValidation.class, UserUtils.UpdateValidation.class})
    @Size(min = NAME_MIN, max = NAME_MAX, groups = {UserUtils.SignUpValidation.class, UserUtils.UpdateValidation.class})
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Tag toTag() {

        Tag tag = new Tag();
        tag.setName(name);
        return tag;
    }
}
