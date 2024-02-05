package org.example.JavaTaigaCode.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.JavaTaigaCode.models.ProjectDTO;
import org.example.JavaTaigaCode.util.GlobalData;
import org.example.JavaTaigaCode.util.HTTPRequest;
import org.springframework.stereotype.Service;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class ProjectService {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    private static String promptUser(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private final String TAIGA_API_ENDPOINT = GlobalData.getTaigaURL();

    private static String promptUserPassword(String prompt) {
        if (System.console() != null) {
            char[] passwordChars = System.console().readPassword(prompt);
            return new String(passwordChars);
        } else {
            System.out.print(prompt);
            return scanner.nextLine();
        }
    }

//    public static int getProjectId(String authToken,String TAIGA_API_ENDPOINT) {
//
//        // Prompting user to enter project slug name. A slug name is nothing but an identifier for a project.
//        // Open any Taiga project and check the url of your browser. Slug name is the value after " /project/SLUG_NAME "
//        // Example https://tree.taiga.io/project/SLUG_NAME/us/1?no-milestone=1
//
//        String projectSlug = promptUser("Enter the Taiga project slug: ");
//        String endpoint = TAIGA_API_ENDPOINT + "/projects/by_slug?slug=" + projectSlug;
//
//        HttpGet request = new HttpGet(endpoint);
//        request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authToken);
//        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
//
//        String responseJson = HTTPRequest.sendHttpRequest(request);
//
//        if (responseJson != null) {
//            try {
//                JsonNode projectInfo = objectMapper.readTree(responseJson);
//                int projectId = projectInfo.has("id") ? projectInfo.get("id").asInt() : -1;
//
//                if (projectId != -1) {
//                    System.out.println("Service.Project details retrieved successfully.");
//                    return projectId;
//                } else {
//                    System.out.println("Invalid project slug. Please try again.");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return -1;
//    }

    // This method returns list of Projects related to member who has logged in.
    public List<ProjectDTO> getPojectList(Integer memberID) {
        String endpoint = TAIGA_API_ENDPOINT + "/projects?member="+ memberID;
        HttpGet request = new HttpGet(endpoint);
        request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + Authentication.authToken);
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        String responseJson = HTTPRequest.sendHttpRequest(request);

        if (responseJson != null) {
            try {
                JsonNode projects = objectMapper.readTree(responseJson);
                if(projects.isArray()) {
                    List<ProjectDTO> projectList = new ArrayList<>();
                    for(JsonNode project: projects) {
                        ProjectDTO p = new ProjectDTO(
                                project.get("id").asInt(),
                                project.get("name").asText(),
                                project.get("slug").asText());
                        projectList.add(p);
                    }
                    return projectList;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}