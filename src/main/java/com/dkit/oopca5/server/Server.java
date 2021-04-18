package com.dkit.oopca5.server;

import com.dkit.oopca5.client.MainMenu;
import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.Course;
import com.dkit.oopca5.core.Student;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {
        try {
            ServerSocket ss = new ServerSocket(8080);

            System.out.println("Server: Server started. Listening for connections on port 8080...");


            while (true) {
                Socket socket = ss.accept();
                System.out.println("Sever: Client connected");
                Thread t = new Thread(new ClientHandler(socket));
                t.start();

                System.out.println("Server: ClientHandler started in thread for client");
                System.out.println("Server: Listening for further connections...");
            }
        } catch (IOException e) {
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server: Server exiting, Goodbye!");
    }

    public class ClientHandler implements Runnable {
        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;


        public ClientHandler(Socket clientSocket) {
            try {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true);


                this.socket = clientSocket;

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            CourseDaoInterface ICourseDAO = new MySqlCourseDao();
            StudentDaoInterface IStudentDAO = new MySqlStudentDao();
            StudentCoursesDaoInterface IStudentCoursesDAO = new MySqlStudentCoursesDao();
            String message;
            String response = null;
            Course response1;

            try {
                while ((message = socketReader.readLine()) != null) {
                    String[] components = message.split(CAOService.BREAKING_CHARACTER);


                    if (components[0].equals("REGISTER")) {
                        int caonumber = Integer.parseInt(components[1]);
                        String date = components[2];
                        String password = components[3];
                        Student s = new Student(caonumber, date, password);

                        try {
                            if (IStudentDAO.isregistered(s)) {

                                response = CAOService.Already_Registered;
                            }
                            else {

                                if (IStudentDAO.registerstudent(s)) {
                                    response = CAOService.SUCCESSFUL_REGISTER;
                                }
                                else {
                                    response = CAOService.FAILED_REGISTER;
                                }
                            }

                            socketWriter.println(response);
                        } catch (DaoException ex) {
                            ex.printStackTrace();
                        }
                    }

                    else if (components[0].equals("LOGIN")) {
                        int caonumber = Integer.parseInt(components[1]);
                        String date = components[2];
                        String password = components[3];
                        Student s = new Student(caonumber,date,password);

                        try {
                            if (IStudentDAO.login(s)) {

                                response = CAOService.LOGIN_SUC;
                            }
                            else {
                                response = CAOService.LOGIN_FAIL;

                            }

                            socketWriter.println(response);
                        } catch (DaoException ex) {
                            ex.printStackTrace();
                        }
                    }else if (components[0].equals("DISPLAY COURSE")) {

                        String id = components[1];
                        try {
                            if (ICourseDAO.findCourse(id) == null) {
                                response = CAOService.FIND_COURSE_FAIL;
                            } else {
                                Course c = ICourseDAO.findCourse(id);
                                response = c.getCourseId() + CAOService.BREAKING_CHARACTER + c.getLevel() + CAOService.BREAKING_CHARACTER
                                        + c.getTitle() + CAOService.BREAKING_CHARACTER + c.getInstitution();
                            }


                            socketWriter.println(response);
                        }catch (DaoException ex) {
                            ex.printStackTrace();
                        }
                    }

                    else if (components[0].equals("DISPLAY_ALL")) {

                        try {
                            if (ICourseDAO.findAllCourses() == null){
                                response = CAOService.DISPLAY_ALL_FAIL;
                            }else
                                {
                                    response="";
                                List<Course> courses = ICourseDAO.findAllCourses();
                                for (Course c : courses){
                                    response += c + CAOService.BREAKING_CHARACTER;
                                }

                            }


                            socketWriter.println(response);

                        } catch (DaoException ex) {
                            ex.printStackTrace();
                        }
                    }
                    else if (components[0].equals("DISPLAY CURRENT")) {
                        int caonumber = Integer.parseInt(components[1]);

                        try {

                            List<String> courses;
                            courses = IStudentCoursesDAO.findCoursesForUser(caonumber);
                            if (courses.size()==0) {

                                response = CAOService.DISPLAY_CURRENT_FAIL;
                            }
                            else{
                                response = "";
                                for (int i =0; i < courses.size(); i ++) {
                                        response += courses.get(i)+CAOService.BREAKING_CHARACTER;
                                    }
                            }
                            socketWriter.println(response);
                        } catch (DaoException ex) {
                            ex.printStackTrace();
                        }
                    }
                    else if (components[0].equals("UPDATE CURRENT")) {
                        int cao = Integer.parseInt(components[1]);
                        List<String> courses = new ArrayList<>();
                        for (int i =2; i <components.length ; i++){
                            courses.add(components[i]);
                        }

                        try {

                            IStudentCoursesDAO.updateCoursesForUser(cao,courses);
                            response = CAOService.UPDATE_CURRENT;
                            socketWriter.println(response);
                        } catch (DaoException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

