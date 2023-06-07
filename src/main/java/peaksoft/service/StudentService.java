package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoStudent.StudentRequest;
import peaksoft.dto.dtoStudent.StudentResponse;

import java.util.List;

public interface StudentService {
    List<StudentResponse> getAllStudents();
    StudentResponse saveStudent(StudentRequest studentRequest);
    StudentResponse updateStudentById(Long studentId,StudentRequest studentRequest);
    StudentResponse getStudentById(Long id);
    SimpleResponse deleteStudentById(Long id);
    SimpleResponse assignStudentToGroup(Long studentId,Long groupId);
    SimpleResponse blockOrUnBlockStudent(Long id, Boolean isBlocked);
    List<StudentResponse> getAllBlockOrUnBlockStudents(Boolean isBlocked);
    List<StudentResponse> getAllStudentByFilter(String word, Long id);
}
