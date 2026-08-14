package com.example.placementmanagement.console;

import com.example.placementmanagement.dto.request.*;
import com.example.placementmanagement.dto.response.*;
import com.example.placementmanagement.enums.*;
import com.example.placementmanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class ConsoleApplicationRunner implements CommandLineRunner {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private JobService jobService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private InterviewService interviewService;

    @Autowired
    private PlacementService placementService;

    @Autowired
    private DashboardService dashboardService;

    @Override
    public void run(String... args) throws Exception {
        // If non-interactive mode or explicitly disabled (e.g. during test runs), skip console loop
        String consoleEnabledProp = System.getProperty("console.enabled", "true");
        if ("false".equalsIgnoreCase(consoleEnabledProp) || System.console() == null && System.getProperty("interactive") == null) {
            System.out.println("Console runner started in non-interactive background mode.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("\n==================================================");
        System.out.println("      PLACEMENT MANAGEMENT SYSTEM (CONSOLE)      ");
        System.out.println("==================================================");

        while (running) {
            printMainMenu();
            System.out.print("Select an option [0-7]: ");
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        handleStudentMenu(scanner);
                        break;
                    case "2":
                        handleCompanyMenu(scanner);
                        break;
                    case "3":
                        handleJobMenu(scanner);
                        break;
                    case "4":
                        handleApplicationMenu(scanner);
                        break;
                    case "5":
                        handleInterviewMenu(scanner);
                        break;
                    case "6":
                        handlePlacementMenu(scanner);
                        break;
                    case "7":
                        showDashboardSummary();
                        break;
                    case "0":
                        running = false;
                        System.out.println("\nExiting Placement Management System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice! Please select between 0 and 7.");
                }
            } catch (Exception e) {
                System.out.println("\n[ERROR]: " + e.getMessage());
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("MAIN MENU:");
        System.out.println("1. Student Management");
        System.out.println("2. Company Management");
        System.out.println("3. Job Opening Management");
        System.out.println("4. Job Application Management");
        System.out.println("5. Interview Management");
        System.out.println("6. Placement Management");
        System.out.println("7. View Dashboard Summary Statistics");
        System.out.println("0. Exit System");
        System.out.println("--------------------------------------------------");
    }

    // ================= STUDENT MENU =================
    private void handleStudentMenu(Scanner scanner) {
        System.out.println("\n--- STUDENT MANAGEMENT ---");
        System.out.println("1. Add New Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Deactivate Student");
        System.out.print("Select Option: ");
        String opt = scanner.nextLine().trim();

        if ("1".equals(opt)) {
            StudentRequestDto dto = new StudentRequestDto();
            System.out.print("Enter Name: ");
            dto.setName(scanner.nextLine().trim());
            System.out.print("Enter Email: ");
            dto.setEmail(scanner.nextLine().trim());
            System.out.print("Enter Phone: ");
            dto.setPhone(scanner.nextLine().trim());
            System.out.print("Enter Department: ");
            dto.setDepartment(scanner.nextLine().trim());
            System.out.print("Enter Course/Branch: ");
            dto.setCourseBranch(scanner.nextLine().trim());
            System.out.print("Enter CGPA (0.0 - 10.0): ");
            dto.setCgpa(Double.parseDouble(scanner.nextLine().trim()));
            System.out.print("Enter Graduation Year: ");
            dto.setGraduationYear(Integer.parseInt(scanner.nextLine().trim()));
            System.out.print("Enter Skills (comma separated, e.g. Java, MySQL): ");
            String skillsStr = scanner.nextLine().trim();
            if (!skillsStr.isEmpty()) {
                dto.setSkills(new HashSet<>(Arrays.asList(skillsStr.split(","))));
            }

            StudentResponseDto response = studentService.createStudent(dto);
            System.out.println("\n[SUCCESS] Student Created with ID: " + response.getId());
        } else if ("2".equals(opt)) {
            List<StudentResponseDto> students = studentService.getAllStudents(null, null);
            System.out.println("\n--- STUDENT LIST ---");
            for (StudentResponseDto s : students) {
                System.out.printf("ID: %d | Name: %s | Email: %s | Dept: %s | CGPA: %.2f | Placed: %s\n",
                    s.getId(), s.getName(), s.getEmail(), s.getDepartment(), s.getCgpa(), s.getIsPlaced() ? "YES" : "NO");
            }
        } else if ("3".equals(opt)) {
            System.out.print("Enter Student ID: ");
            Long id = Long.parseLong(scanner.nextLine().trim());
            StudentResponseDto s = studentService.getStudentById(id);
            System.out.printf("\nID: %d\nName: %s\nEmail: %s\nDepartment: %s\nBranch: %s\nCGPA: %.2f\nGrad Year: %d\nSkills: %s\nPlaced: %s\n",
                s.getId(), s.getName(), s.getEmail(), s.getDepartment(), s.getCourseBranch(), s.getCgpa(), s.getGraduationYear(), s.getSkills(), s.getIsPlaced());
        } else if ("4".equals(opt)) {
            System.out.print("Enter Student ID to Deactivate: ");
            Long id = Long.parseLong(scanner.nextLine().trim());
            studentService.deactivateStudent(id);
            System.out.println("\n[SUCCESS] Student ID " + id + " deactivated.");
        }
    }

    // ================= COMPANY MENU =================
    private void handleCompanyMenu(Scanner scanner) {
        System.out.println("\n--- COMPANY MANAGEMENT ---");
        System.out.println("1. Register New Company");
        System.out.println("2. View All Companies");
        System.out.println("3. Deactivate Company");
        System.out.print("Select Option: ");
        String opt = scanner.nextLine().trim();

        if ("1".equals(opt)) {
            CompanyRequestDto dto = new CompanyRequestDto();
            System.out.print("Enter Company Name: ");
            dto.setCompanyName(scanner.nextLine().trim());
            System.out.print("Enter Description: ");
            dto.setDescription(scanner.nextLine().trim());
            System.out.print("Enter Contact Person: ");
            dto.setContactPerson(scanner.nextLine().trim());
            System.out.print("Enter Email: ");
            dto.setEmail(scanner.nextLine().trim());
            System.out.print("Enter Phone: ");
            dto.setPhone(scanner.nextLine().trim());
            System.out.print("Enter Location: ");
            dto.setLocation(scanner.nextLine().trim());

            CompanyResponseDto res = companyService.registerCompany(dto);
            System.out.println("\n[SUCCESS] Company registered with ID: " + res.getId());
        } else if ("2".equals(opt)) {
            List<CompanyResponseDto> companies = companyService.getAllCompanies(null, null);
            System.out.println("\n--- COMPANY LIST ---");
            for (CompanyResponseDto c : companies) {
                System.out.printf("ID: %d | Name: %s | Contact: %s | Email: %s | Location: %s | Status: %s\n",
                    c.getId(), c.getCompanyName(), c.getContactPerson(), c.getEmail(), c.getLocation(), c.getStatus());
            }
        } else if ("3".equals(opt)) {
            System.out.print("Enter Company ID to Deactivate: ");
            Long id = Long.parseLong(scanner.nextLine().trim());
            companyService.deactivateCompany(id);
            System.out.println("\n[SUCCESS] Company ID " + id + " deactivated.");
        }
    }

    // ================= JOB MENU =================
    private void handleJobMenu(Scanner scanner) {
        System.out.println("\n--- JOB OPENING MANAGEMENT ---");
        System.out.println("1. Create Job Opening");
        System.out.println("2. View All Jobs");
        System.out.print("Select Option: ");
        String opt = scanner.nextLine().trim();

        if ("1".equals(opt)) {
            JobRequestDto dto = new JobRequestDto();
            System.out.print("Enter Company ID: ");
            dto.setCompanyId(Long.parseLong(scanner.nextLine().trim()));
            System.out.print("Enter Job Title: ");
            dto.setJobTitle(scanner.nextLine().trim());
            System.out.print("Enter Minimum Required CGPA: ");
            dto.setMinCgpa(Double.parseDouble(scanner.nextLine().trim()));
            System.out.print("Enter Salary Package (LPA): ");
            dto.setSalaryPackage(Double.parseDouble(scanner.nextLine().trim()));
            System.out.print("Enter Job Location: ");
            dto.setJobLocation(scanner.nextLine().trim());
            dto.setApplicationDeadline(LocalDateTime.now().plusDays(30));

            JobResponseDto res = jobService.createJob(dto);
            System.out.println("\n[SUCCESS] Job opening created with ID: " + res.getId());
        } else if ("2".equals(opt)) {
            List<JobResponseDto> jobs = jobService.getAllJobs(null, null);
            System.out.println("\n--- JOB OPENINGS LIST ---");
            for (JobResponseDto j : jobs) {
                System.out.printf("ID: %d | Company: %s | Title: %s | Min CGPA: %.2f | Package: %.2f LPA | Status: %s\n",
                    j.getId(), j.getCompanyName(), j.getJobTitle(), j.getMinCgpa(), j.getSalaryPackage(), j.getStatus());
            }
        }
    }

    // ================= APPLICATION MENU =================
    private void handleApplicationMenu(Scanner scanner) {
        System.out.println("\n--- APPLICATION MANAGEMENT ---");
        System.out.println("1. Apply for a Job");
        System.out.println("2. View All Applications");
        System.out.println("3. Update Application Status");
        System.out.print("Select Option: ");
        String opt = scanner.nextLine().trim();

        if ("1".equals(opt)) {
            ApplicationRequestDto dto = new ApplicationRequestDto();
            System.out.print("Enter Student ID: ");
            dto.setStudentId(Long.parseLong(scanner.nextLine().trim()));
            System.out.print("Enter Job ID: ");
            dto.setJobId(Long.parseLong(scanner.nextLine().trim()));
            System.out.print("Enter Remarks: ");
            dto.setRemarks(scanner.nextLine().trim());

            ApplicationResponseDto res = applicationService.applyForJob(dto);
            System.out.println("\n[SUCCESS] Application submitted successfully with ID: " + res.getId() + " (Status: " + res.getStatus() + ")");
        } else if ("2".equals(opt)) {
            List<ApplicationResponseDto> apps = applicationService.getAllApplications(null, null, null);
            System.out.println("\n--- APPLICATIONS LIST ---");
            for (ApplicationResponseDto a : apps) {
                System.out.printf("ID: %d | Student: %s (CGPA: %.2f) | Company: %s | Job: %s | Status: %s\n",
                    a.getId(), a.getStudentName(), a.getStudentCgpa(), a.getCompanyName(), a.getJobTitle(), a.getStatus());
            }
        } else if ("3".equals(opt)) {
            System.out.print("Enter Application ID: ");
            Long id = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Enter New Status (SHORTLISTED, INTERVIEW_SCHEDULED, SELECTED, REJECTED, WITHDRAWN): ");
            String statusStr = scanner.nextLine().trim().toUpperCase();

            ApplicationStatusUpdateDto dto = new ApplicationStatusUpdateDto();
            dto.setStatus(ApplicationStatus.valueOf(statusStr));

            ApplicationResponseDto res = applicationService.updateApplicationStatus(id, dto);
            System.out.println("\n[SUCCESS] Application status updated to: " + res.getStatus());
        }
    }

    // ================= INTERVIEW MENU =================
    private void handleInterviewMenu(Scanner scanner) {
        System.out.println("\n--- INTERVIEW MANAGEMENT ---");
        System.out.println("1. Schedule Interview");
        System.out.println("2. View All Interviews");
        System.out.println("3. Update Interview Result");
        System.out.print("Select Option: ");
        String opt = scanner.nextLine().trim();

        if ("1".equals(opt)) {
            InterviewRequestDto dto = new InterviewRequestDto();
            System.out.print("Enter Application ID: ");
            dto.setApplicationId(Long.parseLong(scanner.nextLine().trim()));
            System.out.print("Enter Round Number (1, 2, 3): ");
            dto.setRoundNumber(Integer.parseInt(scanner.nextLine().trim()));
            System.out.print("Enter Type (TECHNICAL, HR, CODING, MANAGERIAL): ");
            dto.setInterviewType(InterviewType.valueOf(scanner.nextLine().trim().toUpperCase()));
            System.out.print("Enter Interviewer Name: ");
            dto.setInterviewerName(scanner.nextLine().trim());
            dto.setScheduledDateTime(LocalDateTime.now().plusDays(3));

            InterviewResponseDto res = interviewService.scheduleInterview(dto);
            System.out.println("\n[SUCCESS] Interview scheduled with ID: " + res.getId());
        } else if ("2".equals(opt)) {
            List<InterviewResponseDto> interviews = interviewService.getAllInterviews();
            System.out.println("\n--- INTERVIEW SCHEDULE LIST ---");
            for (InterviewResponseDto i : interviews) {
                System.out.printf("ID: %d | Candidate: %s | Job: %s | Round %d (%s) | Result: %s\n",
                    i.getId(), i.getStudentName(), i.getJobTitle(), i.getRoundNumber(), i.getInterviewType(), i.getResult());
            }
        } else if ("3".equals(opt)) {
            System.out.print("Enter Interview ID: ");
            Long id = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Enter Result (PASSED, FAILED, ON_HOLD): ");
            String resultStr = scanner.nextLine().trim().toUpperCase();

            InterviewResultUpdateDto dto = new InterviewResultUpdateDto();
            dto.setResult(InterviewResult.valueOf(resultStr));
            System.out.print("Enter Feedback: ");
            dto.setFeedback(scanner.nextLine().trim());

            InterviewResponseDto res = interviewService.updateInterviewResult(id, dto);
            System.out.println("\n[SUCCESS] Interview result updated to: " + res.getResult());
        }
    }

    // ================= PLACEMENT MENU =================
    private void handlePlacementMenu(Scanner scanner) {
        System.out.println("\n--- PLACEMENT MANAGEMENT ---");
        System.out.println("1. Issue Placement Offer");
        System.out.println("2. View All Placements");
        System.out.print("Select Option: ");
        String opt = scanner.nextLine().trim();

        if ("1".equals(opt)) {
            PlacementRequestDto dto = new PlacementRequestDto();
            System.out.print("Enter Student ID: ");
            dto.setStudentId(Long.parseLong(scanner.nextLine().trim()));
            System.out.print("Enter Company ID: ");
            dto.setCompanyId(Long.parseLong(scanner.nextLine().trim()));
            System.out.print("Enter Job ID: ");
            dto.setJobId(Long.parseLong(scanner.nextLine().trim()));
            System.out.print("Enter Job Role: ");
            dto.setJobRole(scanner.nextLine().trim());
            System.out.print("Enter Offered Package (LPA): ");
            dto.setPackageAmount(Double.parseDouble(scanner.nextLine().trim()));

            PlacementResponseDto res = placementService.createPlacement(dto);
            System.out.println("\n[SUCCESS] Placement record created with ID: " + res.getId() + " (Package: " + res.getPackageAmount() + " LPA)");
        } else if ("2".equals(opt)) {
            List<PlacementResponseDto> placements = placementService.getAllPlacements();
            System.out.println("\n--- PLACEMENT RECORDS ---");
            for (PlacementResponseDto p : placements) {
                System.out.printf("ID: %d | Student: %s | Company: %s | Role: %s | Package: %.2f LPA | Status: %s\n",
                    p.getId(), p.getStudentName(), p.getCompanyName(), p.getJobRole(), p.getPackageAmount(), p.getPlacementStatus());
            }
        }
    }

    // ================= DASHBOARD SUMMARY =================
    private void showDashboardSummary() {
        DashboardSummaryDto d = dashboardService.getDashboardSummary();
        System.out.println("\n==================================================");
        System.out.println("         PLACEMENT DASHBOARD STATISTICS           ");
        System.out.println("==================================================");
        System.out.printf(" Total Registered Students:   %d\n", d.getTotalStudents());
        System.out.printf(" Active Recruiting Companies: %d\n", d.getActiveCompanies());
        System.out.printf(" Open Job Drives:             %d\n", d.getOpenJobs());
        System.out.printf(" Total Applications Received: %d\n", d.getTotalApplications());
        System.out.printf(" Shortlisted Candidates:      %d\n", d.getShortlistedStudents());
        System.out.printf(" Selected Candidates:         %d\n", d.getSelectedStudents());
        System.out.printf(" Placed Students Count:       %d\n", d.getPlacedStudents());
        System.out.printf(" Placement Success Rate:      %.2f %%\n", d.getPlacementRatePercentage());
        System.out.printf(" Average Offered Salary:      %.2f LPA\n", d.getAveragePackage());
        System.out.printf(" Highest Offered Salary:      %.2f LPA\n", d.getHighestPackage());
        System.out.println("==================================================");
    }
}
