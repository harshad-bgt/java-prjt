-- ==================================================
-- PLACEMENT MANAGEMENT SYSTEM - DATABASE SEED DATA
-- ==================================================

-- Insert Skills
INSERT INTO skills (id, name) VALUES (1, 'Java');
INSERT INTO skills (id, name) VALUES (2, 'Spring Boot');
INSERT INTO skills (id, name) VALUES (3, 'MySQL');
INSERT INTO skills (id, name) VALUES (4, 'React.js');
INSERT INTO skills (id, name) VALUES (5, 'Python');
INSERT INTO skills (id, name) VALUES (6, 'Data Structures');
INSERT INTO skills (id, name) VALUES (7, 'AWS');
INSERT INTO skills (id, name) VALUES (8, 'Machine Learning');

-- Insert Companies
INSERT INTO companies (id, company_name, description, contact_person, email, phone, website, location, status) VALUES
(1, 'Google India', 'Global leader in technology and search solutions.', 'Siddharth Roy', 'recruitment@google.com', '080-41840000', 'https://careers.google.com', 'Bengaluru', 'ACTIVE'),
(2, 'Microsoft India', 'Empowering every person and organization on the planet.', 'Anita Desai', 'careers@microsoft.com', '040-66920000', 'https://careers.microsoft.com', 'Hyderabad', 'ACTIVE'),
(3, 'Amazon Web Services', 'Pioneer in cloud infrastructure and services.', 'Rajesh Kumar', 'aws-jobs@amazon.com', '080-40005000', 'https://amazon.jobs', 'Bengaluru', 'ACTIVE'),
(4, 'TCS (Tata Consultancy Services)', 'IT services, consulting, and business solutions.', 'Pooja Hegde', 'campus@tcs.com', '022-67789999', 'https://tcs.com', 'Mumbai', 'ACTIVE');

-- Insert Job Openings
INSERT INTO jobs (id, company_id, job_title, job_description, eligibility_criteria, min_cgpa, salary_package, job_location, application_deadline, status, created_at) VALUES
(1, 1, 'Software Engineer - Backend (Java/Spring)', 'Build scalable cloud backend services using Java and Spring Boot.', 'B.Tech / M.Tech in CSE / IT with minimum 7.5 CGPA', 7.50, 18.50, 'Bengaluru', '2027-12-31 23:59:59', 'OPEN', CURRENT_TIMESTAMP),
(2, 2, 'Full Stack Developer', 'Develop modern Web applications with React and Spring APIs.', 'B.Tech in CSE/ECE/EE with 7.0 CGPA', 7.00, 16.00, 'Hyderabad', '2027-12-31 23:59:59', 'OPEN', CURRENT_TIMESTAMP),
(3, 3, 'Cloud Support Engineer', 'Manage cloud infrastructure and automation on AWS.', 'B.Tech in CS/IT/ECE with 6.5 CGPA', 6.50, 12.50, 'Bengaluru', '2027-12-31 23:59:59', 'OPEN', CURRENT_TIMESTAMP),
(4, 4, 'Associate System Engineer', 'Enterprise IT solutions and software engineering.', 'B.Tech in any branch with 6.0 CGPA', 6.00, 7.00, 'Mumbai', '2027-12-31 23:59:59', 'OPEN', CURRENT_TIMESTAMP);

-- Insert Job Required Skills
INSERT INTO job_required_skills (job_id, skill_id) VALUES (1, 1), (1, 2), (1, 3);
INSERT INTO job_required_skills (job_id, skill_id) VALUES (2, 1), (2, 4);
INSERT INTO job_required_skills (job_id, skill_id) VALUES (3, 5), (3, 7);
INSERT INTO job_required_skills (job_id, skill_id) VALUES (4, 1), (4, 6);

-- Insert Students
INSERT INTO students (id, name, email, phone, department, course_branch, cgpa, graduation_year, resume_info, active) VALUES
(1, 'Aarav Sharma', 'aarav.sharma@example.com', '9876543210', 'Computer Science', 'B.Tech CSE', 8.90, 2026, 'Experienced in Java 17, Spring Boot, MySQL, and LeetCode problem solving.', true),
(2, 'Diya Patel', 'diya.patel@example.com', '9876543211', 'Information Technology', 'B.Tech IT', 8.20, 2026, 'Passionate Full Stack Developer proficient in React, Node.js, and Java.', true),
(3, 'Rohan Mehta', 'rohan.mehta@example.com', '9876543212', 'Electronics & Communication', 'B.Tech ECE', 7.10, 2026, 'Cloud enthusiast with AWS Certified Cloud Practitioner credential.', true),
(4, 'Sneha Reddy', 'sneha.reddy@example.com', '9876543213', 'Computer Science', 'B.Tech CSE', 9.40, 2026, 'High academic achiever, competitive coder, proficient in Python & ML.', true),
(5, 'Vikram Singh', 'vikram.singh@example.com', '9876543214', 'Electrical Engineering', 'B.Tech EE', 6.40, 2026, 'Good foundation in Data Structures and basic Java programming.', true);

-- Insert Student Skills
INSERT INTO student_skills (student_id, skill_id) VALUES (1, 1), (1, 2), (1, 3), (1, 6);
INSERT INTO student_skills (student_id, skill_id) VALUES (2, 1), (2, 4), (2, 3);
INSERT INTO student_skills (student_id, skill_id) VALUES (3, 5), (3, 7);
INSERT INTO student_skills (student_id, skill_id) VALUES (4, 1), (4, 5), (4, 8);
INSERT INTO student_skills (student_id, skill_id) VALUES (5, 1), (5, 6);

-- Insert Applications
INSERT INTO applications (id, student_id, job_id, status, applied_at, updated_at, remarks) VALUES
(1, 1, 1, 'SELECTED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Passed technical and HR rounds with outstanding performance.'),
(2, 2, 2, 'SHORTLISTED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Shortlisted based on resume and screening test.'),
(3, 4, 1, 'INTERVIEW_SCHEDULED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Technical round 1 scheduled.'),
(4, 3, 3, 'APPLIED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Application submitted successfully.');

-- Insert Interviews
INSERT INTO interviews (id, application_id, round_number, interview_type, scheduled_date_time, interviewer_name, result, feedback, status) VALUES
(1, 1, 1, 'CODING', '2026-09-01 10:00:00', 'Amitabh Bachchan', 'PASSED', 'Excellent algorithmic skills.', 'COMPLETED'),
(2, 1, 2, 'TECHNICAL', '2026-09-05 14:00:00', 'Sundar Pichai', 'PASSED', 'Strong grasp of System Design and Spring Boot.', 'COMPLETED'),
(3, 3, 1, 'TECHNICAL', '2026-10-15 11:30:00', 'Satya Nadella', 'PENDING', 'Scheduled for upcoming week.', 'SCHEDULED');

-- Insert Placement Record
INSERT INTO placements (id, student_id, company_id, job_id, job_role, package_amount, joining_date, placement_status, offered_at) VALUES
(1, 1, 1, 1, 'Software Engineer - Backend', 18.50, '2026-07-01', 'ACCEPTED', CURRENT_TIMESTAMP);
