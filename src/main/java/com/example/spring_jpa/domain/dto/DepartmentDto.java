package com.example.spring_jpa.domain.dto;

import lombok.Data;

@Data
public class DepartmentDto {
    public static class Request {
        @Data
        public static class Create {
            private String deptCode;
            private String deptName;
            private String location;
        }

        @Data
        public static class Update {
            private String deptName;
            private String location;
        }
    }

    @Data
    public static class Response {
        private Long id;
        private String deptCode;
        private String deptName;
        private String location;
    }
}
