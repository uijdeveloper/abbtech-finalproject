package az.abbtech.user.phonebook.service.impl;

import az.abbtech.user.phonebook.dto.UserPhonebookOperation;
import az.abbtech.user.phonebook.entity.UserPhonebookEntity;
import az.abbtech.user.phonebook.exceptions.FailedToGetSuccessfulResponseException;
import az.abbtech.user.phonebook.repository.UserPhonebookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserPhonebookServiceImplTest {
    @Mock
    private UserPhonebookRepository userPhonebookRepository;

    @InjectMocks
    private UserPhonebookServiceImpl userPhonebookService;

    @BeforeEach
    public void initialize() {
        doReturn(new UserPhonebookEntity()).when(userPhonebookRepository).save(any());
        doNothing().when(userPhonebookRepository).delete(any());

    }

    @Test
    void testPostWhenUserCanNotBeSaved() {
        when(userPhonebookRepository.save(any())).thenThrow(FailedToGetSuccessfulResponseException.class);
        UserPhonebookOperation userPhonebookOperation = userPhonebookService.postUser(UserPhonebookEntity.builder()
                .name("Test").user_id(1l).build());
        Assertions.assertTrue(userPhonebookOperation.getOperation_status().contains("FAILURE"));
    }

    @Test
    void testPostHappyPath() {
        UserPhonebookOperation userPhonebookOperation = userPhonebookService.postUser(UserPhonebookEntity.builder()
                .name("Test").user_id(1l).build());
        Assertions.assertTrue(userPhonebookOperation.getOperation_status().contains("SUCCESS"));
    }

    @Test
    void testUpdateWhenUserIdNull() {
        UserPhonebookOperation userPhonebookOperation = userPhonebookService.updateUser(UserPhonebookEntity.builder()
                .name("Test").build());
        Assertions.assertEquals("FAILURE", userPhonebookOperation.getOperation_status());
    }

    @Test
    void testUpdateWhenUserNotFound() {
        doReturn(Optional.empty()).when(userPhonebookRepository).findById(any());
        UserPhonebookOperation userPhonebookOperation = userPhonebookService.updateUser(UserPhonebookEntity.builder()
                .name("Test").user_id(1l).build());
        Assertions.assertEquals("FAILURE-USER NOT FOUND", userPhonebookOperation.getOperation_status());
    }

    @Test
    void testUpdateWhenUserCanNotBeUpdated() {
        doReturn(Optional.of(UserPhonebookEntity.builder().user_id(1l).build())).when(userPhonebookRepository).findById(any());
        when(userPhonebookRepository.save(any())).thenThrow(new FailedToGetSuccessfulResponseException("EDITING FAILED"));
        UserPhonebookOperation userPhonebookOperation = userPhonebookService.updateUser(UserPhonebookEntity.builder()
                .name("Test").user_id(1l).build());
        verify(userPhonebookRepository, times(1)).findById(any());
        Assertions.assertEquals("FAILURE-EDITING FAILED", userPhonebookOperation.getOperation_status());
    }

    @Test
    void testUpdateHappyPath() {
        doReturn(Optional.of(UserPhonebookEntity.builder().user_id(1l).build())).when(userPhonebookRepository).findById(any());
        UserPhonebookOperation userPhonebookOperation = userPhonebookService.updateUser(UserPhonebookEntity.builder()
                .name("Test").user_id(1l).build());
        verify(userPhonebookRepository, times(1)).findById(any());
        verify(userPhonebookRepository, times(1)).save(any());
        Assertions.assertEquals("SUCCESS", userPhonebookOperation.getOperation_status());
    }

    @Test
    void testRemoveWhenUserIdIsNull() {
        doReturn(Optional.of(UserPhonebookEntity.builder().user_id(1l).build())).when(userPhonebookRepository).findById(any());
        UserPhonebookOperation userPhonebookOperation = userPhonebookService.removeUser(null);
        Assertions.assertEquals("FAILURE-USER_ID IS NULL", userPhonebookOperation.getOperation_status());
    }

    @Test
    void testRemoveWhenDbThrowException() {
        doReturn(Optional.of(UserPhonebookEntity.builder().user_id(1l).build())).when(userPhonebookRepository).findById(any());
        when(userPhonebookRepository.findById(any())).thenThrow(new FailedToGetSuccessfulResponseException("DELETING FAILED"));
        verify(userPhonebookRepository, times(0)).delete(any());
        UserPhonebookOperation userPhonebookOperation = userPhonebookService.updateUser(UserPhonebookEntity.builder()
                .name("test").user_id(1l).build());
        Assertions.assertEquals("FAILURE-DELETING FAILED", userPhonebookOperation.getOperation_status());
    }

    @Test
    void testRemoveHappyPath() {
        doReturn(Optional.of(UserPhonebookEntity.builder().user_id(1l).build())).when(userPhonebookRepository).findById(any());
        UserPhonebookOperation userPhonebookOperation = userPhonebookService.removeUser(1l);
        verify(userPhonebookRepository, times(1)).delete(any());
        Assertions.assertEquals("SUCCESS", userPhonebookOperation.getOperation_status());
    }
}