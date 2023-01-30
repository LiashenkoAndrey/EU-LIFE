package eulife.domain.dto;


import jakarta.annotation.Nullable;

public class UserDto {

    private String first_name;
    private String last_name;
    private String password;
    private String email;

    @Nullable
    private String university;

    @Nullable
    private String faculty;

    @Nullable
    private String github;

    @Nullable
    private String linkedin;

    @Nullable
    private String site;

    @Nullable
    private String age;

    public UserDto() {
    }



    @Override
    public String toString() {
        return "UserDto{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", university='" + university + '\'' +
                ", faculty='" + faculty + '\'' +
                ", github='" + github + '\'' +
                ", linkedin='" + linkedin + '\'' +
                ", site='" + site + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public String getUniversity() {
        return university;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
