package com.shodhacode.service;

import com.shodhacode.model.Submission;
import com.shodhacode.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class JudgeService {

    private final SubmissionRepository submissionRepository;

    public void executeSubmission(Submission submission, String expectedOutput, String input) {
        submission.setStatus("Running");
        submissionRepository.save(submission);

        try {
            Path tempDir = Files.createTempDirectory("sub_" + submission.getId());
            Path codeFile = tempDir.resolve("Main.java");
            Files.writeString(codeFile, submission.getCode());
            Files.writeString(tempDir.resolve("input.txt"), input);

            ProcessBuilder pb = new ProcessBuilder(
                "docker", "run", "--rm",
                "-v", tempDir + ":/app",
                "judge-env",
                "bash", "-c",
                "javac /app/Main.java && java -cp /app Main < /app/input.txt"
            );
            Process proc = pb.start();
            proc.waitFor(5, TimeUnit.SECONDS);

            String output = new String(proc.getInputStream().readAllBytes());
            if (output.trim().equals(expectedOutput.trim()))
                submission.setStatus("Accepted");
            else
                submission.setStatus("Wrong Answer");
            submission.setOutput(output);
        } catch (IOException | InterruptedException e) {
            submission.setStatus("Error");
            submission.setOutput(e.getMessage());
        } finally {
            submissionRepository.save(submission);
        }
    }
}
