package com.maigrand.overwatchdb;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestInstance(Lifecycle.PER_CLASS)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public abstract class AbstractTest {
}
