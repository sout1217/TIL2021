package com.education.jupiter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class StudyTests {

    @FastTest
    @DisplayName("스터디 만들기 fast")
    void create() {
        System.out.println("fast");
    }

    @Test
    @DisplayName("스터디 만들기 slow")
    void create2() {

    }
}