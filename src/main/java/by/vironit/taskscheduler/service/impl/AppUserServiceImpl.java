package by.vironit.taskscheduler.service.impl;

import by.vironit.taskscheduler.assembler.AppUserAssembler;
import by.vironit.taskscheduler.dto.AppUserDto;
import by.vironit.taskscheduler.entities.AppUser;
import by.vironit.taskscheduler.repository.AppUserRepository;
import by.vironit.taskscheduler.repository.TaskGroupsRepository;
import by.vironit.taskscheduler.security.registration.token.ConfirmationToken;
import by.vironit.taskscheduler.security.registration.token.ConfirmationTokenService;
import by.vironit.taskscheduler.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements UserDetailsService, AppUserService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final AppUserRepository appUserRepository;
    private final TaskGroupsRepository taskGroupsRepository;
    private final TaskServiceImpl taskService;
    private final TaskGroupsServiceImpl taskGroupsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private AppUserAssembler assembler;

    @Override
    public CollectionModel<AppUserDto> findAll(Integer page, Integer size, String sort) {

        int pages = page != null ? page : 0;
        int sizes = size != null ? size : 5;
        String sorts = sort != null && !sort.equals("") ? sort : "name";
        Pageable pageable = PageRequest.of(pages, sizes, Sort.by(sorts));
        Page<AppUser> users = appUserRepository.findAll(pageable);

        return !users.isEmpty() ? assembler.toCollectionModel(users) : null;
    }

    @Override
    public AppUser getById(Long id) {
        if (appUserRepository.getById(id) != null) {
            return appUserRepository.getById(id);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        if (taskGroupsRepository.existsById(id)) {
            taskService.deleteByTaskGroupId(id);
            taskGroupsService.deleteById(id);
            confirmationTokenService.deleteConfirmationTokenByAppUserId(id);
            appUserRepository.deleteById(id);
        } else confirmationTokenService.deleteConfirmationTokenByAppUserId(id);
        appUserRepository.deleteById(id);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return appUserRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser) {

        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if (userExists) {

            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

}
