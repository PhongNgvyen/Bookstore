package bookstore.backend.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bookstore.backend.domain.AppUser;
import bookstore.backend.domain.AppUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final AppUserRepository repository;

    public UserDetailServiceImpl(AppUserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser curruser = repository.findByUsername(username);

        if (curruser == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                curruser.getUsername(),
                curruser.getPasswordHash(),
                AuthorityUtils.createAuthorityList(curruser.getRole())
        );
    }
}
