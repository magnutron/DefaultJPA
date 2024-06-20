-- Table creation for Discipline
CREATE TABLE discipline (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255),
                            result_type VARCHAR(255)
);

-- Table creation for Participant
CREATE TABLE participant (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(255),
                             gender VARCHAR(255),
                             age INT,
                             club VARCHAR(255)
);

-- Table creation for Result
CREATE TABLE result (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        result_type VARCHAR(255),
                        date DATE,
                        result_value VARCHAR(255),
                        participant_id BIGINT,
                        discipline_id BIGINT,
                        FOREIGN KEY (participant_id) REFERENCES participant(id),
                        FOREIGN KEY (discipline_id) REFERENCES discipline(id)
);

-- Table creation for the many-to-many relationship between Participant and Discipline
CREATE TABLE participant_discipline (
                                        participant_id BIGINT,
                                        discipline_id BIGINT,
                                        PRIMARY KEY (participant_id, discipline_id),
                                        FOREIGN KEY (participant_id) REFERENCES participant(id),
                                        FOREIGN KEY (discipline_id) REFERENCES discipline(id)
);
