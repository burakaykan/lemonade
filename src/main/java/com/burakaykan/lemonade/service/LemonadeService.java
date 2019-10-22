package com.burakaykan.lemonade.service;

import com.burakaykan.lemonade.model.User;
import com.naturalprogrammer.spring.lemon.LemonService;
import com.naturalprogrammer.spring.lemon.commons.security.UserDto;
import com.naturalprogrammer.spring.lemon.commonsjpa.LecjUtils;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LemonadeService extends LemonService<User, Long> {
    @Override
    public User newUser() {
        return new User();
    }

    @Override
    public Long toId(String id) {
        return Long.valueOf(id);
    }

    @Override
    public void fillAdditionalFields(String registrationId, User user, Map<String, Object> attributes) {

        String nameKey;

        switch (registrationId) {
            case "facebook":
                nameKey = StandardClaimNames.NAME;
                break;

            case "google":
                nameKey = StandardClaimNames.NAME;
                break;

            default:
                throw new UnsupportedOperationException("Fetching name from " + registrationId + " login not supprrted");
        }

        user.setName((String) attributes.get(nameKey));
    }

    @Override
    protected void updateUserFields(User user, User updatedUser, UserDto currentUser) {

        super.updateUserFields(user,updatedUser,currentUser);

        user.setName(updatedUser.getName());

        LecjUtils.afterCommit(() -> {
            if (currentUser.getId().equals(user.getId())) {
                currentUser.setTag(user.toTag());
            }
        });
    }

    @Override
    protected User createAdminUser() {

        User user = super.createAdminUser();
        user.setName("Administrator");
        return user;
    }
}
