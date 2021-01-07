package com.example.tennerr.service;

import com.example.tennerr.entity.Job;
import com.example.tennerr.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public void saveJob(Job job){
        jobRepository.save(job);
    }

    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }


    public List<Job> findAllJobs(Long id){
        return jobRepository.findByUserId(id);
    }

}
