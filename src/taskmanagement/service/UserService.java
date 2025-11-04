package taskmanagement.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import taskmanagement.adapter.AppUserAdapter;
import taskmanagement.entity.AppUser;
import taskmanagement.exception.UserAlreadyExistsException;
import taskmanagement.repository.AppUserRepository;

@Service
public class UserService implements UserDetailsService {
    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(AppUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = repository
                .findByEmail(username.toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));

        return new AppUserAdapter(user);
    }

    public void saveUser(AppUser user){
        if(repository.existsByEmail(user.getEmail())){throw new UserAlreadyExistsException("Username already exists");}
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }
}
