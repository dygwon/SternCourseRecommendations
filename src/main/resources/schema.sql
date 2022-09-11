CREATE TABLE course (
    code CHAR(11) NOT NULL UNIQUE,
    title VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL
);

CREATE TABLE rating (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_code CHAR(12),
    student_id  BIGINT,
    rating SMALLINT NOT NULL,
    comment VARCHAR(1000),
    recommended BOOLEAN
);
ALTER TABLE rating ADD FOREIGN KEY (course_code) REFERENCES course(code);
ALTER TABLE rating ADD UNIQUE MyConstraint (course_code, student_id);