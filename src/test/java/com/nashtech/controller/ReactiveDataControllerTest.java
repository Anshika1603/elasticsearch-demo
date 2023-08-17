package com.nashtech.controller;

import com.nashtech.service.ReactiveDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class ReactiveDataControllerTest {

    @Mock
    private ReactiveDataService reactiveDataService;

    @InjectMocks
    private ReactiveDataController reactiveDataController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPushDataToCloud() {
        ResponseEntity<Object> response = reactiveDataController.pushDataToCloud();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        verify(reactiveDataService).fetchAndSendData();
    }


}