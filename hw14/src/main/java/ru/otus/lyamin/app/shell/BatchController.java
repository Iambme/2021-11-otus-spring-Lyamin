package ru.otus.lyamin.app.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
@ShellComponent
@RequiredArgsConstructor
public class BatchController {
    private final Job importBookJob;

    private final JobLauncher jobLauncher;

    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "sm-jl")
    public void startMigrationJobWithJobLauncher() throws Exception {
        JobExecution execution = jobLauncher.run(importBookJob, new JobParametersBuilder().toJobParameters());
        System.out.println(execution);
    }
}
