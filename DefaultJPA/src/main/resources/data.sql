-- Insert data into Discipline
INSERT INTO discipline (name, result_type) VALUES ('100m', 'Time');
INSERT INTO discipline (name, result_type) VALUES ('Long Jump', 'Distance');

-- Insert data into Participant
INSERT INTO participant (name, gender, age, club) VALUES ('John Doe', 'Male', 25, 'Running Club');
INSERT INTO participant (name, gender, age, club) VALUES ('Jane Smith', 'Female', 22, 'Jumping Club');
INSERT INTO participant (name, gender, age, club) VALUES ('Mark Johnson', 'Male', 28, 'Cycling Club');

-- Insert data into Result for 100m
INSERT INTO result (result_type, date, result_value, participant_id, discipline_id) VALUES ('Time', '2023-06-01', '10.5s', 1, 1);
INSERT INTO result (result_type, date, result_value, participant_id, discipline_id) VALUES ('Time', '2023-06-02', '11.0s', 2, 1);
INSERT INTO result (result_type, date, result_value, participant_id, discipline_id) VALUES ('Time', '2023-06-03', '10.8s', 3, 1);

-- Insert data into Result for Long Jump
INSERT INTO result (result_type, date, result_value, participant_id, discipline_id) VALUES ('Distance', '2023-06-04', '6.5m', 1, 2);
INSERT INTO result (result_type, date, result_value, participant_id, discipline_id) VALUES ('Distance', '2023-06-05', '6.7m', 2, 2);
INSERT INTO result (result_type, date, result_value, participant_id, discipline_id) VALUES ('Distance', '2023-06-06', '6.6m', 3, 2);

-- Insert data into participant_discipline (many-to-many relationship)
INSERT INTO participant_discipline (participant_id, discipline_id) VALUES (1, 1);
INSERT INTO participant_discipline (participant_id, discipline_id) VALUES (1, 2);
INSERT INTO participant_discipline (participant_id, discipline_id) VALUES (2, 1);
INSERT INTO participant_discipline (participant_id, discipline_id) VALUES (2, 2);
INSERT INTO participant_discipline (participant_id, discipline_id) VALUES (3, 1);
INSERT INTO participant_discipline (participant_id, discipline_id) VALUES (3, 2);
