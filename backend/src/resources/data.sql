INSERT INTO user (id, username) VALUES (1, 'alice'), (2, 'bob');

INSERT INTO contest (id, title, description) VALUES
(1, 'Spring Boot Coding Contest', 'Test your coding skills!');

INSERT INTO problem (id, title, description, contest_id, input_sample, expected_output) VALUES
(1, 'Sum Two Numbers', 'Read two integers and print their sum.', 1, '2 3', '5'),
(2, 'Multiply Numbers', 'Read two integers and print their product.', 1, '4 5', '20');
