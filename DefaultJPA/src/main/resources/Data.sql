SET SQL_SAFE_UPDATES = 0;

DELETE FROM test.parent_entities;
DELETE FROM test.child_entities;

-- Insert into my_entities1 table
INSERT INTO parent_entities (id, name) VALUES
                                           (1, 'Entity1 Name 1'),
                                           (2, 'Entity1 Name 2');

-- Insert into my_entities2 table with foreign key references to my_entities1
INSERT INTO child_entities (name, parent_entity_id) VALUES
                                                        ('Entity2 Name 1', 1),
                                                        ('Entity2 Name 2', 1),
                                                        ('Entity2 Name 3', 2),
                                                        ('Entity2 Name 4', 2);

SET SQL_SAFE_UPDATES = 1;
