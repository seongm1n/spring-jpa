package com.example.spring_jpa.domain.dto;

import lombok.Data;

@Data
public class StudentDto {
    public static class Request {
        @Data
        public static class Create {
            private String studentId;
            private String name;
            private Integer grade;
            private String phone;
            private String deptCode;
        }

        @Data
        public static class Update {
            private String name;
            private Integer grade;
            private String phone;
        }
    }

    @Data
    public static class Response {
        private Long id;
        private String studentId;
        private String name;
        private Integer grade;
        private String phone;
        private String deptCode;
    }
}
