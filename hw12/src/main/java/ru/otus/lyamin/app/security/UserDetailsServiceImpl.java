package ru.otus.lyamin.app.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.dao.UserRepository;
import ru.otus.lyamin.app.entity.User;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(userName);
        return user.map(UserDetailsImpl::new).orElseThrow(() -> new UsernameNotFoundException("Not found " + userName));
    }
}
