package br.com.aceleraprogramador.gerenciamento_pedidos.security;

import br.com.aceleraprogramador.gerenciamento_pedidos.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserInfoConfigManager implements UserDetailsService {
    //Get me the user in form of auth object

    private final UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        return userInfoRepository.findByEmailId(emailId)
                .map(UserInfoConfig::new)
                .orElseThrow(() -> new UsernameNotFoundException("UserEmail:" + emailId + " not found"));
    }
}