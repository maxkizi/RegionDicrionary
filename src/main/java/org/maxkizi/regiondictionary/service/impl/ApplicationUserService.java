package org.maxkizi.regiondictionary.service.impl;

import com.querydsl.core.BooleanBuilder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.maxkizi.regiondictionary.exception.ApplicationUserNotFoundException;
import org.maxkizi.regiondictionary.model.ApplicationUser;
import org.maxkizi.regiondictionary.model.QApplicationUser;
import org.maxkizi.regiondictionary.repository.base.ApplicationUserRepository;
import org.maxkizi.regiondictionary.service.base.AbstractBaseService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class ApplicationUserService extends AbstractBaseService<ApplicationUser, Long, ApplicationUserRepository> implements UserDetailsService {

    private final ApplicationUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        BooleanBuilder bb = new BooleanBuilder();
        bb.and(QApplicationUser.applicationUser.isDeleted.isFalse());
        bb.and(QApplicationUser.applicationUser.login.eq(login));
        return get(bb).orElseThrow(ApplicationUserNotFoundException::new);
    }
}
